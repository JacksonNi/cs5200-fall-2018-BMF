import React, { Component } from 'react';
import { Button, Table } from 'react-bootstrap';

class Lineups extends Component {
	constructor(props) {
		super(props);
		this.state={
			toAdd: {
				substitude_in: "",
				substitude_out: "",
				goals_scored: "",
				assists: "",
				own_goals: "",
				yellow_cards: "",
				red_cards: "",
				position: "",
				player_id: ""
			},
		};
	}

	create() {
		const url = 'http://cs5200-fall2018-bmf.us-east-2.elasticbeanstalk.com/api/create/'
		+ this.props.home + '/' + this.props.homeId + '/lineup';
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
					substitude_in: "",
					substitude_out: "",
					goals_scored: "",
					assists: "",
					own_goals: "",
					yellow_cards: "",
					red_cards: "",
					position: "",
					player_id: ""
				},
			})
			this.props.getLineups();
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
				      <th>Substitude In</th>
              <th>Substitude Out</th>
              <th>Goals Scored</th>
              <th>Assists</th>
              <th>Own Goals</th>
              <th>Yellow Cards</th>
              <th>Red Cards</th>
              <th>Position</th>
              <th>Player ID</th>
				    </tr>
				  </thead>
          <tbody>
          {this.props.profile.map((player, i) => {
            return (
                <tr key={i}>
    				      <td>{player.substitude_in}</td>
    				      <td>{player.substitude_out}</td>
                  <td>{player.goals_scored}</td>
                  <td>{player.assists}</td>
                  <td>{player.own_goals}</td>
                  <td>{player.yellow_cards}</td>
                  <td>{player.red_cards}</td>
                  <td>{player.position}</td>
                  <td>{player.player_id}</td>
    				    </tr>
            );
          })}
					<tr>
						<td>
							<input className="table-input"
								value={this.state.toAdd.substitude_in}
								onChange={(e) => {
									let value = e.target.value;
									this.setState((state, props) => {
										let temp = state.toAdd;
										temp.substitude_in = value;
										return ({
											toAdd: temp,
										});
							 });
							}}/>
						</td>
						<td>
							<input className="table-input"
								value={this.state.toAdd.substitude_out}
								onChange={(e) => {
									let value = e.target.value;
									this.setState((state, props) => {
										let temp = state.toAdd;
										temp.substitude_out = value;
										return ({
											toAdd: temp,
										});
							 });
							}}/>
						</td>
						<td>
							<input className="table-input"
								value={this.state.toAdd.goals_scored}
								onChange={(e) => {
									let value = e.target.value;
									this.setState((state, props) => {
										let temp = state.toAdd;
										temp.goals_scored = value;
										return ({
											toAdd: temp,
										});
							 });
							}}/>
						</td>
						<td>
							<input className="table-input"
								value={this.state.toAdd.assists}
								onChange={(e) => {
									let value = e.target.value;
									this.setState((state, props) => {
										let temp = state.toAdd;
										temp.assists = value;
										return ({
											toAdd: temp,
										});
							 });
							}}/>
						</td>
						<td>
							<input className="table-input"
								value={this.state.toAdd.own_goals}
								onChange={(e) => {
									let value = e.target.value;
									this.setState((state, props) => {
										let temp = state.toAdd;
										temp.own_goals = value;
										return ({
											toAdd: temp,
										});
							 });
							}}/>
						</td>
						<td>
							<input className="table-input"
								value={this.state.toAdd.yellow_cards}
								onChange={(e) => {
									let value = e.target.value;
									this.setState((state, props) => {
										let temp = state.toAdd;
										temp.yellow_cards = value;
										return ({
											toAdd: temp,
										});
							 });
							}}/>
						</td>
						<td>
							<input className="table-input"
								value={this.state.toAdd.red_cards}
								onChange={(e) => {
									let value = e.target.value;
									this.setState((state, props) => {
										let temp = state.toAdd;
										temp.red_cards = value;
										return ({
											toAdd: temp,
										});
							 });
							}}/>
						</td>
						<td>
							<input className="table-input"
								value={this.state.toAdd.position}
								onChange={(e) => {
									let value = e.target.value;
									this.setState((state, props) => {
										let temp = state.toAdd;
										temp.position = value;
										return ({
											toAdd: temp,
										});
							 });
							}}/>
						</td>
						<td>
							<input className="table-input"
								value={this.state.toAdd.player_id}
								onChange={(e) => {
									let value = e.target.value;
									this.setState((state, props) => {
										let temp = state.toAdd;
										temp.player_id = value;
										return ({
											toAdd: temp,
										});
							 });
							}}/>
						</td>
					</tr>
          </tbody>
        </Table>
				<Button bsStyle="primary" bsSize="large"
					onClick={() => {
						this.create();
					}}>
					Create
				</Button>
			</div>
		);
	}
}

export default Lineups;
