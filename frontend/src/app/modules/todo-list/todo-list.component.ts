import { Component, OnInit } from '@angular/core';
import {Todo} from "../../model/todo";
import {TodoService} from "../../service/todo.service";

@Component({
  selector: 'app-todo-list',
  templateUrl: './todo-list.component.html',
  styleUrls: ['./todo-list.component.sass']
})
export class TodoListComponent implements OnInit {

  todos: Todo[] = [];
  constructor(private todoService: TodoService) {}

  ngOnInit(): void {
    this.getTodos();
  }

  getTodos() {
    this.todoService.getMyTodos().subscribe(todos => {
      this.todos = todos;
    });
  }

  handleTodoDeletion(id: number) {
    this.todos=this.todos.filter(todo => todo.id != id);
  }

}
