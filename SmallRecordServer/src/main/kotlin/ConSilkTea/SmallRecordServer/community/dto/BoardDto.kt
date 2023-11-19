package ConSilkTea.SmallRecordServer.community.dto

import ConSilkTea.SmallRecordServer.community.entity.Board
import ConSilkTea.SmallRecordServer.community.entity.Comment
import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.NotBlank
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

data class BoardDtoRequest(
    val id: Long?,

    @field:NotBlank
    @JsonProperty("title")
    private val _title: String?,

    @field:NotBlank
    @JsonProperty("content")
    private val _content: String?,

    @field:NotBlank
    @JsonProperty("date")
    private val _date: String?,

    @field:NotBlank
    @JsonProperty("time")
    private val _time: String?,
) {
    val title: String
        get() = _title!!

    val content: String
        get() = _content!!

    val date: LocalDate
        get() = _date!!.toLocalDate()

    val time: LocalTime
        get() = _time!!.toLocalTime()

    private fun String.toLocalDate(): LocalDate =
        LocalDate.parse(this, DateTimeFormatter.ofPattern("yyyy-MM-dd"))

    private fun String.toLocalTime(): LocalTime =
        LocalTime.parse(this, DateTimeFormatter.ofPattern("HH:mm:ss"))
}

data class BoardDtoResponse(
    val id: Long,
    val title: String,
    val content: String,
    val date: String
)

data class CommentDto(
    val id: Long,
    val comment: String,
) {
    companion object {
        fun toDto(comment: Comment): CommentDto {
            return CommentDto(
                comment.id!!,
                comment.comment!!,
            )
        }
    }
}

data class CommentResponse(
    val id: Long,
    val comment: String,
)