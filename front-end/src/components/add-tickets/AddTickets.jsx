import React, { Component } from "react";
import "./AddTicketStyle.css";

class AddTickets extends Component {
  state = {
    ticketPerRelease: 0,
    successMessage: "",
    errorMessage: "",
    totalTickets: 0, // State to hold the total number of tickets
    maxTickets: 0, // State to hold the maximum ticket capacity
  };

  componentDidMount() {
    this.fetchTotalTickets();
    this.fetchMaxTickets();

    // Start polling every 2 seconds (2000ms)
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

  fetchMaxTickets = async () => {
    try {
      const response = await fetch("/api/ticketpool/maxtickets");
      if (response.ok) {
        const maxTickets = await response.json(); // Get the maximum ticket capacity from the response
        this.setState({ maxTickets });
      } else {
        console.error("Failed to fetch maximum tickets");
      }
    } catch (error) {
      console.error("Error fetching maximum tickets:", error);
    }
  };

  handleInputChange = (event) => {
    const { name, value } = event.target;
    this.setState({ [name]: value });
  };

  handleAddTicket = async () => {
    const { ticketPerRelease, totalTickets, maxTickets } = this.state;

    // Validation for exceeding maximum ticket capacity
    if (parseInt(ticketPerRelease, 10) + totalTickets > maxTickets) {
      this.setState({
        errorMessage: "Exceed maximum ticket capacity.",
        successMessage: "",
      });
      return;
    }

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
    const { ticketPerRelease, successMessage, errorMessage, totalTickets, maxTickets } = this.state;

    return (
      <div className="add-tickets-container">
        <h2 id="at-h2">Add Tickets</h2>
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

        {/* Display total number of tickets and maximum ticket capacity */}
        <p>Total Number of Tickets: {totalTickets}</p>
        <p>Maximum Ticket Capacity: {maxTickets}</p>
      </div>
    );
  }
}

export default AddTickets;
