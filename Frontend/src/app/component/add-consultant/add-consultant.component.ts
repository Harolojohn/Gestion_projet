import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ProjectService} from "../../services/project.service";
import {User} from "../../models/user";
import {ActivatedRoute, Route, Router} from "@angular/router";

@Component({
  selector: 'app-add-consultant',
  templateUrl: './add-consultant.component.html',
  styleUrls: ['./add-consultant.component.scss']
})
export class AddConsultantComponent implements OnInit {

  consultantForm: FormGroup;


  constructor(private formBuilder: FormBuilder,
              private projectService : ProjectService,

  ) {}

  initForm(): void {
    this.consultantForm = this.formBuilder.group({
      nom: ['', Validators.required],
      email: ['', Validators.required],
      password: ['', Validators.required],
    });
  }

  ngOnInit(): void {


    this.initForm();
  }

  createUser(user: User): void {
    this.projectService.createUser(user).subscribe(
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

  onSubmit(): void {
    if (this.consultantForm.invalid) {
      return;
    }

    const consultant: any = {
      nom: this.consultantForm.value.nom,
      email: this.consultantForm.value.email,
      password: this.consultantForm.value.password,
    };

    this.projectService.createUser(consultant).subscribe(
      (response) => {
        // Effectuez d'autres actions après la création réussie
      },
      (error) => {
        console.error('Erreur lors de la création :', error);
        // Gérez l'erreur et effectuez d'autres actions si nécessaire
      }
    );
  }

}
