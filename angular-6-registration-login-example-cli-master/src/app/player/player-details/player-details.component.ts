import { Component, OnInit, Input } from '@angular/core';

import {Player} from '../../_models/player';
import {PlayerService} from '../../_services/player.service';
import {PlayerListComponent} from '../player-list/player-list.component';

@Component({
  selector: 'player-details',
  templateUrl: './player-details.component.html',
  styleUrls: ['./player-details.component.css']
})
export class PlayerDetailsComponent implements OnInit {

  @Input() player: Player;

  constructor(private playerService: PlayerService, private listComponent: PlayerListComponent) { }

  ngOnInit() {
  }

  updateActive(isActive: boolean) {
    this.playerService.updatePlayer(this.player.id,
      { firstname: this.player.firstName, gender: this.player.gender,
        nationality: this.player.nationality, height: this.player.height,
        weight: this.player.weight, jerserNumber: this.player.jerserNumber,
        preferredFoot: this.player.preferredFoot,
        active: isActive })
      .subscribe(
        data => {
          console.log(data);
          this.player = data as Player;
        },
        error => console.log(error));
  }

  deletePlayer() {
    this.playerService.deletePlayer(this.player.id)
      .subscribe(
        data => {
          console.log(data);
          this.listComponent.reloadData();
        },
        error => console.log(error));
  }
}
