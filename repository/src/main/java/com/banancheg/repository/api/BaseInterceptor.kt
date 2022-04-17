package com.banancheg.repository.api

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import kotlin.jvm.Throws

class BaseInterceptor : Interceptor {

    companion object {
        val interceptor: BaseInterceptor
            get() = BaseInterceptor()
    }

    private var responseCode: Int = 0

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())
        responseCode = response.code
        return response
    }
}