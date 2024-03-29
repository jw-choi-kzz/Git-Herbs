import React, { useEffect, useRef, useState } from 'react';
import geojsonData from '../../utils/newFIle.json';
import { herbsService } from "../../apis/herbs"

const NaverMap = () => {
  const [mapdata, setMapdata] = useState(null);
  const mapElement = useRef(null);

  useEffect(() => {
    const { naver } = window;
    if (!mapElement.current || !naver) return;

    herbsService.getHerbMap(1)
      .then(response => {
        setMapdata(response);
        calc(response);
        console.log(mapdata);
      })
      .catch(error => {
        console.log(error);
      })

    const mapOptions = {
      zoom: 10,
      zoomControl: true,
      zoomControlOptions: {
        position: naver.maps.Position.TOP_RIGHT,
      },
    };
    const map = new naver.maps.Map(mapElement.current, mapOptions);

    const calc = async (temp) => {
      const hashMap = await temp.reduce((acc, item) => {
        acc[item.code] = item.count;
        return acc;
      })

      geojsonData.features.forEach(element => {
        if (hashMap[element.properties.SIG_CD] != undefined) {
          const feature = new naver.maps.Feature(element);
          const color = () => {
            if (hashMap[element.properties.SIG_CD] < 1000) {
              return "#e6b8af"
            } else if (hashMap[element.properties.SIG_CD] < 2000) {
              return "#f4cccc"
            } else if (hashMap[element.properties.SIG_CD] < 3000) {
              return "#fce5cd"
            } else if (hashMap[element.properties.SIG_CD] < 4000) {
              return "#fff2cc"
            } else if (hashMap[element.properties.SIG_CD] < 5000) {
              return "#d9ead3"
            } else if (hashMap[element.properties.SIG_CD] < 6000) {
              return "#d0e0e3"
            } else {
              return "#c9daf8"
            }
          }
          feature.setStyle({ fillColor: color(), strokeColor: "#000000", strokeWeight: "1", fillOpacity: "0.7" })
          map.data.addFeature(feature, false);
        }
      });
    }

  }, []);

  return <div ref={mapElement} style={{ minHeight: '400px' }} />;
};

export default NaverMap;