import React from 'react';

// 예시 스타일
const styles = {
  container: {
    fontFamily: 'sans-serif',
    padding: '20px',
    maxWidth: '250px'
  },
  header: {
    backgroundColor: '#f2f2f2',
    padding: '10px',
    marginBottom: '10px'
  },
  section: {
    marginBottom: '10px'
  },
  sectionHeader: {
    fontSize: '14px',
    color: '#888',
    marginBottom: '5px'
  },
  content: {
    fontSize: '16px',
    fontWeight: 'bold'
  }
};
// 이미지 스타일
const imageStyle = {
  width: '100%', // 이미지 너비
  height: 'auto', // 이미지 높이 자동 조정
  marginBottom: '10px', // 이미지와 정보 섹션 사이의 간격
};

const HerbInfoBox = ({ data }) => {
  const {
    herbName,
    herbImg, // 이 이미지 ID를 실제 경로로 매핑하는 로직이 필요합니다.
    herbScientificName,
    herbNickname,
    herbMedicalPart,
    herbHarvestingTime,
    herbEnvironment,
    herbQuality,
    medicinalEffects
  } = data;

  // 실제 이미지 경로는 서버의 이미지 저장 구조에 따라 달라질 수 있습니다.
  // 예시: `const imageUrl = `/images/herbs/${herbImg}.jpg`;`
  const imageUrl = `/herbs/001_00000176_root.jpg`;

  return (
    <div style={styles.container}>
      {/* 허브 이미지 */}
      <img src={imageUrl} alt={herbName} style={imageStyle} />
      <div style={styles.header}>
        <div style={styles.sectionHeader}>다른 이름</div>
        <div style={styles.content}>{herbNickname}</div>
      </div>
      <div style={styles.section}>
        <div style={styles.sectionHeader}>약효</div>
        {/* 약효는 리스트로 주어진다고 가정하여 map을 사용 */}
        <ul style={styles.content}>
          {medicinalEffects.map(effect => (
            <li key={effect.medicinalEffect}>{effect.medicinalEffect}</li>
          ))}
        </ul>
      </div>
      <div style={styles.section}>
        <div style={styles.sectionHeader}>생태</div>
        <div style={styles.content}>{herbEnvironment}</div>
      </div>
      <div style={styles.section}>
        <div style={styles.sectionHeader}>채취시기</div>
        <div style={styles.content}>{herbHarvestingTime}</div>
      </div>
      <div style={styles.section}>
        <div style={styles.sectionHeader}>약용부위</div>
        <div style={styles.content}>{herbMedicalPart}</div>
      </div>
      <div style={styles.section}>
        <div style={styles.sectionHeader}>품질기준</div>
        <div style={styles.content}>{herbQuality}</div>
      </div>
    </div>
  );
};

export default HerbInfoBox;