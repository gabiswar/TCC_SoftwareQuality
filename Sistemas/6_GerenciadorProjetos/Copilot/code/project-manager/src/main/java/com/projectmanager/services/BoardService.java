package com.projectmanager.services;

import com.projectmanager.models.Board;
import com.projectmanager.repositories.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BoardService {

    private final BoardRepository boardRepository;

    @Autowired
    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public Board createBoard(Board board) {
        return boardRepository.save(board);
    }

    public List<Board> getAllBoards() {
        return boardRepository.findAll();
    }

    public Optional<Board> getBoardById(Long id) {
        return boardRepository.findById(id);
    }

    public Board updateBoard(Long id, Board updatedBoard) {
        if (boardRepository.existsById(id)) {
            updatedBoard.setId(id);
            return boardRepository.save(updatedBoard);
        }
        return null;
    }

    public void deleteBoard(Long id) {
        boardRepository.deleteById(id);
    }
}