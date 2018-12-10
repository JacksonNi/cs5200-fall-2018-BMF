import React, { Component } from 'react';
import Login from './Login.js';
import Admin from './Admin.js';
import Player from './Player.js';
import Tournament from './Tournament.js';
import Season from './Season.js';
import Match from './Match.js';
import Home from './Home.js';
import Coach from './Coach.js';
import Fan from './Fan.js';
import News from './News.js';

import { Button } from 'react-bootstrap';

import './App.css';

class App extends Component {
  constructor(props) {
    super(props);
    this.state = {
      status: "login",
      profile: "",
    };
  }
  onStatusChange(value) {
    this.setState({
      status: value,
    });
  }
  onProfileChange(value) {
    this.setState({
      profile: value,
    });
  }
  onBothChange(status, profile) {
    this.setState({
      status: status,
      profile: profile,
    });
  }
  render() {
    return (
      <div className="App">
        {this.state.status === 'login' ? <header className="App-header">
            <Login
              onStatusChange={(value) =>
                this.onStatusChange(value)
              }
              onProfileChange={(value) =>
                this.onProfileChange(value)
              }
            />
          }
        </header> : <div className="title">
          <div>{this.state.status}</div>
          <Button bsStyle="primary" bsSize="small"
              onClick={() => {
                this.setState({
                  status: "login"
                });
              }}>
              Log Out
          </Button>
        </div>}
        {this.state.status === 'admin' &&
            <Admin
              status={this.state.status}
              profile={this.state.profile}
              onStatusChange={(value) =>
                this.onStatusChange(value)
              }
              onProfileChange={(value) =>
                this.onProfileChange(value)
              }
            />
          }
          {this.state.status === 'coach' &&
              <Coach
                status={this.state.status}
                profile={this.state.profile}
                onStatusChange={(value) =>
                  this.onStatusChange(value)
                }
                onProfileChange={(value) =>
                  this.onProfileChange(value)
                }
              />
            }
            {this.state.status === 'fan' &&
                <Fan
                  status={this.state.status}
                  profile={this.state.profile}
                  onStatusChange={(value) =>
                    this.onStatusChange(value)
                  }
                  onProfileChange={(value) =>
                    this.onProfileChange(value)
                  }
                />
              }
          {this.state.status === 'player' &&
            <Player
              status={this.state.status}
              profile={this.state.profile}
              onStatusChange={(value) =>
                this.onStatusChange(value)
              }
              onProfileChange={(value) =>
                this.onProfileChange(value)
              }
            />
          }
          {this.state.status === 'tournament' &&
            <Tournament
              status={this.state.status}
              profile={this.state.profile}
              onBothChange={(status, profile) => {
                this.onBothChange(status, profile);
              }}
            />
          }
          {this.state.status === 'season' &&
            <Season
              status={this.state.status}
              profile={this.state.profile}
              onBothChange={(status, profile) => {
                this.onBothChange(status, profile);
              }}
            />
          }
          {this.state.status === 'match' &&
            <Match
              status={this.state.status}
              profile={this.state.profile}
              onBothChange={(status, profile) => {
                this.onBothChange(status, profile);
              }}
            />
          }
          {(this.state.status === 'home' || this.state.status === 'away') &&
            <Home
              status={this.state.status}
              profile={this.state.profile}
              onBothChange={(status, profile) => {
                this.onBothChange(status, profile);
              }}
            />
          }
          {this.state.status === 'news' &&
            <News
              status={this.state.status}
              profile={this.state.profile}
            />
          }
      </div>
    );
  }
}

export default App;
