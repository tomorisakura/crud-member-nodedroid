package com.grevi.msx.network

import com.grevi.msx.model.Member
import com.grevi.msx.network.response.MemberResponse
import com.grevi.msx.utils.Constant
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import java.util.concurrent.TimeUnit

interface ApiServices {

    @GET("/api/member")
    suspend fun getMember() : Response<MemberResponse>

    @FormUrlEncoded
    @POST("/api/member")
    suspend fun postMember(@Field("name") name : String,
                           @Field("age") age : Int,
                           @Field("address") address : String,
                           @Field("hobby") hobby : String) : Response<MemberResponse>


    companion object {
        operator fun invoke(networkInterceptor: NetworkInterceptor) : ApiServices {
            val interceptor = OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .callTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(networkInterceptor)
                .retryOnConnectionFailure(true)
                .build()

            return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .client(interceptor)
                .baseUrl(Constant.BASE_URL)
                .build()
                .create(ApiServices::class.java)
        }
    }
}