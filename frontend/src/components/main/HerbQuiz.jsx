import { useState } from 'react';
import { Row, Col } from 'antd';
import styled from 'styled-components';
import AfterHerbQuiz from './AfterHerbQuiz';

const Container = styled.div`
  width: 280px;
  margin: auto;
  border-radius: 10px;
  padding: 20px;
  box-sizing: border-box;
  background-color: #fff;
  box-shadow: 0 4px 8px 0 rgba(0,0,0,0.2);
  display: flex;
  flex-direction: column;
  align-items: center;
`;

const Title = styled.div`
  font-size: 1.25em;
  color: #4A4A4A;
  width: 100%;
  text-align: left;
`;

const SubTitle = styled.div`
  font-size: 0.85em;
  color: #4A4A4A;
  margin: 2px 0;
  width: 100%;
  text-align: center;
`;

const QuizImage = styled.img`
  width: 110px;
  height: 110px;
  object-fit: cover;
  cursor: pointer;
`;

const HerbQuiz = () => {
    const [quizState, setQuizState] = useState('quiz');
    //"question": string, "imgOne" : string, "imgTwo" : string, "imgThree" : string, "imgFour" : string    
    const question = "다음 중 칡 사진을 고르세요."; ///response.data.question
    
    const QuizOptionsList = [ 
        {
          idx: 1,
          herbUrl: "./herbs/001_00000010_leaf.jpg" //약초 이미지 url(imgOne~imgFour)
        },
        {
          idx: 2,
          herbUrl: "./herbs/001_00000106_flower.jpg"
        },
        {
          idx: 3,
          herbUrl: "./herbs/001_00000176_root.jpg"
        },
        {
          idx: 4,
          herbUrl: "./herbs/001_00000311_leaf.jpg"
        },
    ] //response.data.imageList
    const answer = 1;//퀴즈 풀고 "answer" : int

    const handleImageClick = (id) => {
      if (id === answer) {  //임의의 답(api 요청해서 정답 번호를 받아옴)
          setQuizState('answered');
      } else {
          // Handle wrong answer if needed
      }
  };

  if (quizState === 'answered') {
    return <AfterHerbQuiz />;
}

    return (
      <Container>
      <Title className='bold'>오늘의 약초 퀴즈</Title>
      <SubTitle>{question}</SubTitle>
      <Row gutter={[16, 16]}>
          {QuizOptionsList.map((option, index) => (
              <Col key={option.idx} span={12}>
                  <QuizImage
                      src={option.herbUrl}
                      alt={`Option ${index + 1}`}
                      onClick={() => handleImageClick(option.idx)}
                  />
              </Col>
          ))}
      </Row>
  </Container>
    );
}

export default HerbQuiz;
