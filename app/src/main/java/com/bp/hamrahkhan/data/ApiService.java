package com.bp.hamrahkhan.data;


import io.reactivex.Single;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {


//    @POST("/api/v1/user/login")
//    Single<ResponseBody> login (@Body MobileSend mobileSend) ;

    @POST("/api/v1/user/login")
    Single<MobileSendResponse> login (@Body MobileSend mobileSend) ;
}
