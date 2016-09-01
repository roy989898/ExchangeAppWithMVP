package poly.pom.exchangerateapp;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by User on 31/8/2016.
 */
public class UtilTest {

    @Before
    public void setUp() throws Exception {


    }

    @Test
    public void testGetTodayDate() throws Exception {
        assertEquals(20160901,Util.getTodayDate());

    }
}