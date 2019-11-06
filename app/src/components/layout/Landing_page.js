import React, {Component} from 'react';
import '../../App.css';

class Landing_page extends Component {
    state = {
        isLoading: true,
        clientDetails: null
    };

    // Get the requested data for device details from Java REST API
    async componentDidMount() {
        const response = await fetch('/client_response');
        const jsonBody = await response.json();
        this.setState({clientDetails: jsonBody, isLoading: false});
    }

    render() {
        const {clientDetails, isLoading} = this.state;

        // Show loading message whilst waiting for data
        if (isLoading) {
            return <p>Loading...</p>;
        }

        const content = clientDetails.map(details => (
            <table key={details.name} className="ClientDetails">
                <tbody>
                    <tr>
                        <td>Name:</td>
                        <td>{details.name}</td>
                    </tr>
                    <tr>
                        <td>Agent Version:</td>
                        <td>{details.agentVersion}</td>
                    </tr>
                    <tr>
                        <td>How Many Alerts:</td>
                        <td>{details.howManyAlerts}</td>
                    </tr>
                    <tr>
                        <td>Architecture:</td>
                        <td>{details.architecture}</td>
                    </tr>
                    <tr>
                        <td>Collector:</td>
                        <td>{details.collector}</td>
                    </tr>
                    <tr>
                        <td>CPU Model:</td>
                        <td>{details.cpuModel}</td>
                    </tr>
                    <tr>
                        <td>Description:</td>
                        <td>{details.description}</td>
                    </tr>
                    <tr>
                        <td>Discovery Date:</td>
                        <td>{details.discoveryDate}</td>
                    </tr>
                    <tr>
                        <td>IP Addresses:</td>
                        <td>{details.ipAddresses[0]}, {details.ipAddresses[1]}</td>
                    </tr>
                </tbody>
            </table>

        ));

        // When data has been returned display data in table.
        return (
            <div className="Landing_page-container">
                <div className="Landing_page-body">
                    <h2>Client Device Details</h2>
                    {content}
                </div>
            </div>
        );
    }
}

export default Landing_page;