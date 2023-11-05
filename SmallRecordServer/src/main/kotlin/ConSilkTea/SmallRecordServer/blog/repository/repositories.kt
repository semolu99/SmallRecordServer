package ConSilkTea.SmallRecordServer.blog.repository

import ConSilkTea.SmallRecordServer.blog.entity.WordCount
import org.springframework.data.repository.CrudRepository

interface WordRepository : CrudRepository<WordCount, String> {
    fun findTop10ByOrderByCntDesc(): List<WordCount>
}