package ConSilkTea.SmallRecordServer.community.controller

import ConSilkTea.SmallRecordServer.common.dto.BaseResponse
import ConSilkTea.SmallRecordServer.community.dto.BoardDtoRequest
import ConSilkTea.SmallRecordServer.community.dto.BoardDtoResponse
import ConSilkTea.SmallRecordServer.community.dto.CommentDto
import ConSilkTea.SmallRecordServer.community.dto.CommentResponse
import ConSilkTea.SmallRecordServer.community.entity.Board
import ConSilkTea.SmallRecordServer.community.entity.Comment
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
    @GetMapping("/post/{id}")
    fun boardView(@PathVariable id: Long): BaseResponse<BoardDtoResponse> {
        val response = communityService.boardView(id)
        return BaseResponse(data = response)
    }

    /**
     * 댓글작성
     */
    @PostMapping("/post/{id}")
    fun writeComment(@PathVariable id: Long, @RequestBody commentDto: CommentDto): CommentDto {
        val response: CommentDto = communityService.writeComment(id, commentDto)
        return response
    }

    /**
     * 댓글 모두 불러오기
     */
    @GetMapping("/comment/{id}")
    fun commentlist(@PathVariable id: Long): MutableList<CommentDto> {
        return communityService.getComments(id)
    }
}