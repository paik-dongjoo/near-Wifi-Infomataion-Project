# near-Wifi Infomation Project

프로젝트 : 공공 와이파이 정보를 호출하여 가장 가까운 위치의 와이파이 20개 정보를 제공하는 서비스 <br>

언어 : <img src="https://img.shields.io/badge/Java-000000?style=for-the-badge&logo=Java&logoColor=white" /> <br>
데이터베이스 : <img src="https://img.shields.io/badge/SQLite-003B57?style=for-the-badge&logo=SQLite&logoColor=white" /> <br>
서버 : <img src="https://img.shields.io/badge/Apache%20Tomcat-F8DC75?style=for-the-badge&logo=Apache%20Tomcat&logoColor=black" /> <br>

1. 데이터베이스 테이블 구성 <br>
- History : 근처 Wifi 정보보기 클릭 시 검색기록 저장 <br>
- Wifi_Info : Open Api를 통해 저장한 공공와이파이 데이터 저장  <br> <br>

2. 동작방식 <br>
- 내 위치 가져오기 : 현재 내 위도, 경도값 호출 및 표기 <br>
- Open API 와이파이 정보 가져오기 : api를 호출하여 등록된 서울시 공공와이파이 정보 호출 및 DB 저장 <br>
- 근처 Wifi 정보보기 : 현재 입력된 위치값과 DB에 저장된 와이파이 정보의 위치값을 비교하여 근거리 20개 정보 리턴
