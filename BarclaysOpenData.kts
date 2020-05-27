import org.junit.Test
import org.junit.Assert
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

import org.hamcrest.Matchers.*
import org.hamcrest.MatcherAssert

import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrl

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory


@RunWith(JUnit4::class)
class ApiTest {

  val baseUrl = "https://atlas.api.barclays/open-banking/v2.2"
  val client = OkHttpClient()

  val moshi = Moshi.Builder()
              .add(ATM.UpdateDateAdapter())
              .add(KotlinJsonAdapterFactory())
              .build()
  val adapter = moshi.adapter(ATM.Model::class.java)

  @Test
  fun `ATM Finder should return an accurate number of ATMs`() {
    val apiUrl = baseUrl.toHttpUrl().newBuilder()
                 .addPathSegment("atms")
                 .build()

    val request = Request.Builder()
                  .url(apiUrl)
                  .build()

    val response = client.newCall(request).execute()
    Assert.assertEquals(response.code, 200)

    val atmDirectory = adapter.fromJson(response.body!!.string())
    MatcherAssert.assertThat(atmDirectory!!.meta.TotalResults, greaterThan(1500))
  }
}

