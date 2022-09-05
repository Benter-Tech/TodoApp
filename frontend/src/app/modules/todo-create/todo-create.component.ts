import {Component, Input, OnInit} from '@angular/core';
import {Todo} from "../../model/todo";
import {TodoService} from "../../service/todo.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-todo-create',
  templateUrl: './todo-create.component.html',
  styleUrls: ['./todo-create.component.sass']
})
export class TodoCreateComponent {

  title: string = '';
  description: string = '';
  deadline: Date | undefined = undefined;
  estimatedTime:number | undefined = undefined;
  count: number = 0;

  constructor(private todoService: TodoService, private router: Router) { }

  public create(){
    const newTodo: Todo = new Todo();
    newTodo.title = this.title;
    newTodo.description = this.description;
    newTodo.deadline = this.deadline;
    if (this.estimatedTime) {
      newTodo.estimatedTimeInSeconds = this.estimatedTime * 60;
    }
    if (this.deadline) {
      newTodo.deadline = new Date(this.deadline)
    }
    this.todoService.createTodo(newTodo).subscribe({
      next: success => {
        this.router.navigate(['/'])
      },
      error: err => {
        console.error(err);
      }
    });
  }
}
