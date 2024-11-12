package com.team5.codulgiserver.member.service;

import com.team5.codulgiserver.member.dto.MemberRequestDto;
import com.team5.codulgiserver.member.entity.Member;
import com.team5.codulgiserver.member.repository.MemberRepository;
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
    public ResponseEntity<?> loginMember(MemberRequestDto.login request) {
        Optional<Member> findMember = memberRepository.findByEmail(request.getEmail());
        if (findMember.isPresent()) {
            Member member = findMember.get();
            if (member.getPassword().equals(request.getPassword())) {
                return ResponseEntity.status(HttpStatus.OK).body(member);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("비밀번호를 다시 확인하세요.");
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("가입된 이메일이 없습니다.");
        }
    }


}
