package picfinder.data.network.client

import okhttp3.OkHttpClient
import picfinder.data.network.converters.MoshiConverterFactoryProvider
import retrofit2.Converter
import retrofit2.Retrofit

class RestClient(
    unsplashUrl: String,
    imgurUrl: String,
    okHttpClient: OkHttpClient = OkHttpClientFactory.buildDefaultOkHttpClient(),
    converter: Converter.Factory = MoshiConverterFactoryProvider().getConverterFactory()
) {

    private val baseBuilder by lazy {
        Retrofit.Builder()
            .baseUrl(unsplashUrl)
            .client(okHttpClient)
            .addConverterFactory(converter)
    }

    val unsplashRetrofit: Retrofit by lazy { baseBuilder.baseUrl(unsplashUrl).build() }

    val imgurRetrofit: Retrofit by lazy { baseBuilder.baseUrl(imgurUrl).build() }
}