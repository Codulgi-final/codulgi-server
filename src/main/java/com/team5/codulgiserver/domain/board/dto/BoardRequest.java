package com.team5.codulgiserver.domain.board.dto;


import lombok.Data;

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

