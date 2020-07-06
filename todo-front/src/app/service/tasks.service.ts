import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {BaseHttpService} from "./http/base-http.service";
import {Task, TaskList} from "../model/tasks";

@Injectable({
  providedIn: 'root'
})
export class TasksService extends BaseHttpService {

  BASE_URI = 'http://localhost:8080/'

  constructor(http: HttpClient) {
    super(http);
  }

  getTaskLists(): Promise<TaskList[]> {
    return this.get(this.BASE_URI + 'lists', {});
  }

  createTaskList(title: string): Promise<TaskList> {
    return this.post(this.BASE_URI + 'lists', {title});
  }

  getTaskList(id: number): Promise<TaskList> {
    return this.get(this.BASE_URI + `lists/${id}`, {});
  }

  updateTaskList(list: TaskList): Promise<TaskList> {
    return this.patch(this.BASE_URI + `lists/${list.id}`, list);
  }

  deleteTaskList(id: number): Promise<any> {
    return this.del(this.BASE_URI + `lists/${id}`, {})
  }

  getTasks(listId: number): Promise<Task[]> {
    return this.get(this.BASE_URI + `lists/${listId}/tasks`, {});
  }

  createTask(listId: number, title: string): Promise<Task> {
    return this.post(this.BASE_URI + `lists/${listId}/tasks`, {title});
  }

  getTask(listId: number, id: number): Promise<Task> {
    return this.get(this.BASE_URI + `lists/${listId}/tasks/${id}`, {});
  }

  updateTask(listId: number, task: Task): Promise<Task> {
    return this.patch(this.BASE_URI + `lists/${listId}/tasks/${task.id}`, task);
  }

  deleteTask(listId: number, id: number): Promise<any> {
    return this.del(this.BASE_URI + `lists/${listId}/tasks/${id}`, {});
  }
}
