import React, { Component } from 'react';

class ConfigurationForm extends Component {
  state = {
    configuration: {
      maxTicketCapacity: 0,
      ticketReleaseRate: 0,
      customerRetrievalRate: 0,
      totalTickets: 0,
    },
  };

  handleInputChange = (event) => {
    const { name, value } = event.target;
    this.setState((prevState) => ({
      configuration: {
        ...prevState.configuration,
        [name]: value === '' ? '' : Number(value), // Allow empty string for typing, but convert to number when not empty
      },
    }));
  };
  

  saveConfiguration = async () => {
    try {
      const response = await fetch('/api/configuration/save', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(this.state.configuration),
      });

      if (response.ok) {
        alert('Configuration saved successfully!');
      } else {
        const errorMessage = await response.text();
        throw new Error(errorMessage || 'Failed to save configuration');
      }
    } catch (error) {
      console.error('Error saving configuration:', error);
      alert('Failed to save configuration.');
    }
  };

  loadConfiguration = async () => {
    try {
      const response = await fetch('/api/configuration/load', {
        method: 'GET',
        headers: {
          'Content-Type': 'application/json',
        },
      });

      if (response.ok) {
        const data = await response.json();
        this.setState({ configuration: data });
        alert('Configuration loaded successfully!');
      } else {
        const errorMessage = await response.text();
        throw new Error(errorMessage || 'Failed to load configuration');
      }
    } catch (error) {
      console.error('Error loading configuration:', error);
      alert('Failed to load configuration.');
    }
  };

  render() {
    const { maxTicketCapacity, ticketReleaseRate, customerRetrievalRate, totalTickets } = this.state.configuration;

    return (
      <div>
        <h2>Configuration</h2>
        <form>
          <div>
            <label>
              Max Number of Tickets:
              <input
                type="number"
                name="maxTicketCapacity"
                value={maxTicketCapacity}
                onChange={this.handleInputChange}
              />
            </label>
          </div>
          <div>
            <label>
              Ticket Release Rate:
              <input
                type="number"
                name="ticketReleaseRate"
                value={ticketReleaseRate}
                onChange={this.handleInputChange}
              />
            </label>
          </div>
          <div>
            <label>
              Customer Retrieval Rate:
              <input
                type="number"
                name="customerRetrievalRate"
                value={customerRetrievalRate}
                onChange={this.handleInputChange}
              />
            </label>
          </div>
          <div>
            <label>
              Total Number of Tickets:
              <input
                type="number"
                name="totalTickets"
                value={totalTickets}
                onChange={this.handleInputChange}
              />
            </label>
          </div>
        </form>
        <button onClick={this.saveConfiguration}>Save Configuration</button>
        <button onClick={this.loadConfiguration}>Load Configuration</button>
      </div>
    );
  }
}

// Ensure that you export the class as a default export
export default ConfigurationForm;
