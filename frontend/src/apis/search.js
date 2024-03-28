import { apiInstance } from "../utils/Sample-axios";


const axios = apiInstance;


export const searchService = {

    getBoard : (config) =>{
        return axios.get(`/board`,config =[] )
        .then(response => {
            return response.data.data;
        })
        .catch(e => {
            console.log(e);
        })
    },

    /**
     *  입력한 키워드로 검색을 합니다.
     * @param {*} keyword  검색 키워드
     * @returns [id , herbImg, herbName, "scientificName", medicinalPart(약효부위) , "description"]
     */
    searchKeyword : (keyword) =>{
        return axios.get(`/search?keyword=${keyword}`)
        .then(response => {
            return response.data.data;
        })
        .catch(e =>{
            console.log(e);
        })
    },

    /**
     * 추천 약초 키워드를 받아옵니다. [로그인 필수]
     * @param {*} loginConfig 로그인 config 헤더
     * @returns [herbId,herbName]
     */
    searchRecommend : (loginConfig) =>{
        return axios.get(`/search/recommend`,loginConfig)
        .then(response => {
            return response.data.data;
        })
        .catch(error =>{
            console.log(error);
        })
    },


    /**
     * 
     * @param {*} loginConfig  로그인 config 필수
     * @returns  
     */
    searchRecent : (loginConfig) =>{
        return axios.get(`/search/recent`,loginConfig)
        .then(response => {
            return response.data.data;
        })
        .catch(error =>{
            console.log(error);
        })
    },



}