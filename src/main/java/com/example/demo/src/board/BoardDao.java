package com.example.demo.src.board;

import com.example.demo.src.board.model.GetBoardRes;
import com.example.demo.src.board.model.PostBoardReq;
import com.example.demo.src.board.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import javax.sql.DataSource;
import java.util.List;

@Repository

public class BoardDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    //글 등록
    public int createBoard(PostBoardReq postBoardReq) {
        String createBoardQuery = "insert into Board  (writerIdx, title, content, status) VALUES(?,?,?,?)";
        Object[] createBoardParams = new Object[]{postBoardReq.getWriterIdx(), postBoardReq.getTitle(), postBoardReq.getContent(), postBoardReq.getStatus()};
        this.jdbcTemplate.update(createBoardQuery, createBoardParams);

        String lastInsertIdQuery = "select last_insert_id()";
        return this.jdbcTemplate.queryForObject(lastInsertIdQuery, int.class);
    }

    //idx 글 하나 조회
    public GetBoardRes getBoard(int boardIdx) {
        String getBoardQuery = "select * from Board where boardIdx = ?";
        int getBoardParams = boardIdx;
        return this. jdbcTemplate.queryForObject(getBoardQuery, (rs, rowNum) -> new GetBoardRes(
                rs.getInt("boardIdx"),
                rs.getInt("writerIdx"),
                rs.getString("title"),
                rs.getString("content"),
                rs.getString("status")),getBoardParams);
    }
}

