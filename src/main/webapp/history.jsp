<%@ page import="javasource.AddHistory" %>
<%@ page import="javasource.WifiHistory" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
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

    AddHistory ah = new AddHistory();

%>

<style type="text/css">
    .tg  {border-collapse:collapse;border-spacing:0;width:100%;}
    .tg td{border-color:black;border-style:solid;border-width:1px;font-family:Arial, sans-serif;font-size:14px;
        overflow:hidden;padding:10px 5px;word-break:normal;}
    .tg tr{border-color:black;border-style:solid;border-width:1px;font-family:Arial, sans-serif;font-size:14px;
        overflow:hidden;padding:10px 5px;word-break:normal;}
    .tg th{border-color:black;border-style:solid;border-width:1px;font-family:Arial, sans-serif;font-size:14px;
        font-weight:normal;overflow:hidden;padding:10px 5px;word-break:normal;}
    .tg .tg-8zms{background-color:#00ab70;border-color:#ffffff;color:#ffffff;font-size:100%;text-align:center;vertical-align:top}
    .tg .tg-zet1{border-color:#ffffff;font-family:Tahoma, Geneva, sans-serif !important;font-size:100%;text-align:center;
        vertical-align:top}
</style>

<%
    List<WifiHistory> wifiHistoryList = ah.getHistoryList();
    System.out.println(wifiHistoryList.size());
%>

<table class="tg">
    <thead>
    <tr>
        <th class="tg-8zms">ID</th>
        <th class="tg-8zms">X좌표</th>
        <th class="tg-8zms">Y좌표</th>
        <th class="tg-8zms">조회일자</th>
        <th class="tg-8zms">비고</th>
    </tr>
    </thead>
    <tbody>
    <%
        for (int i = wifiHistoryList.size()-1; i >= 0; i--) {
    %>
    <tr>
        <td>
            <%= wifiHistoryList.get(i).getId() %>
        </td>
        <td>
            <%= wifiHistoryList.get(i).getLAT() %>
        </td>
        <td>
            <%= wifiHistoryList.get(i).getLNT() %>
        </td>
        <td>
            <%= wifiHistoryList.get(i).getCheckDate() %>
        </td>
        <td align="center">
            <button id="deleteHistory">삭제</button>
        </td>
    </tr>
    <%
        }
    %>

    </tbody>
</table>

</body>
</html>
