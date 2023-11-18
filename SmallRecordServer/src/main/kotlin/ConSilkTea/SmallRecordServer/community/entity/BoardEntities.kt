package ConSilkTea.SmallRecordServer.community.entity

import ConSilkTea.SmallRecordServer.community.dto.BoardDtoResponse
import jakarta.persistence.*
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@Entity
class Board(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long? = null,

    @Column(nullable = false, length = 50)
    val title: String,

    @Column(nullable = false, length = 500)
    val content: String,

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    val date: LocalDate,

    @Column(nullable = false)
    @Temporal(TemporalType.TIME)
    val time: LocalTime,
) {
    private fun LocalDate.formatDate(): String =
        this.format(DateTimeFormatter.ofPattern("yyyy년MM월dd일"))

    fun toDto(): BoardDtoResponse =
        BoardDtoResponse(
            id!!,
            title,
            content,
            date.formatDate()
        )
}