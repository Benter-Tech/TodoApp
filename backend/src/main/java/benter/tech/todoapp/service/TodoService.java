package benter.tech.todoapp.service;

import benter.tech.todoapp.entity.Todo;
import benter.tech.todoapp.entity.TodoStatus;
import benter.tech.todoapp.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;

    public List<Todo> findAll() {
        return todoRepository.findAll();
    }

    public Todo findById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("The Id must be provided to get a Todo by id an existing Todo");
        }
        return todoRepository.getReferenceById(id);
    }

    public Todo create(Todo todo) {
        todo.setCreationDate(Instant.now());
        todo.setStatus(TodoStatus.CREATED);
        return todoRepository.save(todo);
    }

    public void deleteById(Long id) {
        todoRepository.deleteById(id);
    }

    public Todo update(Todo todo) {
        if (todo.getId() == null) {
            throw new IllegalArgumentException("The Id must be provided to update an existing Todo");
        }
        Todo existingTodo = findById(todo.getId());
        if (todo.getStatus() != null) {
            existingTodo.setStatus(todo.getStatus());
        }
        if (StringUtils.hasText(todo.getTitle())) {
            existingTodo.setTitle(todo.getTitle());
        }
        existingTodo.setDescription(todo.getDescription());
        existingTodo.setEstimatedTimeInSeconds(todo.getEstimatedTimeInSeconds());
        existingTodo.setDeadline(todo.getDeadline());
        return todoRepository.save(todo);
    }
}
