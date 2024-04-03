# 🍀 GIT HERBS - 안전한 약초 채집을 위한 나만의 약초 저장소
SSAFY 10기 2학기 특화 프로젝트 - 행복만땅 (팀 A205) 


<p align="center">
  <img src="산출물/docs/asset/logo.png" width="400">
</p>

<br><br>

[//]: # (<img src="https://img.shields.io/badge/React-61DAFB?style=for-the-badge&logo=React&logoColor=white"> <img src="https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=SpringBoot&logoColor=white"> <img src="https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=MySQL&logoColor=white"> <img src="https://img.shields.io/badge/Jenkins-D24939?style=for-the-badge&logo=Jenkins&logoColor=white"> <img src="https://img.shields.io/badge/NGINX-009639?style=for-the-badge&logo=NGINX&logoColor=white"> <img src="https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=Docker&logoColor=white"> )

![Github](https://img.shields.io/badge/react-2.6.11-61DAFB?style=plastic&logo=React) ![Github](https://img.shields.io/badge/spring_boot-3.2.3-%236DB33F?style=plastic&logo=Spring) ![Github](https://img.shields.io/badge/MySQL-8.0-%234479A1?style=plastic&logo=mysql) ![Github](https://img.shields.io/badge/Redis-7.2.4-%23DC382D?style=plastic&logo=Redis) ![Github](https://img.shields.io/badge/Fastapi-0.11.0-009688?style=plastic&logo=Fastapi) ![Github](https://img.shields.io/badge/Tensorflow-2.15.0-FF6F00?style=plastic&logo=Tensorflow) ![Github](https://img.shields.io/badge/Docker-25.0.4-2496ED?style=plastic&logo=docker)


## 🌱 [GIT HERBS URL](https://j10a205.p.ssafy.io/)

## 🌱 [UCC URL]() : 추가 예정

## 📅 프로젝트 기간
2024.02.18 (월)  ~ 2024.04.04 (목)

## 💡 서비스 개요
### 배경
매년 꾸준히 발생하고 있는 약초 채집 중 중독 사고를 예방하고, 약초에 대해 잘 모르는 일반인이 산행 중 궁금증을 해소시켜주고자 함

### 목표
약초에 대해 잘 알지 못하는 초보 약초꾼이 약초를 구분하고, 안전하게 채집할 수 있도록 도와주는 서비스 제작


## 📚 산출물

1️⃣ [기획](산출물/docs/기획서.pdf)    
2️⃣ [와이어프레임](산출물/docs/와이어프레임.md)   
3️⃣ [목업](산출물/docs/목업.md)    
4️⃣ [요구사항 명세서](산출물/docs/요구사항명세서.pdf)     
5️⃣ [API 명세서](산출물/docs/API명세서.pdf)     
6️⃣ [아키텍쳐](산출물/docs/아키텍처.md)  
7️⃣ [ERD](산출물/docs/ERD.md)  
8️⃣ [JIRA](산출물/docs/JIRA.md)


## 📚 프로젝트 결과물
- 포팅메뉴얼
- 중간발표자료
- 최종발표자료


## 🍁 주요 기능

| 구분 | 기능 | 설명                                                                                                                                     | 비고                                               | 
|---:|:--:|:---------------------------------------------------------------------------------------------------------------------------------------|--------------------------------------------------|
|  1 | 검색 | - 약초 이름으로 원하는 약초를 찾을 수 있습니다.<br/> - 약초 효능으로 원하는 약초를 찾을 수 있습니다.<br/>- 최근 즐겨찾기한 약초와 비슷한 약초를 추천합니다. <br/> - 최근 검색어를 제공해 검색의 편리함을 증가시켰습니다. | - elasticsearch 로 검색 효율 증가 <br/> - 콘텐츠 기반 필터링 적용 |
|  2 | 촬영 | - 약초 사진을 업로드 하면 약초를 판별하여 알려줍니다. <br/> - 분석 완료된 사진은 나의 도감에 추가할 수 있습니다.                                                                  | - AI 약초 이미지 학습                                   |
|  3 | 도감 | - 관심있는 약초와 도감 등록된 약초들에 대한 조회가 가능합니다. <br/> - 약초에 대한 상세 정보를 제공합니다. <br/> - 약초가 분포되어 있는 지역을 시각화하여 보여줍니다.<br/>                            | - 약초 데이터 수집 후 가공                                 |
|  4 | 퀴즈 | - 하루 한번씩 약초 관련 퀴즈를 제공하여 약초에 관한 관심을 유도합니다.                                                                                              |                                                  |
|  5 | 잔디 | - 사용자의 일일 활동량을 기록합니다.                                                                                                                  |                                                  |
|  6 | 랭킹 | - 한달 활동량을 기반으로 사용자에게 순위를 부여합니다.  <br/> - 사용자의 서비스 이용을 유도합니다.                                                                           |                                                  |
|  7 | 정보 | - 약초 채집에 도움이 될 정보들을 제공합니다. (날씨, 야생동물, 제철약초, 월별 팁, 위급사항 행동 요령)                                                                          | - 실시간 날씨 API 사용 <br/>                            |


## 🔍 서비스 화면 


## 🧰 주요 기술

- 🛠️ **Backend**
  - Intellij
  - JAVA 17
  - Springboot 3.2.3
  - Spring Data JPA 3.2.3
  - Spring Validation 
  - Spring Web 
  - QueryDSL 5.0.0
  - JWT 0.12.5
  - Redis 7.2.4
  - MySQL 8.0
  - Elasticsearch 8.12.2


- 🛠️ **Frontend**
  - Vscode 
  - HTML5
  - CSS
  - JS
  - React 18.2.0
  - Axios 1.6.8
  - React-Router-Dom 6.22.3
  - Node 20.11.1
  - React-Dom 18.2.0
  - Zustand 4.5.2
  - Styled-Components 6.1.8


- 🛠️ **Data**
  - Python(3.11)
  - FastAPI(0.110.0)
  - uvicorn(0.29.0) 
  - tensorflow(2.15.0) 
  - konply(0.6.0) 
  - pandas(2.2.1) 
  - numpy(1.26.4) 
  - scikit-learn(1.12.0) 
  - jupyter hub(4.0.2) 


- 🛠️ **Infra**
  - AWS EC2
  - Amazon S3 1.12.529
  - Docker 25.0.4  
  - NGINX 1.18.0
  - Ubuntu 20.04.6
  - Logstash 8.12.2
  - Kibana 8.12.2
  - JENKINS
  - sonarlint
  - SSL 


- 🛠️ **협업**
  - Jira
  - GitLab
  - Notion
  - Mattermost
  - Discord
  - Figma
  - Webex

  
## 📌 프로젝트 구조
### **Frontend**

```
📦frontend
 ┣ 📂public
 ┃ ┣ 📂grass
 ┃ ┣ 📂herbs
 ┃ ┣ 📂pediaimg
 ┃ ┣ 📂profileimg
 ┣ 📂src
 ┃ ┣ 📂apis
 ┃ ┣ 📂assets
 ┃ ┃ ┣ 📂fonts
 ┃ ┣ 📂components
 ┃ ┃ ┣ 📂board
 ┃ ┃ ┣ 📂escape
 ┃ ┃ ┣ 📂herbdetail
 ┃ ┃ ┣ 📂main
 ┃ ┃ ┣ 📂mypage
 ┃ ┃ ┣ 📂pedia
 ┃ ┃ ┣ 📂picture
 ┃ ┃ ┣ 📂search
 ┃ ┣ 📂pages
 ┃ ┣ 📂store
 ┃ ┣ 📂utils
```

### **Backend**

```
📦githerbs
 ┣ 📂domain
 ┃ ┣ 📂auth
 ┃ ┃ ┣ 📂controller
 ┃ ┃ ┣ 📂dto
 ┃ ┃ ┃ ┣ 📂common
 ┃ ┃ ┃ ┣ 📂request
 ┃ ┃ ┃ ┗ 📂response
 ┃ ┃ ┣ 📂entity
 ┃ ┃ ┣ 📂repository
 ┃ ┃ ┗ 📂service
 ┃ ┣ 📂board
 ┃ ┃ ┣ 📂controller
 ┃ ┃ ┣ 📂dto
 ┃ ┃ ┃ ┣ 📂request
 ┃ ┃ ┃ ┗ 📂response
 ┃ ┃ ┣ 📂entity
 ┃ ┃ ┣ 📂repository
 ┃ ┃ ┗ 📂service
 ┃ ┣ 📂event
 ┃ ┃ ┣ 📂controller
 ┃ ┃ ┣ 📂dto
 ┃ ┃ ┃ ┣ 📂request
 ┃ ┃ ┃ ┗ 📂response
 ┃ ┃ ┣ 📂entity
 ┃ ┃ ┣ 📂repository
 ┃ ┃ ┗ 📂service
 ┃ ┣ 📂herb
 ┃ ┃ ┣ 📂controller
 ┃ ┃ ┣ 📂dto
 ┃ ┃ ┃ ┣ 📂request
 ┃ ┃ ┃ ┗ 📂response
 ┃ ┃ ┣ 📂entity
 ┃ ┃ ┣ 📂repository
 ┃ ┃ ┗ 📂service
 ┃ ┣ 📂manual
 ┃ ┃ ┣ 📂controller
 ┃ ┃ ┣ 📂dto
 ┃ ┃ ┃ ┗ 📂response
 ┃ ┃ ┣ 📂entity
 ┃ ┃ ┣ 📂repository
 ┃ ┃ ┗ 📂service
 ┃ ┣ 📂member
 ┃ ┃ ┣ 📂controller
 ┃ ┃ ┣ 📂dto
 ┃ ┃ ┃ ┣ 📂common
 ┃ ┃ ┃ ┣ 📂request
 ┃ ┃ ┃ ┗ 📂response
 ┃ ┃ ┣ 📂entity
 ┃ ┃ ┣ 📂repository
 ┃ ┃ ┗ 📂service
 ┃ ┗ 📂search
 ┃ ┃ ┣ 📂controller
 ┃ ┃ ┣ 📂dto
 ┃ ┃ ┃ ┗ 📂response
 ┃ ┃ ┣ 📂entity
 ┃ ┃ ┣ 📂repository
 ┃ ┃ ┗ 📂service
 ┣ 📂global
 ┃ ┣ 📂common
 ┃ ┃ ┣ 📂code
 ┃ ┃ ┣ 📂exception
 ┃ ┃ ┗ 📂response
 ┃ ┣ 📂config
 ┃ ┗ 📂handler
 ┗ 📜GitherbsApplication.java
```

## 🏷️  팀원 소개
<table>
  <tbody>
    <tr>
      <td align="center">
        <a href="https://github.com/onid057">
            <img src="https://avatars.githubusercontent.com/u/141606477?v=4" width="100px;" alt="팀장 김세리"/>
            <br />
            <sub><b>팀장 김세리</b></sub>
        </a>
        <div>FE</div>
      </td>
      <br />
      <td align="center">
        <a href="https://github.com/jw-choi-kzz">
            <img src="https://avatars.githubusercontent.com/u/141205653?v=4" width="100px;" alt="팀원 최지원"/>
            <br />
            <sub><b>팀원 최지원</b></sub>
        </a>
        <br />
        <div>FE</div>
      </td>
      <br/>
      <td align="center">
        <a href="https://github.com/JongJae2">
            <img src="https://avatars.githubusercontent.com/u/149658209?v=4" width="100px;" alt="팀원 이종재"/>
            <br />
            <sub><b>팀원 이종재</b></sub>
        </a>
        <br />
        <div>BE, FE</div>
      </td>
    </tr>
    <tr>
      <td align="center">
        <a href="https://github.com/dig04214">
            <img src="https://avatars.githubusercontent.com/u/81416039?v=4" width="100px;" alt="팀원 이병창"/>
            <br />
            <sub><b>팀원 이병창</b></sub>
        </a>
        <br />
        <div>BE, AI</div>
      </td>
      <td align="center">
        <a href="https://github.com/snowman2810">
            <img src="https://avatars.githubusercontent.com/u/140784660?v=4" width="100px;" alt="팀원 강태연"/>
            <br />
            <sub><b>팀원 강태연</b></sub>
        </a>
        <br />
        <div>BE, FE ,INFRA</div>
      </td>
      <td align="center">
        <a href="https://github.com/namhyemi">
            <img src="https://avatars.githubusercontent.com/u/85321894?v=4" width="100px;" alt="팀원 남혜미"/>
            <br />
            <sub><b>팀원 남혜미</b></sub>
        </a>
        <br />
        <div>BE, AI</div>
      </td>
    </tr>
  </tbody>
</table>
