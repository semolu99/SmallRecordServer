package ConSilkTea.SmallRecordServer.community.repository

import ConSilkTea.SmallRecordServer.community.entity.Board
import ConSilkTea.SmallRecordServer.community.entity.Comment
import org.springframework.data.jpa.repository.JpaRepository

interface BoardRepository : JpaRepository<Board, Long> {
}

interface CommentRepository : JpaRepository<Comment, Long> {
    fun findAllByBoardId(boardId: Long): List<Comment>
}