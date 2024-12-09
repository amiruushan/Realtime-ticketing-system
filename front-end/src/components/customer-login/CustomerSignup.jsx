import React, { Component } from "react";
import "./CustomerSignupStyle.css";

class CustomerSignup extends Component {
  state = {
    customerName: "",
    customerEmail: "",
    customerPassword: "",
    errorMessage: "",
    successMessage: "",
  };

  handleInputChange = (event) => {
    const { name, value } = event.target;
    this.setState({ [name]: value });
  };

  handleSignup = async (event) => {
    event.preventDefault();
    const { customerName, customerEmail, customerPassword } = this.state;

    try {
      const response = await fetch("/api/customer/signup", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({ customerName, customerEmail, customerPassword }),
      });

      if (response.ok) {
        this.setState({
          successMessage: "Signup successful! Redirecting...",
          errorMessage: "",
        });
        this.props.onSignup(); // Trigger the callback to update the authentication state
      } else {
        const errorData = await response.json();
        this.setState({
          errorMessage: errorData.message || "Signup failed. Please try again.",
          successMessage: "",
        });
      }
    } catch (error) {
      this.setState({
        errorMessage: "An error occurred. Please try again later.",
        successMessage: "",
      });
      console.error("Error during signup:", error);
    }
  };

  render() {
    const {customerName, customerEmail, customerPassword, errorMessage, successMessage } = this.state;

    return (
      <div className="customer-signup-container">
        <h2 className='cs-h2'>Customer Sign Up</h2>
        <form onSubmit={this.handleSignup}>
          <div>
            <label className='vs-label'>Name:</label>
            <input
              className='cs-input'
              type="text"
              name="customerName"
              value={customerName}
              onChange={this.handleInputChange}
              required
            />
          </div>
          <div>
            <label className='cs-label'>Email:</label>
            <input
              className='cs-input'
              type="email"
              name="customerEmail"
              value={customerEmail}
              onChange={this.handleInputChange}
              required
            />
          </div>
          <div>
            <label className='cs-label'>Password:</label>
            <input
              className='cs-input'
              type="password"
              name="customerPassword"
              value={customerPassword}
              onChange={this.handleInputChange}
              required
            />
          </div>
          <button type="submit">Sign Up</button>
        </form>

        {errorMessage && <p style={{ color: "red" }}>{errorMessage}</p>}
        {successMessage && <p style={{ color: "green" }}>{successMessage}</p>}
      </div>
    );
  }
}

export default CustomerSignup;
