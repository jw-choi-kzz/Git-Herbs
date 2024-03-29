import { useEffect, useRef } from 'react';
import geojsonData from '../../utils/newFIle.json';
import { herbsService } from "../../apis/herbs"

const NaverMap = () => {
  const mapElement = useRef(null);

  useEffect(() => {
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
        const response = await herbsService.getHerbMap(1);
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
    if (count < 1000) {
      return "#e6b8af";
    } else if (count < 2000) {
      return "#f4cccc";
    } else if (count < 3000) {
      return "#fce5cd";
    } else if (count < 4000) {
      return "#fff2cc";
    } else if (count < 5000) {
      return "#d9ead3";
    } else if (count < 6000) {
      return "#d0e0e3";
    } else {
      return "#c9daf8";
    }
  };

  const renderLegend = () => {
    const ranges = [
      { color: "#e6b8af", range: "1000" },
      { color: "#f4cccc", range: "2000" },
      { color: "#fce5cd", range: "3000" },
      { color: "#fff2cc", range: "4000" },
      { color: "#d9ead3", range: "5000" },
      { color: "#d0e0e3", range: "6000" },
      { color: "#c9daf8", range: "6000+" },
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