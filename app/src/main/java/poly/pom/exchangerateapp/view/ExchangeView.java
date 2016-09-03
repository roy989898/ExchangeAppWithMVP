package poly.pom.exchangerateapp.view;


import java.util.ArrayList;

public interface ExchangeView {
    void displayCalculator();
    void hideCalculator();

    void showpresenterResult(Double result);

    void showExchangeResult(int index,double exchangeResult);

    void showUpdateSuccessMessage();

    void showUpdateFailMessage();

    String getcalculateAreaString();

    void setCalculatorArea(String display);

    String[] getExchangeToCountrty();
    String getExchangeFromCountrty();
}
