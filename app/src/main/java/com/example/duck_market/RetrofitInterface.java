package com.example.duck_market;

import com.example.duck_market.Entity.InputCategory;
import com.example.duck_market.Entity.Interest;
import com.example.duck_market.Entity.Merchandise;
import com.example.duck_market.Entity.Recommend;
import com.example.duck_market.Entity.SubCategory;
import com.example.duck_market.Entity.User;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface RetrofitInterface {
    /*
    사용자 등록
     */
    //@FormUrlEncoded
    @POST("/user")
    //Call<Member> createMember(@FieldMap HashMap<String, Object> parameters);
    Call<User> createUser(@Body HashMap<String, Object> parameters);

    /*
    PK값으로 사용자 정보변경
     */
    @GET("/user/{id}")
    Call<User> updateUser(@Body HashMap<String,Object> parameters, @Path("id") long id);

    /*
    PK값으로 사용자 조회
     */
    @GET("/user/{id}")
    Call<User> readUser(@Path("id") long id);

    /*
    로그인
     */
    //@FormUrlEncoded
    @POST("/user/login")
    //Call<Member> loginMember(@FieldMap HashMap<String, Object> parameters);
    Call<User> loginUser(@Body HashMap<String, Object> parameters);

    /*
    대분류로 게시글 검색
     */
    @GET("/merchandise/{major}")
    Call<List<Merchandise>> readMerchandisesByMajor(@Path("major") String major);

    /*
    대분류와 소분류로 게시글 검색
     */
    @GET("/merchandise/{major}/{sub}")
    Call<List<Merchandise>> readMerchandisesByMajorAndSub(@Path("major") String major, @Path("sub") String sub);

    /*
    사용자로 게시글 검색
     */
    @GET("/merchandise/user/{user}")
    Call<List<Merchandise>>  readMerchandiseByUser(@Path("user") String user);

    /*
    검색어를 포함한 게시글 검색
     */
    @GET("/merchandise/name/{name}")
    Call<List<Merchandise>> readMerchandiseByName(@Path("name") String name,@Body HashMap<String,Object> parameters);

    /*
    게시글 생성
     */
    //@FormUrlEncoded
    @POST("/merchandise")
    Call<Merchandise> createMerchandise(@Body HashMap<String, Object> parameters);

    /*
   사용자 PK값을 이용한 관심 검색
    */
    @GET("/interest/user/{id}")
    Call<List<Interest>> readInterestByUser(@Path("id")long id);

    @GET("/interest/merchandise/{id}")
    Call<List<Interest>> readInterestByMerchandise(@Path("id")long id);

    @POST("/interest")
    Call<Interest> createInterest(@Body HashMap<String,Object> parameter);

    @GET("/category/sub/{major}")
    Call<List<String>> readSubByMajor(@Path("major")String major);

    @GET("/category/major")
    Call<List<String>> readByMajor();

    @POST("/category")
    Call<InputCategory> createCategory(@Body HashMap<String,Object> parameter);

    @GET("/mahout/{userId}")
    Call<List<Recommend>> recommendForUser(@Path("userId")long userId);
}
