import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { TodoComponent } from './modules/todo/todo.component';
import {RouterModule, Routes} from "@angular/router";
import { TodoListComponent } from './modules/todo-list/todo-list.component';
import {TodoService} from "./service/todo.service";
import {HttpClientModule} from "@angular/common/http";
import { TodoCreateComponent } from './modules/todo-create/todo-create.component';
import {FormsModule} from "@angular/forms";


const routes: Routes = [
  { path: '', component: TodoListComponent , pathMatch: 'full'},
  { path: 'add', component: TodoCreateComponent},
]

@NgModule({
  declarations: [
    AppComponent,
    TodoComponent,
    TodoListComponent,
    TodoCreateComponent
  ],
  imports: [
    BrowserModule,
    RouterModule.forRoot(routes),
    HttpClientModule,
    FormsModule,
  ],
  exports: [RouterModule],
  providers: [TodoService],
  bootstrap: [AppComponent]
})
export class AppModule { }
