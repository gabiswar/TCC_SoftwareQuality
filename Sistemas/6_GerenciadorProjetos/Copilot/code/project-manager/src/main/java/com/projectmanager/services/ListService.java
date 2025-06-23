package com.projectmanager.services;

import com.projectmanager.models.List;
import com.projectmanager.repositories.ListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List as JavaList;
import java.util.Optional;

@Service
public class ListService {

    @Autowired
    private ListRepository listRepository;

    public List createList(List list) {
        return listRepository.save(list);
    }

    public Optional<List> getListById(Long id) {
        return listRepository.findById(id);
    }

    public JavaList<List> getAllLists() {
        return listRepository.findAll();
    }

    public List updateList(Long id, List updatedList) {
        if (listRepository.existsById(id)) {
            updatedList.setId(id);
            return listRepository.save(updatedList);
        }
        return null;
    }

    public boolean deleteList(Long id) {
        if (listRepository.existsById(id)) {
            listRepository.deleteById(id);
            return true;
        }
        return false;
    }
}