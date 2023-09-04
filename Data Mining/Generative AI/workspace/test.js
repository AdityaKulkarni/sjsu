const { convertTemperature } = require('./utils');

// Test case for convertTemperature function
test('convertTemperature should convert Celsius to Fahrenheit correctly', () => {
  // Arrange
  const celsius = 25;
  const expectedFahrenheit = 77;

  // Act
  const actualFahrenheit = convertTemperature(celsius);

  // Assert
  expect(actualFahrenheit).toBe(expectedFahrenheit);
});