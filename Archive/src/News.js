import React, { Component } from 'react';
import { Table } from 'react-bootstrap';

class News extends Component {
	render() {
    return (
			<div>
        <Table responsive>
				  <thead>
				    <tr>
				      <th>News</th>
				    </tr>
				  </thead>
          <tbody>
          {this.props.profile.map((news, i) => {
            return (
                <tr key={i}>
    				      <td>{news.fact}</td>
    				    </tr>
            );
          })}
        </tbody>
        </Table>
			</div>
		);
	}
}

export default News;
