import React, { Component } from 'react';
import { Button } from 'react-bootstrap';
import fetch from 'cross-fetch';
import Profile from './Profile.js';

class Coach extends Component {
  constructor(props) {
		super(props);
		this.state={
      tournament: ""
    };
  }
  searchT(name) {
    fetch('http://cs5200-fall2018-bmf.us-east-2.elasticbeanstalk.com/api/search/tournament?name=' + name, {
      method: 'GET',
    }).then((response) => {
      return response.json().catch((error) => {
          alert('Incorrect Tournament Name');
       });
    }).then((data) => {
      if (data) {
        this.props.onProfileChange(data);
        this.props.onStatusChange('tournament');
      }
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
              placeholder="Tournament"
              type="text"
            value={this.state.tournament}
            onChange={(e) => {
              this.setState({tournament: e.target.value})
            }}
          />
          <Button bsStyle="primary" bsSize="large"
            onClick={() => {
              this.searchT(this.state.tournament);
            }}>
            Search
         </Button>
      </div>
    </div>
    );
  }
}

export default Coach;
