import React from 'react';

function Input({ value, onChange }) {
  return (
    <input
      type="text"
      placeholder="Enter city name"
      value={value}
      onChange={onChange}
    />
  );
}

export default Input;
