package com.bp.hamrahkhan.data;


import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ApiService {


//    @POST("/api/v1/user/login")
//    Single<ResponseBody> login (@Body MobileSend mobileSend) ;

    @POST("/api/v1/user/login")
    Single<MobileSendResponse> login (@Header("apiCode")String apiCode,@Body MobileSend mobileSend) ;

//    @POST("/api/v1/user/login")
//    Single<SmsRes> login (@Header("apiCode")String apiCode,@Body MobileSend mobileSend) ;


    @POST("/api/v1/user/verify")
    Single<CodeSendResponse> verify (@Header ("apiCode")String apiKey,@Body CodeSend codeSend) ;

}
