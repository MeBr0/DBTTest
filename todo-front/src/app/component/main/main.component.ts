import {Component, OnInit} from '@angular/core';
import {TaskList} from "../../model/tasks";
import {TasksService} from "../../service/tasks.service";

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent implements OnInit {

  lists: TaskList[] = [];

  newTitle: string = '';

  constructor(private service: TasksService) { }

  ngOnInit() {
    this.getTaskLists();
  }

  getTaskLists(): void {
    this.service.getTaskLists().then(lists => {
      this.lists = lists;
    });
  }

  createTaskList(): void {
    if (this.newTitle !== null && this.newTitle !== '') {
      this.service.createTaskList(this.newTitle).then(list => {
        alert(`Task list with title ${list.title} created`);
      }).
      then(res => {
        this.getTaskLists();
      }).
      then(res => {
        this.newTitle = '';
      });
    }
    else {
      alert(`Task list's title cannot be empty`);
    }
  }

  deleteTaskList(list: TaskList): void {
    this.service.deleteTaskList(list.id).then(res => {
      alert(`Task list ${list.title} deleted`);
    }).
    then(res => {
      this.getTaskLists();
    });
  }

}
