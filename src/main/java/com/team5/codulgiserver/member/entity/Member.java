package com.team5.codulgiserver.member.entity;

import com.team5.codulgiserver.board.entity.Board;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;

@Table(name = "member")
@Getter
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String name;

    private String password;

    private LocalDate birthday;

    private String phone;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @OneToMany(mappedBy = "member")
    private Board board;
}
