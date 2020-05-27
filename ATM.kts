import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

import com.squareup.moshi.ToJson
import com.squareup.moshi.FromJson


data class Model(
  val meta: MetaData,
  @Transient val data: List<String> = listOf
)

data class MetaData(
  val TotalResults: Int,
  val Agreement: String,
  val License: String,
  val TermsOfUse: String,
  val LastUpdated: UpdateDate
)

data class UpdateDate(
  val dateTime: LocalDateTime
)

class UpdateDateAdapter {

  val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssZ")

  @FromJson
  fun fromJson(unparsed: String): UpdateDate {
    return UpdateDate(
      LocalDateTime.parse(unparsed, formatter)
    )
  }

  @ToJson
  fun toJson(parsed: UpdateDate): String {
    return parsed.dateTime.format(formatter)
  }
}

