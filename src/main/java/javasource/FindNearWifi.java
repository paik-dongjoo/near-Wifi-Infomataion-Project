package javasource;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FindNearWifi {

    public double nearWifi(double MyLat, double MyLnt, double WifiLat, double WifiLnt){
        double distance = 0;

        double theta = MyLnt - WifiLnt;
        distance = Math.sin(deg2rad(MyLat)) * Math.sin(deg2rad(WifiLat)) + Math.cos(deg2rad(MyLat)) * Math.cos(deg2rad(WifiLat)) * Math.cos(deg2rad(theta));

        distance = Math.acos(distance);
        distance = rad2deg(distance);
        distance = distance * 60 * 1.1515;

        distance = distance * 1.609344;

        distance = Math.round(distance*10000) / 10000.0;

        return distance;
    }

    // This function converts decimal degrees to radians
    private static double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    // This function converts radians to decimal degrees
    private static double rad2deg(double rad) {
        return (rad * 180 / Math.PI);
    }

    public List<Wifi> getWifiList() throws IOException {
        List<Wifi> list = new ArrayList<>();
        LoadWifiInfo lwi = new LoadWifiInfo();
        int repeat = lwi.wifiCnt();

        Connection connection = null;

        try {
            Class.forName("org.sqlite.JDBC");

            connection = DriverManager.getConnection("jdbc:sqlite:/Users/dongjoo/dev/sqlite3/Mission1.sqlite");
            PreparedStatement preparedStatement = null;
            ResultSet rs = null;

            String sql = "select * " +
                    "from Wifi_Info; ";

            preparedStatement = connection.prepareStatement(sql);
            rs = preparedStatement.executeQuery();

            while(rs.next()){
                Wifi wifi = new Wifi();

                wifi.setX_SWIFI_MGR_NO(rs.getString("X_SWIFI_MGR_NO"));
                wifi.setX_SWIFI_WRDOFC(rs.getString("X_SWIFI_WRDOFC"));
                wifi.setX_SWIFI_MAIN_NM(rs.getString("X_SWIFI_MAIN_NM"));
                wifi.setX_SWIFI_ADRES1(rs.getString("X_SWIFI_ADRES1"));
                wifi.setX_SWIFI_ADRES2(rs.getString("X_SWIFI_ADRES2"));
                wifi.setX_SWIFI_INSTL_FLOOR(rs.getString("X_SWIFI_INSTL_FLOOR"));
                wifi.setX_SWIFI_INSTL_TY(rs.getString("X_SWIFI_INSTL_TY"));
                wifi.setX_SWIFI_INSTL_MBY(rs.getString("X_SWIFI_INSTL_MBY"));
                wifi.setX_SWIFI_SVC_SE(rs.getString("X_SWIFI_SVC_SE"));
                wifi.setX_SWIFI_CMCWR(rs.getString("X_SWIFI_CMCWR"));
                wifi.setX_SWIFI_CNSTC_YEAR(rs.getString("X_SWIFI_CNSTC_YEAR"));
                wifi.setX_SWIFI_INOUT_DOOR(rs.getString("X_SWIFI_INOUT_DOOR"));
                wifi.setX_SWIFI_REMARS3(rs.getString("X_SWIFI_REMARS3"));
                wifi.setLAT(rs.getDouble("LAT"));
                wifi.setLNT(rs.getDouble("LNT"));
                wifi.setWORK_DTTM(rs.getString("WORK_DTTM"));

                list.add(wifi);
            }

//                int affectedRows = preparedStatement.executeUpdate();

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

        return list;
    }

}
