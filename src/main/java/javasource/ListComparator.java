package javasource;

import java.util.Comparator;

public class ListComparator implements Comparator<Wifi> {

    @Override
    public int compare(Wifi o1, Wifi o2) {

        double distance1 = o1.getDistance();
        double distance2 = o2.getDistance();

        if(distance1 > distance2){
            return 1;
        } else if(distance1 < distance2){
            return -1;
        } else {
            return 0;
        }
    }
}
