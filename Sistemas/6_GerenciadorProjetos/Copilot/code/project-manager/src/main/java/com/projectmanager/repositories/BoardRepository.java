package com.projectmanager.repositories;

import com.projectmanager.models.Board;
import java.util.List;

public interface BoardRepository {
    Board createBoard(Board board);
    List<Board> getAllBoards();
    Board getBoardById(Long id);
    Board updateBoard(Long id, Board board);
    void deleteBoard(Long id);
}