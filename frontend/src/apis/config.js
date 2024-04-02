
export const configService = {

    /**
     * 로그인이 필요할 경우 넣는 cofnig
     */
    loginConfig: () => ({
        headers: {
          Authorization: 'Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhY2Nlc3MtdG9rZW4iLCJleHAiOjE3MTIxMDk1NTgsImlkIjoxLCJuaWNrbmFtZSI6InRlc3QiLCJzY29wZSI6Ik1FTUJFUiJ9.aWLgI0qLg8jNPcx5Of4ps-LzOzLqwaETK5CTKwDvmko-kK162SK3d2RDtASYWl0YUGuMelMbtyt7hTNNn89B_Q'
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
