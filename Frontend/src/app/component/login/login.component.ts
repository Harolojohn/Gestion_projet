import { Component, OnInit } from '@angular/core';
import {ProjectService} from "../../services/project.service";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import {StorageService} from "../../services/storage.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  userForm: FormGroup;
  constructor(private projectService: ProjectService,
              private formBuilder: FormBuilder,
              private storageService: StorageService,
              private router : Router) { }

  initForm(): void {
    this.userForm = this.formBuilder.group({
      email: ['', Validators.required],
      password: ['', Validators.required],
    });
  }
  ngOnInit(): void {
    this.initForm()
  }




  onSubmit() {
    // Obtenez les données du formulaire de connexion
    const user: any = {
      email: this.userForm.value.email,
      password: this.userForm.value.password
    };

    // Appelez la méthode de connexion du service
    this.projectService.connexion(this.userForm.value).subscribe(
      response => {
        // Traitez la réponse de la demande de connexion ici
        this.storageService.saveUser(response);
        this.router.navigate(['/dashboard'], );
        localStorage.setItem('isLoggedIn', "true");
      },
      error => {
        // Traitez les erreurs ici
        console.error(error);
      }
    );
  }

}
