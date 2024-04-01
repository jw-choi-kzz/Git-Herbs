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
     *  사용자의 프로필 이미지 또는 닉네임을 수정합니다. [로그인 필수]
     * @param {*} loginConfig  <수정필요> 로그인 config 헤더
     * @returns [ userId, userNickname, userImgurl]
     */
    updateProfile : (loginConfig) =>{
        return axios.put(`/user`,loginConfig)
        .then(response => {
            return response.data.data;
        })
        .catch(error =>{
            console.log(error);
        })
    },

}