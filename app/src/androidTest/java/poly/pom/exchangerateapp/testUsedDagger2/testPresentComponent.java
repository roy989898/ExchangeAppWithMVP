package poly.pom.exchangerateapp.testUsedDagger2;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by User on 4/9/2016.
 */
@Singleton
@Component(modules = {testPresenterModule.class})
public interface testPresentComponent {

}
