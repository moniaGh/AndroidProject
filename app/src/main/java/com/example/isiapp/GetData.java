package com.example.isiapp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface GetData {
 /*  @Headers({
           "Authorization: Bearer "+myToken
   })*/
    @GET("/api/1.0/isi/case/start-cases")
    Call<List<CasesClass>> getCases(@Header("Authorization")String authorization);

    @GET("/api/1.0/isi/cases/participated")
    Call<List<partCases>> getPartCases(@Header("Authorization")String authorization);

    @POST("/isi/oauth2/token")
   @FormUrlEncoded
    Call<AccessToken> getAccess(@Field("grant_type") String grant_type,
                                @Field("scope") String scope,
                                @Field("client_id") String client_id,
                                @Field("client_secret") String client_secret,
                                @Field("username") String username,
                                @Field("password") String password
                                );
   @GET("/api/1.0/isi/project/{prj_uid}/activity/{act_uid}/steps")
   Call<List<stepOne>> getStepOne(@Path("prj_uid") String pro_uid, @Path("act_uid") String tas_uid,@Header("Authorization")String authorization);

   @GET("/api/1.0/isi/project/{prj_uid}/dynaform/{dyn_uid}")
   Call<dynaContent> getDynCon(@Path("prj_uid")String pro_uid,@Path("dyn_uid") String step_obj,@Header("Authorization")String authorization);



}
