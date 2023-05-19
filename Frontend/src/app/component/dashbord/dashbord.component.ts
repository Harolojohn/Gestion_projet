import { Component, OnInit } from '@angular/core';
import {StorageService} from "../../services/storage.service";
import {User} from "../../models/user";

@Component({
  selector: 'app-dashbord',
  templateUrl: './dashbord.component.html',
  styleUrls: ['./dashbord.component.scss']
})
export class DashbordComponent implements OnInit {

  user?: User
  currentUser: any;
  constructor(private storageService: StorageService,) { }

  ngOnInit(): void {
    this.currentUser = this.storageService.getUser();
  }

}
