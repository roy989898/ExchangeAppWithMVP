package poly.pom.exchangerateapp.view;


import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import poly.pom.exchangerateapp.R;
import poly.pom.exchangerateapp.repository.CountryName;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;

@LargeTest
public class UiTest {
    @Before
    public void setUp() throws Exception {
        onView(withId(R.id.btDel)).perform(click());

    }

    @Rule
    public ActivityTestRule<MainActivity> rule=new ActivityTestRule(MainActivity.class);
    @Test
    public void testTheCalculatorButton() throws Exception {
        onView(withId(R.id.edMoney1)).perform(click());
        onView(withId(R.id.bt9)).perform(click());
        onView(withId(R.id.edMoney1)).check(matches(withText("9")));
        onView(withId(R.id.btDel)).perform(click());

        onView(withId(R.id.bt8)).perform(click());
        onView(withId(R.id.edMoney1)).check(matches(withText("8")));
        onView(withId(R.id.btDel)).perform(click());

        onView(withId(R.id.bt7)).perform(click());
        onView(withId(R.id.edMoney1)).check(matches(withText("7")));
        onView(withId(R.id.btDel)).perform(click());

        onView(withId(R.id.bt6)).perform(click());
        onView(withId(R.id.edMoney1)).check(matches(withText("6")));
        onView(withId(R.id.btDel)).perform(click());

        onView(withId(R.id.bt5)).perform(click());
        onView(withId(R.id.edMoney1)).check(matches(withText("5")));
        onView(withId(R.id.btDel)).perform(click());

        onView(withId(R.id.bt4)).perform(click());
        onView(withId(R.id.edMoney1)).check(matches(withText("4")));
        onView(withId(R.id.btDel)).perform(click());

        onView(withId(R.id.bt3)).perform(click());
        onView(withId(R.id.edMoney1)).check(matches(withText("3")));
        onView(withId(R.id.btDel)).perform(click());

        onView(withId(R.id.bt2)).perform(click());
        onView(withId(R.id.edMoney1)).check(matches(withText("2")));
        onView(withId(R.id.btDel)).perform(click());

        onView(withId(R.id.bt1)).perform(click());
        onView(withId(R.id.edMoney1)).check(matches(withText("1")));
        onView(withId(R.id.btDel)).perform(click());

        onView(withId(R.id.bt0)).perform(click());
        onView(withId(R.id.edMoney1)).check(matches(withText("0")));
        onView(withId(R.id.btDel)).perform(click());

        onView(withId(R.id.btPoint)).perform(click());
        onView(withId(R.id.edMoney1)).check(matches(withText(".")));
        onView(withId(R.id.btDel)).perform(click());

        onView(withId(R.id.btDivide)).perform(click());
        onView(withId(R.id.edMoney1)).check(matches(withText("/")));
        onView(withId(R.id.btDel)).perform(click());

        onView(withId(R.id.btMinu)).perform(click());
        onView(withId(R.id.edMoney1)).check(matches(withText("-")));
        onView(withId(R.id.btDel)).perform(click());

        onView(withId(R.id.btTimes)).perform(click());
        onView(withId(R.id.edMoney1)).check(matches(withText("*")));
        onView(withId(R.id.btDel)).perform(click());

        onView(withId(R.id.btPlus)).perform(click());
        onView(withId(R.id.edMoney1)).check(matches(withText("+")));
        onView(withId(R.id.btDel)).perform(click());
    }

    @Test
    public void tetsTheSpinner() throws Exception {

        String[] countryName= CountryName.getCountryNameArray();

        for(String name:countryName){
            onView(withId(R.id.spCountry1)).perform(click());
            onData(allOf(is(name),is(instanceOf(String.class)))).perform(click());
        }

        for(String name:countryName){
            onView(withId(R.id.spCountry2)).perform(click());
            onData(allOf(is(name),is(instanceOf(String.class)))).perform(click());
        }


    }



    @Test
    public void testTheCalculation() throws Exception {
        onView(withId(R.id.edMoney1)).perform(click());

        onView(withId(R.id.bt1)).perform(click());
        onView(withId(R.id.btPlus)).perform(click());
        onView(withId(R.id.bt4)).perform(click());
        onView(withId(R.id.btOk)).perform(click());
        onView(withId(R.id.edMoney1)).check(matches(withText("5.0")));
    }

    @Test
    public void testDisplayAreaChange() throws Exception {

        onView(withId(R.id.edMoney1)).perform(click());
        onView(withId(R.id.bt1)).perform(click());
        onView(withId(R.id.edMoney1)).check(matches(withText("1")));

        onView(withId(R.id.btDel)).perform(click());

        onView(withId(R.id.edMoney2)).perform(click());
        onView(withId(R.id.bt1)).perform(click());
        onView(withId(R.id.edMoney2)).check(matches(withText("1")));

    }

    @Test
    public void testTheExchange() throws Exception {
        onView(withId(R.id.spCountry1)).perform(click());
        onData(allOf(is("HKD"),is(instanceOf(String.class)))).perform(click());

        onView(withId(R.id.spCountry2)).perform(click());
        onData(allOf(is("JPY"),is(instanceOf(String.class)))).perform(click());

        onView(withId(R.id.edMoney1)).perform(click());

        onView(withId(R.id.bt9)).perform(click());
        onView(withId(R.id.bt1)).perform(click());

        onView(withId(R.id.btOk)).perform(click());

        onView(withId(R.id.edMoney2)).check(matches(withText("99.0")));


    }
}

