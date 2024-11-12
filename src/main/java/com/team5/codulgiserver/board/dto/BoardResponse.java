package com.team5.codulgiserver.board.dto;

import com.team5.codulgiserver.board.entity.Board;
import lombok.Data;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Getter
public class BoardResponse {

    @Data
    public static class board {
        private Long id;
        private String title;
        private String content;
        private MultipartFile thumbnail;
        private String authorName;
        private String authorEmail;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

        public board(Board board) {
            this.id = board.getId();
            this.title = board.getTitle();
            this.content = board.getContent();
            this.thumbnail = board.getThumbnail();
            this.authorName = board.getMember().getName();
            this.authorEmail = board.getMember().getEmail();
            this.createdAt = board.getCreatedAt();
            this.updatedAt = board.getUpdatedAt();
        }
    }
}
