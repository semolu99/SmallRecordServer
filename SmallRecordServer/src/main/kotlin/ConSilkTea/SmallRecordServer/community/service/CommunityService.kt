package ConSilkTea.SmallRecordServer.community.service

import ConSilkTea.SmallRecordServer.common.exception.InvalidInputException
import ConSilkTea.SmallRecordServer.community.dto.BoardDtoRequest
import ConSilkTea.SmallRecordServer.community.dto.BoardDtoResponse
import ConSilkTea.SmallRecordServer.community.dto.CommentDto
import ConSilkTea.SmallRecordServer.community.entity.Board
import ConSilkTea.SmallRecordServer.community.entity.Comment
import ConSilkTea.SmallRecordServer.community.repository.BoardRepository
import ConSilkTea.SmallRecordServer.community.repository.CommentRepository
import jakarta.transaction.Transactional
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.function.Consumer


@Transactional
@Service
class CommunityService(
    private val boardRepository: BoardRepository,
    private val commentRepository: CommentRepository
) {
    /**
     * 게시글 작성
     */
    fun postUp(boardDtoRequest: BoardDtoRequest): String {
        var board = Board(
            null,
            boardDtoRequest.title,
            boardDtoRequest.content,
            boardDtoRequest.date,
            boardDtoRequest.time
        )

        boardRepository.save(board)

        return "게시글이 작성 완료 되었습니다."
    }

    /**
     * 게시글 리스트
     */
    fun boardList(): List<Board> {
        return boardRepository.findAll()
    }

    /**
     * 게시글 처리
     */
    fun boardView(id: Long): BoardDtoResponse {
        val board = boardRepository.findByIdOrNull(id)
            ?: throw InvalidInputException("id", "없는 게시물(${id}) 입니다.")
        return board.toDto()
    }

    /**
     * 댓글 작성하기
     */
    fun writeComment(id: Long, commentDto: CommentDto): CommentDto {
        val comment = Comment(
            null,
            commentDto.comment,
        )
        val board = boardRepository.findById(id)
            .orElseThrow { IllegalArgumentException("없는 게시판 입니다.") }

        comment.board = board
        commentRepository.save(comment)

        return CommentDto.toDto(comment)
    }

    /**
     * 글에 해당하는 전체 댓글 불러오기
     */
    fun getComments(id: Long): MutableList<CommentDto> {
        val comments = commentRepository.findAllByBoardId(id.toLong())
        val commentDtos: MutableList<CommentDto> = ArrayList()
        comments.forEach(Consumer { s: Comment? -> commentDtos.add(CommentDto.toDto(s!!)) })
        return commentDtos
    }
}