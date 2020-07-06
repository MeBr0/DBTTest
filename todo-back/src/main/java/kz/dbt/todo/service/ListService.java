package kz.dbt.todo.service;

import kz.dbt.todo.dao.ListDao;
import kz.dbt.todo.entity.TaskList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ListService {

    private ListDao listDao;

    @Autowired
    public ListService(ListDao listDao) {
        this.listDao = listDao;
    }

    public ResponseEntity<Object> getAll() {
        return ResponseEntity.ok(listDao.findAll());
    }

    public ResponseEntity<Object> create(TaskList list) {
        return ResponseEntity.status(HttpStatus.CREATED).body(listDao.save(list));
    }

    public ResponseEntity<Object> get(Long id) {
        Optional<TaskList> list = listDao.findById(id);

        return list.<ResponseEntity<Object>>map(ResponseEntity::ok).
                orElseGet(() -> ResponseEntity.notFound().build());
    }

    public ResponseEntity<Object> update(Long id, TaskList list) {
        Optional<TaskList> instance = listDao.findById(id);

        if (!instance.isPresent())
            return ResponseEntity.notFound().build();

        TaskList taskList = instance.get();

        taskList.setTitle(list.getTitle());

        return ResponseEntity.ok(listDao.save(taskList));
    }

    public ResponseEntity<Object> delete(Long id) {
        Optional<TaskList> instance = listDao.findById(id);

        if (!instance.isPresent())
            return ResponseEntity.notFound().build();

        TaskList taskList = instance.get();

        listDao.delete(taskList);

        return ResponseEntity.noContent().build();
    }
}
