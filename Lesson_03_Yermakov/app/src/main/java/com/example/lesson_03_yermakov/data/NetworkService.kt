package com.example.lesson_03_yermakov.data

import com.example.lesson_03_yermakov.data.requestmodel.RequestLogin
import com.example.lesson_03_yermakov.data.responsemodel.BaseResponse
import com.example.lesson_03_yermakov.data.responsemodel.product.ResponseProduct
import com.example.lesson_03_yermakov.data.responsemodel.user.ResponseLogin
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT

interface NetworkService {
    @PUT("user/signin")
    suspend fun login(
        @Body requestLogin: RequestLogin,
    ): BaseResponse<ResponseLogin>

    @GET("products")
    suspend fun getCatalog(): BaseResponse<List<ResponseProduct>>
}