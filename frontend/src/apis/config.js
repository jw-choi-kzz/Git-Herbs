

export const configService = {


    /**
     * 로그인이 필요할 경우 넣는 cofnig
     */
    loginConfig: () => ({
        headers: {
          Authorization: 'Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhY2Nlc3MtdG9rZW4iLCJleHAiOjE3MTE5ODUzODEsImlkIjoxLCJuaWNrbmFtZSI6InRlc3QiLCJzY29wZSI6Ik1FTUJFUiJ9.rbdEfp8SBdYC9hH3wzwfpMPh8L_kcpuRIxYMUAstVbseySndkEqpUdXTD-11Jygbbar-pa4kjAi2BZoo7VSbVQ'
        }
      }),
    


  /**
   * 카카오 API 요청에 필요한 헤더 
   */
  kakaoconfig: () => ({
    headers: {
      Authorization: 'KakaoAK 8a18ae99d48d2eee9185b60c07d99e85'
    }
  })


}