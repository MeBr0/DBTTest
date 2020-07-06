import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {MainComponent} from "./component/main/main.component";
import {ListComponent} from "./component/list/list.component";
import {TaskComponent} from "./component/task/task.component";


const routes: Routes = [
  {
    path: '',
    redirectTo: 'lists',
    pathMatch: 'full'
  },
  {
    path: 'lists',
    component: MainComponent
  },
  {
    path: 'lists/:id',
    component: ListComponent
  },
  {
    path: 'lists/:listId/tasks/:id',
    component: TaskComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
