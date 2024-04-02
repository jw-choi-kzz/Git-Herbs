import { Col, Row } from 'antd';
import { useEffect, useState } from 'react';
import styled from 'styled-components';
import { eventService } from '../../apis/event';
import { configService } from "../../apis/config"
import AfterHerbQuiz from './AfterHerbQuiz';

const Container = styled.div`
  width: 320px;
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
  color: #21351F;
  width: 100%;
  text-align: left;
`;

const SubTitle = styled.div`
  font-size: 16px;
  color: #21351F;
  margin: 4px 0;
  width: 100%;
  text-align: left;
`;

const QuizImage = styled.img`
  width: 120px;
  height: 120px;
  margin: 4px;
  object-fit: cover;
  cursor: pointer;
`;

const HerbQuiz = () => {
  const [quizState, setQuizState] = useState(null);
  //"question": string, "imgOne" : string, "imgTwo" : string, "imgThree" : string, "imgFour" : string   
  const [question, setquestion] = useState();
  const [imgOne, setImgOne] = useState();
  const [imgTwo, setImgTwo] = useState();
  const [imgThree, setImgThree] = useState();
  const [imgFour, setImgFour] = useState();

  useEffect(() => {
    response();
  }, []);

  const response = async () => {
    const data = await eventService.getQuiz();
    setquestion(data.question);
    setImgOne(data.imgOne);
    setImgTwo(data.imgTwo);
    setImgThree(data.imgThree);
    setImgFour(data.imgFour);
  }

  // const question = "다음 중 칡 사진을 고르세요."; ///response.data.question

  const QuizOptionsList = [
    {
      idx: 1,
      herbUrl: imgOne //약초 이미지 url(imgOne~imgFour)
    },
    {
      idx: 2,
      herbUrl: imgTwo
    },
    {
      idx: 3,
      herbUrl: imgThree
    },
    {
      idx: 4,
      herbUrl: imgFour
    },
  ] //response.data.imageList

  const handleImageClick = async (id) => {

    const response = await eventService.postQuiz({ answer: id - 1 }, configService.loginConfig());
    if (response != undefined) {
      setQuizState(response)
    } else {
      setQuizState(3)
    }


  };

  if (quizState != null) {
    return <AfterHerbQuiz data={{ quizState }} />;
  }

  return (
    <Container>
      <Title className='bold'>오늘의 약초 퀴즈</Title>
      <SubTitle className='medium'>{question}</SubTitle>
      <br />
      <Row gutter={[26, 26]} justify="space-around">
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
