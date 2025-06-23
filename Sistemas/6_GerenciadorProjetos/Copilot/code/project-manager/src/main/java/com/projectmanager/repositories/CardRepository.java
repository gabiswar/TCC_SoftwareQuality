package com.projectmanager.repositories;

import com.projectmanager.models.Card;
import java.util.List;

public interface CardRepository {
    Card createCard(Card card);
    Card getCardById(Long id);
    List<Card> getAllCardsByListId(Long listId);
    Card updateCard(Card card);
    void deleteCard(Long id);
}