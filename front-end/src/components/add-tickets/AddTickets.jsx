import React, { Component } from "react";

class AddTickets extends Component {
  state = {
    ticketPerRelease: 0,
    successMessage: "",
    errorMessage: "",
    totalTickets: 0, // State to hold the total number of tickets
  };

  componentDidMount() {
    this.fetchTotalTickets();

    // Start polling every 5 seconds (5000ms)
    this.pollingInterval = setInterval(this.fetchTotalTickets, 2000);
  }

  componentWillUnmount() {
    // Clear the interval to prevent memory leaks
    clearInterval(this.pollingInterval);
  }

  fetchTotalTickets = async () => {
    try {
      const response = await fetch("/api/ticketpool/totaltickets");

      if (response.ok) {
        const totalTickets = await response.json(); // Get the total tickets from the response
        this.setState({ totalTickets });
      } else {
        console.error("Failed to fetch total tickets");
      }
    } catch (error) {
      console.error("Error fetching total tickets:", error);
    }
  };

  handleInputChange = (event) => {
    const { name, value } = event.target;
    this.setState({ [name]: value });
  };

  handleAddTicket = async () => {
    const { ticketPerRelease } = this.state;

    try {
      const response = await fetch(`/api/vendor/addticket?ticketPerRelease=${ticketPerRelease}`, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
      });

      if (response.ok) {
        this.setState({
          successMessage: "Ticket added successfully!",
          errorMessage: "",
        });
        this.fetchTotalTickets(); // Update immediately after adding tickets
      } else {
        const errorMessage = await response.text();
        this.setState({
          errorMessage: errorMessage || "Failed to add tickets.",
          successMessage: "",
        });
      }
    } catch (error) {
      console.error("Error adding tickets:", error);
      this.setState({
        errorMessage: "An error occurred. Please try again later.",
        successMessage: "",
      });
    }
  };

  render() {
    const { ticketPerRelease, successMessage, errorMessage, totalTickets } = this.state;

    return (
      <div className="add-tickets-container">
        <h2>Add Tickets</h2>
        <div>
          <label>Tickets Per Release:</label>
          <input
            type="number"
            name="ticketPerRelease"
            value={ticketPerRelease}
            onChange={this.handleInputChange}
          />
        </div>
        <button onClick={this.handleAddTicket}>Add Tickets</button>

        {/* Display success or error messages */}
        {errorMessage && <p style={{ color: "red" }}>{errorMessage}</p>}
        {successMessage && <p style={{ color: "green" }}>{successMessage}</p>}

        {/* Display total number of tickets */}
        <p>Total Number of Tickets: {totalTickets}</p>
      </div>
    );
  }
}

export default AddTickets;
