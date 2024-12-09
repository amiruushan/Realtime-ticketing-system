import React, { Component } from "react";
// import "./PurchaseTicketsStyle.css";

class PurchaseTickets extends Component {
  state = {
    ticketsPerPurchase: 0, // Input field value
    successMessage: "",
    errorMessage: "",
    totalTickets: 0, // To display the total number of available tickets
  };

  componentDidMount() {
    this.fetchTotalTickets();

    // Start polling every 2 seconds (updating the total no of tickets in every 2 seconds)
    this.pollingInterval = setInterval(this.fetchTotalTickets, 2000);
  }

  componentWillUnmount() {
    clearInterval(this.pollingInterval);
  }

  fetchTotalTickets = async () => {
    try {
      const response = await fetch("/api/ticketpool/totaltickets");
      if (response.ok) {
        const totalTickets = await response.json();
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

  handlePurchase = async () => {
    const { ticketsPerPurchase } = this.state;

    try {
      const response = await fetch(`/api/customer/purchase_tickets?ticketsPerPurchase=${ticketsPerPurchase}`, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
      });

      if (response.ok) {
        this.setState({
          successMessage: "Tickets purchased successfully!",
          errorMessage: "",
        });
        this.fetchTotalTickets(); // Update the total tickets
      } else {
        const errorMessage = await response.text();
        this.setState({
          errorMessage: errorMessage || "Failed to purchase tickets.",
          successMessage: "",
        });
      }
    } catch (error) {
      console.error("Error purchasing tickets:", error);
      this.setState({
        errorMessage: "An error occurred. Please try again later.",
        successMessage: "",
      });
    }
  };

  render() {
    const { ticketsPerPurchase, successMessage, errorMessage, totalTickets } = this.state;

    return (
      <div className="purchase-tickets-container">
        <h2 id="pt-h2">Purchase Tickets</h2>
        <div>
          <label>Tickets to Purchase:</label>
          <input
            type="number"
            name="ticketsPerPurchase"
            value={ticketsPerPurchase}
            onChange={this.handleInputChange}
          />
        </div>
        <button onClick={this.handlePurchase}>Purchase Tickets</button>

        {/* Display success or error messages */}
        {errorMessage && <p style={{ color: "red" }}>{errorMessage}</p>}
        {successMessage && <p style={{ color: "green" }}>{successMessage}</p>}

        {/* Display total number of tickets */}
        <p>Total Tickets Available: {totalTickets}</p>
      </div>
    );
  }
}

export default PurchaseTickets;
