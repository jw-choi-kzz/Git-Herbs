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
  margin: 5px 0;
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
  width: 120px;
  height: 120px;
  object-fit: cover;
  cursor: pointer;
`;

const HerbQuiz = () => {
    const [quizState, setQuizState] = useState('quiz');
    const quizId = 1; //response.data.quizId
    const question = "다음 중 칡 사진을 고르세요."; ///response.data.question
    const answer = 1;
    const QuizOptionsList = [ 
        {
            id: 1, //약초 id
            imageUrl: "./herbs/001_00000010_leaf.jpg" //약초 이미지 url
        },
        {
            id: 2,
            imageUrl: "./herbs/001_00000106_flower.jpg"
        },
        {
            id: 3,
            imageUrl: "./herbs/001_00000176_root.jpg"
        },
        {
            id: 4,
            imageUrl: "./herbs/001_00000311_leaf.jpg"
        },
    ] //response.data.imageList

    const handleImageClick = (id) => {
      if (id === answer) {  //임의의 답
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
              <Col key={option.id} span={12}>
                  <QuizImage
                      src={option.imageUrl}
                      alt={`Option ${index + 1}`}
                      onClick={() => handleImageClick(option.id)}
                  />
              </Col>
          ))}
      </Row>
  </Container>
    );
}

export default HerbQuiz;
