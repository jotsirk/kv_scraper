import apiClient from '../apiClient';

export const getAllTicks = () => apiClient.get('/property‐ticks/all-active').then((response) => response.data);
export const createTick = data => apiClient.post('/property‐ticks', data);
export const archiveTick = (tickId: number) => apiClient.patch(`/property‐ticks`, tickId);
