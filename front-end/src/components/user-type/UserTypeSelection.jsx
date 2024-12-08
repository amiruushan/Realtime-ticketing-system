import React, { Component } from 'react';

class UserTypeSelection extends Component {

  handleVendorClick = () => {
    // Redirect to the vendor login page using window.location
    window.location.href = '/vendor-login';
  };

  handleCustomerClick = () => {
    alert('Customer selected!');
  };

  render() {
    return (
      <div className="user-type-selection-container">
        <h2>Select Your User Type</h2>
        <div className="button-group">
          <button className="button" onClick={this.handleVendorClick}>
            Vendor
          </button>
          <button className="button" onClick={this.handleCustomerClick}>
            Customer
          </button>
        </div>
      </div>
    );
  }
}

export default UserTypeSelection;
