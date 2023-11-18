package ConSilkTea.SmallRecordServer.community.repository

import ConSilkTea.SmallRecordServer.community.entity.Board
import org.springframework.data.jpa.repository.JpaRepository

interface BoardRepository : JpaRepository<Board, Long> {
}