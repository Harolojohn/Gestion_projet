import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {Consultant} from "../models/consultant";
import {Project} from "../models/project";
import {Temps} from "../models/temps";
import {User} from "../models/user";

@Injectable({
  providedIn: 'root'
})
export class ProjectService {
   apiUrl = "http://129.146.43.26:9050"
  constructor(private http: HttpClient) { }

  login(email: string, password: string): Observable<Consultant> {
    const loginData = { email, password };
    return this.http.post<Consultant>(`${this.apiUrl}/login`, loginData);
  }



  getAllProjects(): Observable<Project[]> {
    const headers = new HttpHeaders().set('Content-Type', 'application/json');
    return this.http.get<Project[]>(`${this.apiUrl}/Project`,{headers});
  }

  getAllConsultant(): Observable<Consultant[]> {
    const headers = new HttpHeaders().set('Content-Type', 'application/json');
    return this.http.get<Consultant[]>(`${this.apiUrl}/User/consultants`,{headers});
  }

  createProject(project: Project): Observable<Project> {
    const headers = new HttpHeaders().set('Content-Type', 'application/json');
    return this.http.post<Project>(`${this.apiUrl}/Project`, project,{headers});
  }

  editProject(project: Project): Observable<Project> {
    const headers = new HttpHeaders().set('Content-Type', 'application/json');
    const url = `${this.apiUrl}/Project}`; // Assurez-vous de spécifier l'ID du projet à mettre à jour
    return this.http.put<Project>(url, project, { headers });
  }

  getData(id: number): Observable<any> {
    const url = `${this.apiUrl}/Project/${id}`;
    return this.http.get<any>(url);
  }

  getTimeId(id: number): Observable<any> {
    const url = `${this.apiUrl}/Enregistrement/${id}`;
    return this.http.get<any>(url);
  }

  createEnregistrement(temps: Temps): Observable<Temps> {
    const headers = new HttpHeaders().set('Content-Type', 'application/json');
    return this.http.post<Temps>(`${this.apiUrl}/Enregistrement/save`, temps,{headers});
  }

  getTimeEntriesByProject(projectId: number): Observable<Temps[]> {
    return this.http.get<Temps[]>(`${this.apiUrl}/projects/${projectId}/time-entries`);
  }

  createTimeEntry(timeEntry: Temps): Observable<Temps> {
    return this.http.post<Temps>(`${this.apiUrl}/time-entries`, timeEntry);
  }

  createUser(user: any): Observable<any> {
    const headers = new HttpHeaders()
      .set('Authorization', 'Bearer fdsfr54sag47sgddsgs58dgfrtd5s')
      .set('Content-Type', 'application/json');

    return this.http.post<any>(`${this.apiUrl}/User/consultant`, user,{headers});
  }


  connexion(user: User): Observable<any> {
  //  const url = 'http://votre-serveur-spring/connexion';
    const headers = new HttpHeaders().set('Content-Type', 'application/json');
    console.log(user)

    return this.http.post(`${this.apiUrl}/User/connexion`, user, { headers });
  }

  delete(id: string | undefined | null): Observable<any> {
    return this.http.delete(`${this.apiUrl}/Project/${id}`);
  }


}
