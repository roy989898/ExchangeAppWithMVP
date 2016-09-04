package poly.pom.exchangerateapp.testUsedDagger2;

import poly.pom.exchangerateapp.MyApplication;
import poly.pom.exchangerateapp.PresenterComponent;
import poly.pom.exchangerateapp.testUsed.DaggerTestComponent;
import poly.pom.exchangerateapp.testUsed.TestModule;

/**
 * Created by User on 4/9/2016.
 */
public class TestApplication extends MyApplication {
    @Override
    public PresenterComponent getComponent() {
        return DaggerTestComponent.builder().testModule(new TestModule()).build();
    }
}
