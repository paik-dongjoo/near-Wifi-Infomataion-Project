package javasource;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AddHistory {

    public void addHistory(double lat, double lnt){

        if(lat != 0 && lnt != 0){
            Connection connection = null;

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date now = new Date();
            String nowTime1 = sdf.format(now);

            try {
                Class.forName("org.sqlite.JDBC");

                connection = DriverManager.getConnection("jdbc:sqlite:/Users/dongjoo/dev/sqlite3/Mission1.sqlite");
                PreparedStatement preparedStatement = null;
                ResultSet rs = null;

                String sql = "insert into history (LNT, LAT, CHECK_DATE)" +
                        "values (?, ?, ?); ";

                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setDouble(1, lat);
                preparedStatement.setDouble(2, lnt);
                preparedStatement.setString(3, nowTime1);

                int affectedRows = preparedStatement.executeUpdate();

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

    }

    public List<WifiHistory> getHistoryList(){
        List<WifiHistory> historyList = new ArrayList<>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try {
            Class.forName("org.sqlite.JDBC");

            connection = DriverManager.getConnection("jdbc:sqlite:/Users/dongjoo/dev/sqlite3/Mission1.sqlite");

            String sql = "select ID, LAT, LNT, CHECK_DATE " +
                    "from history; ";

            preparedStatement = connection.prepareStatement(sql);
            rs = preparedStatement.executeQuery();

            while(rs.next()){
                WifiHistory wh = new WifiHistory();

                wh.setId(rs.getInt("ID"));
                wh.setLAT(rs.getDouble("LAT"));
                wh.setLNT(rs.getDouble("LNT"));
                wh.setCheckDate(rs.getString("CHECK_DATE"));

                historyList.add(wh);
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

        return historyList;
    }

}
