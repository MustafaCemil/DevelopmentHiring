package main.cardgame.service;

import main.cardgame.model.Cards;
import main.cardgame.model.Players;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public abstract class AbstractService {

    public abstract String orderOfAttack(String playerNameOne, String playerNameTwo);

    public List<Players> initialHand(String playerNameOne, String playerNameTwo) {
        List<Players> players = createPlayers(playerNameOne, playerNameTwo);
        for (Players player : players) {
            List<Cards> cards = randomCardSelected(player.getCardDeck(), 3);

            cards.stream().forEach(removeCard -> {
                player.getCardDeck().removeIf(card -> card.getCardId().equals(removeCard.getCardId()));
            });
            player.setCardSlot(cards);
        }

        return players;
    }

    public List<Cards> randomCardSelected(List<Cards> cards, Integer limit) {
        Random random = new Random();

        return IntStream
                .generate(() -> random.nextInt(cards.size()))
                .distinct()
                .limit(limit)
                .mapToObj(cards::get)
                .collect(Collectors.toList());
    }

    public List<Players> createPlayers(String playerNameOne, String playerNameTwo) {
        Players playerOne = new Players();
        Players playerTwo = new Players();
        List<Cards> cardsOne = this.createCards();
        List<Cards> cardsTwo = this.createCards();

        playerOne.setPlayerId(1L);
        playerOne.setPlayerName(playerNameOne);
        playerOne.setCardDeck(cardsOne);
        playerOne.setStatus(true);

        playerTwo.setPlayerId(2L);
        playerTwo.setPlayerName(playerNameTwo);
        playerTwo.setCardDeck(cardsTwo);

        return Arrays.asList(playerOne, playerTwo);
    }

    public List<Cards> createCards() {
        final List<Integer> cardDamages = Arrays.asList(0, 0, 1, 1, 2, 2, 2, 3, 3, 3, 3, 4, 4, 4, 5, 5, 6, 6, 7, 8);
        final List<Cards> cards = new ArrayList<>();

        for (int i = 1; i <= cardDamages.size(); i++) {
            Cards card = new Cards((long) i, cardDamages.get(i - 1));
            cards.add(card);
        }

        return cards;
    }
}
