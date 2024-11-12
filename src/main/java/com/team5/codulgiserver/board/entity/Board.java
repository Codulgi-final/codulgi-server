package com.team5.codulgiserver.board.entity;

import com.team5.codulgiserver.board.dto.BoardRequest;
import com.team5.codulgiserver.member.entity.Member;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Table(name = "board")
@Getter
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String content;
    private MultipartFile thumbnail;

    /* 작성자*/
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private Integer like;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Board(BoardRequest.save request, Member member) {
        this.title = request.getTitle();
        this.content = request.getContent();
        this.thumbnail = request.getImage();
        this.member = member;
        this.like = 0;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public Board() {
    }

    public Board update(BoardRequest.update request) {
        if (request.getTitle() != null) {
            this.title = request.getTitle();
        }
        if (request.getContent() != null) {
            this.content = request.getContent();
        }
        if (request.getImage() != null) {
            this.thumbnail = request.getImage();
        }
        this.updatedAt = LocalDateTime.now();

        return this;
    }
}
