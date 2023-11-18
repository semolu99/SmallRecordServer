package ConSilkTea.SmallRecordServer.community.service

import ConSilkTea.SmallRecordServer.common.exception.InvalidInputException
import ConSilkTea.SmallRecordServer.community.dto.BoardDtoRequest
import ConSilkTea.SmallRecordServer.community.dto.BoardDtoResponse
import ConSilkTea.SmallRecordServer.community.entity.Board
import org.springframework.data.repository.findByIdOrNull
import ConSilkTea.SmallRecordServer.community.repository.BoardRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Transactional
@Service
class CommunityService(
    private val boardRepository: BoardRepository
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
}