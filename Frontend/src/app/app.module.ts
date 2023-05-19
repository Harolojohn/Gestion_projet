import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './component/login/login.component';
import { DashbordComponent } from './component/dashbord/dashbord.component';
import { ListeProjetComponent } from './component/liste-projet/liste-projet.component';
import { AjouterProjetComponent } from './component/ajouter-projet/ajouter-projet.component';
import { AddConsultantComponent } from './component/add-consultant/add-consultant.component';
import { ListConsultantComponent } from './component/list-consultant/list-consultant.component';
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import { RegisterComponent } from './component/register/register.component';
import {AuthInterceptor} from "./services/auth.interceptor";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import { TimerComponent } from './component/timer/timer.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    DashbordComponent,
    ListeProjetComponent,
    AjouterProjetComponent,
    AddConsultantComponent,
    ListConsultantComponent,
    RegisterComponent,
    TimerComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [ { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true }],
  bootstrap: [AppComponent]
})
export class AppModule { }
