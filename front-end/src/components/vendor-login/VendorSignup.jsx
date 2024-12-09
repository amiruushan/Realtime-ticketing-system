import React, { Component } from "react";
import "./VendorSignupStyle.css";

class VendorSignup extends Component {
  state = {
    vendorName: "",
    vendorEmail: "",
    vendorPassword: "",
    errorMessage: "",
    successMessage: "",
  };

  handleInputChange = (event) => {
    const { name, value } = event.target;
    this.setState({ [name]: value });
  };

  handleSignup = async (event) => {
    event.preventDefault();
    const { vendorName, vendorEmail, vendorPassword } = this.state;

    try {
      const response = await fetch("/api/vendor/signup", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({ vendorName, vendorEmail, vendorPassword }),
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
    const { vendorName, vendorEmail, vendorPassword, errorMessage, successMessage } = this.state;

    return (
      <div className="vendor-signup-container">
        <h2 className='vs-h2'>Vendor Sign Up</h2>
        <form onSubmit={this.handleSignup}>
          <div>
            <label className='vs-label'>Name:</label>
            <input
              className='vs-input'
              type="text"
              name="vendorName"
              value={vendorName}
              onChange={this.handleInputChange}
              required
            />
          </div>
          <div>
            <label className='vs-label'>Email:</label>
            <input
              className='vs-input'
              type="email"
              name="vendorEmail"
              value={vendorEmail}
              onChange={this.handleInputChange}
              required
            />
          </div>
          <div>
            <label className='vs-label'>Password:</label>
            <input
              className='vs-input'
              type="password"
              name="vendorPassword"
              value={vendorPassword}
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

export default VendorSignup;
