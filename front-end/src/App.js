import React from 'react';
import './App.css';
import ConfigurationForm from './/components/Configuration/ConfigurationForm'; // Correct the import here

const App = () => {
  return (
    <div className="App">
      <h1>Welcome to the Real time Ticketing System</h1>
      <ConfigurationForm />
    </div>
  );
};

export default App;
