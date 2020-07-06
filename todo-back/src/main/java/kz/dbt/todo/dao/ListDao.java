package kz.dbt.todo.dao;

import kz.dbt.todo.entity.TaskList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ListDao extends JpaRepository<TaskList, Long> {

}
