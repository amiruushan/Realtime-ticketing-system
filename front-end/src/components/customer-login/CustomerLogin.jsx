import React, { Component } from 'react';
// import './CustomerLoginStyle.css'; // Add styles specific to customer login if needed

class CustomerLogin extends Component {
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
      const response = await fetch(`/api/customer/login?email=${email}&password=${password}`, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
      });

      if (response.ok) {
        const result = await response.text(); // Assuming the backend sends text as response
        alert(`Login successful! ${result}`);
        this.props.onLogin(); // Notify parent component of successful login
      } else {
        alert('Login failed! Check your credentials.');
      }
    } catch (error) {
      console.error('Error during login:', error);
      alert('Login failed. Please try again.');
    }
  };

  render() {
    return (
      <div className="customer-login-container">
        <h2 className="cl-h2">Customer Sign in</h2>

        <form onSubmit={this.handleLogin}>
          <div>
            <label className="cl-email">Email:</label>
            <input
              type="email"
              name="email"
              value={this.state.email}
              onChange={this.handleInputChange}
              required
            />
          </div>
          <div>
            <label className="cl-password">Password:</label>
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
            onClick={() => (window.location.href = '/customer-signup')} // Update with the actual signup route
          >
            Sign up
          </span>
        </h3>
      </div>
    );
  }
}

export default CustomerLogin;
