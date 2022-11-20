package com.example.demo.src.board.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostBoardReq {
    private int boardIdx;
    private int writerIdx;
    private String title;
    private String content;
    private String status;
}
