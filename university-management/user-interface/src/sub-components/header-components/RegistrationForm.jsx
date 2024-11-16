import React, { useState } from 'react';
import './RegistrationForm.css';  // Import the CSS file

const RegistrationForm = () => {
  // State variables to store form values and OTP
  const [name, setName] = useState('');
  const [email, setEmail] = useState('');
  const [phone, setPhone] = useState('');
  const [otp, setOtp] = useState('');
  const [otpSent, setOtpSent] = useState(false);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState('');

  // Handle "Send OTP" button click
  const sendOtp = async () => {
    if (!phone) {
      alert('Please enter your phone number');
      return;
    }
    setLoading(true);
    setError('');

    try {
      const response = await fetch('http://localhost:8083/adminregistraion/initiate', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({ name, email, phone }),
      });
  
      // Check if the response status is OK (2xx)
      if (!response.ok) {
        throw new Error(`HTTP error! Status: ${response.status}`);
      }
  
      // Parse the JSON response
      const data = await response.json();
  
      if (data.status === 'success') {
        alert(data.message);  // OTP sent message
        setOtpSent(true);
      } else {
        alert('Error sending OTP. Please try again.');
      }
    } catch (error) {
      console.error("Error during registration:", error);
      setError('Network error. Please try again later.');
    } finally {
      setLoading(false);
    }
  };

  // Handle "Submit" button click
  const submitForm = async () => {
    if (!name || !email || !phone || !otp) {
      alert('Please fill all the fields and enter the OTP');
      return;
    }

    setLoading(true);
    setError('');

    try {
      // Make the API call to submit the registration details
      const url = `http://localhost:8083/adminregistraion/complete?otp=${otp}`;
      const response = await fetch(url, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({ name, email, phone }),
      });

      const data = await response.json();

      if (data.status === 'success') {
        alert('Registration successful!');
      } else {
        alert('Error during registration. Please try again.');
      }
    } catch (error) {
      setError('Network error. Please try again later.');
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="registration-container">
      <h2>Admin Registration</h2>
      {error && <p className="error-message">{error}</p>}
      
      <div>
        <input
          id="name"  // Adding id for styling or targeting
          type="text"
          value={name}
          onChange={(e) => setName(e.target.value)}
          placeholder="Enter your name"
        />
      </div>
      
      <div>
        <input
          id="email"  // Adding id for styling or targeting
          type="email"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
          placeholder="Enter your email"
        />
      </div>
      
      <div>
        <input
          id="phone"  // Adding id for styling or targeting
          type="text"
          value={phone}
          onChange={(e) => setPhone(e.target.value)}
          placeholder="Enter your phone number"
        />
      </div>

      <button
        id="sendOtpButton"  // Adding id for Send OTP button
        onClick={sendOtp}
        disabled={loading}
      >
        {loading ? 'Sending OTP...' : 'Send OTP'}
      </button>

      {otpSent && (
        <div>
          <label htmlFor="otp">OTP:</label>
          <input
            id="otp"  // Adding id for OTP input
            type="text"
            value={otp}
            onChange={(e) => setOtp(e.target.value)}
            placeholder="Enter the OTP"
          />
        </div>
      )}

      <button
        id="submitButton"
        onClick={submitForm}
        disabled={loading}
      >
        {loading ? 'Submitting...' : 'Submit'}
      </button>
    </div>
  );
};

export default RegistrationForm;
