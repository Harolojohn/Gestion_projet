import { Component, OnInit } from '@angular/core';
import {ProjectService} from "../../services/project.service";
import {Project} from "../../models/project";
import {Router} from "@angular/router";

@Component({
  selector: 'app-liste-projet',
  templateUrl: './liste-projet.component.html',
  styleUrls: ['./liste-projet.component.scss']
})
export class ListeProjetComponent implements OnInit {

  projects: Project[];
  constructor(private  projectService : ProjectService,
               private router : Router) { }

  ngOnInit(): void {
    this.loadProjects()
  }

  loadProjects() {
    this.projectService.getAllProjects().subscribe(
      (projects: any) => {
        this.projects = projects.contentProject;
      },
      (error) => {
        console.log('Error retrieving projects:', error);
      }
    );
  }

  navigateToComponent(id: number) {
    this.router.navigate(['/addProject', id]);
  }

  navigateToTime(id: number) {
    this.router.navigate(['/time', id]);
  }

  delete(id:any) {

    this.projectService.delete(id).subscribe({
      next: (res) => {
        this.router.navigate(['/listProjet']);
      },
      error: (e) => {
      },
    })

  }

}
