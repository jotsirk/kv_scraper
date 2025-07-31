import apiClient from '../apiClient';

export const getAllTicks = () => apiClient.get('/property‐ticks').then((response) => response.data);
export const createTick  = data => apiClient.post('/property‐ticks', data);
