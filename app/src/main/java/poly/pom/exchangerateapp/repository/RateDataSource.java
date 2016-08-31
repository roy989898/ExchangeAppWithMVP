package poly.pom.exchangerateapp.repository;


import poly.pom.exchangerateapp.repository.RetrofitModule.Bank;
import retrofit2.Call;

public interface RateDataSource {
    public void refreshData(RefreshCallback callback);

    public void convertValue(String from, String to, double money, ConvertValueCallback callback);

    public void deleteAllRate();



    public void setBankAPI(Call<Bank> bankAPI);

    interface RefreshCallback {
        public void refreshSuccess();

        public void refreshFail(String error);
    }

    interface ConvertValueCallback {
        public void conertValueSuccess(double resultValue);

        public void conertValueFail(String error);
    }
}
