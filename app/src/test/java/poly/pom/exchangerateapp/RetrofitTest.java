package poly.pom.exchangerateapp;

import org.junit.Before;
import org.junit.Test;

import poly.pom.exchangerateapp.repository.RetrofitModule.Bank;
import poly.pom.exchangerateapp.repository.RetrofitModule.FixerIOAPI;
import retrofit2.Call;
import retrofit2.GsonConverterFactory;
import retrofit2.Response;
import retrofit2.Retrofit;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

/**
 * Created by User on 31/8/2016.
 */
public class RetrofitTest {
    private FixerIOAPI fixerIOAPi;
    private Call<Bank> call;

    @Before
    public void setUp() throws Exception {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.fixer.io")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        fixerIOAPi = retrofit.create(FixerIOAPI.class);
        call = fixerIOAPi.loadLatestEeurBaseRate();

    }

    @Test
    public void tryTest() throws Exception {
        Response<Bank> result = call.execute();
        Bank bank = result.body();
        assertNotNull(bank);
    }
}
