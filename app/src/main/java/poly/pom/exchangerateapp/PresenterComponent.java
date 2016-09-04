package poly.pom.exchangerateapp;

import javax.inject.Singleton;

import dagger.Component;
import poly.pom.exchangerateapp.view.ExchangeFargment;
import poly.pom.exchangerateapp.view.ExchangeView;

/**
 * Created by User on 4/9/2016.
 */
@Singleton
@Component(modules = {PresenterModule.class})
public interface PresenterComponent {
    void inject(ExchangeFargment target);
}
