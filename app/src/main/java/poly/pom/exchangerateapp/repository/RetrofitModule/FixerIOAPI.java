package poly.pom.exchangerateapp.repository.RetrofitModule;

import retrofit2.Call;
import retrofit2.http.GET;
import rx.Observable;


public interface FixerIOAPI {
//   http://api.fixer.io/latest
    @GET("/latest")
    Observable<Bank> loadLatestEeurBaseRate();
}
