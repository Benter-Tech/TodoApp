package benter.tech.todoapp.service;

import benter.tech.todoapp.entity.Todo;
import benter.tech.todoapp.repository.TodoRepository;
import org.hibernate.LazyInitializationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.InvalidDataAccessApiUsageException;

import javax.persistence.EntityNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TodoServiceTest {

    @Autowired
    private TodoService todoService;

    @BeforeEach
    void setup() {
        Todo todo = Todo.builder().title("Test").build();
        todoService.create(todo);
    }

    @Test
    void findById() {
        assertThrows(IllegalArgumentException.class, () ->todoService.findById(null));
        assertEquals("Test", todoService.findById(1L).getTitle());
    }

    @Test
    void deleteById() {
        assertThrows(InvalidDataAccessApiUsageException.class, () ->todoService.deleteById(null));
        todoService.deleteById(1L);
        assertThrows(org.hibernate.LazyInitializationException.class, () ->todoService.findById(1L).getTitle());
    }

    @Test
    void findAll() {
        assertEquals("Test", todoService.findAll().get(0).getTitle());
    }
}