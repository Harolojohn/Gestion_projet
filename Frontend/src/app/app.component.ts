import { Component } from '@angular/core';
import {StorageService} from "./services/storage.service";
import {User} from "./models/user";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'gestionProjets';

  user?: User
  currentUser: any;
  constructor(private storageService: StorageService,) {
  }

  ngOnInit(): void {
    this.currentUser = this.storageService.getUser();
    console.log(this.currentUser.content
      ,'yessss')
  }
}
