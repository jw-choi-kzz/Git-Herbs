import { apiInstance } from "../utils/Sample-axios";


const axios = apiInstance;

export const manuualService = {

    getBoard : (config) =>{
        return axios.get(`/board`,config =[] )
        .then(response => {
            return response.data.data;
        })
        .catch(e => {
            console.log(e);
        })
    },


    getTip : () =>{
        return axios.get(`/manual/tip`)
        .then(response => {
            return response.data.data;
        })
        .catch(error => {
            console.log(error);
        })
    },

    /**
     * 
     * @param {*} lat  위도 
     * @param {*} lng  경도
     * @returns  [region ,animals = 배열형태 ]
     */
    getAnimal : (lat, lng) =>{
        return axios.get(`/manual/animal?lat=${lat}&lon=${lng}`)
        .then(response => {
            return response.data.data;
        })
        .catch(error => {
            console.log(error);
        })
    }


}