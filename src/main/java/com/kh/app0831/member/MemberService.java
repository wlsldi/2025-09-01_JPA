package com.kh.app0831.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public Long join(MemberDto dto){
        MemberEntity entity = MemberEntity.from(dto);
        memberRepository.join(entity);
        return entity.getNo();
    }

    public MemberDto login(MemberDto dto) {
        com.kh.app0831.member.MemberEntity entity = memberRepository.login(dto);
        if (entity == null) {
            // 로그인 실패 로직 (예: 예외 발생)
            return null;
        }
        MemberDto loginMemberDto = MemberDto.from(entity);
        return loginMemberDto;
    }
}