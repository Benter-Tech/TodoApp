package benter.tech.todoapp.controller;

import benter.tech.todoapp.entity.Todo;
import benter.tech.todoapp.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/todos")
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @GetMapping("")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(todoService.findAll());
    }

    @PostMapping("")
    public ResponseEntity<?> createTodo(@RequestBody Todo todo) {
        Todo createdTodo = todoService.create(todo);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTodo);
    }

    @PutMapping("")
    public ResponseEntity<?> updateTodo(@RequestBody Todo todo) {
        Todo updatedTodo = todoService.update(todo);
        return ResponseEntity.ok(updatedTodo);
    }

    @DeleteMapping("")
    public ResponseEntity<?> deleteById(@RequestParam()long id){
        todoService.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
