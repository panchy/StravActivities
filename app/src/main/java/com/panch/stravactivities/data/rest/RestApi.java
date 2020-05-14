package com.panch.stravactivities.data.rest;

import com.panch.stravactivities.data.model.AthleteActivity;
import com.panch.stravactivities.data.model.StravaAuth;

import java.util.List;

import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RestApi {
    @FormUrlEncoded
    @POST("oauth/token")
    Single<Response<StravaAuth>> getToken(@Field("client_id") String clientId,
                                          @Field("client_secret") String clientSecret,
                                          @Field("code") String code,
                                          @Field("grant_type") String grantType);

    @FormUrlEncoded
    @POST("oauth/token")
    Single<Response<StravaAuth>> getTokenWithRefreshToken(@Field("client_id") String clientId,
                                                          @Field("client_secret") String clientSecret,
                                                          @Field("refresh_token") String refresh_token,
                                                          @Field("grant_type") String grantType);

    @GET("api/v3/athlete/activities")
    Single<Response<List<AthleteActivity>>> getActivities();


}
