package poly.pom.exchangerateapp.repository;

import io.realm.Realm;
import poly.pom.exchangerateapp.repository.RetrofitModule.Bank;
import poly.pom.exchangerateapp.repository.RetrofitModule.FixerIOAPI;
import retrofit2.Call;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;

/**
 * Created by User on 31/8/2016.
 */
public class RateDataSourceImpl implements RateDataSource {
    private Call<Bank> bankAPI;
    private Realm realm;


    public RateDataSourceImpl() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.fixer.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        FixerIOAPI fixerIOAPi = retrofit.create(FixerIOAPI.class);
        bankAPI = fixerIOAPi.loadLatestEeurBaseRate();
        
        realm = Realm.getDefaultInstance();


    }

    @Override
    public void refreshData(RefreshCallback callback) {

    }

    @Override
    public void convertValue(String from, String to, double money, ConvertValueCallback callback) {

    }

    @Override
    public void deleteAll() {

    }
}
