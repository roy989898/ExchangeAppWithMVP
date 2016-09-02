package poly.pom.exchangerateapp.view;


public interface ExchangeView {
    void displayCalculator();

    void showpresenterResult(Double result);

    void showExchangeResult(double[] exchangeResult);

    void showUpdateSuccessMessage();

    void showUpdateFailMessage();
}
