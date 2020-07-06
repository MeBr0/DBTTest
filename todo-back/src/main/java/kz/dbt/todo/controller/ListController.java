package kz.dbt.todo.controller;

import kz.dbt.todo.entity.TaskList;
import kz.dbt.todo.service.ListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("lists")
public class ListController {

    private ListService service;

    @Autowired
    public ListController(ListService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Object> getAllLists() {
        return service.getAll();
    }

    @PostMapping
    public ResponseEntity<Object> createList(@RequestBody TaskList list) {
        return service.create(list);
    }

    @GetMapping("{id}")
    public ResponseEntity<Object> getList(@PathVariable Long id) {
        return service.get(id);
    }

    @PatchMapping("{id}")
    public ResponseEntity<Object> updateList(@PathVariable Long id, @RequestBody TaskList list) {
        return service.update(id, list);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteList(@PathVariable Long id) {
        return service.delete(id);
    }
}
