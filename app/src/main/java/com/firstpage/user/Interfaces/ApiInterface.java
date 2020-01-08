package com.firstpage.user.Interfaces;

import com.firstpage.user.Model.Conform_order;
import com.firstpage.user.Model.Conform_order_response;
import com.firstpage.user.Model.Exceptioncheck;
import com.firstpage.user.Model.Exceptioncheck_response;
import com.firstpage.user.Model.Login;
import com.firstpage.user.Model.Loginresponse;
import com.firstpage.user.Model.Ongoing_response;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {
    @POST("v2/provider/login")
    Call<Loginresponse> login(@Body Login login);
    @GET ("v1/provider/ongoing-order")
    Call<Ongoing_response> ongoingdata();
    @POST("v1/provider/order/confirm")
    Call<Conform_order_response> orderconform(@Body Conform_order conform_order);
    @POST("v1/order/exception")
    Call<Exceptioncheck_response> exception(@Body Exceptioncheck exceptioncheck);

}
