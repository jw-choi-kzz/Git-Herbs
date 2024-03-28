

export const configService = {


    /**
     * 로그인이 필요할 경우 넣는 cofnig
     */
    loginConfig: () => {
        headers: {
            Authorization: 'Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhY2Nlc3MtdG9rZW4iLCJleHAiOjE3MTE2ODYwOTYsImlkIjoxLCJuaWNrbmFtZSI6InRlc3QiLCJzY29wZSI6Ik1FTUJFUiJ9.de1_C2PXKY-w090N4uJGkE5sDRP2jWGDNU-fB1m2URGedPG1_w93MRoc72moxphRoB0vgQGeOa4oo0jDdlyMNA'
        }
    },


    /**
     * 카카오 API 요청에 필요한 헤더 
     */
    kakaoconfig : () =>{
        headers:{
            Authorization: 'KakaoAK 8a18ae99d48d2eee9185b60c07d99e85'
        }
    }


}