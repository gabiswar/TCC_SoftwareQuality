package com.projectmanager.controllers;

import com.projectmanager.models.List;
import com.projectmanager.services.ListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lists")
public class ListController {

    @Autowired
    private ListService listService;

    @PostMapping
    public ResponseEntity<List> createList(@RequestBody List list) {
        List createdList = listService.createList(list);
        return ResponseEntity.ok(createdList);
    }

    @GetMapping("/{boardId}")
    public ResponseEntity<List<List>> getListsByBoardId(@PathVariable Long boardId) {
        List<List> lists = listService.getListsByBoardId(boardId);
        return ResponseEntity.ok(lists);
    }

    @PutMapping("/{id}")
    public ResponseEntity<List> updateList(@PathVariable Long id, @RequestBody List list) {
        List updatedList = listService.updateList(id, list);
        return ResponseEntity.ok(updatedList);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteList(@PathVariable Long id) {
        listService.deleteList(id);
        return ResponseEntity.noContent().build();
    }
}