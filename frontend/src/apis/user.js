import { apiInstance } from "../utils/Sample-axios";


const axios = apiInstance;


export const userServcie = {


    getBoard : (config) =>{
        return axios.get(`/board`,config =[] )
        .then(response => {
            return response.data.data;
        })
        .catch(e => {
            console.log(e);
        })
    },

}