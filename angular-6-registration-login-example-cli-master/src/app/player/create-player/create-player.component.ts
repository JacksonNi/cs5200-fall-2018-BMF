import { Component, OnInit } from '@angular/core';
import {Player} from '../../_models';
import {PlayerService} from '../../_services/player.service';



@Component({
  selector: 'create-player',
  templateUrl: './create-player.component.html',
  styleUrls: ['./create-player.component.css']
})
export class CreatePlayerComponent implements OnInit {

  player: Player = new Player();
  submitted = false;

  constructor(private playerService: PlayerService) { }

  ngOnInit() {
  }

  newPlayer(): void {
    this.submitted = false;
    this.player = new Player();
  }

  save() {
    this.playerService.createPlayer(this.player)
      .subscribe(data => console.log(data), error => console.log(error));
    this.player = new Player();
  }

  onSubmit() {
    this.submitted = true;
    this.save();
  }
}
