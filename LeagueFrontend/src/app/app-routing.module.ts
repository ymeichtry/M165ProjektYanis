import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {QuizComponent} from "./home/quiz/quiz.component";

const routes: Routes = [
  { path: '', redirectTo: '/home', pathMatch: 'full'},
  {
    path: 'home',
    component: QuizComponent,

  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
