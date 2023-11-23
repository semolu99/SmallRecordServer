package ConSilkTea.SmallRecordServer.diary.entity

import ConSilkTea.SmallRecordServer.common.status.Activity
import jakarta.persistence.*
import java.time.LocalDate
import java.time.LocalTime


@Entity
class Diary(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val diaryId: Long? = null,

    @Column(nullable = false, length = 10)
    @Enumerated(EnumType.STRING)
    val activity: Activity,

    @Column(nullable = false, length = 50)
    val diaryContent: String?,

    @Column(nullable = false)
    @Temporal(TemporalType.TIME)
    val prTime: LocalTime,

    @Column(nullable = false)
    @Temporal(TemporalType.TIME)
    val poTime: LocalTime,

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    val diaryDate: LocalDate,
)