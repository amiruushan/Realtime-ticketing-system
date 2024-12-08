import React, { Component } from 'react';

class VendorLogin extends Component {
  state = {
    email: '',
    password: '',
  };

  handleInputChange = (event) => {
    const { name, value } = event.target;
    this.setState({ [name]: value });
  };

  handleLogin = async (event) => {
    event.preventDefault();
    const { email, password } = this.state;
  
    try {
      const response = await fetch("/api/vendor/login?email=" + email + "&password=" + password, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
      });
  
      if (response.ok) {
        alert("Login successful!");
        this.props.onLogin(); // Notify App.js about successful login
      } else {
        alert("Login failed! Check your credentials.");
      }
    } catch (error) {
      console.error("Error during login:", error);
      alert("Login failed. Please try again.");
    }
  };
  

  render() {
    console.log('Rendering VendorLogin...');
    return (
      <div className="vendor-login-container">
        <h2>Vendor Sign in</h2>

        <form onSubmit={this.handleLogin}>
          <div>
            <label>Email:</label>
            <input
              type="email"
              name="email"
              value={this.state.email}
              onChange={this.handleInputChange}
              required
            />
          </div>
          <div>
            <label>Password:</label>
            <input
              type="password"
              name="password"
              value={this.state.password}
              onChange={this.handleInputChange}
              required
            />
          </div>
          <button type="submit">Login</button>
        </form>
        <h3>
          Don't have an account?{' '}
          <span
            style={{ color: 'red', textDecoration: 'underline', cursor: 'pointer' }}
            onClick={() => (window.location.href = '/vendor-signup')}
          >
            Sign up
          </span>
        </h3>
      </div>
    );
  }
}

export default VendorLogin;
