import React, { Component } from 'react';
import { Button } from 'react-bootstrap';
import Profile from './Profile.js';

class Player extends Component {
	constructor(props) {
		super(props);
		this.state={
			value: ""
		};
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
		        	value={this.state.value}
		        	onChange={(e) => {
		        		this.setState({value: e.target.value})
		        	}}
		        />
				<Button bsStyle="primary" bsSize="large"
		        	onClick={() => {

		        	}}>
		        	Search
		        </Button>
		        </div>
			</div>
		);
	}
}

export default Player;
