package com.example.demo.src.board.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
public class Board {
    private int boardIdx;
    private int writerIdx;
    private String title;
    private String content;
    private String status;
}

