package poly.pom.exchangerateapp.testUsed;


import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import poly.pom.exchangerateapp.PresenterModule;
import poly.pom.exchangerateapp.presenter.ExchangePresenter;
import poly.pom.exchangerateapp.presenter.ExchangePresenterImpl;
import poly.pom.exchangerateapp.repository.RateDataSource;
import poly.pom.exchangerateapp.repository.RateDataSourceImpl;
import poly.pom.exchangerateapp.repository.RetrofitModule.Bank;
import poly.pom.exchangerateapp.repository.RetrofitModule.FixerIOAPI;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static org.mockito.Matchers.anyDouble;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@Module
public class TestModule  {
    @Provides
    @Singleton
    RateDataSource provideRateDataSource() {
        RateDataSource dataSource2 = mock(RateDataSource.class);
        when(dataSource2.refreshData()).thenReturn(Observable.just(true));
        when(dataSource2.convertValue(anyString(),anyString(),anyDouble())).thenReturn(Observable.just(99D));
        return dataSource2;

    }

    @Provides
    ExchangePresenter provideExchangePresenter(RateDataSource datasource) {
        return new ExchangePresenterImpl(datasource, Schedulers.io(), AndroidSchedulers.mainThread());
    }
}
