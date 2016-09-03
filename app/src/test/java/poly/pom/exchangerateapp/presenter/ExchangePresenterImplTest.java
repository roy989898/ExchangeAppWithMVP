package poly.pom.exchangerateapp.presenter;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import poly.pom.exchangerateapp.repository.RateDataSource;
import poly.pom.exchangerateapp.view.ExchangeView;
import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

import static org.mockito.Matchers.anyDouble;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by User on 3/9/2016.
 */
public class ExchangePresenterImplTest {

    private ExchangeView mockView;
    private RateDataSource mockDataSource;
    private ExchangePresenterImpl presenter;

    @Before
    public void setUp() throws Exception {
        mockView = Mockito.mock(ExchangeView.class);
        mockDataSource = Mockito.mock(RateDataSource.class);

        presenter = new ExchangePresenterImpl(mockDataSource, Schedulers.immediate(), Schedulers.immediate());
        presenter.setView(mockView);
    }

    @Test
    public void testUpdateRateWithSuccess() throws Exception {
        when(mockDataSource.refreshData()).thenReturn(Observable.just(true));
        presenter.updateRate();
        verify(mockView, times(1)).showUpdateSuccessMessage();
        verify(mockView, never()).showUpdateFailMessage();


    }

    @Test
    public void testUpdateRatewithFail() throws Exception {
        when(mockDataSource.refreshData()).thenReturn(Observable.create(new Observable.OnSubscribe<Boolean>() {
            @Override
            public void call(Subscriber<? super Boolean> subscriber) {
                subscriber.onError(new Exception());
            }
        }));
        presenter.updateRate();
        verify(mockView, times(1)).showUpdateFailMessage();
        verify(mockView, never()).showUpdateSuccessMessage();


    }

    @Test
    public void testSetView() throws Exception {

    }

    @Test
    public void testCalculateExchange() throws Exception {
//        prepare
        when(mockDataSource.convertValue(anyString(),anyString(),anyDouble())).thenReturn(Observable.just(13.1));
        when(mockView.getcalculateAreaString()).thenReturn("77");
        when(mockView.getExchangeFromCountrty()).thenReturn("HKD");
        when(mockView.getExchangeToCountrty()).thenReturn(new String[]{"USD","KBN"});

//        test
        presenter.calculateExchange();
        verify(mockView,times(1)).getExchangeFromCountrty();
        verify(mockView,times(1)).getExchangeToCountrty();
        verify(mockDataSource,times(2)).convertValue(anyString(),anyString(),anyDouble());
        verify(mockView,times(2)).showExchangeResult(anyInt(), Matchers.eq(13.1));

    }

    @Test
    public void testInputAnswerCalculate() throws Exception {
        when(mockView.getcalculateAreaString()).thenReturn("5*5+7");

        presenter.inputAnswerCalculate();

        verify(mockView,times(1)).setCalculatorArea(eq("32.0"));
    }

    @Test
    public void testInputClick() throws Exception {
        presenter.inputClick();
        verify(mockView,times(1)).displayCalculator();

    }

    @Test
    public void testHideButtonCLick() throws Exception {
        presenter.hideButtonCLick();
        verify(mockView,times(1)).hideCalculator();
    }

    @Test
    public void testResume() throws Exception {

    }

    @Test
    public void testPause() throws Exception {

    }
}