import React, { Component } from 'react';
import './UserTypeStyle.css';
class UserTypeSelection extends Component {

  handleVendorClick = () => {
    // Redirect to the vendor login page using window.location
    window.location.href = '/vendor-login';
  };

  handleCustomerClick = () => {
    window.location.href = '/customer-login';
  };

  render() {
    return (
      <div className="user-type-selection-container">
        <h2 className="user-h2">Select Your User Type</h2>
        <div className="button-group">
          <button className="user-type-button" onClick={this.handleVendorClick}>
            Vendor
          </button>
          <button className="user-type-button" onClick={this.handleCustomerClick}>
            Customer
          </button>
        </div>
      </div>
    );
  }
}

export default UserTypeSelection;
