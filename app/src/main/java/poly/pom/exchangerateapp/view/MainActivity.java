package poly.pom.exchangerateapp.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;
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
    private RateDataSourceImpl dataSource;

//    private Bank bank;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       /* setContentView(R.layout.activity_main);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.fixer.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        FixerIOAPI fixerIOAPi = retrofit.create(FixerIOAPI.class);
        Observable<Bank> call = fixerIOAPi.loadLatestEeurBaseRate();

        dataSource = new RateDataSourceImpl();
        dataSource.setBankAPI(call);

        dataSource.deleteAllRate();


        final Realm realm = Realm.getDefaultInstance();

        dataSource.refreshData(new RateDataSource.RefreshCallback() {
            @Override
            public void refreshSuccess() {
                RealmQuery<Rate> query = realm.where(Rate.class);
                RealmResults<Rate> result = query.findAll();

                Toast.makeText(MainActivity.this,query.count()+"",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void refreshFail(String error) {

                RealmQuery<Rate> query = realm.where(Rate.class);
                RealmResults<Rate> result = query.findAll();
                Toast.makeText(MainActivity.this,query.count()+"Fail",Toast.LENGTH_SHORT).show();
            }
        });
*/










    }

}
