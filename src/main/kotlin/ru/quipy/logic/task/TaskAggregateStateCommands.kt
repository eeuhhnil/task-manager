package ru.quipy.logic.task

import ru.quipy.api.task.*
import java.util.UUID

fun TaskAggregateState.createTask(projectId: UUID, taskId: UUID, taskName: String): TaskCreatedEvent{
    return  TaskCreatedEvent(
        projectId = projectId,
        taskId = taskId,
        taskName = taskName
    )
}

fun TaskAggregateState.changeTaskTitle(newTaskName: String) : TaskNameChangeEvent {
    if (this.taskName == newTaskName) {
        throw IllegalArgumentException("New task name is the same as old task name")
    }

    return TaskNameChangeEvent(
        taskId = this.getId(),
        oldTaskName = this.taskName,
        newTaskName = newTaskName
    )
}

fun TaskAggregateState.addExecutorToTask(userId: UUID) : TaskExecutorAssignedEvent {
    if (executors.contains(userId)) {
        throw IllegalArgumentException("Executor was added already!")
    }

    return TaskExecutorAssignedEvent(
        taskId = this.getId(),
        userId = userId
    )
}

fun TaskAggregateState.changeTaskStatus(newStatusId: UUID): TaskStatusChangeEvent {
    if (this.statusId == newStatusId) {
        throw IllegalArgumentException("New status is the same as old status")
    }

    return TaskStatusChangeEvent(
        taskId = this.getId(),
        newStatusId = newStatusId,
        oldStatusId = this.statusId
    )
}
