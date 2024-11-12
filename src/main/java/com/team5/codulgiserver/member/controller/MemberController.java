package com.team5.codulgiserver.member.controller;

import com.team5.codulgiserver.member.dto.MemberRequestDto;
import com.team5.codulgiserver.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    /* 사용자 생성 */
    @PostMapping
    public ResponseEntity<?> createMember(@RequestBody MemberRequestDto.save request) {
        return memberService.createMember(request);
    }

    /* 사용자 로그인 */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody MemberRequestDto.login request) {
        return memberService.loginMember(request);
    }

}
