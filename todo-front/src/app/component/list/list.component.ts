import {Component, OnInit} from '@angular/core';
import {Task, TaskList} from "../../model/tasks";
import {TasksService} from "../../service/tasks.service";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.css']
})
export class ListComponent implements OnInit {

  list: TaskList;
  tasks: Task[];

  newTitle: string = '';

  constructor(private service: TasksService, private route: ActivatedRoute) { }

  ngOnInit() {
    this.getTasks();
  }

  getTasks(): void {
    this.route.params.subscribe(params => {
      const listId = +params['id'];

      this.service.getTasks(listId).then(tasks => {
        this.tasks = tasks;
      });

      this.service.getTaskList(listId).then(list => {
        this.list = list;
      });
    });
  }

  createTask(): void {
    if (this.newTitle !== null && this.newTitle !== '') {
      this.service.createTask(this.list.id, this.newTitle).then(list => {
        alert(`Task with title ${list.title} created`);
      }).
      then(res => {
        this.getTasks();
      });
    }
    else {
      alert(`Task's title cannot be empty`);
    }
  }

  doTask(task: Task): void {
    task.done = !task.done;

    this.service.updateTask(this.list.id, task).then(task => {
      this.getTasks();
    });
  }

  deleteTask(task: Task): void {
    this.service.deleteTask(this.list.id, task.id).then(res => {
      alert(`Task ${task.title} deleted`);
    }).
    then(res => {
      this.getTasks();
    });
  }

  updateTitle(): void {
    if (this.list.title !== null && this.list.title !== '') {
      this.service.updateTaskList(this.list).then(list => {
        this.list = list;
      });
    }
    else {
      alert(`Task list's title cannot be empty`);
    }
  }
}
