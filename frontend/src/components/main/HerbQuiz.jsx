import { Row, Col } from 'antd';
import styled from 'styled-components';

const Container = styled.div`
  width: 334px;
  height: 450px;
  border-radius: 10px;
  padding: 10px;
  box-sizing: border-box;
  background-color: #fff;
  box-shadow: 0 4px 8px 0 rgba(0,0,0,0.2);
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
`;

const Title = styled.div`
  margin: 0;
  font-size: 1.5em;
  color: #000;
  font-weight: bold;
  /* Assuming "text-xl" and "bold" are CSS classes defined elsewhere, you might still need them. */
`;

const SubTitle = styled.div`
  font-size: 0.85em;
  color: #4A4A4A;
  margin: 2px 0;
  /* Assuming "medium" is a CSS class defined elsewhere, you might still need it. */
`;

const QuizImage = styled.img`
  width: 120px;
  height: 120px;
  object-fit: cover;
`;

const HerbQuiz = () => {
    const quizId = 1; //response.data.quizId
    const question = "다음 중 칡 사진을 고르세요."; ///response.data.question
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

    return (
    <Container>
      <Title>오늘의 약초 퀴즈</Title>
      <SubTitle>{question}</SubTitle>
      <Row gutter={[16, 16]}>
        {QuizOptionsList.map((option, index) => (
          <Col key={option.id} span={12} className="flex justify-center">
            <QuizImage src={option.imageUrl} alt={`Option ${index + 1}`} />
          </Col>
        ))}
      </Row>
    </Container>
    );
}

export default HerbQuiz;
