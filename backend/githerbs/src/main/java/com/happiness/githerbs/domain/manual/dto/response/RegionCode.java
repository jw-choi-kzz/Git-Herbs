package com.happiness.githerbs.domain.manual.dto.response;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.happiness.githerbs.global.common.code.ErrorCode;
import com.happiness.githerbs.global.common.exception.BaseException;

public class RegionCode {
	private RegionCode() {
	}

	protected static final Map<String, Map<String, Integer>> map;
	private static final List<Integer> list;

	public static Integer getCode(String region, String district) {
		Map<String, Integer> districts = map.get(region);
		if (districts != null) {
			return districts.get(district);
		}
		return null;
	}

	public static String findKeysByCode(int code) {

		for (Map.Entry<String, Map<String, Integer>> entry : map.entrySet()) {
			String regionKey = entry.getKey();
			Map<String, Integer> regionMap = entry.getValue();

			for (Map.Entry<String, Integer> subEntry : regionMap.entrySet()) {
				String subRegionKey = subEntry.getKey();
				int subRegionCode = subEntry.getValue();

				if (subRegionCode == code) {
					String result = regionKey + " ";
					result += subRegionKey;
					return result;
				}
			}
		}

		return null; // 해당하는 키가 없을 경우 null 반환
	}

	public static Integer findRandomCode() {

		int num = 0;
		try {
			num = SecureRandom.getInstanceStrong().nextInt(list.size());
		} catch (NoSuchAlgorithmException e) {
			throw new BaseException(ErrorCode.INTERNAL_SERVER_ERROR);
		}

		return list.get(num);
	}

	static {
		list = List.of(43130, 43150, 43800, 44180, 44770, 45790, 46800, 46860, 46870, 46910, 47280, 47760, 47770, 47930,
			43760, 31710, 48330, 46130, 46770);

		map = new HashMap<>();
		Map<String, Integer> seoul = new HashMap<>();
		seoul.put("종로구", 11110);
		seoul.put("중구", 11140);
		seoul.put("용산구", 11170);
		seoul.put("성동구", 11200);
		seoul.put("광진구", 11215);
		seoul.put("동대문구", 11230);
		seoul.put("중랑구", 11260);
		seoul.put("성북구", 11290);
		seoul.put("강북구", 11305);
		seoul.put("도봉구", 11320);
		seoul.put("노원구", 11350);
		seoul.put("은평구", 11380);
		seoul.put("서대문구", 11410);
		seoul.put("마포구", 11440);
		seoul.put("양천구", 11470);
		seoul.put("강서구", 11500);
		seoul.put("구로구", 11530);
		seoul.put("금천구", 11545);
		seoul.put("영등포구", 11560);
		seoul.put("동작구", 11590);
		seoul.put("관악구", 11620);
		seoul.put("서초구", 11650);
		seoul.put("강남구", 11680);
		seoul.put("송파구", 11710);
		seoul.put("강동구", 11740);
		map.put("서울특별시", seoul);
		map.put("서울", seoul);

		Map<String, Integer> busan = new HashMap<>();
		busan.put("중구", 26110);
		busan.put("서구", 26140);
		busan.put("동구", 26170);
		busan.put("영도구", 26200);
		busan.put("부산진구", 26230);
		busan.put("동래구", 26260);
		busan.put("남구", 26290);
		busan.put("북구", 26320);
		busan.put("해운대구", 26350);
		busan.put("사하구", 26380);
		busan.put("금정구", 26410);
		busan.put("강서구", 26440);
		busan.put("연제구", 26470);
		busan.put("수영구", 26500);
		busan.put("사상구", 26530);
		busan.put("기장군", 26710);
		map.put("부산광역시", busan);
		map.put("부산", busan);

		Map<String, Integer> daegu = new HashMap<>();
		daegu.put("중구", 27110);
		daegu.put("동구", 27140);
		daegu.put("서구", 27170);
		daegu.put("남구", 27200);
		daegu.put("북구", 27230);
		daegu.put("수성구", 27260);
		daegu.put("달서구", 27290);
		daegu.put("달성군", 27710);
		daegu.put("군위군", 27720);
		map.put("대구광역시", daegu);
		map.put("대구", daegu);

		Map<String, Integer> incheon = new HashMap<>();
		incheon.put("중구", 28110);
		incheon.put("동구", 28140);
		incheon.put("미추홀구", 28177);
		incheon.put("연수구", 28185);
		incheon.put("남동구", 28200);
		incheon.put("부평구", 28237);
		incheon.put("계양구", 28245);
		incheon.put("서구", 28260);
		incheon.put("강화군", 28710);
		incheon.put("옹진군", 28720);
		map.put("인천광역시", incheon);
		map.put("인천", incheon);

		Map<String, Integer> gwangju = new HashMap<>();
		gwangju.put("동구", 29110);
		gwangju.put("서구", 29140);
		gwangju.put("남구", 29155);
		gwangju.put("북구", 29170);
		gwangju.put("광산구", 29200);
		map.put("광주광역시", gwangju);
		map.put("광주", gwangju);

		Map<String, Integer> daejeon = new HashMap<>();
		daejeon.put("동구", 30110);
		daejeon.put("중구", 30140);
		daejeon.put("서구", 30170);
		daejeon.put("유성구", 30200);
		daejeon.put("대덕구", 30230);
		map.put("대전광역시", daejeon);
		map.put("대전", daejeon);

		Map<String, Integer> ulsan = new HashMap<>();
		ulsan.put("중구", 31110);
		ulsan.put("남구", 31140);
		ulsan.put("동구", 31170);
		ulsan.put("북구", 31200);
		ulsan.put("울주군", 31710);
		map.put("울산광역시", ulsan);
		map.put("울산", ulsan);

		Map<String, Integer> sejong = new HashMap<>();
		sejong.put("세종특별자치시", 36110);
		map.put("세종특별자치시", sejong);
		map.put("세종", sejong);

		Map<String, Integer> gyeonggi = new HashMap<>();
		gyeonggi.put("수원시 장안구", 41111);
		gyeonggi.put("수원시 권선구", 41113);
		gyeonggi.put("수원시 팔달구", 41115);
		gyeonggi.put("수원시 영통구", 41117);
		gyeonggi.put("성남시 수정구", 41131);
		gyeonggi.put("성남시 중원구", 41133);
		gyeonggi.put("성남시 분당구", 41135);
		gyeonggi.put("의정부시", 41150);
		gyeonggi.put("안양시 만안구", 41171);
		gyeonggi.put("안양시 동안구", 41173);
		gyeonggi.put("부천시", 41190);
		gyeonggi.put("광명시", 41210);
		gyeonggi.put("평택시", 41220);
		gyeonggi.put("동두천시", 41250);
		gyeonggi.put("안산시 상록구", 41271);
		gyeonggi.put("안산시 단원구", 41273);
		gyeonggi.put("고양시 덕양구", 41281);
		gyeonggi.put("고양시 일산동구", 41285);
		gyeonggi.put("고양시 일산서구", 41287);
		gyeonggi.put("과천시", 41290);
		gyeonggi.put("구리시", 41310);
		gyeonggi.put("남양주시", 41360);
		gyeonggi.put("오산시", 41370);
		gyeonggi.put("시흥시", 41390);
		gyeonggi.put("군포시", 41410);
		gyeonggi.put("의왕시", 41430);
		gyeonggi.put("하남시", 41450);
		gyeonggi.put("용인시 처인구", 41461);
		gyeonggi.put("용인시 기흥구", 41463);
		gyeonggi.put("용인시 수지구", 41465);
		gyeonggi.put("파주시", 41480);
		gyeonggi.put("이천시", 41500);
		gyeonggi.put("안성시", 41550);
		gyeonggi.put("김포시", 41570);
		gyeonggi.put("화성시", 41590);
		gyeonggi.put("광주시", 41610);
		gyeonggi.put("양주시", 41630);
		gyeonggi.put("포천시", 41650);
		gyeonggi.put("여주시", 41670);
		gyeonggi.put("연천군", 41800);
		gyeonggi.put("가평군", 41820);
		gyeonggi.put("양평군", 41830);
		map.put("경기도", gyeonggi);
		map.put("경기", gyeonggi);

		Map<String, Integer> chungbuk = new HashMap<>();
		chungbuk.put("청주시 상당구", 43111);
		chungbuk.put("청주시 서원구", 43112);
		chungbuk.put("청주시 흥덕구", 43113);
		chungbuk.put("청주시 청원구", 43114);
		chungbuk.put("충주시", 43130);
		chungbuk.put("제천시", 43150);
		chungbuk.put("보은군", 43720);
		chungbuk.put("옥천군", 43730);
		chungbuk.put("영동군", 43740);
		chungbuk.put("증평군", 43745);
		chungbuk.put("진천군", 43750);
		chungbuk.put("괴산군", 43760);
		chungbuk.put("음성군", 43770);
		chungbuk.put("단양군", 43800);
		map.put("충청북도", chungbuk);
		map.put("충북", chungbuk);

		Map<String, Integer> chungnam = new HashMap<>();
		chungnam.put("천안시 동남구", 44131);
		chungnam.put("천안시 서북구", 44133);
		chungnam.put("공주시", 44150);
		chungnam.put("보령시", 44180);
		chungnam.put("아산시", 44200);
		chungnam.put("서산시", 44210);
		chungnam.put("논산시", 44230);
		chungnam.put("계룡시", 44250);
		chungnam.put("당진시", 44270);
		chungnam.put("금산군", 44710);
		chungnam.put("부여군", 44760);
		chungnam.put("서천군", 44770);
		chungnam.put("청양군", 44790);
		chungnam.put("홍성군", 44800);
		chungnam.put("예산군", 44810);
		chungnam.put("태안군", 44825);
		map.put("충청남도", chungnam);
		map.put("충남", chungnam);

		Map<String, Integer> jeonbuk = new HashMap<>();
		jeonbuk.put("전주시 완산구", 45111);
		jeonbuk.put("전주시 덕진구", 45113);
		jeonbuk.put("군산시", 45130);
		jeonbuk.put("익산시", 45140);
		jeonbuk.put("정읍시", 45180);
		jeonbuk.put("남원시", 45190);
		jeonbuk.put("김제시", 45210);
		jeonbuk.put("완주군", 45710);
		jeonbuk.put("진안군", 45720);
		jeonbuk.put("무주군", 45730);
		jeonbuk.put("장수군", 45740);
		jeonbuk.put("임실군", 45750);
		jeonbuk.put("순창군", 45770);
		jeonbuk.put("고창군", 45790);
		jeonbuk.put("부안군", 45800);
		map.put("전라북도", jeonbuk);
		map.put("전북특별자치도", jeonbuk);
		map.put("전북", jeonbuk);

		Map<String, Integer> jeonnam = new HashMap<>();
		jeonnam.put("목포시", 46110);
		jeonnam.put("여수시", 46130);
		jeonnam.put("순천시", 46150);
		jeonnam.put("나주시", 46170);
		jeonnam.put("광양시", 46230);
		jeonnam.put("담양군", 46710);
		jeonnam.put("곡성군", 46720);
		jeonnam.put("구례군", 46730);
		jeonnam.put("고흥군", 46770);
		jeonnam.put("보성군", 46780);
		jeonnam.put("화순군", 46790);
		jeonnam.put("장흥군", 46800);
		jeonnam.put("강진군", 46810);
		jeonnam.put("해남군", 46820);
		jeonnam.put("영암군", 46830);
		jeonnam.put("무안군", 46840);
		jeonnam.put("함평군", 46860);
		jeonnam.put("영광군", 46870);
		jeonnam.put("장성군", 46880);
		jeonnam.put("완도군", 46890);
		jeonnam.put("진도군", 46900);
		jeonnam.put("신안군", 46910);
		map.put("전라남도", jeonnam);
		map.put("전남", jeonnam);

		Map<String, Integer> gyeongbuk = new HashMap<>();
		gyeongbuk.put("포항시 남구", 47111);
		gyeongbuk.put("포항시 북구", 47113);
		gyeongbuk.put("경주시", 47130);
		gyeongbuk.put("김천시", 47150);
		gyeongbuk.put("안동시", 47170);
		gyeongbuk.put("구미시", 47190);
		gyeongbuk.put("영주시", 47210);
		gyeongbuk.put("영천시", 47230);
		gyeongbuk.put("상주시", 47250);
		gyeongbuk.put("문경시", 47280);
		gyeongbuk.put("경산시", 47290);
		gyeongbuk.put("의성군", 47730);
		gyeongbuk.put("청송군", 47750);
		gyeongbuk.put("영양군", 47760);
		gyeongbuk.put("영덕군", 47770);
		gyeongbuk.put("청도군", 47820);
		gyeongbuk.put("고령군", 47830);
		gyeongbuk.put("성주군", 47840);
		gyeongbuk.put("칠곡군", 47850);
		gyeongbuk.put("예천군", 47900);
		gyeongbuk.put("봉화군", 47920);
		gyeongbuk.put("울진군", 47930);
		gyeongbuk.put("울릉군", 47940);
		map.put("경상북도", gyeongbuk);
		map.put("경북", gyeongbuk);

		Map<String, Integer> gyeongnam = new HashMap<>();
		gyeongnam.put("창원시 의창구", 48121);
		gyeongnam.put("창원시 성산구", 48123);
		gyeongnam.put("창원시 마산합포구", 48125);
		gyeongnam.put("창원시 마산회원구", 48127);
		gyeongnam.put("창원시 진해구", 48129);
		gyeongnam.put("진주시", 48170);
		gyeongnam.put("통영시", 48220);
		gyeongnam.put("사천시", 48240);
		gyeongnam.put("김해시", 48250);
		gyeongnam.put("밀양시", 48270);
		gyeongnam.put("거제시", 48310);
		gyeongnam.put("양산시", 48330);
		gyeongnam.put("의령군", 48720);
		gyeongnam.put("함안군", 48730);
		gyeongnam.put("창녕군", 48740);
		gyeongnam.put("고성군", 48820);
		gyeongnam.put("남해군", 48840);
		gyeongnam.put("하동군", 48850);
		gyeongnam.put("산청군", 48860);
		gyeongnam.put("함양군", 48870);
		gyeongnam.put("거창군", 48880);
		gyeongnam.put("합천군", 48890);
		map.put("경상남도", gyeongnam);
		map.put("경남", gyeongnam);

		Map<String, Integer> jeju = new HashMap<>();
		jeju.put("제주시", 50110);
		jeju.put("서귀포시", 50130);
		map.put("제주특별자치도", jeju);
		map.put("제주도", jeju);
		map.put("제주", jeju);

		Map<String, Integer> gangwon = new HashMap<>();
		gangwon.put("춘천시", 51110);
		gangwon.put("원주시", 51130);
		gangwon.put("강릉시", 51150);
		gangwon.put("동해시", 51170);
		gangwon.put("태백시", 51190);
		gangwon.put("속초시", 51210);
		gangwon.put("삼척시", 51230);
		gangwon.put("홍천군", 51720);
		gangwon.put("횡성군", 51730);
		gangwon.put("영월군", 51750);
		gangwon.put("평창군", 51760);
		gangwon.put("정선군", 51770);
		gangwon.put("철원군", 51780);
		gangwon.put("화천군", 51790);
		gangwon.put("양구군", 51800);
		gangwon.put("인제군", 51810);
		gangwon.put("고성군", 51820);
		gangwon.put("양양군", 51830);
		map.put("강원특별자치도", gangwon);
		map.put("강원도", gangwon);
		map.put("강원", gangwon);
	}

}
