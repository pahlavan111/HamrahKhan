package com.bp.hamrahkhan.retrofit;


import com.bp.hamrahkhan.model.sms.MobileSend;
import com.bp.hamrahkhan.model.sms.MobileSendResponse;
import com.bp.hamrahkhan.model.verify.CodeSend;
import com.bp.hamrahkhan.model.verify.CodeSendResponse;
import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ApiService {

    @POST("/api/v1/user/login")
    Single<MobileSendResponse> login (@Header("apiCode")String apiCode, @Body MobileSend mobileSend) ;


    @POST("/api/v1/user/login/verify")
    Single<CodeSendResponse> verify (@Header ("apiCode")String apiKey, @Body CodeSend codeSend) ;

}
