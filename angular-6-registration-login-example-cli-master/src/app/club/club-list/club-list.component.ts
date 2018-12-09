import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import {Club} from '../../_models/Club';
import {ClubService} from '../../_services/club.service';



@Component({
  selector: 'clubs-list',
  templateUrl: './club-list.component.html',
  styleUrls: ['./club-list.component.css']
})
export class ClubListComponent implements OnInit {

  clubs: Observable<Club[]>;

  constructor(private clubService: ClubService) { }

  ngOnInit() {
    this.reloadData();
  }

  deleteClubs() {
    this.clubService.deleteAll()
      .subscribe(
        data => {
          console.log(data);
          this.reloadData();
        },
        error => console.log('ERROR: ' + error));
  }

  reloadData() {
    this.clubs = this.clubService.getClubsList();
  }
}
