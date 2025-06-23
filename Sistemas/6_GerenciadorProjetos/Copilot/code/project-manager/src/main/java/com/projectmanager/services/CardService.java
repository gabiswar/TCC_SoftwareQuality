package com.projectmanager.services;

import com.projectmanager.models.Card;
import com.projectmanager.repositories.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CardService {

    @Autowired
    private CardRepository cardRepository;

    public Card createCard(Card card) {
        return cardRepository.save(card);
    }

    public List<Card> getAllCardsByListId(Long listId) {
        return cardRepository.findByListId(listId);
    }

    public Optional<Card> getCardById(Long cardId) {
        return cardRepository.findById(cardId);
    }

    public Card updateCard(Long cardId, Card cardDetails) {
        Card card = cardRepository.findById(cardId)
                .orElseThrow(() -> new RuntimeException("Card not found with id " + cardId));
        card.setTitle(cardDetails.getTitle());
        card.setDescription(cardDetails.getDescription());
        card.setDueDate(cardDetails.getDueDate());
        return cardRepository.save(card);
    }

    public void deleteCard(Long cardId) {
        Card card = cardRepository.findById(cardId)
                .orElseThrow(() -> new RuntimeException("Card not found with id " + cardId));
        cardRepository.delete(card);
    }

    public void moveCard(Long cardId, Long targetListId) {
        Card card = cardRepository.findById(cardId)
                .orElseThrow(() -> new RuntimeException("Card not found with id " + cardId));
        card.setListId(targetListId);
        cardRepository.save(card);
    }
}