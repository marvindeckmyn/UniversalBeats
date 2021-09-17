package be.howest.marvindeckmyn.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*

private const val BASE_API_URL = "https://fierce-mesa-02091.herokuapp.com/api/"

private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

private val retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(BASE_API_URL)
        .build()

interface UniversalBeatsApiService {
    @GET("beats")
    suspend fun getBeats():
            List<Beats>

    @GET("producers")
    suspend fun getProducers():
            List<Producers>

    @GET("genres")
    suspend fun getGenres():
            List<Genres>

    @GET("moods")
    suspend fun getMoods():
            List<Moods>

    @GET("producers/{id}")
    suspend fun getProducer(@Path(value = "id", encoded = false) key: Int):
            List<Producers>

    @GET("plays/{beat_id}")
    suspend fun getPlays(@Path(value = "beat_id", encoded = false) key: Int):
            List<Plays>

    @GET("soldbeats/{id}")
    suspend fun getSoldBeats(@Path(value = "id", encoded = false) key:Int):
            List<SoldBeats>

    @GET("beats/producer/{producer_id}")
    suspend fun getBeatsByProducerId(@Path(value = "producer_id", encoded = false) key:Int):
            List<Beats>

    @GET("producers/user/{username}")
    suspend fun getProducerByUsername(@Path(value = "username", encoded = false) key: String):
            List<Producers>

    @POST("beats")
    suspend fun postBeat(@Body beat: Beats): Response<ResponseBody>

    @POST("login")
    suspend fun login(@Body user: User): Response<ResponseBody>

    @POST("register")
    suspend fun register(@Body registerUser: RegisterUser): Response<ResponseBody>

    @POST("plays")
    suspend fun registerPlay(@Body plays: Plays): Response<ResponseBody>

    @POST("buybeat")
    suspend fun buyBeat(@Body buyBeat: BuyBeat): Response<ResponseBody>

    @PUT("updateuserinfo")
    suspend fun updateUserInfo(@Body userInfo: UserInfo): Response<ResponseBody>

    @PUT("changeavatar")
    suspend fun changeAvatar(@Body avatar: Avatar): Response<ResponseBody>

    @PUT("changepassword")
    suspend fun changePassword(@Body newPassword: NewPassword): Response<ResponseBody>
}

object UniversalBeatsApi {
    val retrofitService : UniversalBeatsApiService by lazy {
        retrofit.create(UniversalBeatsApiService::class.java)
    }
}