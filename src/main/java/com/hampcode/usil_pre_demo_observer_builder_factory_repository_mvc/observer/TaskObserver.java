package com.hampcode.usil_pre_demo_observer_builder_factory_repository_mvc.observer;

import com.hampcode.usil_pre_demo_observer_builder_factory_repository_mvc.model.Task;

public interface TaskObserver {
    String onTaskCompleted(Task task);
}

