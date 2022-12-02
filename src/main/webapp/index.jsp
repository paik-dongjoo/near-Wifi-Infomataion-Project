<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <title>와이파이 정보 구하기</title>
</head>

<body>
<h1>와이파이 정보 구하기
</h1>

<a href="index.jsp">홈</a> |
<a href="history.jsp">위치 히스토리 목록</a> |
<a href="load-wifi.jsp">Open API 와이파이 정보 가져오기</a>
<br/> <br/>

LAT : <input type = "TEXT" name = "LAT" id = "LAT" size = "20" value="0.0"> ,
LNT : <input type = "TEXT" name = "LNT" id = "LNT" size = "20" value="0.0">
<button id="FindMyLocation">내 위치 가져오기</button>
<form action="WifiService.jsp">
    <button id="nearWifi" onclick="submitLocate();">근처 Wifi 정보 보기</button>
    <input type = "hidden" name = "hiddenLAT" id = "hiddenLAT">
    <input type = "hidden" name = "hiddenLNT" id = "hiddenLNT">
</form>

<%--<iframe id="if" name = "ifSubmit"></iframe>--%>

<style>

    form{display:inline}

    #if{
        width: 0px;
        height: 0px;
        border: 0px;
    }
</style>
<script>
    document.getElementById("FindMyLocation").addEventListener('click', FindLocation);
    function FindLocation(){
        const lat = "";
        const lon = "";
        const posOptions = {
            enableHighAccuracy: false,
            timeout: 10000,
            maximumAge: 30000
        };
        function success(pos) {
            const crd = pos.coords;

            console.log('Your current position is:');
            console.log('Latitude : ' + crd.latitude);
            console.log('Longitude: ' + crd.longitude);
            console.log('More or less ' + crd.accuracy + ' meters.');

            document.getElementById("LAT").value = crd.latitude;
            document.getElementById("LNT").value = crd.longitude;

        };
        function error(err) {
            console.warn('ERROR(' + err.code + '): ' + err.message);
            alert('ERROR(' + err.code + '): ' + err.message);
        };
        if (navigator.geolocation) {
            navigator.geolocation.getCurrentPosition(success, error, posOptions);
        }
    }
</script>

<script>
    function submitLocate(){
        const lat = document.getElementById("LAT").value;
        const lnt = document.getElementById("LNT").value;
        document.getElementById("hiddenLAT").value = lat;
        document.getElementById("hiddenLNT").value = lnt;
    }
</script>

<br/> <br/>

<style type="text/css">
    .tg  {border-collapse:collapse;border-spacing:0;width:100%;}
    .tg td{border-color:black;border-style:solid;border-width:1px;font-family:Arial, sans-serif;font-size:14px;
        overflow:hidden;padding:10px 5px;word-break:normal;}
    .tg th{border-color:black;border-style:solid;border-width:1px;font-family:Arial, sans-serif;font-size:14px;
        font-weight:normal;overflow:hidden;padding:10px 5px;word-break:normal;}
    .tg .tg-8zms{background-color:#00ab70;border-color:#ffffff;color:#ffffff;font-size:100%;text-align:center;vertical-align:top}
    .tg .tg-zet1{border-color:#ffffff;font-family:Tahoma, Geneva, sans-serif !important;font-size:100%;text-align:center;
        vertical-align:top}
</style>
<table class="tg">
    <thead>
    <tr>
        <th class="tg-8zms">거리(Km)</th>
        <th class="tg-8zms">관리번호</th>
        <th class="tg-8zms">자치구</th>
        <th class="tg-8zms">와이파이명</th>
        <th class="tg-8zms">도로명주소</th>
        <th class="tg-8zms">상세주소</th>
        <th class="tg-8zms">설치위치(층)</th>
        <th class="tg-8zms">설치유형</th>
        <th class="tg-8zms">설치기관</th>
        <th class="tg-8zms">서비스구분</th>
        <th class="tg-8zms">망종류</th>
        <th class="tg-8zms">설치년도</th>
        <th class="tg-8zms">실내외구분</th>
        <th class="tg-8zms">WIFI 접속환경</th>
        <th class="tg-8zms">X좌표</th>
        <th class="tg-8zms">Y좌표</th>
        <th class="tg-8zms">작업일자</th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <td class="tg-zet1" colspan="17">위치 정보를 입력한 후에 조회해 주세요.</td>
    </tr>
    </tbody>
</table>

</body>
</html>