import React, { Component } from "react";
import "./App.css";
import ConfigurationForm from "./components/Configuration/ConfigurationForm";
import UserTypeSelection from "./components/user-type/UserTypeSelection";
import VendorLogin from "./components/vendor-login/VendorLogin";
import VendorSignup from "./components/vendor-login/VendorSignup";
import AddTickets from "./components/add-tickets/AddTickets";

class App extends Component {
  constructor(props) {
    super(props);
    this.state = {
      currentPath: window.location.pathname,
      isAuthenticated: false, // Track authentication status
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

  handleLogin = () => {
    this.setState({ isAuthenticated: true });
    this.navigateTo("/add-tickets");
  };

  handleSignup = () => {
    this.setState({ isAuthenticated: true });
    this.navigateTo("/add-tickets");
  };

  navigateTo = (path) => {
    this.setState({ currentPath: path });
    window.history.pushState({}, "", path);
  };

  render() {
    const { currentPath, isAuthenticated } = this.state;

    return (
      <div className="App">
        <h1>Real-Time Ticketing System</h1>

        {currentPath === "/user-type-selection" ? (
          <UserTypeSelection />
        ) : currentPath === "/vendor-login" ? (
          <VendorLogin onLogin={this.handleLogin} />
        ) : currentPath === "/vendor-signup" ? (
          <VendorSignup onSignup={this.handleSignup} />
        ) : currentPath === "/add-tickets" ? (
          isAuthenticated ? (
            <AddTickets />
          ) : (
            <div>
              <p style={{ color: "red" }}>You must log in or sign up first!</p>
              <button onClick={() => this.navigateTo("/vendor-login")}>Go to Login</button>
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
