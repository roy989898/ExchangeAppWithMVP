package poly.pom.exchangerateapp.view;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import poly.pom.exchangerateapp.R;
import poly.pom.exchangerateapp.repository.RateDataSource;
import poly.pom.exchangerateapp.repository.RateDataSourceImpl;
import poly.pom.exchangerateapp.repository.RealmModule.Rate;
import poly.pom.exchangerateapp.repository.RetrofitModule.Bank;
import poly.pom.exchangerateapp.repository.RetrofitModule.FixerIOAPI;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.GsonConverterFactory;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import rx.Observable;

import static junit.framework.Assert.assertNotNull;

public class MainActivity extends AppCompatActivity {


//    private Bank bank;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ExchangeFargment exchangeFragment = new ExchangeFargment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.flContainer,exchangeFragment);
        transaction.commit();





    }

}
