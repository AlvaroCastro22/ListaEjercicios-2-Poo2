package com.hampcode.usil_pre_demo_observer_builder_factory_repository_mvc.repository;

import com.hampcode.usil_pre_demo_observer_builder_factory_repository_mvc.model.Task;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TextFileTaskRepository implements TaskRepository {

    private final String fileName = "tasks.txt";
    private int currentId = 1;

    public TextFileTaskRepository() {
        ensureFileExists(); 
        initializeIdCounter();
    }

    // Método para crear el archivo si no existe
    private void ensureFileExists() {
        File file = new File(fileName);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // Inicializa el contador de ID en base a las tareas existentes en el archivo
    private void initializeIdCounter() {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    int id = Integer.parseInt(parts[0]);
                    currentId = Math.max(currentId, id + 1); // Incrementa el ID automáticamente
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addTask(Task task) {
        task.setId(currentId++); // Asigna el ID actual y luego lo incrementa

        try (FileWriter writer = new FileWriter(fileName, true)) {
            writer.write(task.getId() + "," + task.getName() + "," + task.isCompleted()+","+task.getDescripcion() +","+task.getTipo().name()+ "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Task> getAllTasks() {
        List<Task> tasks = new ArrayList<>();
        
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 5) {
                    int id = Integer.parseInt(parts[0]);
                    String name = parts[1];
                    boolean isCompleted = Boolean.parseBoolean(parts[2]);
                    String descripcion = parts[3];
                    String tipo = parts[4];
                    
                    Task task = Task.builder()
                            .id(id)
                            .name(name)
                            .isCompleted(isCompleted)
                            .descripcion(descripcion)
                            .tipo(tipo.equals("laboral") ? Task.Tipo.laboral : Task.Tipo.personal)
                            .build();
                    tasks.add(task);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return tasks;
    }

    @Override
    public void updateTask(Task task) {
        List<Task> tasks = getAllTasks();
        try (FileWriter writer = new FileWriter(fileName)) {
            for (Task t : tasks) {
                if (t.getId() == task.getId()) {
                    t.setCompleted(task.isCompleted()); // Actualizar estado de completitud
                }
                writer.write(t.getId() + "," + t.getName() + "," + t.isCompleted() + ","+task.getDescripcion()+","+task.getTipo().name() +"\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteTask(int id) {
        List<Task> tasks = getAllTasks();
        try (FileWriter writer = new FileWriter(fileName)) {
            for (Task t : tasks) {
                if (t.getId() != id) {  // Solo reescribe las tareas que no tienen el id especificado
                    writer.write(t.getId() + "," + t.getName() + "," + t.isCompleted() +","+t.getDescripcion()+","+t.getTipo().name()+ "\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int[] getTaskStatusCounts() {
        int completedCount = 0;
        int incompleteCount = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 5) {
                    boolean isCompleted = Boolean.parseBoolean(parts[2]);
                    if (isCompleted) {
                        completedCount++;
                    } else {
                        incompleteCount++;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new int[]{completedCount, incompleteCount};
    }
}
