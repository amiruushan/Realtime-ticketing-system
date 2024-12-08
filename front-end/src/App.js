import React, { Component } from 'react';
import './App.css';
import ConfigurationForm from './components/Configuration/ConfigurationForm';
import UserTypeSelection from './components/user-type/UserTypeSelection';
import VendorLogin from './components/vendor-login/VendorLogin';
import VendorSignup from './components/vendor-login/VendorSignup';

class App extends Component {
  constructor(props) {
    super(props);
    this.state = {
      currentPath: window.location.pathname, // Track the current path
    };
  }

  componentDidMount() {
    // Listen to changes in the URL
    window.addEventListener('popstate', this.updatePath);
  }

  componentWillUnmount() {
    // Clean up listener when the component unmounts
    window.removeEventListener('popstate', this.updatePath);
  }

  // Update the current path whenever the URL changes
  updatePath = () => {
    this.setState({ currentPath: window.location.pathname });
  };

  render() {
    const currentPath = window.location.pathname;
    console.log("Current Path:", currentPath);  // Debugging step
  
    return (
      <div className="App">
        <h1>Real-Time Ticketing System</h1>
  
        {/* Checking path to render correct component */}
        {currentPath === '/user-type-selection' ? (
          <UserTypeSelection />
        ) : currentPath === '/vendor-login' ? (
          <VendorLogin />
        ) : currentPath === '/vendor-signup' ? (
          <VendorSignup />
        ) : (
          <ConfigurationForm />
        )}
      </div>
    );
  }
  
}

export default App;
