import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';

// used to create fake backend
import { fakeBackendProvider } from './_helpers';

import { AppComponent } from './app.component';
import { routing } from './app.routing';

import { AlertComponent } from './_directives';
import { AuthGuard, CanAdminProvide} from './_guards';
import { JwtInterceptor, ErrorInterceptor } from './_helpers';
import { AlertService, AuthenticationService, UserService } from './_services';
import { HomeComponent } from './home';
import { LoginComponent } from './login';
import { RegisterComponent } from './register';
import {CreateClubComponent} from './club/create-club/create-club.component';
import {ClubDetailsComponent} from './club/club-details/club-details.component';
import {ClubListComponent} from './club/club-list/club-list.component';
import {SearchClubsComponent} from './club/search-clubs/search-clubs.component';
import {PlayerListComponent} from './player/player-list/player-list.component';
import {PlayerDetailsComponent} from './player/player-details/player-details.component';
import {CreatePlayerComponent} from './player/create-player/create-player.component';
import {SearchPlayersComponent} from './player/search-player/search-players.component';
console.log(CanAdminProvide)
@NgModule({
    imports: [
        BrowserModule,
        FormsModule,
        ReactiveFormsModule,
        routing,
        HttpClientModule
    ],
    declarations: [
        AppComponent,
        AlertComponent,
        HomeComponent,
        LoginComponent,
        RegisterComponent,
      // add
        CreateClubComponent,
        ClubDetailsComponent,
        ClubListComponent,
        SearchClubsComponent,
      // Player
        CreatePlayerComponent,
        PlayerListComponent,
        PlayerDetailsComponent,
        SearchPlayersComponent
    ],
    providers: [
        AuthGuard,
        CanAdminProvide,
        AlertService,
        AuthenticationService,
        UserService,
        { provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true },
        { provide: HTTP_INTERCEPTORS, useClass: ErrorInterceptor, multi: true },

        // provider used to create fake backend
        fakeBackendProvider
    ],
    bootstrap: [AppComponent]
})
export class AppModule {}

