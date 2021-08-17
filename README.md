# 큰 틀
- 회원 관리
  - 최초 회원가입을 한 회원은 영업자의 권한만 가지고 있다.
    - 회원가입 정보 중 계좌 관련 정보를 입력 받아야한다.
    - 장례지도사 자격 인증, 사업자 등록을 통해서 다른 권한 레벨을 얻게 된다.
      - 이후, 다른 업종에 등록한 회원은 다른 등록은 할 수 없다.
        - 각 직업마다의 흐름이 있다.  
  - 장례지도사와 사업자는 자격 인증을 거쳐야한다.
  - 지역
    - 17개 시,도만 한다.
    - 회원가입 단계에서 입력받는 지역 데이터는 지역 별 영업자 수를 파악하기 위함.
      1) 장례지도사 자격증을 사진 파일로 가지고 있는다.
- 메인 페이지
  - 장례지도사 인증 버튼
  - 사업자 인증 버튼
  - 인력 정보 등록 버튼
  - 장례지도사 호출 버튼

# 구현된 것
- 회원가입
  - 정규표현식 적용 완료 
# 구현할 것
- 회원의 비밀번호에 대한 암호화
- 장례지도사 신청을 여러번 할수 없도록 제한하기

# 전달 (읽은 후 지우면 됨)
- 파일 업로드테스트를 하려면 ResultData 에 getData 함수가 필요해서 잠시 원상복구 해뒀음.


   
