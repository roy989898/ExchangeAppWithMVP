package poly.pom.exchangerateapp.testUsed;

import javax.inject.Singleton;

import dagger.Component;
import poly.pom.exchangerateapp.PresenterComponent;
import poly.pom.exchangerateapp.PresenterModule;
import poly.pom.exchangerateapp.view.ExchangeFargment;
import poly.pom.exchangerateapp.view.ExchangeView;


@Singleton
@Component(modules = {TestModule.class})
public interface TestComponent extends PresenterComponent {
    void inject(ExchangeFargment target);
}
