package com.team5.codulgiserver.domain.board.dto;

import com.team5.codulgiserver.domain.board.entity.Board;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BoardResponse {

    @Data
    public static class board {
        private Long id;
        private String title;
        private String content;
        private String thumbnail;
        private String authorName;
        private String authorEmail;
        private Integer likeCount;  // 추가된 필드
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

        public board(Board board) {
            this.id = board.getId();
            this.title = board.getTitle();
            this.content = board.getContent();
            this.thumbnail = board.getThumbnail();
            this.authorName = board.getMember().getName();
            this.authorEmail = board.getMember().getEmail();
            this.likeCount = board.getLikeCount();  // 추가된 필드 초기화
            this.createdAt = board.getCreatedAt();
            this.updatedAt = board.getUpdatedAt();
        }
    }
}