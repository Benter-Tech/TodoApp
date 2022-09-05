import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Todo} from "../../model/todo";
import {TodoService} from "../../service/todo.service";

@Component({
  selector: 'app-todo',
  templateUrl: './todo.component.html',
  styleUrls: ['./todo.component.sass']
})
export class TodoComponent {
  @Input()
  todo!: Todo;

  @Output()
  deleteAction: EventEmitter<number> = new EventEmitter()

  constructor(private todoService: TodoService) { }

  getFormattedDate(date?: Date) {
    if (date) {
      return new Date(date).toLocaleString()
    }
    return '-'
  }

  delete(){
    if(this.todo.id) {
      this.todoService.deleteTodo(this.todo).subscribe({
        next: success => {
          this.deleteAction.emit(this.todo.id)
        },
        error: err => {
          console.error("Something went wrong while deleting todo")
        }
        });
    }
  }
}
