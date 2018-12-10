import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import {Player} from '../../_models/player';
import {PlayerService} from '../../_services/player.service';



@Component({
  selector: 'players-list',
  templateUrl: './player-list.component.html',
  styleUrls: ['./player-list.component.css']
})
export class PlayerListComponent implements OnInit {

  players: Observable<Player[]>;

  constructor(private playerService: PlayerService) { }

  ngOnInit() {
    this.reloadData();
  }

  deletePlayers() {
    this.playerService.deleteAll()
      .subscribe(
        data => {
          console.log(data);
          this.reloadData();
        },
        error => console.log('ERROR: ' + error));
  }

  reloadData() {
    this.players = this.playerService.getPlayersList();
  }
}
