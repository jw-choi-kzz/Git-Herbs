import axios from "axios";

const BASE_URL = "https://j10a205.p.ssafy.io/api/v1/";

const apiInstance = axios.create({
  baseURL: `${BASE_URL}`,
  headers: {
    'Content-Type': 'application/json',
  },
});

export { apiInstance };
