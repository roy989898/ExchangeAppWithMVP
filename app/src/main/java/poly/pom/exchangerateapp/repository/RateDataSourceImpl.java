package poly.pom.exchangerateapp.repository;

import java.util.HashMap;
import java.util.Map;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import poly.pom.exchangerateapp.Util;
import poly.pom.exchangerateapp.repository.RealmModule.Rate;
import poly.pom.exchangerateapp.repository.RetrofitModule.Bank;
import poly.pom.exchangerateapp.repository.RetrofitModule.Rates;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.exceptions.OnErrorNotImplementedException;
import rx.functions.Func1;
import rx.observers.TestSubscriber;
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
    public Observable<Boolean> refreshData() {


        return bankAPI.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).map(new Func1<Bank, Boolean>() {

            @Override
            public Boolean call(Bank bank) {
                Realm realm = Realm.getDefaultInstance();
//                delete the old first
                deleteAllRate(realm);
                realm.beginTransaction();
                Rates rates = bank.getRates();
                if (rates == null)
                    throw new RuntimeException("the rates get from Internet isnull");
                int todatDayint = Util.getTodayDate();

                HashMap<String, Double> rateMap = rates.getRateMap();
                for (Map.Entry<String, Double> entry : rateMap.entrySet()) {
                    Rate rate = realm.createObject(Rate.class);
                    rate.setName(entry.getKey());
                    rate.setRateBaseEur(entry.getValue());
                    rate.setUpdateDate(todatDayint);
                }
                realm.commitTransaction();

                realm.close();

                return true;


            }
        });


    }

    //if need refresh,but can't,will return negative

    //    if fail ,return -1
    @Override
    public Observable<Double> convertValue(final String from, final String to, final double money) {
      /* if(hasCache()){
           if(isUpdateExpired()){}

       }else{

       }*/
      return Observable.create(new Observable.OnSubscribe<Boolean>() {
            @Override
            public void call(Subscriber<? super Boolean> subscriber) {
                subscriber.onNext(hasCache());
//                subscriber.onCompleted();
            }
        }).map(new Func1<Boolean, Boolean>() {
            @Override
            public Boolean call(Boolean hasCacheBoolean) {
                if (hasCacheBoolean) {
                    if (isUpdateExpired()) {
//                        expired,need update
                        try {
                            TestSubscriber<Boolean> subscriber = new TestSubscriber<>();
                            refreshData().subscribe(subscriber);
                            subscriber.awaitTerminalEvent();
                            subscriber.assertNoErrors();
                        } catch (OnErrorNotImplementedException e) {
                        }

                        return true;
                    } else {
//                        no need update

                        return true;

                    }
                } else {
//                    must update!!
                    refreshData().subscribe();
                    boolean hasCache=hasCache();
                    if (hasCache) {
                        return true;
                    } else
                        return false;

                }

            }
        }).map(new Func1<Boolean, Double>() {
            @Override
            public Double call(Boolean aBoolean) {
                if (aBoolean) {
                    return getRealmandCalculate(from, to, money);
                } else {
                    return -1d;
                }
            }
        });

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
        realm.close();
        return number > 0;
    }


    private boolean isUpdateExpired() {

        Realm realm = Realm.getDefaultInstance();
        if (realm == null) {
            return false;
        }
        RealmQuery<Rate> query = realm.where(Rate.class);
        Rate rate = query.findFirst();
        Boolean result = rate.getUpdateDate() < Util.getTodayDate();
        realm.close();
        return result;

    }

    @Override
    public void deleteAllRate() {
        Realm realm = Realm.getDefaultInstance();


        realm.beginTransaction();
        realm.where(Rate.class).findAll().deleteAllFromRealm();
        realm.commitTransaction();


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
