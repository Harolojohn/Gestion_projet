import { Component, OnInit } from '@angular/core';
import {User} from "../../models/user";
import {ProjectService} from "../../services/project.service";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {

  userForm: FormGroup;

  constructor(private formBuilder: FormBuilder,
                private projectService : ProjectService
                ) {}

  initForm(): void {
    this.userForm = this.formBuilder.group({
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
    if (this.userForm.invalid) {
      return;
    }

    const user: any = {
      nom: this.userForm.value.nom,
      email: this.userForm.value.email,
      password: this.userForm.value.password,
    };

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
}
