import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ProjectService} from "../../services/project.service";
import {User} from "../../models/user";
import {ActivatedRoute} from "@angular/router";
import {Project} from "../../models/project";

@Component({
  selector: 'app-ajouter-projet',
  templateUrl: './ajouter-projet.component.html',
  styleUrls: ['./ajouter-projet.component.scss']
})
export class AjouterProjetComponent implements OnInit {

  projectForm: FormGroup;
  isEditMode: boolean = false;
  project : any
  projectId: any

  constructor(private formBuilder: FormBuilder,
              private projectService : ProjectService,
              private route : ActivatedRoute
  ) {}


  initForm(id: any): void {
    this.projectForm = this.formBuilder.group({
      nom_projet: ['', Validators.required],
      budget: ['', Validators.required],
      taux_horaire: ['', Validators.required],
      id_user: 1,
    });

    if (this.isEditMode) {
      this.projectForm.patchValue({
        nom_projet: this.project.nom_projet,
        budget: this.project.budget,
        taux_horaire: this.project.taux_horaire,
        // ...
      });
    }
  }

  ngOnInit(): void {

    this.route.paramMap.subscribe(params => {
      const idParam = params.get('id');
      if (idParam !== null) {
        const projectId = +idParam;
        console.log(projectId);
        this.projectId = projectId;
        this.isEditMode = true

        this.getDataById(projectId); // Appelez getDataById() avec l'ID du projet
       // this.initForm(id); // Appelez initForm()
      }
      console.log(idParam);
    });
    this.initForm(this.projectId);

  }

  createUser(): void {
    this.projectService.createUser(this.projectForm.value).subscribe(
      (response) => {
        // Effectuez d'autres actions après la création réussie
      },
      (error) => {
        console.error('Erreur lors de la création :', error);
        // Gérez l'erreur et effectuez d'autres actions si nécessaire
      }
    );
  }

 /* onSubmit(): void {
    if (this.projectForm.invalid) {
      return;
    }
    console.log(this.projectForm.value)

    this.projectService.createProject(this.projectForm.value).subscribe(
      (response) => {
        console.log('Création réussie :', response);
        // Effectuez d'autres actions après la création réussie
      },
      (error) => {
        console.error('Erreur lors de la création :', error);
        // Gérez l'erreur et effectuez d'autres actions si nécessaire
      }
    );
  }*/


  getDataById(id: any) {
    this.projectService.getData(id).subscribe(
      (data) => {
        this.project = data.contentProject;
        console.log(this.project);
        this.initForm(id); // Passer l'ID en tant que paramètre pour initForm()
      },
      (error) => {
        console.error('Error fetching data:', error);
      }
    );
  }


  onSubmit(): void {
    if (this.projectForm.invalid) {
      return;
    }

    const project: Project = {
      id: this.projectId, // Assurez-vous d'avoir l'ID du projet correctement défini
      nom_projet: this.projectForm.value.nom_projet,
      budget: this.projectForm.value.budget,
      taux_horaire: this.projectForm.value.taux_horaire,
    };

    if (this.isEditMode) {
      // En mode édition, effectuez la mise à jour du projet
      this.projectService.editProject(project).subscribe(
        (response) => {
          console.log('Mise à jour réussie :', response);
          // Effectuez d'autres actions après la mise à jour réussie
        },
        (error) => {
          console.error('Erreur lors de la mise à jour :', error);
          // Gérez l'erreur et effectuez d'autres actions si nécessaire
        }
      );
    } else {
      // En mode création, effectuez la création du projet
      this.projectService.createProject(project).subscribe(
        (response) => {
          console.log('Création réussie :', response);
          // Effectuez d'autres actions après la création réussie
        },
        (error) => {
          console.error('Erreur lors de la création :', error);
          // Gérez l'erreur et effectuez d'autres actions si nécessaire
        }
      );
    }
  }

}
