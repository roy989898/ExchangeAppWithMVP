package poly.pom.exchangerateapp;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by User on 31/8/2016.
 */
public class Util {

    static public int getTodayDate(){
        Calendar c = Calendar.getInstance();
        SimpleDateFormat format=new SimpleDateFormat("yyyyMMdd");
        String result = format.format(c.getTime());


        return Integer.parseInt(result);
    }
}
