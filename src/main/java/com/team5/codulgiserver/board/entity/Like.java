package com.team5.codulgiserver.board.entity;

import com.team5.codulgiserver.member.entity.Member;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Table(name = "m_b_like")
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;

    private LocalDateTime likedAt = LocalDateTime.now();
}
