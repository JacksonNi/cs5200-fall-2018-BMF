import { Component, OnInit } from '@angular/core';
import {Club} from '../../_models/Club';
import {ClubService} from '../../_services/club.service';



@Component({
  selector: 'create-club',
  templateUrl: './create-club.component.html',
  styleUrls: ['./create-club.component.css']
})
export class CreateClubComponent implements OnInit {

  club: Club = new Club();
  submitted = false;

  constructor(private clubService: ClubService) { }

  ngOnInit() {
  }

  newClub(): void {
    this.submitted = false;
    this.club = new Club();
  }

  save() {
    this.clubService.createClub(this.club)
      .subscribe(data => console.log(data), error => console.log(error));
    this.club = new Club();
  }

  onSubmit() {
    this.submitted = true;
    this.save();
  }
}
