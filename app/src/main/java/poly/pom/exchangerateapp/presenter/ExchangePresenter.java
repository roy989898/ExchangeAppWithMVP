package poly.pom.exchangerateapp.presenter;

import poly.pom.exchangerateapp.view.ExchangeView;

public interface ExchangePresenter {

    void updateRate();

    void setView(ExchangeView view);

    void calculateExchange();

    void inputAnswerCalculate();

    void inputClick();

    void hideButtonCLick();


}
