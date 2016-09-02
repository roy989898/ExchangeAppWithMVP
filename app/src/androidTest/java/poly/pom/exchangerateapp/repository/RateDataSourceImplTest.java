package poly.pom.exchangerateapp.repository;

import android.support.test.filters.LargeTest;
import android.support.test.runner.AndroidJUnit4;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import poly.pom.exchangerateapp.repository.RealmModule.Rate;
import poly.pom.exchangerateapp.repository.RetrofitModule.Bank;
import poly.pom.exchangerateapp.repository.RetrofitModule.FixerIOAPI;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.observers.TestSubscriber;
import rx.schedulers.Schedulers;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

/**
 * Created by User on 31/8/2016.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class RateDataSourceImplTest {
    private RateDataSource dataSource;
    private Boolean success = false;
    private int count = 0;

    @Before
    public void setUp() throws Exception {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.fixer.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        FixerIOAPI fixerIOAPi = retrofit.create(FixerIOAPI.class);
        Observable<Bank> call = fixerIOAPi.loadLatestEeurBaseRate();

        dataSource = new RateDataSourceImpl();
        dataSource.setBankAPI(call);

//        dataSource.deleteAllRate();


    }


    private void deleteAll() {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.where(Rate.class).findAll().deleteAllFromRealm();
        realm.commitTransaction();

//ensure alldelete

        RealmQuery<Rate> query = realm.where(Rate.class);
        RealmResults<Rate> result = query.findAll();
        Assert.assertThat(query.count(), Matchers.is(0L));

        realm.close();
    }


    @Test
    public void testRefreshData() throws Exception {
        deleteAll();

        Realm realm = Realm.getDefaultInstance();


        TestSubscriber<Boolean> subscriber = new TestSubscriber<>();
//        write at here


        dataSource.refreshData().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);

        subscriber.awaitTerminalEvent();
        subscriber.assertNoErrors();
        assertTrue(subscriber.getOnNextEvents().get(0));
        Thread.sleep(1000);

        RealmQuery<Rate> query2 = realm.where(Rate.class);
        RealmResults<Rate> result2 = query2.findAll();
        Assert.assertThat(query2.count(), Matchers.is(31L));
        for (int i = 0; i < query2.count(); i++) {
            Rate rate = result2.get(0);
            assertNotNull(rate.getName());
            assertNotNull(rate.getRateBaseEur());
            assertNotNull(rate.getUpdateDate());
        }


        realm.close();

        deleteAll();



    }

    @Test
    public void convertValueTestwithCacheandOnline() throws Exception {
//        trun on the device wifi
        TestSubscriber<Double> suber=new TestSubscriber<>();
        dataSource.convertValue(CountryName.HKD,CountryName.JPY,1).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(suber);
        suber.awaitTerminalEvent();
        suber.assertNoErrors();
        Double result = suber.getOnNextEvents().get(0);
        Assert.assertThat(result,Matchers.not(0D));
        Assert.assertThat(result,Matchers.not(-1D));

    }
    @Test
    public void convertValueTestwithCacheandOffnline() throws Exception {
//        trun off the device wifi
        TestSubscriber<Double> suber=new TestSubscriber<>();
        dataSource.convertValue(CountryName.HKD,CountryName.JPY,1).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(suber);
        suber.awaitTerminalEvent();
        suber.assertNoErrors();
        Double result = suber.getOnNextEvents().get(0);
        Assert.assertThat(result,Matchers.not(0D));
        Assert.assertThat(result,Matchers.not(-1D));

    }
    @Test
    public void convertValueTestwithNoCacheandOffnline() throws Exception {
//        trun off the device wifi
//        delete all cache firsdt
        deleteAll();


        TestSubscriber<Double> suber=new TestSubscriber<>();
        dataSource.convertValue(CountryName.HKD,CountryName.JPY,1).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(suber);
        suber.awaitTerminalEvent();
        suber.assertNoErrors();
        Double result = suber.getOnNextEvents().get(0);
        Assert.assertThat(result,Matchers.not(0D));
        Assert.assertThat(result,Matchers.is(-1D));

    }

    @Test
    public void convertValueTestwithNoCacheandOnline() throws Exception {
//        trun On the device wifi
//        delete all cache firsdt
        deleteAll();


        TestSubscriber<Double> suber=new TestSubscriber<>();
        dataSource.convertValue(CountryName.HKD,CountryName.JPY,1).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(suber);
        suber.awaitTerminalEvent();
        suber.assertNoErrors();
        Double result = suber.getOnNextEvents().get(0);
        Assert.assertThat(result,Matchers.not(0D));
        Assert.assertThat(result,Matchers.not(-1D));

    }
}