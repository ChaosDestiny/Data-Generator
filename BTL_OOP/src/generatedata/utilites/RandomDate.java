package generatedata.utilites;

import java.text.SimpleDateFormat;
import java.util.Date;

public class RandomDate {

    public static String genData(long ms) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(ms);
        return formatter.format(date);
    }
}