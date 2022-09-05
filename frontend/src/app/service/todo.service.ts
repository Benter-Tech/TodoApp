import {Injectable} from '@angular/core';
import {Todo} from "../model/todo";
import {HttpClient, HttpParams} from "@angular/common/http";
import {Observable} from "rxjs";
import {environment} from "../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class TodoService {

  private url = environment.baseUrl + "/api/todos";

  constructor(private http: HttpClient) {
  }

  public getMyTodos(): Observable<Todo[]> {
    return this.http.get<Todo[]>(this.url);
  }

  public createTodo(todo: Todo): Observable<Todo> {
    return this.http.post<Todo>(this.url, todo);
  }

  public deleteTodo(todo: Todo): Observable<Object> {
      let queryParams = new HttpParams();
      queryParams = queryParams.append("id", todo.id!);
      return this.http.delete(this.url, {params: queryParams})
  }

}
