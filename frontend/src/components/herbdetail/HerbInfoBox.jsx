import React from 'react';

const styles = {
  container: {
    padding: '20px',
    maxWidth: '280px',
    backgroundColor: '#F5F5F5', // Assuming a white background
    boxShadow: '0 2px 4px rgba(0, 0, 0, 0.1)', // Optional shadow for depth
  },
  section: {
    backgroundColor: '#fff',
    padding: '10px',
    borderRadius: '16px', // Rounded corners for the section
    marginBottom: '20px', // Space between sections
  },
  sectionHeader: {
    fontSize: '16px',
    fontWeight: 'bold',
    color: '#305F72', // Greenish header color as per the example
    marginBottom: '4px', // Reduced space between header and sub-header
  },
  sectionSubHeader: {
    fontSize: '10px',
    textTransform: 'uppercase', // Make sub-header text uppercase
    color: '#888',
    marginBottom: '10px', // Space between sub-header and content
  },
  sectionDivisionLine: {
    borderTop: '1px solid #444', // Corrected property and value
    margin: '0px',
  },
  content: {
    fontSize: '14px',
    color: '#333', // Darker font color for content for better readability
  }
};
// 이미지 스타일
const imageStyle = {
  width: '100px', // 이미지 너비
  height: 'auto', // 이미지 높이 자동 조정
  marginBottom: '10px', // 이미지와 정보 섹션 사이의 간격
  borderRadius: '8px',
};

const HerbInfoBox = ({ data }) => {
  if (!data || !Array.isArray(data.medicinalEffects)) {
    return <div>로딩 중...</div>; // 또는 적절한 오류 메시지
  }
  const {
    herbName,
    herbImg, // 이 이미지 ID를 실제 경로로 매핑하는 로직이 필요합니다.
    herbScientificName,
    herbNickname,
    herbMedicalPart,
    herbHarvestingTime,
    herbEnvironment,
    herbQuality,
    medicinalEffects = [] // 기본값으로 빈 배열 할당
  } = data;

  // 실제 이미지 경로는 서버의 이미지 저장 구조에 따라 달라질 수 있습니다.
  // 예시: `const imageUrl = `/images/herbs/${herbImg}.jpg`;`
  const imageUrl = `/herbs/002_plant.png`;

  return (
    <div style={styles.container}>
      {/* 허브 이미지 */}
      <img src={imageUrl} alt={herbName} style={imageStyle} />
      <div style={styles.section}>
        <div style={styles.sectionHeader}>다른 이름</div>
        <div style={styles.sectionSubHeader}>Other Names</div>
        <div style={styles.sectionDivisionLine}></div>
        <div style={styles.content}>{herbNickname}</div>
      </div>
      <div style={styles.section}>
        <div style={styles.sectionHeader}>약효</div>
        <div style={styles.sectionSubHeader}>Medicinal Properties</div>
        <div style={styles.sectionDivisionLine}></div>
        {/* 약효는 리스트로 주어진다고 가정하여 map을 사용 */}
        <ul style={styles.content}>
          {medicinalEffects.map(effect => (
            <li key={effect.medicinalEffect}>{effect.medicinalEffect}</li>
          ))}
        </ul>
      </div>
      <div style={styles.section}>
        <div style={styles.sectionHeader}>생태</div>
        <div style={styles.sectionSubHeader}>Ecology</div>
        <div style={styles.sectionDivisionLine}></div>
        <div style={styles.content}>{herbEnvironment}</div>
      </div>
      <div style={styles.section}>
        <div style={styles.sectionHeader}>채취시기</div>
        <div style={styles.sectionSubHeader}>Harvest Period</div>
        <div style={styles.sectionDivisionLine}></div>
        <div style={styles.content}>{herbHarvestingTime}</div>
      </div>
      <div style={styles.section}>
        <div style={styles.sectionHeader}>약용부위</div>
        <div style={styles.sectionSubHeader}>Used Parts</div>
        <div style={styles.sectionDivisionLine}></div>
        <div style={styles.content}>{herbMedicalPart}</div>
      </div>
      <div style={styles.section}>
        <div style={styles.sectionHeader}>품질기준</div>
        <div style={styles.sectionSubHeader}>Quality Standards</div>
        <div style={styles.sectionDivisionLine}></div>
        <div style={styles.content}>{herbQuality}</div>
      </div>
      <div style={styles.section}>
        <div style={styles.sectionHeader}>위치</div>
        <div style={styles.sectionSubHeader}>Habitat / Location</div>
        <div style={styles.sectionDivisionLine}></div>
        <div style={styles.content}>지도 들어갈위치</div>
      </div>
    </div>
  );
};

export default HerbInfoBox;