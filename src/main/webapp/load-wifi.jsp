<%@ page import="javasource.LoadWifiInfo" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <title>와이파이 정보 구하기</title>
</head>
<style>
    body {text-align: center;}
</style>
<body>

<%
    int totalWifi = 0;
    LoadWifiInfo lwi = new LoadWifiInfo();
    lwi.resetWifiInfo();
    totalWifi = lwi.loadWifi();
%>

<h1><%=totalWifi%>개의 WIFI 정보를 정상적으로 저장하였습니다.</h1>
<a href="index.jsp">홈으로 가기</a>
</body>
</html>
