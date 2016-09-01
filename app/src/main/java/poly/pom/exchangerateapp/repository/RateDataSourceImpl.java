package poly.pom.exchangerateapp.repository;

import android.util.Log;

import java.util.HashMap;
import java.util.Map;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import poly.pom.exchangerateapp.Util;
import poly.pom.exchangerateapp.repository.RealmModule.Rate;
import poly.pom.exchangerateapp.repository.RetrofitModule.Bank;
import poly.pom.exchangerateapp.repository.RetrofitModule.Rates;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by User on 31/8/2016.
 */
public class RateDataSourceImpl implements RateDataSource {
    private Observable<Bank> bankAPI;


    public RateDataSourceImpl() {
        /*Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.fixer.io/")
                .addConverterFactory(GsonConverterFactory.create())
                 .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        FixerIOAPI fixerIOAPi = retrofit.create(FixerIOAPI.class);
        bankAPI = fixerIOAPi.loadLatestEeurBaseRate();*/


    }


    @Override//        get the rate form the internet and write to the Realm
    public void refreshData(final RefreshCallback callback) {

        if (bankAPI == null) {
            callback.refreshFail("bankAPI or realm is null");
            return;
        }

        bankAPI.observeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Bank>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Log.d("RxRatitraft",e.toString());
                callback.refreshFail(e.toString());
            }

            @Override
            public void onNext(final Bank bank) {
                Realm realm = Realm.getDefaultInstance();
//                delete the old first
                deleteAllRate(realm);
                realm.executeTransactionAsync(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        Rates rates = bank.getRates();

                        if (rates == null) {
                            callback.refreshFail("Response null");
                            return;
                        }

//                       write at here

                        int todatDayint = Util.getTodayDate();

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
        });



    }

    //if need refresh,but can't,will return negative
    @Override
    public void convertValue(final String from, final String to, final double money, final ConvertValueCallback callback) {
        if (bankAPI != null) {
            if (hasCache()) {
//                has cache
                if (!isUpdateExpired()) {
//                    <24
//                    TODO calculate
                    double converted = getRealmandCalculate(from, to, money);
                    if (converted != -1) {
//                        converted success
                        callback.conertValueSuccess(converted);
                    } else {
//                        cpnvert fail
                        callback.conertValueFail("Cant convert");
                    }


                } else {
//                    >24,update
                    refreshData(new RefreshCallback() {
                        @Override
                        public void refreshSuccess() {
//                            online
                            //                    TODO calculate
                            double converted = getRealmandCalculate(from, to, money);
                            if (converted != -1) {
//                        converted success
                                callback.conertValueSuccess(converted);
                            } else {
//                        cpnvert fail
                                callback.conertValueFail("Cant convert");
                            }

                        }

                        @Override
                        public void refreshFail(String error) {
//                              offline
//                            TODO update fail
                            //                    TODO calculate
                            double converted = getRealmandCalculate(from, to, money);
                            if (converted != -1) {
//                        converted success
                                callback.conertValueSuccess(converted * -1);
//                                because update fail but convert success,so,return negative
                            } else {
//                        cpnvert fail
                                callback.conertValueFail("Cant convert");
                            }

                        }
                    });
                }
            } else {
//                no cache
                refreshData(new RefreshCallback() {
                    @Override
                    public void refreshSuccess() {
//                        TODO calculate
                        double converted = getRealmandCalculate(from, to, money);
                        if (converted != -1) {
//                        converted success
                            callback.conertValueSuccess(converted);
                        } else {
//                        cpnvert fail
                            callback.conertValueFail("Cant convert");
                        }
                    }

                    @Override
                    public void refreshFail(String error) {
//                      update fail
//                        TODO can't update
//                        TODO can't convert
                        callback.conertValueFail("No Rate information");
                    }
                });
            }
        } else {
            callback.conertValueFail("realm and bankAPI is null");
        }
    }

    private double getRealmandCalculate(String from, String to, double money) {
        Realm realm = Realm.getDefaultInstance();
        if (realm == null) {
            return -1;
        }
        Rate fromRateOBJ = realm.where(Rate.class).equalTo(RealmColumnName.COL_NAME, from).findAll().first();
        Rate toRateOBJ = realm.where(Rate.class).equalTo(RealmColumnName.COL_NAME, to).findAll().first();
        return calculate(fromRateOBJ.getRateBaseEur(), toRateOBJ.getRateBaseEur(), money);

    }

    private double calculate(double fromRate, double toRate, double value) {
        return toRate / fromRate * value;
    }

    private boolean hasCache() {
        Realm realm = Realm.getDefaultInstance();
        if (realm == null) {
            return false;
        }
        RealmQuery<Rate> query = realm.where(Rate.class);
        long number = query.count();
        return number > 0;
    }

    private boolean isUpdateExpired() {

        Realm realm = Realm.getDefaultInstance();
        if (realm == null) {
            return false;
        }
        RealmQuery<Rate> query = realm.where(Rate.class);
        Rate rate = query.findFirst();
        return rate.getUpdateDate() < Util.getTodayDate();

    }

    @Override
    public void deleteAllRate() {
        Realm realm = Realm.getDefaultInstance();
        if (realm != null) {
            RealmResults<Rate> rates = realm.where(Rate.class).findAll();
            realm.beginTransaction();
            long count = realm.where(Rate.class).count();
            for (int i = 0; i < count; i++) {
                rates.get(i).deleteFromRealm();
            }
            realm.commitTransaction();

        }

        realm.close();

    }

    @Override
    public void setBankAPI(Observable<Bank> bankAPI) {
        this.bankAPI = bankAPI;
    }

    private void deleteAllRate(Realm realm) {

        if (realm != null) {
            RealmResults<Rate> rates = realm.where(Rate.class).findAll();
            realm.beginTransaction();
            long count = realm.where(Rate.class).count();
            for (int i = 0; i < count; i++) {
                rates.get(i).deleteFromRealm();
            }
            realm.commitTransaction();

        }

    }
}
