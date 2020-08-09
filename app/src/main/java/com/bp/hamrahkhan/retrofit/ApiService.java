package com.bp.hamrahkhan.retrofit;


import com.bp.hamrahkhan.data.sms.MobileSend;
import com.bp.hamrahkhan.data.sms.MobileSendResponse;
import com.bp.hamrahkhan.data.verify.CodeSend;

import io.reactivex.Single;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ApiService {


//    @POST("/api/v1/user/login")
//    Single<ResponseBody> login (@Body MobileSend mobileSend) ;

    @POST("/api/v1/user/login")
    Single<MobileSendResponse> login (@Header("apiCode")String apiCode, @Body MobileSend mobileSend) ;


//    @POST("/api/v1/user/login/verify")
//    Single<CodeSendResponse> verify (@Header ("apiCode")String apiKey, @Body CodeSend codeSend) ;

    @POST("/api/v1/user/login/verify")
    Single<ResponseBody> verify (@Header ("apiCode")String apiKey, @Body CodeSend codeSend) ;

}
