package kz.dbt.todo.dao;

import kz.dbt.todo.entity.Task;
import kz.dbt.todo.entity.TaskList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskDao extends JpaRepository<Task, Long> {

    List<Task> getAllByList(TaskList list);
    Task findByIdAndList(Long id, TaskList list);
}
