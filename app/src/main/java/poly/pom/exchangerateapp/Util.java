package poly.pom.exchangerateapp;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by User on 31/8/2016.
 */
public class Util {

    static public String getTodayDate(){
        Calendar c = Calendar.getInstance();
        SimpleDateFormat format=new SimpleDateFormat("yyyyMMdd");
        String result = format.format(c.getTime());


        return result;
    }
}
