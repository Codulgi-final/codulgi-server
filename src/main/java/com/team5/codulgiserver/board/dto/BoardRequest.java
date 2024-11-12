package com.team5.codulgiserver.board.dto;


import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

public class BoardRequest {

    @Data
    public static class save {
        private String title;
        private String content;
        private String image;
        private Long authorId;
    }

    @Data
    public static class update {
        private Long id;
        private String title;
        private String content;
        private String image;
        private Long authorId;
    }
}

