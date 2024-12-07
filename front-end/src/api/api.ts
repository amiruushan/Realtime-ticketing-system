import axios from 'axios';

const API_URL = "http://localhost:8080/api"; // Backend base URL

export const saveConfiguration = async (data: any) => {
    return axios.post(`${API_URL}/save`, data);
};

