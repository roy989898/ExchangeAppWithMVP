package poly.pom.exchangerateapp.view;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import poly.pom.exchangerateapp.MyApplication;
import poly.pom.exchangerateapp.R;
import poly.pom.exchangerateapp.custom_widget.TextFitTextView;
import poly.pom.exchangerateapp.presenter.ExchangePresenter;
import poly.pom.exchangerateapp.repository.CountryName;

/**
 * A simple {@link Fragment} subclass.
 */
public class ExchangeFargment extends Fragment implements AdapterView.OnItemSelectedListener, ExchangeView {

    float initialTextSixe;

    @BindView(R.id.edMoney1)
    TextFitTextView edMoney1;
    @BindView(R.id.edMoney2)
    TextFitTextView edMoney2;
    @BindView(R.id.bt7)
    Button bt7;
    @BindView(R.id.bt4)
    Button bt4;
    @BindView(R.id.bt1)
    Button bt1;
    @BindView(R.id.btPoint)
    Button btPoint;
    @BindView(R.id.bt8)
    Button bt8;
    @BindView(R.id.bt5)
    Button bt5;
    @BindView(R.id.bt2)
    Button bt2;
    @BindView(R.id.bt0)
    Button bt0;
    @BindView(R.id.bt9)
    Button bt9;
    @BindView(R.id.bt6)
    Button bt6;
    @BindView(R.id.bt3)
    Button bt3;
    @BindView(R.id.btDivide)
    Button btDivide;
    @BindView(R.id.btDel)
    Button btDel;
    @BindView(R.id.btMinu)
    Button btMinu;
    @BindView(R.id.btTimes)
    Button btTimes;
    @BindView(R.id.btPlus)
    Button btPlus;
    @BindView(R.id.btOk)
    Button btOk;
    @BindView(R.id.spCountry1)
    Spinner spCountry1;
    @BindView(R.id.spCountry2)
    Spinner spCountry2;

    @Inject
    ExchangePresenter presenter;
    private TextFitTextView textToThisView;
    private TextFitTextView thisViewShowExchangeResult;
    private String calculateFormula = "";
    private Spinner fromSpinner;
    private Spinner toSpinner;

    public ExchangeFargment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_exchange_fargment, container, false);
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        // Sets the Toolbar to act as the ActionBar for this Activity window.
        // Make sure the toolbar exists in the activity and is not null
        ButterKnife.bind(this, view);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        setHasOptionsMenu(true);

        textToThisView = edMoney1;
        thisViewShowExchangeResult = edMoney2;

        initialTextSixe = textToThisView.getTextSize();

        ArrayAdapter<String> spinnerArray = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, CountryName.getCountryNameArray());
        spCountry1.setAdapter(spinnerArray);
        spCountry2.setAdapter(spinnerArray);
        spCountry1.setOnItemSelectedListener(this);
        fromSpinner = spCountry1;
        toSpinner = spCountry2;

        ((MyApplication) getActivity().getApplication()).getComponent().inject(this);


        presenter.setView(this);


        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_refresh) {
            presenter.updateRate();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @OnClick({R.id.edMoney1, R.id.edMoney2, R.id.bt7, R.id.bt4, R.id.bt1, R.id.btPoint, R.id.bt8, R.id.bt5, R.id.bt2, R.id.bt0, R.id.bt9, R.id.bt6, R.id.bt3, R.id.btDivide, R.id.btDel, R.id.btMinu, R.id.btTimes, R.id.btPlus, R.id.btOk})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.edMoney1:
                calculateFormula = "";
                textToThisView = (TextFitTextView) view;
                thisViewShowExchangeResult = edMoney2;

                fromSpinner = spCountry1;
                toSpinner = spCountry2;
                break;
            case R.id.edMoney2:
                calculateFormula = "";
                textToThisView = (TextFitTextView) view;
                thisViewShowExchangeResult = edMoney1;

                fromSpinner = spCountry2;
                toSpinner = spCountry1;
                break;
            case R.id.bt7:
                calculateFormula = calculateFormula + "7";
                textToThisView.setText(calculateFormula);
                break;
            case R.id.bt4:
                calculateFormula = calculateFormula + "4";
                textToThisView.setText(calculateFormula);
                break;
            case R.id.bt1:
                calculateFormula = calculateFormula + "1";
                textToThisView.setText(calculateFormula);
                break;
            case R.id.btPoint:
                calculateFormula = calculateFormula + ".";
                textToThisView.setText(calculateFormula);
                break;
            case R.id.bt8:
                calculateFormula = calculateFormula + "8";
                textToThisView.setText(calculateFormula);
                break;
            case R.id.bt5:
                calculateFormula = calculateFormula + "5";
                textToThisView.setText(calculateFormula);
                break;
            case R.id.bt2:
                calculateFormula = calculateFormula + "2";
                textToThisView.setText(calculateFormula);
                break;
            case R.id.bt0:
                calculateFormula = calculateFormula + "0";
                textToThisView.setText(calculateFormula);
                break;
            case R.id.bt9:
                calculateFormula = calculateFormula + "9";
                textToThisView.setText(calculateFormula);
                break;
            case R.id.bt6:
                calculateFormula = calculateFormula + "6";
                textToThisView.setText(calculateFormula);
                break;
            case R.id.bt3:
                calculateFormula = calculateFormula + "3";
                textToThisView.setText(calculateFormula);
                break;
            case R.id.btDivide:
                calculateFormula = calculateFormula + "/";
                textToThisView.setText(calculateFormula);
                break;
            case R.id.btDel:
                calculateFormula = "";
                textToThisView.setTextWithReSetSize(calculateFormula, initialTextSixe);
                break;
            case R.id.btMinu:
                calculateFormula = calculateFormula + "-";
                textToThisView.setText(calculateFormula);
                break;
            case R.id.btTimes:
                calculateFormula = calculateFormula + "*";
                textToThisView.setText(calculateFormula);
                break;
            case R.id.btPlus:
                calculateFormula = calculateFormula + "+";
                textToThisView.setText(calculateFormula);
                break;
            case R.id.btOk:
                presenter.inputAnswerCalculate();
                presenter.calculateExchange();
                break;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.spCountry1:

                break;
            case R.id.spCountry2:

                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    //Exchange view Method


    @Override
    public void showpresenterResult(Double result) {

    }

    @Override
    public void showExchangeResult(int index, double exchangeResult) {
        thisViewShowExchangeResult.setTextWithReSetSize(exchangeResult + "", initialTextSixe);
    }

    @Override
    public void showUpdateSuccessMessage() {
        Toast.makeText(getContext(), getString(R.string.updateSuccessMessage), Toast.LENGTH_SHORT).show();

    }

    @Override
    public void showUpdateFailMessage() {
        Toast.makeText(getContext(), getString(R.string.updateFailMessage), Toast.LENGTH_SHORT).show();
    }

    @Override
    public String getcalculateAreaString() {
        return calculateFormula;
    }

    @Override
    public void setCalculatorArea(String display) {
        calculateFormula = display;
        textToThisView.setText(display);
    }

    @Override
    public String[] getExchangeToCountrty() {

        String toCountryString = ((TextView) toSpinner.getSelectedView()).getText().toString();
        return new String[]{toCountryString};
    }

    @Override
    public String getExchangeFromCountrty() {
        String fromCountryString = ((TextView) fromSpinner.getSelectedView()).getText().toString();
        return fromCountryString;
    }
}
