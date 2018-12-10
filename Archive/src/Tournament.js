import React, { Component } from 'react';
import { Button, Table } from 'react-bootstrap';
import fetch from 'cross-fetch';

class Tournament extends Component {
  constructor(props) {
		super(props);
		this.state = {
      seasonName: "",
		};
	}

  searchSeason(name) {
    fetch('http://cs5200-fall2018-bmf.us-east-2.elasticbeanstalk.com/api/search/season?name=' + name, {
      method: 'GET',
    }).then((response) => {
      return response.json().catch((error) => {
					alert('Incorrect Season Name');
	     });
    }).then((data) => {
      if (data)
        this.props.onBothChange('season', data);
    });
  }

	render() {
		return (
			<div>
        <Table responsive>
				  <thead>
				    <tr>
				      <th>Name</th>
				      <th>Faculty Name</th>
				    </tr>
				  </thead>
				  <tbody>
            <tr>
				      <td>{this.props.profile.name}</td>
				      <td>{this.props.profile.faculty.fullName}</td>
				    </tr>
          </tbody>
        </Table>
				<div style={{
					marginTop: 30
				}}>
				<input className='profile-input'
		        	placeholder="Season"
		        	type="text"
		        	value={this.state.seasonName}
		        	onChange={(e) => {
		        		this.setState({seasonName: e.target.value})
		        	}}
		        />
				    <Button bsStyle="primary" bsSize="large"
		        	onClick={() => {
                this.searchSeason(this.state.seasonName)
		        	}}>
		        	Search
		        </Button>
		        </div>
			</div>
		);
	}
}

export default Tournament;
