import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './component/login/login.component';
import {DashbordComponent} from "./component/dashbord/dashbord.component";
import {ListeProjetComponent} from "./component/liste-projet/liste-projet.component";
import {AjouterProjetComponent} from "./component/ajouter-projet/ajouter-projet.component";
import {AddConsultantComponent} from "./component/add-consultant/add-consultant.component";
import {ListConsultantComponent} from "./component/list-consultant/list-consultant.component";
import {RegisterComponent} from "./component/register/register.component";
import {TimerComponent} from "./component/timer/timer.component";
const routes: Routes = [
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
  { path: 'dashboard', component: DashbordComponent },
  { path: 'listProjet', component: ListeProjetComponent },
  { path: 'listConsultant', component: ListConsultantComponent },
  { path: 'addProject', component: AjouterProjetComponent },
  { path: 'addProject/:id', component: AjouterProjetComponent },
  { path: 'addConsultant', component: AddConsultantComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'time/:id', component: TimerComponent },
  // Ajoutez d'autres routes ici pour les autres composants et fonctionnalités
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
