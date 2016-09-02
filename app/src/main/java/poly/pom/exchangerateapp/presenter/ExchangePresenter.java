package poly.pom.exchangerateapp.presenter;

import poly.pom.exchangerateapp.view.ExchangeView;

public interface ExchangePresenter {

    void updateRate();

    void setView(ExchangeView view);

    void calculate(String input);

    void inputClick();

    void hideButtonCLick();
}
