import axios from 'axios';

const baseURL =
  process.env.REACT_APP_API_URL ||
  process.env.API_URL;

const apiClient = axios.create({
  baseURL,
  timeout: 5000,
  headers: {'Content-Type': 'application/json'},
});

export default apiClient;
