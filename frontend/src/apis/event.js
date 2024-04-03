import { apiInstance } from "../utils/Sample-axios";


const axios = apiInstance;


export const eventService = {


    /**
     * 퀴즈 목록을 가져옵니다.
     * @returns  ["question", "imgOne", "imgTwo", "imgThree", "imgFour" ]
     */
    getQuiz : () =>{
        return axios.get(`/event/quiz`)
        .then(response =>{
            return response.data.data;
        })
        .catch(error =>{
            console.log(error);
        })
    },


    /**
     * imgOne 클릭하면 answer = 0 , imgTwo 클릭하면 answer = 1 이런 식으로 작성 
     * @param {*} quizRequest  : [ "answer" ]
     * @param {*} loginconfig : 로그인 config 
     * @returns  true = 정답 , false = 오답
     */
    postQuiz : (quizRequest,loginconfig) =>{
        return axios.post(`/event/quiz`,quizRequest,loginconfig)
        .then(response =>{
            return response.data.data;
        })
        .catch(error =>{
            console.log(error);
        })
    },


    /**
     * 랭킹을 불러옵니다. 
     * @returns ["userId", "imgId", "nickname"] 
     */
    getRank : () =>{
        return axios.get(`/event/rank`)
        .then(response =>{
            return response.data.data;
        })
        .catch(error =>{
            console.log(error);
        })
    },


    /**
     * 오늘의 약초 정보를 불러옵니다.
     * @returns ["herbId", "herbImg" , "herbName", "herbMedicalPart"] 
     */
    getDaily : () =>{
        return axios.get(`/event/daily`)
        .then(response =>{
            return response.data.data;
        })
        .catch(error =>{
            console.log(error);
        })
    },

    /**
     *  사용자의 뱃지 획득 정보를 받아옵니다. [로그인 필수]
     * @param {*} loginConfig  <수정필요> 로그인 config 헤더
     * @returns [ {badgeId, name(뱃지이름), details(뱃지 설명), createdId} ]
     */
    getUserBadge : (loginConfig) =>{
        return axios.get(`/event/badge`, loginConfig)
        .then(response => {
           
            return response.data.data;
        })
        .catch(error =>{
            console.log(error);
        })
    },
}