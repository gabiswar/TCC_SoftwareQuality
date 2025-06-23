package com.projectmanager.controllers;

import com.projectmanager.models.Card;
import com.projectmanager.services.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cards")
public class CardController {

    @Autowired
    private CardService cardService;

    @PostMapping
    public ResponseEntity<Card> createCard(@RequestBody Card card) {
        Card createdCard = cardService.createCard(card);
        return ResponseEntity.ok(createdCard);
    }

    @GetMapping("/{listId}")
    public ResponseEntity<List<Card>> getCardsByListId(@PathVariable Long listId) {
        List<Card> cards = cardService.getCardsByListId(listId);
        return ResponseEntity.ok(cards);
    }

    @PutMapping("/{cardId}")
    public ResponseEntity<Card> updateCard(@PathVariable Long cardId, @RequestBody Card card) {
        Card updatedCard = cardService.updateCard(cardId, card);
        return ResponseEntity.ok(updatedCard);
    }

    @DeleteMapping("/{cardId}")
    public ResponseEntity<Void> deleteCard(@PathVariable Long cardId) {
        cardService.deleteCard(cardId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{cardId}/move/{listId}")
    public ResponseEntity<Card> moveCard(@PathVariable Long cardId, @PathVariable Long listId) {
        Card movedCard = cardService.moveCard(cardId, listId);
        return ResponseEntity.ok(movedCard);
    }
}