// import axios from "axios";

// const instance = axios.create({
//     baseURL: import.meta.env.VITE_API_ROOT,
//     header: {
//         "Access-Control-Allow-Origin": "",
//         "Access-Control-Allow-Credentials": "true",
//         "Access-Control-Allow-Methods": "GET, POST, OPTIONS, PUT, PATCH, DELETE",
//     },
// // });

// const refreshInstance = axios.create({
//     baseURL: import.meta.env.VITE_API_ROOT,
//   });
  
//   const regenerateRefreshToken = async () => {
//     const res = await refreshInstance.post("/login/oauth/token", {
//       refreshToken: localStorage.getItem("refreshToken"),
//     });
  
//     return res;
//   };
  
//   instance.interceptors.request.use(
//     (config) => {
//       config.headers["Authorization"] = `Bearer ${localStorage.getItem("accessToken")}`;
//       return config;
//     },
//     (error) => {
//       console.log(error);
//       return Promise.reject(error);
//     }
//   );
  
//   instance.interceptors.response.use(
//     (response) => {
//       if (response.status == 404) {
//         console.log("404 페이지로 넘기기");
//       }
  
//       return response;
//     },
//     async (error) => {
//       if (error.response?.status == 401) {
//         const originRequest = error.config;
  
//         try {
//           const res = await regenerateRefreshToken();
  
//           if (res.status == 200) {
//             const newAccessToken = res.data.token;
//             localStorage.setItem("accessToken", res.data.accessToken);
//             localStorage.setItem("refreshToken", res.data.refreshToken);
//             axios.defaults.headers.common["Authorization"] = `Bearer ${newAccessToken}`;
//             return axios(originRequest);
//           }
//         } catch (error) {
//           alert("로그아웃 되었습니다. 다시 로그인해주세요.");
//           localStorage.removeItem("userId");
//           localStorage.removeItem("accessToken");
//           localStorage.removeItem("refreshToken");
//           window.location.replace("/loginpage");
//         }
//       }
  
//       return Promise.reject(error);
//     }
//   );
  
//   export default instance;
  