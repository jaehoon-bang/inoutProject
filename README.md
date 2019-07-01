# 근태앱 설명

   ## 로그인
     http://localhost/auth
     {
      username : "",
      password : "",
      flag ""
     }
   ## JWT토큰인증을통한 로그인
     JWT 사용이유 : 기존 Session에 저장하던 유저 정보를 http Header에 암호화된 Token으로 저장 함으로써
     Session사용으로인한 서버 부하를 낮추기위함.
     
     JWT 장점 : 
               1)Token을 Http Header에 저장하기 때문에 서버의 부하를 낮출 수 있다.
               2)암호화된 Token을 사용하기 때문에 보안성 상승 효과.
               
     JWT 단점 :
               1)JWT Token body claims에 많은 정보를 넣을 수록 토큰 길이가 길어진다.
               2)Token은 암호화되어있지만 탈취가 불가능 한 것은 아니다.
               (탈취에 대한 해결방안으로 access Token과 refresh Token을 생성하여 탈취에대한 대비를 하였다.)
               기간이 짧게 설정되어진 access Token을 인증 전용 DB를 따로 설계하여 저장하고 서버 호출시
               인증 DB를 거쳐 한번더 인증하도록 하였으며, 좀더 기간이 긴 refresh Token은 유저정보가 담긴
               Main DB에 저장하여 access Token이 만료되었을시 refresh Token을 사용 인증 후 다시 access Token
       
