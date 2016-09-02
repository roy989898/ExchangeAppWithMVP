package poly.pom.exchangerateapp.repository;


import poly.pom.exchangerateapp.repository.RetrofitModule.Bank;
import retrofit2.Call;
import rx.Observable;

public interface RateDataSource {
    public Observable<Boolean>  refreshData();

    public Observable<Double> convertValue(String from, String to, double money);

    public void deleteAllRate();




    public void setBankAPI(Observable<Bank> bankAPI);




}
