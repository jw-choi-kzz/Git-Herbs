
export const configService = {

    /**
     * 로그인이 필요할 경우 넣는 cofnig
     */
    loginConfig: () => {
      // 로컬스토리지에서 token 값을 가져옴
      const token = localStorage.getItem('accessToken');
  
      // token 값이 존재하는지 확인
      if (token) {
        // token이 존재하면 Bearer 토큰을 생성하여 Authorization 헤더에 추가
        return {
          headers: {
            Authorization: `Bearer ${token}`
          }
        };
      } else {
        // token 값이 없으면 빈 객체 반환
        return {};
      }
    },
  
  mulitconfig : () =>{

          // 로컬스토리지에서 token 값을 가져옴
          const token = localStorage.getItem('accessToken');
  
          // token 값이 존재하는지 확인
          if (token) {
            // token이 존재하면 Bearer 토큰을 생성하여 Authorization 헤더에 추가
            return {
              headers: {
                Authorization: `Bearer ${token}`,
               'Content-Type': 'multipart/form-data'
              }
            };
          } else {
            // token 값이 없으면 빈 객체 반환
            return {};
          }
  } ,   


  /**
   * 카카오 API 요청에 필요한 헤더 
   */
  kakaoconfig: () => ({
    headers: {
      Authorization: 'KakaoAK 8a18ae99d48d2eee9185b60c07d99e85'
    }
  })


}