import { apiInstance } from "../utils/Sample-axios";


const axios = apiInstance;


const boardService = {

    /**
     * 심봤다 게시판 게시글 좋아요  (로그인 필수 )
     * @param {*} boardId PathVariable 
     * @param {*} config  헤더에 엑세스토큰 담아야함 
     * @returns  반환 변수 : {boardId, flag }  ture는 좋아요 누른 것 , false는 안 누른 것
     */
    putFavorite: (boardId, config) => {
        return axios.put(`/board/${boardId}`, config)
            .then(response => {
                return response.data.data;
            })
            .catch(error => {
                console.log(error);
            });
    },

    /**
     * 심봤다 게시판 게시글 삭제하기 (로그인 필수)
     * @param {*} boardId  삭제할 boardId 
     * @param {*} config   Header에 토큰 담아야함 
     * @returns 
     */
    deleteBoard: (boardId, config) => {
        return axios.delete(`/board/${boardId}`, config)
            .then(response => {
                return response.data.data;
            })
            .catch(error => {
                console.log(error);
            })
    },


    /**
     * 심봤다 게시판 게시글 조회  
     * @param {*} config  로그인을 한 상태면 헤더에 토큰 담기
     * @returns [boardId , "memberId", "userNickname", "userImgUrl", "imgUrl", "likeCnt", "likeCheck", "createAt" ] true는 좋아요 누른 것 false 는 안 누른 것
     */
    getBoard: (config) => {
        return axios.get(`/board`, config = [])
            .then(response => {
                return response.data.data;
            })
            .catch(e => {
                console.log(e);
            })
    },

    /**
     * 심봤다 게시판 글 작성  (로그인 필수 )
     * @param {*} request  request : { imgUrl, similar }  담겨야함
     * @param {*} config   access token 담겨야함 
     * @returns 
     */
    writeBoard: (request, config) => {
        return axios.post(`/board`, request, config)
            .then(response => {
                return response.data.data;
            })
            .catch(e => {
                console.log(e);
            })
    },

    /**
     * 좋아요 누른 게시글 불러오기 (로그인 필수)
     * @param {*} config  엑세스 토큰이 헤더에 들어가야함.
     * @returns [boardId , "memberId", "userNickname", "userImgUrl", "imgUrl", "likeCnt", "likeCheck", "createAt" ] true는 좋아요 누른 것 false 는 안 누른 것
     */
    getFavoriteBoard: (config) => {
        return axios.get(`/board/my-like`, config)
            .then(response => {
                return response.data.data;
            })
            .catch(e => {
                console.log(e);
            })
    },


    /**
     * 내가 작성한 게시글 불러오기 (로그인 필수 )
     * @param {*} config  액세스 토큰을 담은 헤더 필요
     * @returns [boardId , "memberId", "userNickname", "userImgUrl", "imgUrl", "likeCnt", "likeCheck", "createAt" ] true는 좋아요 누른 것 false 는 안 누른 것
     */
    getMyBoard: (config) => {
        return axios.get(`/board/my-board`, config)
            .then(response => {
                return response.data.data;
            })
            .catch(error => {
                console.log(error);
            })
    },



}

export default boardService;