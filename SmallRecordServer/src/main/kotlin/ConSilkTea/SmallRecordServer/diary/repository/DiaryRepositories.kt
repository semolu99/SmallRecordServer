package ConSilkTea.SmallRecordServer.diary.repository

import ConSilkTea.SmallRecordServer.diary.entity.Diary
import org.springframework.data.jpa.repository.JpaRepository

interface DiaryRepository : JpaRepository<Diary, Long>{
}