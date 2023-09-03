const API_KEY = '5172f3b8007346debe270917232908';

const ApiService = {
  getWeather: async (city) => {
    const url = `http://api.weatherapi.com/v1/current.json?key=${API_KEY}&q=${city}&aqi=no`;
    const response = await fetch(url);
    const data = await response.json();
    return data;
  },
};
export default ApiService;
