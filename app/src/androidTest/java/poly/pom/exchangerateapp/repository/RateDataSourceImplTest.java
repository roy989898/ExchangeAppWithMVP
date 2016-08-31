package poly.pom.exchangerateapp.repository;

import android.provider.Telephony;
import android.support.test.filters.LargeTest;
import android.support.test.runner.AndroidJUnit4;

import org.awaitility.Awaitility;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;


import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import poly.pom.exchangerateapp.repository.RealmModule.Rate;
import poly.pom.exchangerateapp.repository.RetrofitModule.Bank;
import poly.pom.exchangerateapp.repository.RetrofitModule.FixerIOAPI;
import retrofit2.Call;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNotSame;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Created by User on 31/8/2016.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class RateDataSourceImplTest {
    private RateDataSource dataSource;
    private Boolean success=false;

    @Before
    public void setUp() throws Exception {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.fixer.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        FixerIOAPI fixerIOAPi = retrofit.create(FixerIOAPI.class);
        Call<Bank> call = fixerIOAPi.loadLatestEeurBaseRate();

        dataSource = new RateDataSourceImpl();
        dataSource.setBankAPI(call);

        dataSource.deleteAllRate();


    }

    @Test
    public void testRefreshData() throws Exception {


        final Realm realm = Realm.getDefaultInstance();

        dataSource.refreshData(new RateDataSource.RefreshCallback() {
            @Override
            public void refreshSuccess() {
                success=true;
            }

            @Override
            public void refreshFail(String error) {

            }
        });
        Awaitility.await().atMost(10,TimeUnit.SECONDS).until(new Callable<Boolean>() {

            @Override
            public Boolean call() throws Exception {

                return success;
            }

        });
        RealmQuery<Rate> query = realm.where(Rate.class);
        RealmResults<Rate> result = query.findAll();
        for(int i=0;i<realm.where(Rate.class).count();i++){
            Rate rate = result.get(0);
            assertNotNull(rate.getName());
            assertNotNull(rate.getRateBaseEur());
            assertNotNull(rate.getUpdateDate());
        }

        dataSource.deleteAllRate();



    }
}