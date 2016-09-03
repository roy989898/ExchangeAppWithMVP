package poly.pom.exchangerateapp.view;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import poly.pom.exchangerateapp.R;
import poly.pom.exchangerateapp.custom_widget.TextFitTextView;

/**
 * A simple {@link Fragment} subclass.
 */
public class ExchangeFargment extends Fragment {


    @BindView(R.id.edMoney1)
    TextView edMoney1;
    @BindView(R.id.edMoney2)
    TextFitTextView edMoney2;

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
//        edMoney2.setFitTextToBox(true);

        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        setHasOptionsMenu(true);

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }
}
