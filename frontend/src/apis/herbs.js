import { apiInstance } from "../utils/Sample-axios";


const axios = apiInstance;


export const herbsService ={



    /**
     * 
     * @returns ["herbId" , "herbName", "bookmark", "acquireCheck"] bookmark,acquireCheck 가 1이면 획득 , 0이면 미획득
     */
    getHerbs : (loginConfig) => {
        return axios.get(`herbs`,loginConfig)
        .then(response => {
            return response.data.data.content;
        })
        .catch(error => {
            console.log(error);
        })
    },

    /**
     * 특정한 허브의 상세 정보를 불러옵니다
     * @param {*} herbId  
     * @returns [herbId ,herbName ,herbImgId, herbScientificName = 영어 이름 , herbNickname = 다른 이름 , herbMedicalPart =약효 부위, 
     * herbHarvestingTime = 수확 시기 , herbEnvironment =생태 , herbQuality = 품질 기준 , herMedicinalEffects = 약효, 배열형태로 들어옴]
     */
    getHerbInfo : (herbId) =>{
        return axios.get(`/herbs/${herbId}`)
        .then(response => {
            return response.data.data;
        })
        .catch(error => {
            console.log(error);
        })
    },

    /**
     * 특정한 허브의 지도 정보를 불러옵니다
     * @param {*} herbId  
     * @returns [code, count] 지역코드와 개수
     */
    getHerbMap : (herbId) => {
        return axios.get(`/herbs/${herbId}/map`)
        .then(response =>{
            return response.data.data;
        })
        .catch(error => {
            console.log(error)
        })
    },
    
    /**
     * 제철 약초 불러옵니다.
     * @returns [id, imgId, herbName]
     */
    getSeason : () =>{
       return axios.get(`/herbs/seasons`)
       .then(response =>{
            return response.data.data;
       }) 
       .catch(error =>{
        console.log(error);
       })
    },


    /**
     * 분석에 사용된 사진을 도감에 저장합니다.
     * @param {*} myHerbRequestDto  [ herbId, imgId,similarity]
     * @param {*} loginconfig  로그인한 헤더
     * @returns 
     */
    postHerb : (myHerbRequestDto,loginconfig) => {
        return axios.post(`/herbs/my-herbs`,myHerbRequestDto,loginconfig)
        .then(response =>{
            return response.data;
        })
        .catch(error =>{
            console.log(error);
        })
    },


    /**
     * 도감에 등록한 사진을 제거합니다. 
     * @param {*} myHerbId  제거하려는 허브의 ID 값
     * @param {*} loginConfig  로그인한 헤더
     * @returns 
     */
    deleteHerb : (myHerbId,loginConfig) =>{
        return axios.patch(`/herbs/my-herbs?myHerbId=${myHerbId}`,loginConfig)
        .then(response =>{
            return response.data;
        })
        .catch(error =>{
            console.log(error);
        })
    },


    /**
     * 내가 찍은 약초 사진을 불러옵니다.
     * @param {*} herbId 약초 아이디
     * @param {*} loginConfig 로그인헤더
     * @returns 
     */
    getMyHerbImg : (herbId,loginConfig) =>{
        return axios.get(`/herbs/${herbId}/my-herbs`,loginConfig)
        .then(response =>{
            return response.data.data.content;
        })
        .catch(error =>{
            console.log(error);
        })
    },


    /**
     * 즐겨찾기 등록을 합니다.  "bookmark": 0 일 때 실행되는 함수
     * @param {*} herbId  허브 Id
     * @param {*} loginConfig  로그인 헤더 
     * @returns 
     */
    postBookmark : (herbId,loginConfig) =>{
        return axios.post(`/herbs/${herbId}/bookmark`,loginConfig)
        .then(response => {
            return response.data;
        })
        .catch(error =>{
            console.log(error);
        })
    },

    /**
     * 즐겨찾기를 삭제합니다. "bookmark" : 1일 때 실행되는 함수 
     * @param {*} herbId  허브 Id
     * @param {*} loginConfig  로그인 헤더 
     * @returns 
     */
    deleteBookmark : (herbId,loginConfig) =>{
        return axios.delete(`/herbs/${herbId}/bookmark`,loginConfig)
        .then(response => {
            return response.data;
        })
        .catch(error =>{
            console.log(error);
        })
    },


}