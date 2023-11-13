package ConSilkTea.SmallRecordServer.member.repository

import ConSilkTea.SmallRecordServer.member.entity.Member
import ConSilkTea.SmallRecordServer.member.entity.MemberRole
import org.springframework.data.jpa.repository.JpaRepository

interface MemberRepository : JpaRepository<Member, Long> {
    fun findByLoginId(loginId: String): Member?
}

interface MemberRoleRepository : JpaRepository<MemberRole, Long>