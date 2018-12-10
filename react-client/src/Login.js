import React, { Component } from 'react';
import { Button } from 'react-bootstrap';
import fetch from 'cross-fetch';

class Login extends Component {
	constructor(props) {
		super(props);
		this.state = {
			username: "",
			password: "",
			type: "admin",
		};
	}
	login() {
		fetch('http://cs5200-fall2018-bmf.us-east-2.elasticbeanstalk.com/api/login', {
		  method: 'POST',
		  headers: {
		    Accept: 'application/json',
		    'Content-Type': 'application/json',
		  },
		  body: JSON.stringify({
		    username: this.state.username,
		    password: this.state.password,
		  }),
		}).then((response) => {
			return response.json().catch((error) => {
					alert('Incorrect username/password');
	     });
		}).then((data) => {
			if (data) {
				this.props.onProfileChange(data);
	      this.checkType(data.id);
			}
		});
	}
	checkType(id) {
		fetch('http://cs5200-fall2018-bmf.us-east-2.elasticbeanstalk.com/api/check-user-type/' + id, {
		  method: 'GET',
		}).then((response) => {
			return response.text();
		}).then((data) => {
			this.props.onStatusChange(data);
		}).catch((error) => {
        	console.error(error);
      	});
	}
	register(type) {
		const url = 'http://cs5200-fall2018-bmf.us-east-2.elasticbeanstalk.com/api/register/' + type;
		fetch(url, {
		  method: 'POST',
		  headers: {
		    Accept: 'application/json',
		    'Content-Type': 'application/json',
		  },
		  body: JSON.stringify({
		    username: this.state.username,
		    password: this.state.password,
		  }),
		}).then((response) => {
			return response.json();
		}).then((data) => {
			this.props.onProfileChange(data);
      		this.checkType(data.id);
		}).catch((error) => {
        	console.error(error);
      	});
	}
	getNews() {
		fetch('http://cs5200-fall2018-bmf.us-east-2.elasticbeanstalk.com/api/search/news', {
		  method: 'GET',
		}).then((response) => {
			return response.json();
		}).then((data) => {
			if (data) {
				this.props.onProfileChange(data);
				this.props.onStatusChange('news');
			}
		});
	}
	render() {
		return (
			<div className='login'>
		        <input placeholder="username"
		        	type="text"
		        	className="login-input"
		        	value={this.state.username}
		        	onChange={(e) => {
		        		this.setState({username: e.target.value})
		        	}}
		        />
		        <input placeholder="password"
		        	type="password"
		        	className="login-input"
		        	value={this.state.password}
		        	onChange={(e) => {
		        		this.setState({password: e.target.value})
		        	}}
		        />
		        <select value={this.state.type} onChange={(e) => {
		        	this.setState({
		        		type: e.target.value
		        	});
		        }}>
		          <option value="admin">Admin</option>
		          <option value="coach">Coach</option>
		          <option value="player">Player</option>
		          <option value="faculty">Faculty</option>
		          <option value="fan">Fan</option>
			    </select>
		        <Button bsStyle="primary" bsSize="large"
		        	onClick={() => {
		        		this.register(this.state.type);
		        	}}>
		        	Register
		        </Button>
		        <Button bsStyle="primary" bsSize="large"
		        	onClick={() => {
		        		this.login();
		        	}}>
		        	Login
		        </Button>
						<div style={{
							marginTop: 40
						}}>
							<Button bsStyle="primary" bsSize="large"
			        	onClick={() => {
			        		this.getNews();
			        	}}>
			        	Annoucements
			        </Button>
					</div>
		    </div>
		);
	}
}

export default Login;
