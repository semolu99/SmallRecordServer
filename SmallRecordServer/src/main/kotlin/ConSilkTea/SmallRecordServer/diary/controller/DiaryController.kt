package ConSilkTea.SmallRecordServer.diary.controller

import ConSilkTea.SmallRecordServer.common.dto.BaseResponse
import ConSilkTea.SmallRecordServer.diary.dto.DiaryDtoRequest
import ConSilkTea.SmallRecordServer.diary.service.DiaryService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/api/diary")
@RestController
class DiaryController(
    private val diaryService: DiaryService
) {
    /**
     * 회원가입
     */
    @PostMapping("/post")
    fun postUp(@RequestBody @Valid diaryDtoRequest: DiaryDtoRequest) : BaseResponse<Unit> {
        val resultMsg: String = diaryService.diaryPost(diaryDtoRequest)
        return BaseResponse(message = resultMsg)
    }
}