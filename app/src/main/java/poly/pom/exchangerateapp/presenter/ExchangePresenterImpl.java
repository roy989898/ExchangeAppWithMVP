package poly.pom.exchangerateapp.presenter;

import android.telecom.Connection;

import com.gadberry.utility.expression.Argument;
import com.gadberry.utility.expression.Expression;
import com.gadberry.utility.expression.InvalidExpressionException;

import java.util.ArrayList;

import poly.pom.exchangerateapp.repository.RateDataSource;
import poly.pom.exchangerateapp.view.ExchangeView;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class ExchangePresenterImpl implements ExchangePresenter, LifePresenter {
    private ExchangeView view;
    private RateDataSource datasource;


    public ExchangePresenterImpl(RateDataSource datasource) {
        this.datasource = datasource;
    }

    @Override
    public void updateRate() {
        if (view == null) return;
        Observer<Boolean> observer = new Observer<Boolean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                view.showUpdateFailMessage();

            }

            @Override
            public void onNext(Boolean aBoolean) {
                view.showUpdateSuccessMessage();

            }
        };
        datasource.refreshData().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
    }

    @Override
    public void setView(ExchangeView view) {
        this.view = view;

    }

    @Override
    public void calculateExchange() {
        Double money=Double.parseDouble(view.getcalculateAreaString());
        if (view == null) return;
        String from = view.getExchangeFromCountrty();
        String[] to = view.getExchangeToCountrty();
        for (int i=0;i<to.length;i++) {
            final int index=i;
            Observer<Double> observer = new Observer<Double>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onNext(Double aDouble) {

                    view.showExchangeResult(index,aDouble);

                }
            };
            datasource.convertValue(from,to[i],money).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe();

        }


    }



    @Override
    public void inputAnswerCalculate() {
        if (view == null) return;
        String calculate = view.getcalculateAreaString();
        try {
            Argument result = Expression.evaluate(calculate);
            view.setCalculatorArea(result.toString());
        } catch (InvalidExpressionException e) {
            e.printStackTrace();
        }

    }


    @Override
    public void inputClick() {
        view.displayCalculator();

    }

    @Override
    public void hideButtonCLick() {
        view.hideCalculator();

    }



    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }
}
