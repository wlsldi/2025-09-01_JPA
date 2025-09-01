package com.kh.app0831.member;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/member")
@RequiredArgsConstructor
public class MemberApiController {

    private final MemberService memberService;

    @PostMapping("join")
    public Long join(@RequestBody MemberDto dto){
        return memberService.join(dto);
    }

    @PostMapping("login")
    public MemberDto login(@RequestBody MemberDto dto){
        MemberDto loginMemberDto = memberService.login(dto);
        if (loginMemberDto == null) {
            // 로그인 실패 시 null 또는 예외 처리
            return null;
        }
        return loginMemberDto;
    }
}