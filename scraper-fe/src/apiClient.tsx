import axios from 'axios';

const baseURL = `http://${window.location.hostname}:8080/api`;

const apiClient = axios.create({
  baseURL,
  timeout: 5000,
  headers: {'Content-Type': 'application/json'},
});

export default apiClient;
