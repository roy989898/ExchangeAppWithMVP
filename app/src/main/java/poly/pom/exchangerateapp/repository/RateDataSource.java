package poly.pom.exchangerateapp.repository;


public interface RateDataSource {
    public void refreshData(RefreshCallback callback);

    public void convertValue(String from, String to, double money, ConvertValueCallback callback);


    interface RefreshCallback {
        public void refreshSuccess();

        public void refreshFail(String error);
    }

    interface ConvertValueCallback {
        public void conertValueSuccess(double resultValue);

        public void conertValueFail(String error);
    }
}
