import { apiInstance } from "../utils/Sample-axios";

const axios = apiInstance;

export const userServcie = {

    /**
     *  사용자 정보를 받아옵니다. [로그인 필수]
     * @param {*} loginConfig  <수정필요> 로그인 config 헤더
     * @returns [userId , userNickname, userImgurl, rank, grass([{date: date, count: int}])]
     */
    getUserInfo : (loginConfig) =>{
        return axios.get(`/user`,loginConfig)
        .then(response => {
            return response.data.data;
        })
        .catch(error =>{
            console.log(error);
        })
    },

    /**
     *  사용자의 뱃지 획득 정보를 받아옵니다. [로그인 필수]
     * @param {*} loginConfig  <수정필요> 로그인 config 헤더
     * @returns [ {bedgeId, name(뱃지이름), details(뱃지 설명), createdId} ]
     */
    getUserBedge : (loginConfig) =>{
        return axios.get(`/user/bedge`,loginConfig)
        .then(response => {
            return response.data.data;
        })
        .catch(error =>{
            console.log(error);
        })
    },

    /**
     *  사용자의 프로필 이미지를 수정합니다. [로그인 필수]
     * @param {*} loginConfig  <수정필요> 로그인 config 헤더
     * @returns [ userId, userNickname, userImgurl]
     */
    updateProfileImg : (loginConfig,) =>{
        return axios.put(`/user/img`,loginConfig)
        .then(response => {
            return response.data.data;
        })
        .catch(error =>{
            console.log(error);
        })
    },

    /**
     * 사용자의 닉네임을 수정합니다. [로그인 필수]
     * @param {*} nickname 새로운 닉네임
     * @param {*} loginConfig 로그인 설정(헤더에 인증 토큰 포함)
     * @returns Promise 객체, 응답으로 [userId, userNickname, userImgurl] 반환
     */
    updateNickname: (nickname, loginConfig) => {
        return axios.put(`/user/nickname`, nickname, loginConfig)
        .then(response => {
            return response.data.data;
        })
        .catch(error => {
            console.log(error);
        });
    },
    

}