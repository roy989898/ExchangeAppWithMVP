package poly.pom.exchangerateapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import poly.pom.exchangerateapp.repository.RetrofitModule.Bank;
import poly.pom.exchangerateapp.repository.RetrofitModule.FixerIOAPI;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.GsonConverterFactory;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.fixer.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        FixerIOAPI fixerIOAPi = retrofit.create(FixerIOAPI.class);
        Call<Bank> call = fixerIOAPi.loadLatestEeurBaseRate();

         call.enqueue(new Callback<Bank>() {
            @Override
            public void onResponse(Response<Bank> response) {
                Bank bank = response.body();
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });*/

    }
}
