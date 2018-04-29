package service.repository;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import service.model.ModelWebserviceResponse;

/**
 * Created by hadis.t on 4/29/2018.
 */

public interface ApiService {
    public static final String BASE_URL="http://api.icndb.com/";
    @Headers({
            "content-type: application/x-www-form-urlencoded"
    })

    @GET("jokes/random/10")
    Call<ModelWebserviceResponse> get();
}
