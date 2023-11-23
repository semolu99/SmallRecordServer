package ConSilkTea.SmallRecordServer.diary.service

import ConSilkTea.SmallRecordServer.diary.dto.DiaryDtoRequest
import ConSilkTea.SmallRecordServer.diary.entity.Diary
import ConSilkTea.SmallRecordServer.diary.repository.DiaryRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Transactional
@Service
class DiaryService(
    private val diaryRepository: DiaryRepository
) {
    /**
     * 다이어리 작성
     */
    fun diaryPost(diaryDtoRequest: DiaryDtoRequest): String {
        var diary = Diary(
            null,
            diaryDtoRequest.activity,
            diaryDtoRequest.diaryContent,
            diaryDtoRequest.prTime,
            diaryDtoRequest.poTime,
            diaryDtoRequest.diaryDate,
        )

        diaryRepository.save(diary)

        return "다이어리가 작성 완료 되었습니다."
    }
}