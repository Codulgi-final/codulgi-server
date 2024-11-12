package com.team5.codulgiserver.member.dto;

import com.team5.codulgiserver.member.entity.Gender;
import lombok.Data;

import java.time.LocalDate;

@Data
public class MemberRequestDto {

    @Data
    public static class save {
        private String email;
        private String name;
        private String password;
        private LocalDate birthday;
        private String phone;
        private Gender gender;
    }

    @Data
    public static class login {
        private String email;
        private String password;
    }
}
