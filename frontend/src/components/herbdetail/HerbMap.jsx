import { useEffect, useRef } from 'react';
import geojsonData from '../../utils/newFIle.json';
import { herbsService } from "../../apis/herbs"

const NaverMap = ({ data }) => {
  const mapElement = useRef(null);

  useEffect(() => {
    const herbId = data.herbId;
    const initializeMap = async () => {
      const loadNaverMap = async () => {
        const { naver } = window;
        if (!mapElement.current || !naver) {
          await new Promise(resolve => setTimeout(resolve, 100));
          return loadNaverMap();
        }
        return naver;
      };

      const naver = await loadNaverMap();

      const mapOptions = {
        center: new naver.maps.LatLng(36.6809732, 128.1351415),
        zoom: 7
      };
      const map = new naver.maps.Map(mapElement.current, mapOptions);

      try {
        const response = await herbsService.getHerbMap(herbId);
        await renderMap(response, map, naver);
      } catch (error) {
        console.log(error);
      }
    };

    initializeMap();
  }, []);

  const renderMap = async (list, map, naver) => {
    const hashMap = await list.reduce((acc, item) => {
      acc[item.code] = item.count;
      return acc;
    }, {});

    geojsonData.features.forEach(element => {
      if (hashMap[element.properties.SIG_CD] !== undefined) {
        const feature = new naver.maps.Feature(element);
        const color = getColor(hashMap[element.properties.SIG_CD]);
        feature.setStyle({ fillColor: color, strokeColor: "#000000", strokeWeight: "1", fillOpacity: "0.7" });
        map.data.addFeature(feature, false);
      }
    });
  };

  const getColor = (count) => {
    if (count < 1500) {
      return "#6a4c93";
    } else if (count < 3000) {
      return "#1982c4";
    } else if (count < 4500) {
      return "#8ac926";
    } else if (count < 6000) {
      return "#ffca3a";
    } else {
      return "#ff595e";
    }
  };

  const renderLegend = () => {
    const ranges = [
      { color: "#6a4c93", range: "1500" },
      { color: "#1982c4", range: "3000" },
      { color: "#8ac926", range: "4500" },
      { color: "#ffca3a", range: "6000" },
      { color: "#ff595e", range: "6000+" },
    ];

    return (
      <div style={{ display: 'flex', justifyContent: 'center', marginTop: '10px' }}>
        {ranges.map(({ color, range }) => (
          <div key={color} style={{ display: 'flex', flexDirection: 'column', alignItems: 'center', marginRight: '10px' }}>
            <div style={{ backgroundColor: color, width: '20px', height: '20px', marginBottom: '5px' }}></div>
            <span>{range}</span>
          </div>
        ))}
      </div>
    );
  };

  return (
    <div>
      <div ref={mapElement} style={{ minHeight: '400px' }} />
      {renderLegend()}
    </div>
  );
};

export default NaverMap;