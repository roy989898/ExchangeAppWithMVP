package poly.pom.exchangerateapp.repository;

import java.util.HashMap;
import java.util.Map;

import io.realm.Realm;
import poly.pom.exchangerateapp.Util;
import poly.pom.exchangerateapp.repository.RealmModule.Rate;
import poly.pom.exchangerateapp.repository.RetrofitModule.Bank;
import poly.pom.exchangerateapp.repository.RetrofitModule.Rates;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by User on 31/8/2016.
 */
public class RateDataSourceImpl implements RateDataSource {
    private Call<Bank> bankAPI;
    private Realm realm;

    public RateDataSourceImpl() {
        /*Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.fixer.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        FixerIOAPI fixerIOAPi = retrofit.create(FixerIOAPI.class);
        bankAPI = fixerIOAPi.loadLatestEeurBaseRate();*/

        realm = Realm.getDefaultInstance();


    }

    @Override
    public void setBankAPI(Call<Bank> bankAPI) {
        this.bankAPI = bankAPI;
    }

    @Override
    public void refreshData(final RefreshCallback callback) {
//        get the rate form the internet and write to the Realm
        if (bankAPI == null ) {
            callback.refreshFail("bankAPI or realm is null");
            return;
        }

        bankAPI.enqueue(new Callback<Bank>() {
            @Override
            public void onResponse(final Response<Bank> response) {
//                write to the database
                Realm realm = Realm.getDefaultInstance();
                realm.executeTransactionAsync(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        Rates rates = response.body().getRates();

                        if(rates==null)
                            callback.refreshFail("Response null");
//                       write at here
                        String todayDate = Util.getTodayDate();
                        int todatDayint = Integer.parseInt(todayDate);

                        HashMap<String, Double> rateMap = rates.getRateMap();
                        for (Map.Entry<String, Double> entry : rateMap.entrySet()) {
                            Rate rate = realm.createObject(Rate.class);
                            rate.setName(entry.getKey());
                            rate.setRateBaseEur(entry.getValue());
                            rate.setUpdateDate(todatDayint);
                        }

                    }

                }, new Realm.Transaction.OnSuccess() {
                    @Override
                    public void onSuccess() {
                        callback.refreshSuccess();

                    }
                }, new Realm.Transaction.OnError() {
                    @Override
                    public void onError(Throwable error) {
                        callback.refreshFail(error.toString());
                    }
                });
                callback.refreshSuccess();

            }

            @Override
            public void onFailure(Throwable t) {
                callback.refreshFail(t.toString());
            }
        });

    }

    @Override
    public void convertValue(String from, String to, double money, ConvertValueCallback callback) {

    }

    @Override
    public void deleteAllRate() {
        if (realm != null) {
            realm.beginTransaction();
            realm.deleteAll();
            realm.cancelTransaction();
        }

    }
}
