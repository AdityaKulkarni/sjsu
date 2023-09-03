import React, { useState } from 'react';
import Input from './Input';
import Button from './Button';
import ApiService from './ApiService';

function Weather() {
  const [city, setCity] = useState('');
  const [weatherData, setWeatherData] = useState(null);

  const handleCityChange = (event) => {
    setCity(event.target.value);
  };

  const handleGetWeather = async () => {
    const data = await ApiService.getWeather(city);
    setWeatherData(data);
  };

  return (
    <div className="Weather">
      <Input value={city} onChange={handleCityChange} />
      <Button onClick={handleGetWeather} />
      {weatherData && (
        <div>
          <h2>Weather Information</h2>
          <p>Temperature: {weatherData.current.temp_c}°C</p>
          <p>Condition: {weatherData.current.condition.text}</p>
          <p>Humidity: {weatherData.current.humidity}%</p>
        </div>
      )}
    </div>
  );
}

export default Weather;
