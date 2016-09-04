package poly.pom.exchangerateapp;


import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
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

@Module
public class PresenterModule {
    @Provides
    @Singleton
    RateDataSource provideRateDataSource() {
        RateDataSource dataSource = new RateDataSourceImpl();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.fixer.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        FixerIOAPI fixerIOAPi = retrofit.create(FixerIOAPI.class);
        Observable<Bank> call = fixerIOAPi.loadLatestEeurBaseRate();
        dataSource.setBankAPI(call);
        return dataSource;
    }

    @Provides
    ExchangePresenter provideExchangePresenter(RateDataSource datasource) {
        return new ExchangePresenterImpl(datasource, Schedulers.io(), AndroidSchedulers.mainThread());
    }
}
