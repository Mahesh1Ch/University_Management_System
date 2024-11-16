import React, { useState } from 'react';
import RegistrationForm from './RegistrationForm';
import './Header.css'; // Importing CSS file for styling

const Header = () => {
  // State to toggle visibility of the RegistrationForm
  const [showRegistrationForm, setShowRegistrationForm] = useState(false);

  // Handle the button click to show/hide the RegistrationForm
  const toggleRegistrationForm = () => {
    setShowRegistrationForm(!showRegistrationForm);
  };

  return (
    <header id="header-container" className="header-container">
      <div className="header-content">
        <button className="registration-button" onClick={toggleRegistrationForm}>
          Registration
        </button>
        
        {/* Conditionally render RegistrationForm */}
        {showRegistrationForm && <RegistrationForm />}
      </div>
    </header>
  );
};

export default Header;
