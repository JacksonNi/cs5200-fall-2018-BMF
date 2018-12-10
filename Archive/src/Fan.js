import React, { Component } from 'react';
import { Button, Table } from 'react-bootstrap';
import fetch from 'cross-fetch';
import Profile from './Profile.js';

class Fan extends Component {
  constructor(props) {
		super(props);
		this.state={
      clubName: "",
      playerName: "",
      clubs: [],
      players: []
    };
  }
  followClub(name) {
    fetch('http://cs5200-fall2018-bmf.us-east-2.elasticbeanstalk.com/api/update/fan/'
      + this.props.profile.id + '/follow/club?name=' + name, {
      method: 'GET',
    }).then((response) => {
      return response.json().catch((error) => {
          alert('Incorrect Club Name');
       });
    }).then((data) => {
      if (data) {
        alert('Follow succeed');
      }
    });
  }
  followPlayer(name) {
    fetch('http://cs5200-fall2018-bmf.us-east-2.elasticbeanstalk.com/api/update/fan/'
      + this.props.profile.id + '/follow/player?name=' + name, {
      method: 'GET',
    }).then((response) => {
      return response.json().catch((error) => {
          alert('Incorrect Player Name');
       });
    }).then((data) => {
      if (data) {
        alert('Follow succeed');
      }
    });
  }
  getClubs() {
    fetch('http://cs5200-fall2018-bmf.us-east-2.elasticbeanstalk.com/api/search/fan/'
      + this.props.profile.id + '/followedclubs', {
      method: 'GET',
    }).then((response) => {
      return response.json()
    }).then((data) => {
      this.setState({
        clubs: data
      });
    });
  }
  getPlayers() {
    fetch('http://cs5200-fall2018-bmf.us-east-2.elasticbeanstalk.com/api/search/fan/'
      + this.props.profile.id + '/followedplayers', {
      method: 'GET',
    }).then((response) => {
      return response.json()
    }).then((data) => {
      this.setState({
        players: data
      });
    });
  }
	render() {
    return (
      <div>
        <Profile
          profile={this.props.profile}
          status={this.props.status}
          onSubmit={this.props.onProfileChange}
        />
        <div style={{
          marginTop: 30
        }}>
          <input className='profile-input'
              placeholder="Club Name"
              type="text"
            value={this.state.clubName}
            onChange={(e) => {
              this.setState({clubName: e.target.value})
            }}
          />
          <Button bsStyle="primary" bsSize="large"
            onClick={() => {
              this.followClub(this.state.clubName);
            }}>
            Follow
         </Button>
         <Button bsStyle="primary" bsSize="large"
           onClick={() => {
             this.getClubs();
           }}>
           List All Clubs
        </Button>
         <Table responsive>
 				  <thead>
 				    <tr>
 				      <th>Club Name</th>
 				    </tr>
 				  </thead>
           <tbody>
           {this.state.clubs.map((club, i) => {
             return (
                 <tr key={i}>
     				      <td>{club.name}</td>
     				    </tr>
             );
           })}
         </tbody>
         </Table>
      </div>
      <div style={{
        marginTop: 30
      }}>
        <input className='profile-input'
            placeholder="Player Name"
            type="text"
          value={this.state.playerName}
          onChange={(e) => {
            this.setState({playerName: e.target.value})
          }}
        />
        <Button bsStyle="primary" bsSize="large"
          onClick={() => {
            this.followPlayer(this.state.playerName);
          }}>
          Follow
       </Button>
       <Button bsStyle="primary" bsSize="large"
         onClick={() => {
           this.getPlayers();
         }}>
         List All Players
      </Button>
       <Table responsive>
        <thead>
          <tr>
            <th>Player Name</th>
          </tr>
        </thead>
         <tbody>
         {this.state.players.map((player, i) => {
           return (
               <tr key={i}>
                <td>{player.username}</td>
              </tr>
           );
         })}
       </tbody>
       </Table>
    </div>
    </div>
    );
  }
}

export default Fan;
