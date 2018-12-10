import React, { Component } from 'react';
import { Button, FormGroup, ControlLabel, FormControl } from 'react-bootstrap';
import fetch from 'cross-fetch';

function FieldGroup({ id, label, help, ...props }) {
  return (
    <FormGroup controlId={id}>
      <ControlLabel>{label}</ControlLabel>
      <FormControl {...props} />
    </FormGroup>
  );
}

class Profile extends Component {
	constructor(props) {
		super(props);
		this.state = {
			id: this.props.profile.id || "",
			fullName: this.props.profile.fullName || "",
			username: this.props.profile.username || "",
			membership: this.props.profile.membership || "",
			password:this.props.profile.password || "",
			player_id: this.props.profile.player_id || "",
			dob: this.props.profile.dob || "",
			gender: this.props.profile.gender || "",
			nationality: this.props.profile.nationality || "",
			height: this.props.profile.height || "",
			weight: this.props.profile.weight || "",
			jerseyNumber: this.props.profile.jerseyNumber || "",
			type: this.props.profile.type || "",
			CRUDAuthorized: this.props.profile.CRUDAuthorized || "",
		};
	}
	update() {
		fetch('http://cs5200-fall2018-bmf.us-east-2.elasticbeanstalk.com/api/update/' + this.props.status, {
		  method: 'POST',
		  headers: {
		    Accept: 'application/json',
		    'Content-Type': 'application/json',
		  },
		  body: JSON.stringify(
		    this.state
		  ),
		}).catch((error) => {
        	console.error(error);
      	});
	}
	render() {
		return (
			 <form >
    			<FieldGroup
		        	className='profile-input'
		        	placeholder={this.state.fullName}
		        	label='Name'
		        	type="text"
		        	bsSize='sm'
		        	value={this.state.fullName}
		        	onChange={(e) => {
		        		this.setState({fullName: e.target.value})
		        	}}
		        />
    			<FieldGroup
		        	className='profile-input'
		        	placeholder={this.state.username}
		        	type="text"
		        	label='User Name'
		        	value={this.state.username}
		        	onChange={(e) => {
		        		this.setState({username: e.target.value})
		        	}}
		        />
		        {this.state.membership && <FieldGroup
		        	className='profile-input'
		        	placeholder={this.state.membership}
		        	type="text"
		        	label='Membership'
		        	value={this.state.membership}
		        	onChange={(e) => {
		        		this.setState({membership: e.target.value})
		        	}}
		        />}
		        <FieldGroup className='profile-input'
		        	placeholder={this.state.password}
		        	type="password"
		        	label='Password'
		        	value={this.state.password}
		        	onChange={(e) => {
		        		this.setState({password: e.target.value})
		        	}}
		        />
		        <FieldGroup className='profile-input'
		        	placeholder={this.state.dob}
		        	type="text"
		        	label='Date of Birth'
		        	value={this.state.dob}
		        	onChange={(e) => {
		        		this.setState({dob: e.target.value})
		        	}}
		        />
		        <FieldGroup className='profile-input'
		        	placeholder={this.state.gender}
		        	type="text"
		        	label='Gender'
		        	value={this.state.gender}
		        	onChange={(e) => {
		        		this.setState({gender: e.target.value})
		        	}}
		        />
		        <FieldGroup className='profile-input'
		        	placeholder={this.state.nationality}
		        	type="text"
		        	label='Nationality'
		        	value={this.state.nationality}
		        	onChange={(e) => {
		        		this.setState({nationality: e.target.value})
		        	}}
		        />
		        {this.state.height && <FieldGroup className='profile-input'
		        	placeholder={this.state.height}
		        	type="text"
		        	label='Height'
		        	value={this.state.height}
		        	onChange={(e) => {
		        		this.setState({height: e.target.value})
		        	}}
		        />}
		        {this.state.weight && <FieldGroup className='profile-input'
		        	placeholder={this.state.weight}
		        	type="text"
		        	label='Weight'
		        	value={this.state.weight}
		        	onChange={(e) => {
		        		this.setState({weight: e.target.value})
		        	}}
		        />}
		        {this.state.jerseyNumber && <FieldGroup className='profile-input'
		        	placeholder={this.state.jerseyNumber}
		        	type="text"
		        	label='Jersey Number'
		        	value={this.state.jerseyNumber}
		        	onChange={(e) => {
		        		this.setState({jerseyNumber: e.target.value})
		        	}}
		        /> }
		        {this.state.type && <FieldGroup className='profile-input'
		        	placeholder={this.state.type}
		        	type="text"
		        	label='Position'
		        	value={this.state.type}
		        	onChange={(e) => {
		        		this.setState({type: e.target.value})
		        	}}
		        /> }
		        {this.state.CRUDAuthorized && <FieldGroup className='profile-input'
		        	placeholder={this.state.CRUDAuthorized}
		        	type="text"
		        	label='Authorized'
		        	value={this.state.CRUDAuthorized}
		        	onChange={(e) => {
		        		this.setState({CRUDAuthorized: e.target.value})
		        	}}
		        />}
		        <Button bsStyle="primary" bsSize="large"
		        	onClick={() => {
		        		this.update();
		        	}}>
		        	Update
		        </Button>
		    </form>
		);
	}
}

export default Profile;
