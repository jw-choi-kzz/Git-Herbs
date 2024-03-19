import axios from "axios";

const BASE_URL = "";

const apiClient = axios.create({
  baseURL: BASE_URL,
});

const SampleApi = {
  login: async (userData) => {
    try {
      const response = await apiClient.post("/login", userData);
      return response.data;
    } catch (error) {
      console.error(error);
    }
  },
};

export { SampleApi };
