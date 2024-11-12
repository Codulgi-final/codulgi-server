package com.team5.codulgiserver.member.service;

import com.team5.codulgiserver.member.dto.MemberRequestDto;
import com.team5.codulgiserver.member.dto.MemberResponse;
import com.team5.codulgiserver.member.entity.Member;
import com.team5.codulgiserver.member.repository.MemberRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    /* 회원가입 */
    @Transactional
    public ResponseEntity<?> createMember(MemberRequestDto.save request) {
        Optional<Member> findMember = memberRepository.findByEmail(request.getEmail());

        if (findMember.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("이미 가입된 이메일입니다.");
        }

        Member member = new Member(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(memberRepository.save(member));
    }

    /* 로그인 */
    public ResponseEntity<?> loginMember(MemberRequestDto.login request, HttpSession session) {

        Optional<Member> findMember = memberRepository.findByEmail(request.getEmail());

        /* Request를 통해 해당 이메일과 일치하는 사람이 있다면 */
        if (findMember.isPresent()) {
            Member member = findMember.get();

            /* 비밀번호가 일치 할때 */
            if (member.getPassword().equals(request.getPassword())) {

                MemberResponse memberResponse = new MemberResponse(member);

                /* 세션에 로그인을 성공한 사용자의 정보를 저장한다.*/
                session.setAttribute("member", memberResponse);

                return ResponseEntity.status(HttpStatus.OK).body(memberResponse);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("비밀번호를 다시 확인하세요.");
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("가입된 이메일이 없습니다.");
        }
    }


}
