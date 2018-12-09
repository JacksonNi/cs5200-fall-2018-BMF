import { Component, OnInit } from '@angular/core';
import {Club} from '../../_models/Club';
import {ClubService} from '../../_services/club.service';

@Component({
  selector: 'search-customers',
  templateUrl: './search-clubs.component.html',
  styleUrls: ['./search-clubs.component.css']
})
export class SearchClubsComponent implements OnInit {

  age: number;
  clubName: string;
  clubs: Club[];

  constructor(private dataService: ClubService) { }

  ngOnInit() {
    this.clubName = null;
  }

  private searchClubs() {
    this.dataService.getClubsByClubName(this.clubName)
      .subscribe(clubs => this.clubs = clubs);
  }

  onSubmit() {
    this.searchClubs();
  }
}
