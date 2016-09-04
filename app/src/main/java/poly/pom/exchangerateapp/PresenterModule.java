package poly.pom.exchangerateapp;


import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import poly.pom.exchangerateapp.presenter.ExchangePresenter;
import poly.pom.exchangerateapp.presenter.ExchangePresenterImpl;
import poly.pom.exchangerateapp.repository.RateDataSource;
import poly.pom.exchangerateapp.repository.RateDataSourceImpl;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

@Module
public class PresenterModule {
    @Provides
    @Singleton
    RateDataSource provideRateDataSource() {
        return new RateDataSourceImpl();
    }

    @Provides
    ExchangePresenter provideExchangePresenter(RateDataSource datasource) {
        return new ExchangePresenterImpl(datasource, Schedulers.io(), AndroidSchedulers.mainThread());
    }
}
