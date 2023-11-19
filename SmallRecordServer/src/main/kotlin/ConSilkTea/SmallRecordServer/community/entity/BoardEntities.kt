package ConSilkTea.SmallRecordServer.community.entity

import ConSilkTea.SmallRecordServer.community.dto.BoardDtoResponse
import ConSilkTea.SmallRecordServer.community.dto.CommentDto
import jakarta.persistence.*
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction
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

@Entity
class Comment(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false, length = 500)
    var comment: String?,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    var board: Board? = null
) {
    fun toDto(): CommentDto =
        CommentDto(
            id!!,
            comment!!,
        )
}