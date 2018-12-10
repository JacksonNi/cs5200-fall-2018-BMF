import React, { Component } from 'react';
import { Button, Table } from 'react-bootstrap';
import fetch from 'cross-fetch';

class Match extends Component {
  search(id, home) {
    fetch('http://cs5200-fall2018-bmf.us-east-2.elasticbeanstalk.com/api/search/match/' + id + '/' + home, {
      method: 'GET',
    }).then((response) => {
      if (response.ok) return response.json();
    }).then((data) => {
        this.props.onBothChange(home, data);
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
				      <th>Venue</th>
				      <th>Scheduled</th>
              <th>Status</th>
              <th>Winner ID</th>
              <th>Season Name</th>
              <th>Actions</th>
				    </tr>
				  </thead>
          <tbody>
          {this.props.profile.map((match, i) => {
            return (
                <tr key={i}>
    				      <td>{match.venue}</td>
    				      <td>{match.scheduled}</td>
                  <td>{match.status}</td>
                  <td>{match.winner_id}</td>
                  <td>{match.season.sname}</td>
                  <td>
                    <Button bsStyle="primary" bsSize="large"
        		        	onClick={() => {
                        this.search(match.id, 'home')
        		        	}}>
        		        	Home Info
        		        </Button>
                    <Button bsStyle="primary" bsSize="large"
        		        	onClick={() => {
                        this.search(match.id, 'away')
        		        	}}>
        		        	Away Info
        		        </Button>
                  </td>
    				    </tr>
            );
          })}
        </tbody>
        </Table>
			</div>
		);
	}
}

export default Match;
