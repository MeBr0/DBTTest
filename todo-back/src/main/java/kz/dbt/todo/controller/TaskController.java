package kz.dbt.todo.controller;

import kz.dbt.todo.entity.Task;
import kz.dbt.todo.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("lists/{listId}/tasks")
public class TaskController {

    private TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public ResponseEntity<Object> getAllTasks(@PathVariable Long listId) {
        return taskService.getAll(listId);
    }

    @PostMapping
    public ResponseEntity<Object> createTask(@PathVariable Long listId, @RequestBody Task task) {
        return taskService.create(listId, task);
    }

    @GetMapping("{id}")
    public ResponseEntity<Object> getTask(@PathVariable Long listId, @PathVariable Long id) {
        return taskService.get(listId, id);
    }

    @PatchMapping("{id}")
    public ResponseEntity<Object> updateTask(@PathVariable Long listId, @PathVariable Long id, @RequestBody Task task) {
        return taskService.update(listId, id, task);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteTask(@PathVariable Long listId, @PathVariable Long id) {
        return taskService.delete(listId, id);
    }
}
