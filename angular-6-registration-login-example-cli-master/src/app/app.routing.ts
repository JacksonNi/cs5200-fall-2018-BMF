import { Routes, RouterModule } from '@angular/router';

import { HomeComponent } from './home';
import { LoginComponent } from './login';
import { RegisterComponent } from './register';
import { AuthGuard, CanAdminProvide } from './_guards';
import {SearchClubsComponent} from './club/search-clubs/search-clubs.component';
import {CreateClubComponent} from './club/create-club/create-club.component';
import {ClubListComponent} from './club/club-list/club-list.component';
import {PlayerListComponent} from './player/player-list/player-list.component';
import {CreatePlayerComponent} from './player/create-player/create-player.component';
import {SearchPlayersComponent} from './player/search-player/search-players.component';

const appRoutes: Routes = [
    { path: '', component: HomeComponent, canActivate: [AuthGuard] },
    { path: 'login', component: LoginComponent },
    { path: 'register', component: RegisterComponent },
    { path: 'club', component: ClubListComponent },
    { path: 'addClub', component: CreateClubComponent, canActivate: [CanAdminProvide]  },
    { path: 'findClubByName', component: SearchClubsComponent},

    { path: 'player', component: PlayerListComponent },
    { path: 'addPlayer', component: CreatePlayerComponent, canActivate: [CanAdminProvide]  },
    { path: 'findPlayerByName', component: SearchPlayersComponent},


    // otherwise redirect to home
    { path: '**', redirectTo: '' }
];

export const routing = RouterModule.forRoot(appRoutes);
