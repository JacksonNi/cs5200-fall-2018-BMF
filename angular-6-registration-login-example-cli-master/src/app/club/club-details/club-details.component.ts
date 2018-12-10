import { Component, OnInit, Input } from '@angular/core';


import { ClubListComponent } from '../club-list/club-list.component';
import { Club } from '../../_models/Club';
import { ClubService } from '../../_services/club.service';

@Component({
  selector: 'customer-details',
  templateUrl: './club-details.component.html',
  styleUrls: ['./club-details.component.css']
})
export class ClubDetailsComponent implements OnInit {

  @Input() club: Club;

  constructor(private clubService: ClubService, private listComponent: ClubListComponent) { }

  ngOnInit() {
  }

  updateActive(isActive: boolean) {
    this.clubService.updateClub(this.club.id,
      { clubName: this.club.clubName, country: this.club.country, city: this.club.city, active: isActive })
      .subscribe(
        data => {
          console.log(data);
          this.club = data as Club;
        },
        error => console.log(error));
  }

  deleteClub() {
    this.clubService.deleteClub(this.club.id)
      .subscribe(
        data => {
          console.log(data);
          this.listComponent.reloadData();
        },
        error => console.log(error));
  }
}
