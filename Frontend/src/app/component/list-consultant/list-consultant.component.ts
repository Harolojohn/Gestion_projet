import { Component, OnInit } from '@angular/core';
import {ProjectService} from "../../services/project.service";
import {Project} from "../../models/project";
import {Consultant} from "../../models/consultant";

@Component({
  selector: 'app-list-consultant',
  templateUrl: './list-consultant.component.html',
  styleUrls: ['./list-consultant.component.scss']
})
export class ListConsultantComponent implements OnInit {

  consultant : any
  constructor( private  projectService : ProjectService) { }

  ngOnInit(): void {
    this.loadConsultant()
  }

  loadConsultant() {
    this.projectService.getAllConsultant().subscribe(
      (data:any ) => {
        this.consultant = data.content;
        console.log(this.consultant)
      },
      (error) => {
        console.log('Error retrieving projects:', error);
      }
    );
  }



}
