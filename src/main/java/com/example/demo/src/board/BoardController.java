package com.example.demo.src.board;


import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.config.BaseResponseStatus;
import com.example.demo.src.user.model.PostUserRes;
import com.example.demo.utils.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.demo.src.board.model.*;

import java.util.List;

import static com.example.demo.config.BaseResponseStatus.*;

@RestController
@RequestMapping("/app/boards")

public class BoardController {
    //*******************

    // 로그 기록
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private final BoardProvider boardProvider;
    @Autowired
    private final BoardService boardService;
    @Autowired
    private final JwtService jwtService;


    public BoardController(BoardProvider boardProvider, BoardService boardService, JwtService jwtService) {
        this.boardProvider = boardProvider;
        this.boardService = boardService;
        this.jwtService = jwtService;
    }

    //***********************

    // Body

    /**
     * 게시판 등록
     * [POST] /boards/write
     */
    @ResponseBody
    @PostMapping("/write")
    public BaseResponse<PostBoardRes> createBoard(@RequestBody PostBoardReq postBoardReq) throws BaseException {
        if (postBoardReq.getTitle() == null || postBoardReq.getTitle().length() < 2) {
            return new BaseResponse<>(POST_BOARDS_EMPTY_TITLE);
        }
        PostBoardRes postBoardRes = boardService.createBoard(postBoardReq);
        return new BaseResponse<>(postBoardRes);
    }

    /**
     * 게시글 하나 조회
     * [GET] /boards/:boardIdx
     */
    @ResponseBody
    @GetMapping("/{boardIdx}")
    public BaseResponse<GetBoardRes> getBoard(@PathVariable("boardIdx") int boardIdx){

        try{
            GetBoardRes getBoardRes = boardProvider.getBoard(boardIdx);
            return new BaseResponse<>(getBoardRes);
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }




}