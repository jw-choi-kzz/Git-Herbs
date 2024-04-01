
export const configService = {

    /**
     * 로그인이 필요할 경우 넣는 cofnig
     */
    loginConfig: () => ({
        headers: {
          Authorization: 'Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhY2Nlc3MtdG9rZW4iLCJleHAiOjE3MTIwMTg0MTQsImlkIjoxLCJuaWNrbmFtZSI6InRlc3QiLCJzY29wZSI6Ik1FTUJFUiJ9.ShQ4ckIJBqzSXRbBPk6E2TDIfuXTlblNUnpBNQ-gcAnkrBcFMkVxNs-1gFpudUHF6hzbN0een5Z5H9cO7zdOGQ'
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