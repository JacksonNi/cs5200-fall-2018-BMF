import { Component, OnInit } from '@angular/core';
import {Player} from '../../_models';
import {PlayerService} from '../../_services/player.service';

@Component({
  selector: 'search-players',
  templateUrl: './search-players.component.html',
  styleUrls: ['./search-players.component.css']
})
export class SearchPlayersComponent implements OnInit {

  age: number;
  playerfullName: string;
  players: Player[];

  constructor(private dataService: PlayerService) { }

  ngOnInit() {
    this.playerfullName = null;
  }

  private searchPlayers() {
    this.dataService.getPlayersByName(this.playerfullName)
      .subscribe(players => this.players = players);
  }
  onSubmit() {
    this.searchPlayers();
  }
}
