package com.hampcode.usil_pre_demo_observer_builder_factory_repository_mvc.factory;

import com.hampcode.usil_pre_demo_observer_builder_factory_repository_mvc.model.DatabaseType;
import com.hampcode.usil_pre_demo_observer_builder_factory_repository_mvc.repository.MySQLTaskRepository;
import com.hampcode.usil_pre_demo_observer_builder_factory_repository_mvc.repository.PostgreSQLTaskRepository;
import com.hampcode.usil_pre_demo_observer_builder_factory_repository_mvc.repository.TaskRepository;
import com.hampcode.usil_pre_demo_observer_builder_factory_repository_mvc.repository.TextFileTaskRepository;

public class TaskRepositoryFactory {

    public static TaskRepository getRepository(DatabaseType dbType) {
        return switch (dbType) {
            case MYSQL -> new MySQLTaskRepository();
            case POSTGRESQL -> new PostgreSQLTaskRepository();
            case TEXTFILE -> new TextFileTaskRepository();
        };
    }
}
