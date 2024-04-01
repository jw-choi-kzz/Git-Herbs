import axios from "axios";

const BASE_URL = "https://j10a205.p.ssafy.io/api/v1/";

const apiInstance = axios.create({
  baseURL: `${BASE_URL}`,
  headers: {
    'Content-Type': 'application/json',
  },
});

// const SampleApi = {
//   login: async (userData) => {
//     try {
//       const response = await apiClient.post("/login", userData);
//       return response.data;
//     } catch (error) {
//       console.error(error);
//     }
//   },
// };

export { apiInstance };
