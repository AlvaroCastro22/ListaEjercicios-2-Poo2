package com.hampcode.usil_pre_demo_observer_builder_factory_repository_mvc.view;

import com.hampcode.usil_pre_demo_observer_builder_factory_repository_mvc.controller.TaskController;
import com.hampcode.usil_pre_demo_observer_builder_factory_repository_mvc.model.DatabaseType;
import com.hampcode.usil_pre_demo_observer_builder_factory_repository_mvc.model.Task;
import com.hampcode.usil_pre_demo_observer_builder_factory_repository_mvc.service.TaskService;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class TaskView extends JFrame {

    private final TaskController taskController;
    private JTextField taskNameField;
    private JTable taskTable;
    private DefaultTableModel tableModel;
    private JTextArea notificationArea;

    private final HomeView2 homeView;

    public TaskView(DatabaseType dbType, HomeView2 homeView) {
        TaskService taskService = new TaskService(dbType);
        this.taskController = new TaskController(taskService);  
        this.homeView = homeView;
        initializeUI();
        updateTaskTable();
    }

    private void initializeUI() {
        setTitle("Gestión de Tareas");
        setSize(600, 400);
        setLayout(new BorderLayout());

       
        JPanel inputPanel = new JPanel(new FlowLayout());
        taskNameField = new JTextField(15);
        JButton addTaskButton = new JButton("Agregar Tarea");

        addTaskButton.addActionListener((ActionEvent e) -> {
            String taskName = taskNameField.getText();
            if (!taskName.isEmpty()) {
                Task task = Task.builder().name(taskName).isCompleted(false).build();
                taskController.addTask(task);
                updateTaskTable();
                taskNameField.setText("");
            }
        });

        inputPanel.add(new JLabel("Nombre de la Tarea:"));
        inputPanel.add(taskNameField);
        inputPanel.add(addTaskButton);

        
        tableModel = new DefaultTableModel(new String[]{"ID", "Nombre", "Completada"}, 0);
        taskTable = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(taskTable);

       
        JPanel actionPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton completeTaskButton = new JButton("Completar Tarea");
        JButton summaryButton = new JButton("Mostrar Resumen de Estado");
        JButton backButton = new JButton("Regresar a Home");

        completeTaskButton.addActionListener((ActionEvent e) -> {
            int selectedRow = taskTable.getSelectedRow();
            if (selectedRow != -1) {
                int taskId = (int) tableModel.getValueAt(selectedRow, 0);
                Task task = taskController.getAllTasks().stream().filter(t -> t.getId() == taskId).findFirst().orElse(null);

                if (task != null && !task.isCompleted()) {
                    String notification = taskController.completeTask(task);
                    updateTaskTable();
                    notificationArea.append(notification + "\n");
                } else {
                    JOptionPane.showMessageDialog(this, "Seleccione una tarea no completada.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Seleccione una tarea de la lista.");
            }
        });

       
        summaryButton.addActionListener((ActionEvent e) -> {
            int[] counts = taskController.getTaskStatusCounts();
            notificationArea.append("Tareas Completadas: " + counts[0] + "\n");
            notificationArea.append("Tareas Pendientes: " + counts[1] + "\n");
        });

       
        backButton.addActionListener((ActionEvent e) -> {
            this.dispose();
            homeView.setVisible(true);
        });

       
        actionPanel.add(completeTaskButton);
        actionPanel.add(summaryButton);
        actionPanel.add(backButton);

        
        notificationArea = new JTextArea(5, 30);
        notificationArea.setEditable(false);
        JScrollPane notificationScrollPane = new JScrollPane(notificationArea);

        
        add(inputPanel, BorderLayout.NORTH);
        add(tableScrollPane, BorderLayout.CENTER);
        add(actionPanel, BorderLayout.SOUTH);  
        add(notificationScrollPane, BorderLayout.EAST);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void updateTaskTable() {
        tableModel.setRowCount(0);
        List<Task> tasks = taskController.getAllTasks();

        for (Task task : tasks) {
            tableModel.addRow(new Object[]{task.getId(), task.getName(), task.isCompleted() ? "Sí" : "No"});
        }
    }
}
