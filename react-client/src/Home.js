import React, { Component } from 'react';
import { Button, Table } from 'react-bootstrap';
import fetch from 'cross-fetch';
import Lineups from './Lineups.js';

class Home extends Component {
  constructor(props) {
		super(props);
		this.state={
      showLineups: false,
			data: ""
		};
	}

  getLineups() {
    fetch('http://cs5200-fall2018-bmf.us-east-2.elasticbeanstalk.com/api/search/' + this.props.status + '/' + this.props.profile.id + '/lineups', {
      method: 'GET',
    }).then((response) => {
      if (response.ok) return response.json();
    }).then((data) => {
        this.setState({
          showLineups: true,
          data: data
        });
    }).catch((error) => {
      console.error(error);
    });
  }

	render() {
    return (
			<div>
        <Table responsive>
				  <thead>
				    <tr>
				      <th>Formation</th>
				      <th>Score</th>
              <th>Ball Possession</th>
              <th>Corner Kicks</th>
              <th>Free Kicks</th>
              <th>Fouls</th>
              <th>Goal Kicks</th>
              <th>Shots on Target</th>
              <th>Yellow Cards</th>
              <th>Club ID</th>
				    </tr>
				  </thead>
				  <tbody>
            <tr>
				      <td>{this.props.profile.formation}</td>
				      <td>{this.props.profile.score}</td>
              <td>{this.props.profile.ball_possession}</td>
              <td>{this.props.profile.corner_kicks}</td>
              <td>{this.props.profile.free_kicks}</td>
              <td>{this.props.profile.fouls}</td>
              <td>{this.props.profile.goal_kicks}</td>
              <td>{this.props.profile.shots_on_target}</td>
              <td>{this.props.profile.yellow_cards}</td>
              <td>{this.props.profile.club_id}</td>
				    </tr>
          </tbody>
        </Table>
				<div style={{
					marginTop: 30
				}}>
				    <Button bsStyle="primary" bsSize="large"
		        	onClick={() => {
                this.getLineups()
		        	}}>
		        	Get Lineups
		        </Button>
		   </div>
       {this.state.showLineups &&
         <Lineups homeId={this.props.profile.id}
           home={this.props.status}
           getLineups={() => {
             this.getLineups();
           }}
           profile={this.state.data}/>}
			</div>
		);
	}
}

export default Home;
