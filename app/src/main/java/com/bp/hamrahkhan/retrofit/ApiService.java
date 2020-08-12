package com.bp.hamrahkhan.retrofit;


import com.bp.hamrahkhan.model.path.GetPathResponse;
import com.bp.hamrahkhan.model.path.SingleBody;
import com.bp.hamrahkhan.model.sms.MobileSendBody;
import com.bp.hamrahkhan.model.sms.MobileSendResponse;
import com.bp.hamrahkhan.model.verify.CodeSendBody;
import com.bp.hamrahkhan.model.verify.CodeSendResponse;
import java.util.Map;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;

public interface ApiService {

//    @POST("/api/v1/user/login")
//    Single<MobileSendResponse> login (@Header("apiCode")String apiCode, @Body MobileSendBody mobileSendBody) ;
    @POST("/api/v1/user/login")
    Call<MobileSendResponse> login (@Header("apiCode")String apiCode, @Body MobileSendBody mobileSendBody) ;




//    @POST("/api/v1/user/login/verify")
//    Single<CodeSendResponse> verify (@Header ("apiCode")String apiKey, @Body CodeSendBody codeSendBody) ;

    @POST("/api/v1/user/login/verify")
    Call<CodeSendResponse> verify (@Header ("apiCode")String apiKey, @Body CodeSendBody codeSendBody) ;

//    @POST("/api/v1/path/list")
//    Single<GetPathResponse> getPathList (@Header ("apiCode")String apiKey,@Header("Authorization")String auth, @Body String str) ;

//    @POST("/api/v1/path/list")
//    Single<GetPathResponse> getPathList (@HeaderMap Map<String,String> headers, @Body SingleBody singleBody) ;


//    @POST("/api/v1/path/list")
//    Call<ResponseBody> getPathList (@HeaderMap Map<String,String> headers, @Body SingleBody singleBody) ;

    @POST("/api/v1/path/list")
    Call<GetPathResponse> getPathList (@HeaderMap Map<String,String> headers, @Body SingleBody singleBody) ;



}
