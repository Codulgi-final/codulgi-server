package com.team5.codulgiserver.domain.member.controller;

import com.team5.codulgiserver.domain.member.dto.MemberRequestDto;
import com.team5.codulgiserver.domain.member.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final HttpSession session;

    /* 사용자 생성 */
    @PostMapping
    public ResponseEntity<?> createMember(@RequestBody MemberRequestDto.save request) {
        return memberService.createMember(request);
    }

    /* 사용자 로그인 */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody MemberRequestDto.login request) {

        return memberService.loginMember(request, session);
    }

    /* 사용자 로그아웃 */
    @GetMapping("/logout")
    public ResponseEntity<String> logout(HttpSession session) {
        session.removeAttribute("member");
        return ResponseEntity.ok("로그아웃이 완료되었습니다.");
    }

}