package ConSilkTea.SmallRecordServer.community.controller

import ConSilkTea.SmallRecordServer.common.dto.BaseResponse
import ConSilkTea.SmallRecordServer.community.dto.BoardDtoRequest
import ConSilkTea.SmallRecordServer.community.dto.BoardDtoResponse
import ConSilkTea.SmallRecordServer.community.entity.Board
import ConSilkTea.SmallRecordServer.community.repository.BoardRepository
import ConSilkTea.SmallRecordServer.community.service.CommunityService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*


@RequestMapping("/api/community")
@RestController
class BoardController(
    private val communityService: CommunityService
) {
    /**
     * 게시글 작성
     */
    @PostMapping("/postup")
    fun postup(@RequestBody @Valid boardDtoRequest: BoardDtoRequest): String {
        return communityService.postUp(boardDtoRequest)
    }

    /**
     * 게시글 리스트
     */
    @GetMapping("/list")
    fun list(): List<Board> {
        return communityService.boardList()
    }

    /**
     * 게시글 내용
     */
    @GetMapping("/view/{id}")
    fun boardView(@PathVariable id: Long): BaseResponse<BoardDtoResponse> {
        val response = communityService.boardView(id)
        return BaseResponse(data = response)
    }
}