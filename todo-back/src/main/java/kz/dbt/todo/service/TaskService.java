package kz.dbt.todo.service;

import kz.dbt.todo.dao.ListDao;
import kz.dbt.todo.dao.TaskDao;
import kz.dbt.todo.entity.Task;
import kz.dbt.todo.entity.TaskList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private ListDao listDao;
    private TaskDao taskDao;

    @Autowired
    public TaskService(ListDao listDao, TaskDao taskDao) {
        this.listDao = listDao;
        this.taskDao = taskDao;
    }

    public ResponseEntity<Object> getAll(Long listId) {
        List<Task> tasks = listDao.findById(listId).
                map(list -> taskDao.getAllByList(list)).
                orElse(null);

        if (tasks == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(tasks);
    }

    public ResponseEntity<Object> create(Long listId, Task task) {
        Optional<TaskList> list = listDao.findById(listId);

        if (!list.isPresent())
            return ResponseEntity.notFound().build();

        TaskList taskList = list.get();

        taskList.getTasks().add(task);
        task.setList(taskList);

        listDao.save(taskList);
        taskDao.save(task);

        return ResponseEntity.ok(task);
    }

    public ResponseEntity<Object> get(Long listId, Long id) {
        Task task = listDao.findById(listId).
                map(list -> taskDao.findByIdAndList(id, list)).
                orElse(null);

        if (task == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(task);
    }

    public ResponseEntity<Object> update(Long listId, Long id, Task task) {
        Task instance = listDao.findById(listId).
                map(list -> taskDao.findByIdAndList(id, list)).
                orElse(null);

        if (instance == null)
            return ResponseEntity.notFound().build();

        instance.setTitle(task.getTitle());
        instance.setDone(task.isDone());

        return ResponseEntity.ok(taskDao.save(instance));
    }

    public ResponseEntity<Object> delete(Long listId, Long id) {
        Task instance = listDao.findById(listId).
                map(list -> taskDao.findByIdAndList(id, list)).
                orElse(null);

        if (instance == null)
            return ResponseEntity.notFound().build();

        taskDao.delete(instance);

        return ResponseEntity.noContent().build();
    }
}
