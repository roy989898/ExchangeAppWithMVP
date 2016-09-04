package poly.pom.exchangerateapp.presenter;

import com.gadberry.utility.expression.Argument;
import com.gadberry.utility.expression.Expression;
import com.gadberry.utility.expression.InvalidExpressionException;

import poly.pom.exchangerateapp.repository.RateDataSource;
import poly.pom.exchangerateapp.view.ExchangeView;
import rx.Observable;
import rx.Observer;
import rx.Scheduler;
import rx.Subscriber;


public class ExchangePresenterImpl implements ExchangePresenter, LifePresenter {
    private ExchangeView view;
    private RateDataSource datasource;
    private Scheduler ioScheduler;
    private Scheduler mainScheduler;


    public ExchangePresenterImpl(RateDataSource datasource, Scheduler ioScheduler, Scheduler mainScheduler) {
        this.datasource = datasource;
        this.ioScheduler = ioScheduler;
        this.mainScheduler = mainScheduler;
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
                if (aBoolean)
                    view.showUpdateSuccessMessage();
                else
                    view.showUpdateFailMessage();


            }
        };
        datasource.refreshData().subscribeOn(ioScheduler).observeOn(mainScheduler).subscribe(observer);
    }

    @Override
    public void setView(ExchangeView view) {
        this.view = view;

    }

    @Override
    public void calculateExchange() {
        Double money=null;
       try {
            money = Double.parseDouble(view.getcalculateAreaString());
       }catch (Exception e){
          return;

       }

        if (view == null) return;
        String from = view.getExchangeFromCountrty();
        String[] to = view.getExchangeToCountrty();
        for (int i = 0; i < to.length; i++) {
            final int index = i;
            Observer<Double> observer = new Observer<Double>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onNext(Double aDouble) {

                    view.showExchangeResult(index, aDouble);

                }
            };
            datasource.convertValue(from, to[i], money).subscribeOn(ioScheduler)
                    .observeOn(mainScheduler)
                    .subscribe(observer);

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
            view.setCalculatorArea("ERROR");
        }

    }


   /* @Override
    public void inputClick() {
        view.displayCalculator();

    }

    @Override
    public void hideButtonCLick() {
        view.hideCalculator();

    }
*/

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }
}
