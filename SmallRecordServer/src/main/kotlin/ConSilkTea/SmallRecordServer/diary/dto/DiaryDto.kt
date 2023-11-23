package ConSilkTea.SmallRecordServer.diary.dto

import ConSilkTea.SmallRecordServer.common.annotation.ValidEnum
import ConSilkTea.SmallRecordServer.common.status.Activity
import ConSilkTea.SmallRecordServer.common.status.Gender
import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.NotBlank
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

data class DiaryDtoRequest(
    val diaryId: Long?,

    @field:NotBlank
    @field:ValidEnum(enumClass = Activity::class, message = "type을 확인 해주세요.")
    @JsonProperty("activity")
    private val _activity: String?,

    @field:NotBlank
    @JsonProperty("diaryContent")
    private val _diaryContent: String?,

    @field:NotBlank
    @JsonProperty("prTime")
    private val _prTime: String?,

    @field:NotBlank
    @JsonProperty("poTime")
    private val _poTime: String?,

    @field:NotBlank
    @JsonProperty("diaryDate")
    private val _diaryDate: String?,
) {
    val activity: Activity
        get() = Activity.valueOf(_activity!!)

    val diaryContent: String
        get() = _diaryContent!!

    val prTime: LocalTime
        get() = _prTime!!.toLocalTime()

    val poTime: LocalTime
        get() = _poTime!!.toLocalTime()

    val diaryDate: LocalDate
        get() = _diaryDate!!.toLocalDate()

    private fun String.toLocalDate(): LocalDate =
        LocalDate.parse(this, DateTimeFormatter.ofPattern("yyyy-MM-dd"))

    private fun String.toLocalTime(): LocalTime =
        LocalTime.parse(this, DateTimeFormatter.ofPattern("HH:mm:ss"))
}

/**
 * 다이어리에 들어가야할 정보
 * 1. 애기가 뭐했는지 타입
 * 2.시간 2가지로 받을거야
 * 3. 메모
 * 4. 날짜
 */