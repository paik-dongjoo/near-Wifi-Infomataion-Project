package javasource;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LoadWifiInfo {

    public void resetWifiInfo(){
        Connection connection = null;

        try {
            Class.forName("org.sqlite.JDBC");

            connection = DriverManager.getConnection("jdbc:sqlite:/Users/dongjoo/dev/sqlite3/Mission1.sqlite");
            Statement statement = connection.createStatement();
            int resultData = statement.executeUpdate("delete from Wifi_Info");
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if(connection != null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public void openApi(int startIdx, int endIdx) throws IOException {

        StringBuilder urlBuilder = new StringBuilder("http://openapi.seoul.go.kr:8088");
        urlBuilder.append("/" + URLEncoder.encode("496968595864356e3437427a6e4948","UTF-8") );
        urlBuilder.append("/" + URLEncoder.encode("json","UTF-8") );
        urlBuilder.append("/" + URLEncoder.encode("TbPublicWifiInfo","UTF-8"));
        urlBuilder.append("/" + URLEncoder.encode(String.valueOf(startIdx),"UTF-8"));
        urlBuilder.append("/" + URLEncoder.encode(String.valueOf(endIdx),"UTF-8"));

        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;

        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }

        StringBuilder sb = new StringBuilder();
        String line;

        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }

        rd.close();
        conn.disconnect();
        String result = sb.toString();

        JsonParser jsonParser = new JsonParser();
        Object obj = jsonParser.parse(result);
        JsonObject jsonObject = (JsonObject) obj;
        obj = jsonObject.getAsJsonObject("TbPublicWifiInfo");

        JsonArray jsonArray = ((JsonObject) obj).getAsJsonArray("row");


        List<Wifi> wifiList = new ArrayList<>();

        for (int i = 0; i < jsonArray.size(); i++) {

            Wifi wifi = new Wifi();
            JsonObject jObj = (JsonObject) jsonArray.get(i);
            wifi.setX_SWIFI_MGR_NO(String.valueOf(jObj.get("X_SWIFI_MGR_NO").getAsString()));
            wifi.setX_SWIFI_WRDOFC(String.valueOf(jObj.get("X_SWIFI_WRDOFC").getAsString()));
            wifi.setX_SWIFI_MAIN_NM(String.valueOf(jObj.get("X_SWIFI_MAIN_NM").getAsString()));
            wifi.setX_SWIFI_ADRES1(String.valueOf(jObj.get("X_SWIFI_ADRES1").getAsString()));
            wifi.setX_SWIFI_ADRES2(String.valueOf(jObj.get("X_SWIFI_ADRES2").getAsString()));
            wifi.setX_SWIFI_INSTL_FLOOR(String.valueOf(jObj.get("X_SWIFI_INSTL_FLOOR").getAsString()));
            wifi.setX_SWIFI_INSTL_TY(String.valueOf(jObj.get("X_SWIFI_INSTL_TY").getAsString()));
            wifi.setX_SWIFI_INSTL_MBY(String.valueOf(jObj.get("X_SWIFI_INSTL_MBY").getAsString()));
            wifi.setX_SWIFI_SVC_SE(String.valueOf(jObj.get("X_SWIFI_SVC_SE").getAsString()));
            wifi.setX_SWIFI_CMCWR(String.valueOf(jObj.get("X_SWIFI_CMCWR").getAsString()));
            wifi.setX_SWIFI_CNSTC_YEAR(String.valueOf(jObj.get("X_SWIFI_CNSTC_YEAR").getAsString()));
            wifi.setX_SWIFI_INOUT_DOOR(String.valueOf(jObj.get("X_SWIFI_INOUT_DOOR").getAsString()));
            wifi.setX_SWIFI_REMARS3(String.valueOf(jObj.get("X_SWIFI_REMARS3").getAsString()));
            wifi.setLAT(Double.parseDouble(jObj.get("LAT").getAsString()));
            wifi.setLNT(Double.parseDouble(jObj.get("LNT").getAsString()));
            wifi.setWORK_DTTM(String.valueOf(jObj.get("WORK_DTTM")));

            wifiList.add(wifi);
        }

        Connection connection = null;

        try {
            Class.forName("org.sqlite.JDBC");

            connection = DriverManager.getConnection("jdbc:sqlite:/Users/dongjoo/dev/sqlite3/Mission1.sqlite");
            PreparedStatement preparedStatement = null;
            ResultSet rs = null;

            for (int i = 0; i < wifiList.size(); i++) {

                String sql = "insert into Wifi_Info " +
                        "values " +
                        "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " +
                        "?, ?, ?); ";

                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, wifiList.get(i).getX_SWIFI_MGR_NO());
                preparedStatement.setString(2, wifiList.get(i).getX_SWIFI_WRDOFC());
                preparedStatement.setString(3, wifiList.get(i).getX_SWIFI_MAIN_NM());
                preparedStatement.setString(4, wifiList.get(i).getX_SWIFI_ADRES1());
                preparedStatement.setString(5, wifiList.get(i).getX_SWIFI_ADRES2());
                preparedStatement.setString(6, wifiList.get(i).getX_SWIFI_INSTL_FLOOR());
                preparedStatement.setString(7, wifiList.get(i).getX_SWIFI_INSTL_TY());
                preparedStatement.setString(8, wifiList.get(i).getX_SWIFI_INSTL_MBY());
                preparedStatement.setString(9, wifiList.get(i).getX_SWIFI_SVC_SE());
                preparedStatement.setString(10, wifiList.get(i).getX_SWIFI_CMCWR());
                preparedStatement.setString(11, wifiList.get(i).getX_SWIFI_CNSTC_YEAR());
                preparedStatement.setString(12, wifiList.get(i).getX_SWIFI_INOUT_DOOR());
                preparedStatement.setString(13, wifiList.get(i).getX_SWIFI_REMARS3());
                preparedStatement.setDouble(14, wifiList.get(i).getLAT());
                preparedStatement.setDouble(15, wifiList.get(i).getLNT());
                preparedStatement.setString(16, wifiList.get(i).getWORK_DTTM());

                int affectedRows = preparedStatement.executeUpdate();

            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if(connection != null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public int loadWifi() throws IOException {
        List<Wifi> wifiList = new ArrayList<>();

        StringBuilder urlBuilder = new StringBuilder("http://openapi.seoul.go.kr:8088");
        urlBuilder.append("/" + URLEncoder.encode("496968595864356e3437427a6e4948","UTF-8") );
        urlBuilder.append("/" + URLEncoder.encode("json","UTF-8") );
        urlBuilder.append("/" + URLEncoder.encode("TbPublicWifiInfo","UTF-8"));
        urlBuilder.append("/" + URLEncoder.encode("1","UTF-8"));
        urlBuilder.append("/" + URLEncoder.encode("1","UTF-8"));

        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;

        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }

        StringBuilder sb = new StringBuilder();
        String line;

        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }

        rd.close();
        conn.disconnect();
        String result = sb.toString();

        JsonParser jsonParser = new JsonParser();
        Object obj = jsonParser.parse(result);
        JsonObject jsonObject = (JsonObject) obj;
        obj = jsonObject.getAsJsonObject("TbPublicWifiInfo");

        int totalCnt = Integer.parseInt(String.valueOf(((JsonObject) obj).get("list_total_count")));
        int repeat = totalCnt / 1000;
        if(totalCnt%1000 > 0){
            repeat++;
        }


        /* 필요한 횟수만큼 open api 호출 */
        int startIdx = 1;
        int endIdx = 1000;
        for (int i = 0; i < repeat; i++) {
            openApi(startIdx, endIdx);
            startIdx += 1000;
            endIdx += 1000;
        }

        return totalCnt;

    }

    public int wifiCnt() throws IOException {
        int cnt = 0;

        StringBuilder urlBuilder = new StringBuilder("http://openapi.seoul.go.kr:8088");
        urlBuilder.append("/" + URLEncoder.encode("496968595864356e3437427a6e4948","UTF-8") );
        urlBuilder.append("/" + URLEncoder.encode("json","UTF-8") );
        urlBuilder.append("/" + URLEncoder.encode("TbPublicWifiInfo","UTF-8"));
        urlBuilder.append("/" + URLEncoder.encode("1","UTF-8"));
        urlBuilder.append("/" + URLEncoder.encode("1","UTF-8"));

        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;

        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }

        StringBuilder sb = new StringBuilder();
        String line;

        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }

        rd.close();
        conn.disconnect();
        String result = sb.toString();

        JsonParser jsonParser = new JsonParser();
        Object obj = jsonParser.parse(result);
        JsonObject jsonObject = (JsonObject) obj;
        obj = jsonObject.getAsJsonObject("TbPublicWifiInfo");

        cnt = Integer.parseInt(String.valueOf(((JsonObject) obj).get("list_total_count")));

        return cnt;
    }

}
