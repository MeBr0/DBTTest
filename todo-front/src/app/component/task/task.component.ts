import {Component, OnInit} from '@angular/core';
import {Task} from "../../model/tasks";
import {TasksService} from "../../service/tasks.service";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-task',
  templateUrl: './task.component.html',
  styleUrls: ['./task.component.css']
})
export class TaskComponent implements OnInit {

  listId: number;
  task: Task;

  constructor(private service: TasksService, private route: ActivatedRoute, private router: Router) { }

  ngOnInit() {
    this.getTask();
  }

  getTask(): void {
    this.route.params.subscribe(params => {
      this.listId = +params['listId'];
      const id = +params['id'];

      this.service.getTask(this.listId, id).then(task => {
        this.task = task;
      })
    });
  }

  saveTask(): void {
    this.service.updateTask(this.listId, this.task).then(task => {
      this.getTask();
    }).
    then(task => {
      this.router.navigate(['lists', this.listId]);
    })
  }
}
