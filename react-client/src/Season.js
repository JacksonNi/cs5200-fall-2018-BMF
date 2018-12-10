import React, { Component } from 'react';
import { Button, Table } from 'react-bootstrap';
import fetch from 'cross-fetch';

class Season extends Component {
  searchMatch() {
    fetch('http://cs5200-fall2018-bmf.us-east-2.elasticbeanstalk.com/api/search/season/' + this.props.profile.id + '/matches', {
      method: 'GET',
    }).then((response) => {
      if (response.ok) return response.json();
    }).then((data) => {
        this.props.onBothChange('match', data);
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
				      <th>Name</th>
				      <th>Scheduled</th>
              <th>Played</th>
              <th>Max Coverage Level</th>
              <th>Max Covered</th>
              <th>Start</th>
              <th>End</th>
              <th>Year</th>
              <th>Current</th>
              <th>Tournament Name</th>
				    </tr>
				  </thead>
				  <tbody>
            <tr>
				      <td>{this.props.profile.sname}</td>
				      <td>{this.props.profile.scheduled}</td>
              <td>{this.props.profile.played}</td>
              <td>{this.props.profile.max_coverage_level}</td>
              <td>{this.props.profile.max_covered}</td>
              <td>{this.props.profile.start}</td>
              <td>{this.props.profile.end}</td>
              <td>{this.props.profile.year}</td>
              <td>{this.props.profile.current}</td>
              <td>{this.props.profile.tournament.name}</td>
				    </tr>
          </tbody>
        </Table>
				<div style={{
					marginTop: 30
				}}>
				    <Button bsStyle="primary" bsSize="large"
		        	onClick={() => {
                this.searchMatch()
		        	}}>
		        	Search Matches
		        </Button>
		   </div>
			</div>
		);
	}
}

export default Season;
