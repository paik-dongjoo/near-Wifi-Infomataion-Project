<%@ page import="javasource.AddHistory" %>
<%@ page import="javasource.FindNearWifi" %>
<%@ page import="javasource.Wifi" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Comparator" %>
<%@ page import="java.util.Collections" %>
<%@ page import="javasource.ListComparator" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>

<head>
    <title>와이파이 정보 구하기</title>
</head>

<body>
<h1>위치 히스토리 목록
</h1>

<a href="index.jsp">홈</a> |
<a href="history.jsp">위치 히스토리 목록</a> |
<a href="load-wifi.jsp">Open API 와이파이 정보 가져오기</a>
<br/> <br/>

<%

    double lat = Double.parseDouble(request.getParameter("hiddenLAT"));
    double lnt = Double.parseDouble(request.getParameter("hiddenLNT"));

    AddHistory ah = new AddHistory();
    ah.addHistory(lat, lnt);

    FindNearWifi fnw = new FindNearWifi();
    List<Wifi> wifiList = fnw.getWifiList();

    double distance = 0;
    for (int i = 0; i < wifiList.size(); i++) {
        distance = fnw.nearWifi(lat, lnt, wifiList.get(i).getLAT(), wifiList.get(i).getLNT());
        wifiList.get(i).setDistance(distance);
    }

    Collections.sort(wifiList, new ListComparator());

%>

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

    <%
        for (int i = 0; i < 20; i++) {
    %>
    <tr>

        <td>
            <%=  wifiList.get(i).getDistance() %>
        </td>
        <td>
            <%=  wifiList.get(i).getX_SWIFI_MGR_NO() %>
        </td>
        <td>
            <%=  wifiList.get(i).getX_SWIFI_WRDOFC() %>
        </td>
        <td>
            <%=  wifiList.get(i).getX_SWIFI_MAIN_NM() %>
        </td>
        <td>
            <%=  wifiList.get(i).getX_SWIFI_ADRES1() %>
        </td>
        <td>
            <%=  wifiList.get(i).getX_SWIFI_ADRES2() %>
        </td>
        <td>
            <%=  wifiList.get(i).getX_SWIFI_INSTL_FLOOR() %>
        </td>
        <td>
            <%=  wifiList.get(i).getX_SWIFI_INSTL_TY() %>
        </td>
        <td>
            <%=  wifiList.get(i).getX_SWIFI_INSTL_MBY() %>
        </td>
        <td>
            <%=  wifiList.get(i).getX_SWIFI_SVC_SE() %>
        </td>
        <td>
            <%=  wifiList.get(i).getX_SWIFI_CMCWR() %>
        </td>
        <td>
            <%=  wifiList.get(i).getX_SWIFI_CNSTC_YEAR() %>
        </td>
        <td>
            <%=  wifiList.get(i).getX_SWIFI_INOUT_DOOR() %>
        </td>
        <td>
            <%=  wifiList.get(i).getX_SWIFI_REMARS3() %>
        </td>
        <td>
            <%=  wifiList.get(i).getLAT() %>
        </td>
        <td>
            <%=  wifiList.get(i).getLNT() %>
        </td>
        <td>
            <%=  wifiList.get(i).getWORK_DTTM() %>
        </td>

    </tr>

    <%
        }
    %>
    </tbody>
</table>

</body>
</html>