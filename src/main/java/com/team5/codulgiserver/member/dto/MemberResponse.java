package com.team5.codulgiserver.member.dto;

import com.team5.codulgiserver.member.entity.Gender;
import com.team5.codulgiserver.member.entity.Member;
import lombok.Data;

import java.time.LocalDate;

@Data
public class MemberResponse {
    private Long id;
    private String email;
    private String name;
    private LocalDate birthday;
    private String phone;
    private Gender gender;

    public MemberResponse(Member member) {
        this.id = member.getId();
        this.email = member.getEmail();
        this.name = member.getName();
        this.birthday = member.getBirthday();
        this.phone = member.getPhone();
        this.gender = member.getGender();
    }
}
