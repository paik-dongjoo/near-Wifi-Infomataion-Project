package javasource;

import java.io.IOException;
import java.util.List;

public class WifiInfoService {

    public static void main(String[] args) throws IOException {
//        LoadWifiInfo lwi = new LoadWifiInfo();
//        lwi.loadWifi();

        FindNearWifi fnw = new FindNearWifi();
        double distance = fnw.nearWifi(	36.1156512, 	127.7881632, 37.49118, 127.05655);
        distance = Math.round(distance*10000) / 10000.0;
        System.out.println(distance + "km");
//
//        AddHistory ah = new AddHistory();
//        ah.getHistoryList();

//        FindNearWifi fnw = new FindNearWifi();
//        List<Wifi> list = fnw.getWifiList();
//        System.out.println(list.size());
//        for (int i = 0; i < list.size(); i++) {
//            System.out.println(list.get(i).getX_SWIFI_MGR_NO());
//        }
    }

}
