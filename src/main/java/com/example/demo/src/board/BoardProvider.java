package com.example.demo.src.board;

import com.example.demo.config.BaseException;
import com.example.demo.config.secret.Secret;
import com.example.demo.src.board.model.GetBoardRes;
import com.example.demo.src.user.model.*;
import com.example.demo.utils.AES128;
import com.example.demo.utils.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import static com.example.demo.config.BaseResponseStatus.*;

@Service

public class BoardProvider {
    private final BoardDao boardDao;
    private final JwtService jwtService;

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public BoardProvider(BoardDao boardDao, JwtService jwtService) {
        this.boardDao = boardDao;
        this.jwtService = jwtService;
    }

    // 게시판들의 정보 조회
//    public List<GetBoardRes> getBoardRes() throws BaseException {
//        try {
//            List<GetBoardRes> getBoardRes = boardDao.getBoards();
//            return getBoardRes;
//        } catch (Exception exception) {
//            throw new BaseException(DATABASE_ERROR);
//        }
//    }

    //idx로 게시판 하나 조회
    public GetBoardRes getBoard(int boardIdx) throws BaseException {
        try {
            GetBoardRes getBoardRes = boardDao.getBoard(boardIdx);
            return getBoardRes;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

}
