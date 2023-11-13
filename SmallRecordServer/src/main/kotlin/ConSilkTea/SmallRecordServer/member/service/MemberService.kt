package ConSilkTea.SmallRecordServer.member.service

import ConSilkTea.SmallRecordServer.common.authority.JwtTokenProvider
import ConSilkTea.SmallRecordServer.common.authority.TokenInfo
import ConSilkTea.SmallRecordServer.common.exception.InvalidInputException
import ConSilkTea.SmallRecordServer.common.status.ROLE
import ConSilkTea.SmallRecordServer.member.dto.LoginDto
import ConSilkTea.SmallRecordServer.member.dto.MemberDtoRequest
import ConSilkTea.SmallRecordServer.member.dto.MemberDtoResponse
import ConSilkTea.SmallRecordServer.member.entity.Member
import ConSilkTea.SmallRecordServer.member.entity.MemberRole
import ConSilkTea.SmallRecordServer.member.repository.MemberRepository
import ConSilkTea.SmallRecordServer.member.repository.MemberRoleRepository
import jakarta.transaction.Transactional
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.stereotype.Service

@Transactional
@Service
class MemberService(
    private val memberRepository: MemberRepository,
    private val memberRoleRepository: MemberRoleRepository,
    private val authenticationManagerBuilder: AuthenticationManagerBuilder,
    private val jwtTokenProvider: JwtTokenProvider,
) {
    /**
     * 회원가입
     */
    fun signUp(memberDtoRequest: MemberDtoRequest): String {
        //ID 중복 검사
        var member: Member? = memberRepository.findByLoginId(memberDtoRequest.loginId)
        if (member != null) {
            throw InvalidInputException("loginId", "이미 등록된 ID 입니다.")
        }

        member = memberDtoRequest.toEntity()
        memberRepository.save(member)

        val memberRole: MemberRole = MemberRole(null, ROLE.MEMBER, member)
        memberRoleRepository.save(memberRole)

        return "회원가입이 완료되었습니다."
    }

    /**
     * 로그인 -> 토큰 발행
     */
    fun login(loginDto: LoginDto): TokenInfo {
        val authenticationToken = UsernamePasswordAuthenticationToken(loginDto.loginId, loginDto.password)
        val authentication = authenticationManagerBuilder.`object`.authenticate(authenticationToken)

        return jwtTokenProvider.createToken(authentication)
    }

    /**
     * 내 정보 조회
     */
    fun searchMyInfo(id: Long): MemberDtoResponse {
        val member = memberRepository.findByIdOrNull(id) ?: throw InvalidInputException("id", "회원번호(${id}가 존재하지 않는 유저입니다.")
        return member.toDto()
    }

    /**
     * 내 정보 수정
     */
    fun saveMyInfo(memberDtoRequest: MemberDtoRequest): String {
        val member = memberDtoRequest.toEntity()
        memberRepository.save(member)
        return "수정 완료되었습니다."
    }
}