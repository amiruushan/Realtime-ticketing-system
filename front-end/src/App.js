import React, { Component } from "react";
import "./App.css";
import ConfigurationForm from "./components/Configuration/ConfigurationForm";
import UserTypeSelection from "./components/user-type/UserTypeSelection";
import VendorLogin from "./components/vendor-login/VendorLogin";
import VendorSignup from "./components/vendor-login/VendorSignup";
import AddTickets from "./components/add-tickets/AddTickets";
import CustomerLogin from "./components/customer-login/CustomerLogin";
import CustomerSignup from "./components/customer-login/CustomerSignup";
import PurchaseTickets from "./components/purchase-tickets/PurchaseTickets";

class App extends Component {
  constructor(props) {
    super(props);
    this.state = {
      currentPath: window.location.pathname,
      isAuthenticatedVendor: false, // Track vendor authentication
      isAuthenticatedCustomer: false, // Track customer authentication
    };
  }

  componentDidMount() {
    window.addEventListener("popstate", this.updatePath);
  }

  componentWillUnmount() {
    window.removeEventListener("popstate", this.updatePath);
  }

  updatePath = () => {
    this.setState({ currentPath: window.location.pathname });
  };

  handleLoginVendor = () => {
    this.setState({ isAuthenticatedVendor: true });
    this.navigateTo("/add-tickets");
  };

  handleSignupVendor = () => {
    this.setState({ isAuthenticatedVendor: true });
    this.navigateTo("/purchase-tickets");
  };

  handleLoginCustomer = () => {
    this.setState({ isAuthenticatedCustomer: true });
    this.navigateTo("/purchase-tickets");
  };

  handleSignupCustomer = () => {
    this.setState({ isAuthenticatedCustomer: true });
    this.navigateTo("/purchase-tickets");
  };

  navigateTo = (path) => {
    this.setState({ currentPath: path });
    window.history.pushState({}, "", path);
  };

  render() {
    const { currentPath, isAuthenticatedVendor, isAuthenticatedCustomer } = this.state;

    return (
      <div className="App">
        <h1>Real-Time Ticketing System</h1>

        {currentPath === "/user-type-selection" ? (
          <UserTypeSelection />
        ) : currentPath === "/vendor-login" ? (
          <VendorLogin onLogin={this.handleLoginVendor} />
        ) : currentPath === "/customer-login" ? (
          <CustomerLogin onLogin={this.handleLoginCustomer} />
        ) : currentPath === "/vendor-signup" ? (
          <VendorSignup onSignup={this.handleSignupVendor} />
        ) : currentPath === "/customer-signup" ? (
          <CustomerSignup onSignup={this.handleSignupCustomer} />
        ) : currentPath === "/add-tickets" ? (
          isAuthenticatedVendor ? (
            <AddTickets />
          ) : (
            <div>
              <p style={{ color: "red" }}>You must log in or sign up first!</p>
              <button onClick={() => this.navigateTo("/vendor-login")}>
                Go to Vendor Login
              </button>
            </div>
          )
        ) : currentPath === "/purchase-tickets" ? (
          isAuthenticatedCustomer ? (
            <PurchaseTickets />
          ) : (
            <div>
              <p style={{ color: "red" }}>You must log in or sign up first!</p>
              <button onClick={() => this.navigateTo("/customer-login")}>
                Go to Customer Login
              </button>
            </div>
          )
        ) : (
          <ConfigurationForm />
        )}
      </div>
    );
  }
}

export default App;
