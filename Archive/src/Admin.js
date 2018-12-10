import React, { Component } from 'react';
import { Button, Table } from 'react-bootstrap';
import fetch from 'cross-fetch';
import Profile from './Profile.js';

class Admin extends Component {
	constructor(props) {
		super(props);
		this.state={
			username: "",
			users: [],
			types: [],
			toAdd: {
				fullName: "",
				username: "",
				password: "",
				dob: "",
				nationality: "",
				height: "",
				weight: ""
			},
			toAddType: "",
			tournament: "",
		};
	}

	search(username) {
		fetch('http://cs5200-fall2018-bmf.us-east-2.elasticbeanstalk.com/api/search/user?username=' + username, {
		  method: 'GET',
		}).then((response) => {
			return response.json().catch((error) => {
					alert('Incorrect User Name');
	     });
		}).then((data) => {
			if (data) {
				let temp = [];
				temp.push(data);
				this.setState({
					users: temp
				});
			}
		});
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

	listAll() {
		fetch('http://cs5200-fall2018-bmf.us-east-2.elasticbeanstalk.com/api/search/users', {
		  method: 'GET',
		}).then((response) => {
			return response.json();
		}).then((data) => {
			console.log(data);
			this.setState({
				users: data
			});
		}).catch((error) => {
        	console.error(error);
      	});
	}

	checkType(i) {
		fetch('http://cs5200-fall2018-bmf.us-east-2.elasticbeanstalk.com/api/check-user-type/' + this.state.users[i].id, {
		  method: 'GET',
		}).then((response) => {
			return response.text();
		}).then((data) => {
			console.log(data);
			this.setState((state, props) => {
	      		let temp = state.types;
	      		temp[i] = data;
	      		return ({
	      			types: temp,
	      		});
	      	});
		}).catch((error) => {
        	console.error(error);
      	});
	}

	update(i) {
			fetch('http://cs5200-fall2018-bmf.us-east-2.elasticbeanstalk.com/api/update/player', {
			  method: 'POST',
			  headers: {
			    Accept: 'application/json',
			    'Content-Type': 'application/json',
			  },
			  body: JSON.stringify(
			    this.state.users[i]
			  ),
			}).then((response) => {
				if (response.ok) this.listAll();
			}).catch((error) => {
	        	console.error(error);
      });
	}

	delete(i) {
		fetch('http://cs5200-fall2018-bmf.us-east-2.elasticbeanstalk.com/api/remove/user/' + this.state.users[i].id, {
			  method: 'GET',
			}).then((response) => {
				if (response.ok) this.listAll();
			}).catch((error) => {
	        	console.error(error);
      	})
	}

	create() {
		const url = 'http://cs5200-fall2018-bmf.us-east-2.elasticbeanstalk.com/api/register/' + this.state.toAddType;
		fetch(url, {
		  method: 'POST',
		  headers: {
		    Accept: 'application/json',
		    'Content-Type': 'application/json',
		  },
		  body: JSON.stringify(
		    this.state.toAdd
		  ),
		}).then((response) => {
			return response.json();
		}).then((data) => {
			this.setState({
				toAdd: {
					fullName: "",
					username: "",
					password: "",
					dob: "",
					nationality: "",
					height: "",
					weight: ""
				},
				toAddType: "",
			})
      this.listAll();
		}).catch((error) => {
      console.error(error);
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
				<div style={{
					marginTop: 30
				}}>
				<input className='profile-input'
		        	placeholder="User Name"
		        	type="text"
		        	value={this.state.username}
		        	onChange={(e) => {
		        		this.setState({username: e.target.value})
		        	}}
		        />
				<Button bsStyle="primary" bsSize="large"
		        	onClick={() => {
		        		this.search(this.state.username);
		        	}}>
		        	Search
		        </Button>

		        <Button bsStyle="primary" bsSize="large"
		        	onClick={() => {
		        		this.listAll();
		        	}}>
		        	List All Users
		        </Button>
				<Table responsive>
				  <thead>
				    <tr>
				      <th>Id</th>
				      <th>Name</th>
				      <th>UserName</th>
				      <th>Password</th>
				      <th>Date of Birth</th>
				      <th>Nationality</th>
				      <th>Height</th>
				      <th>Weight</th>
				      <th>Type</th>
				      <th>Actions</th>

				    </tr>
				  </thead>
				  <tbody>
				  	{this.state.users.map((user, i) => {
				  		return (
				  			<tr key={i}>
						      <td>{user.id}</td>
						      <td>
						      	<input
											className="table-input"
						      		value={this.state.users[i].fullName}
						      		onChange={(e) => {
							      	let value = e.target.value;
							      	this.setState((state, props) => {
							      		let temp = state.users;
							      		temp[i].fullName = value;
							      		return ({
							      			users: temp,
							      		});
							      	});
							      }}/>
						      </td>
						      <td>
						      	<input
											className="table-input"
						      		value={this.state.users[i].username}
						      		onChange={(e) => {
							      	let value = e.target.value;
							      	this.setState((state, props) => {
							      		let temp = state.users;
							      		temp[i].username = value;
							      		return ({
							      			users: temp,
							      		});
							      	});
							      }}/>
							  </td>
							  <td>
						      	<input
											className="table-input"
											value={this.state.users[i].password}
						      		onChange={(e) => {
								      	let value = e.target.value;
								      	this.setState((state, props) => {
								      		let temp = state.users;
								      		temp[i].password = value;
								      		return ({
								      			users: temp,
								      		});
								     });
							      }}/>
							  </td>
							  <td>
						      	<input
											className="table-input"
											value={this.state.users[i].dob}
						      		onChange={(e) => {
							      	let value = e.target.value;
							      	this.setState((state, props) => {
							      		let temp = state.users;
							      		temp[i].dob = value;
							      		return ({
							      			users: temp,
							      		});
							      	});
							      }}/>
							  </td>
							  <td>
						      	<input
											className="table-input"
											value={this.state.users[i].nationality}
						      		onChange={(e) => {
							      	let value = e.target.value;
							      	this.setState((state, props) => {
							      		let temp = state.users;
							      		temp[i].nationality = value;
							      		return ({
							      			users: temp,
							      		});
							      	});
							      }}/>
							  </td>
							  <td>
						      	<input
											className="table-input"
											value={this.state.users[i].height}
						      		onChange={(e) => {
							      	let value = e.target.value;
							      	this.setState((state, props) => {
							      		let temp = state.users;
							      		temp[i].height = value;
							      		return ({
							      			users: temp,
							      		});
							      	});
							      }}/>
							  </td>
							  <td>
						      	<input
											className="table-input"
											value={this.state.users[i].weight}
						      		onChange={(e) => {
							      	let value = e.target.value;
							      	this.setState((state, props) => {
							      		let temp = state.users;
							      		temp[i].weight = value;
							      		return ({
							      			users: temp,
							      		});
							      	});
							      }}/>
							  </td>
							  <td>
						      	<input
											className="table-input"
											value={this.state.types[i]} onChange={(e) => {
							      	let value = e.target.value;
							      	this.setState((state, props) => {
							      		let temp = state.types;
							      		temp[i] = value;
							      		return ({
							      			types: temp,
							      		});
							      	});
							      }}/>
							  </td>
						   	  <td>
										<Button bsStyle="primary" bsSize="large"
						        	onClick={() => {
						        		this.update(i);
						        	}}>
						        	Update
						        </Button>
						        <Button bsStyle="primary" bsSize="large"
						        	onClick={() => {
						        		this.delete(i);
						        	}}>
						        	Delete
						        </Button>
		        			  </td>
						    </tr>
				  		);
				  	})}

						<tr>
							<td>{null}</td>
							<td>
								<input
									className="table-input"
									value={this.state.toAdd.fullName}
									onChange={(e) => {
									let value = e.target.value;
									this.setState((state, props) => {
										let temp = state.toAdd;
										temp.fullName = value;
										return ({
											toAdd: temp,
										});
									});
								}}/>
							</td>
							<td>
								<input
									className="table-input"
									value={this.state.toAdd.username}
									onChange={(e) => {
									let value = e.target.value;
									this.setState((state, props) => {
										let temp = state.toAdd;
										temp.username = value;
										return ({
											toAdd: temp,
										});
									});
								}}/>
						</td>
						<td>
								<input
									className="table-input"
									value={this.state.toAdd.password}
									onChange={(e) => {
										let value = e.target.value;
										this.setState((state, props) => {
											let temp = state.toAdd;
											temp.password = value;
											return ({
												toAdd: temp,
											});
								 });
								}}/>
						</td>
						<td>
								<input
									className="table-input"
									value={this.state.toAdd.dob}
									onChange={(e) => {
									let value = e.target.value;
									this.setState((state, props) => {
										let temp = state.toAdd;
										temp.dob = value;
										return ({
											toAdd: temp,
										});
									});
								}}/>
						</td>
						<td>
								<input
									className="table-input"
									value={this.state.toAdd.nationality}
									onChange={(e) => {
									let value = e.target.value;
									this.setState((state, props) => {
										let temp = state.users;
										temp.nationality = value;
										return ({
											toAdd: temp,
										});
									});
								}}/>
						</td>
						<td>
								<input
									className="table-input"
									value={this.state.toAdd.height}
									onChange={(e) => {
									let value = e.target.value;
									this.setState((state, props) => {
										let temp = state.users;
										temp.height = value;
										return ({
											toAdd: temp,
										});
									});
								}}/>
						</td>
						<td>
								<input
									className="table-input"
									value={this.state.toAdd.weight}
									onChange={(e) => {
									let value = e.target.value;
									this.setState((state, props) => {
										let temp = state.users;
										temp.weight = value;
										return ({
											toAdd: temp,
										});
									});
								}}/>
						</td>
						<td>
								<input
									className="table-input"
									value={this.state.toAddType}
									onChange={(e) => {
									this.setState({
										toAddType: e.target.value
									});
								}}/>
						</td>
							<td>
								<Button bsStyle="primary" bsSize="large"
									onClick={() => {
										this.create();
									}}>
									Create
								</Button>
								</td>
						</tr>

				  </tbody>
				</Table>;
		        </div>
			</div>
		);
	}
}

export default Admin;
