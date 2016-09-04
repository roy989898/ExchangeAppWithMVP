package poly.pom.exchangerateapp.testUsedDagger2;

import org.mockito.Mockito;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import poly.pom.exchangerateapp.presenter.ExchangePresenter;
import poly.pom.exchangerateapp.presenter.ExchangePresenterImpl;
import poly.pom.exchangerateapp.repository.RateDataSource;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static org.mockito.Matchers.anyDouble;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@Module
public class testPresenterModule {
    @Provides
    @Singleton
    RateDataSource provideRateDataSource(){
        RateDataSource mockDataSource = mock(RateDataSource.class);
        when(mockDataSource.refreshData()).thenReturn(Observable.just(true));
        when(mockDataSource.convertValue(anyString(),anyString(),anyDouble())).thenReturn(Observable.just(10d));

        return mockDataSource;

    }
    @Provides
    ExchangePresenter provideExchangePresenter(RateDataSource mockDataSource) {
        return new ExchangePresenterImpl(mockDataSource, Schedulers.io(), AndroidSchedulers.mainThread());
    }

}
