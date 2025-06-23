package com.projectmanager.repositories;

import com.projectmanager.models.List;
import java.util.List;

public interface ListRepository {
    List<List> findAll();
    List<List> findByBoardId(Long boardId);
    List<List> save(List list);
    List<List> update(Long id, List list);
    void delete(Long id);
}