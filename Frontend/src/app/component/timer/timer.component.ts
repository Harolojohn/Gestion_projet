import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {ProjectService} from "../../services/project.service";
import {User} from "../../models/user";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {StorageService} from "../../services/storage.service";

@Component({
  selector: 'app-timer',
  templateUrl: './timer.component.html',
  styleUrls: ['./timer.component.scss']
})
export class TimerComponent implements OnInit {
  timeForm: FormGroup;
  project : any

  user?: User
  currentUser: any;

  constructor(
    private route : ActivatedRoute,
    private formBuilder: FormBuilder,
    private storageService: StorageService,
    private router: Router,
    private projectService : ProjectService) { }

  ngOnInit(): void {
    this.currentUser = this.storageService.getUser();

    this.route.paramMap.subscribe(params => {
      const idParam = params.get('id');
      if (idParam !== null) {
        const projectId = +idParam;
        console.log(projectId);
      /*  this.projectId = projectId;
        this.isEditMode = true*/

        this.getDataById(projectId); // Appelez getDataById() avec l'ID du projet
        // this.initForm(id); // Appelez initForm()
      }
      console.log(idParam);
    });
    this.initForm()
  }

  initForm(): void {
    this.timeForm = this.formBuilder.group({
      date_depart: ['', Validators.required],
      date_fin: ['', Validators.required],

    });
  }

  onSubmit() {

    const time: any = {
      date_depart: this.timeForm.value.date_depart,
      date_fin: this.timeForm.value.date_fin,
      id_user: this.currentUser.content.id,
      id_project: this.project.id,
    };

    this.projectService.createEnregistrement(time).subscribe(
      (response) => {
        // Effectuez d'autres actions après la création réussie
        this.router.navigate(['/listProjet']);
      },
      (error) => {
        console.error('Erreur lors de la création :', error);
        // Gérez l'erreur et effectuez d'autres actions si nécessaire
      }
    );
  }

  getDataById(id: any) {
    this.projectService.getData(id).subscribe(
      (data) => {
        this.project = data.contentProject;
        console.log(this.project);
       // Passer l'ID en tant que paramètre pour initForm()
      },
      (error) => {
        console.error('Error fetching data:', error);
      }
    );
  }
}
