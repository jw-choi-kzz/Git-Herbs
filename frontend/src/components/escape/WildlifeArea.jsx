import React, { useEffect, useState } from 'react';
import styled from 'styled-components';
import axios from 'axios';
import {manuualService} from '../../apis/manual';


const WildlifeArea = ({ weatherdata }) => {
  const [loading, setLoading] = useState(true);
  const [response1, setResponse] = useState();

  useEffect(() => {
    setLoading(true);

    if (weatherdata && weatherdata.coord) {
      const x = weatherdata.coord.lon;
      const y = weatherdata.coord.lat;

      manuualService.getAnimal(x,y)
        .then(response => {
          setResponse(response);
          setLoading(false);
        })
        .catch(e => {
          console.log(e);
          setLoading(false);
        });
    }
  }, [weatherdata]);


  const parsedRegion = response1 && response1.region ? response1.region.split(' ') : [];

  return (

    <Div>
      {loading ? (
        <LoadingText>Loading...</LoadingText>
      ) : (
        <>
          {response1 && response1.animals && response1.animals.length > 0 ? (
           <> <Div2>
                {parsedRegion.map((regionPart, index) => (
                  <div key={index}>{regionPart}</div>
                ))}
              </Div2><Div3>
              {response1.animals
                .sort() 
                .slice(0,13)
                .map((animal, index) => (
                  <span key={index}>{animal}, </span>
                ))}
           </Div3></>
          ) : (
            <div>No animals data available</div>
          )}
       </>
      )}
    </Div>

  );
};

export default WildlifeArea;

const LoadingText = styled.div`
  text-align: center;
  font-size: 14px;
  color: #888;
`;



const Div = styled.div`
  width : 300px;
  height : auto;
  margin : auto;
  border-radius: 10px;
  box-shadow: 0 4px 8px 0 rgba(0,0,0,0.2);
  background-color: #fff;
  display: flex;
  flex-driection : row;
  gap: 13px;
  font-weight: 400;
  padding: 10px
`;


const Div2 = styled.div`
  display: flex; 
  flex-wrap: wrap;
  justify-content: center; 
  align-items: center; 
  gap: 10px; 
  
  border-color: rgba(0, 0, 0, 1);
  border-style: solid;
  border-width: 1px;
  white-space: nowrap;
  text-align: center;
  padding: 10px 10px;
  font: 24px ABeeZee, sans-serif;
`;


const Div3 = styled.div`
  margin: auto 0;
  font: 14px Spoqa Han Sans Neo, sans-serif;
`;

