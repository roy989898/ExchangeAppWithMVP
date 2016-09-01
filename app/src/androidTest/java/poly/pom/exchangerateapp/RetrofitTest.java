package poly.pom.exchangerateapp;


import android.app.Application;
import android.support.test.filters.LargeTest;
import android.support.test.runner.AndroidJUnit4;
import android.test.ApplicationTestCase;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import poly.pom.exchangerateapp.repository.RetrofitModule.Bank;
import poly.pom.exchangerateapp.repository.RetrofitModule.FixerIOAPI;
import poly.pom.exchangerateapp.repository.RetrofitModule.Rates;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.GsonConverterFactory;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static org.awaitility.Awaitility.await;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.not;



/**
 * Created by User on 31/8/2016.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class RetrofitTest extends ApplicationTestCase<Application> {



    private Bank bank;

    public RetrofitTest() {
        super(Application.class);
    }

    @Before
    public void setUp() throws Exception {
        super.setUp();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.fixer.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        FixerIOAPI fixerIOAPi = retrofit.create(FixerIOAPI.class);
        Observable<Bank> call = fixerIOAPi.loadLatestEeurBaseRate();

        call.observeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Bank>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {




            }

            @Override
            public void onNext(Bank rbank) {
                bank=rbank;
            }
        });


        await().atMost(5, TimeUnit.SECONDS).until(new Callable<Boolean>() {

            @Override
            public Boolean call() throws Exception {
                return bank != null;
            }
        });


    }

    @Test
    public void testBankNotNull() throws IOException {

        assertNotNull(bank);

    }

    @Test
    public void shoulBiggerthanZerForAllTheRate() throws Exception {
        Rates rates = bank.getRates();
        assertNotNull(rates);

        assertNotNull(rates.getAUD());
        assertNotNull(rates.getBGN());
        assertNotNull(rates.getBRL());
        assertNotNull(rates.getCAD());
        assertNotNull(rates.getCHF());
        assertNotNull(rates.getCNY());
        assertNotNull(rates.getCZK());
        assertNotNull(rates.getDKK());
        assertNotNull(rates.getGBP());
        assertNotNull(rates.getHKD());
        assertNotNull(rates.getHRK());
        assertNotNull(rates.getHUF());
        assertNotNull(rates.getIDR());
        assertNotNull(rates.getILS());
        assertNotNull(rates.getINR());
        assertNotNull(rates.getJPY());
        assertNotNull(rates.getKRW());
        assertNotNull(rates.getMXN());
        assertNotNull(rates.getMYR());
        assertNotNull(rates.getNOK());
        assertNotNull(rates.getNZD());
        assertNotNull(rates.getPHP());
        assertNotNull(rates.getPLN());
        assertNotNull(rates.getRON());
        assertNotNull(rates.getRUB());
        assertNotNull(rates.getSEK());
        assertNotNull(rates.getSGD());
        assertNotNull(rates.getTHB());
        assertNotNull(rates.getTRY());
        assertNotNull(rates.getUSD());
        assertNotNull(rates.getZAR());

    }
}
