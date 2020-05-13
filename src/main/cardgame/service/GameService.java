package main.cardgame.service;

import main.cardgame.model.Cards;
import main.cardgame.model.Players;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class GameService extends AbstractService {

    @Override
    public String orderOfAttack(String playerNameOne, String playerNameTwo) {
        List<Players> players = this.initialHand(playerNameOne, playerNameTwo);
        players = selectedPlayer(players);

        return players.stream()
                .map(Players::getPlayerName)
                .findFirst()
                .get();
    }

    public List<Players> selectedPlayer(List<Players> playersList) {
        for (Players activePlayer : playersList) {
            Long actvPlayerId = activePlayer.getPlayerId();
            Players passivePlayer = playersList.stream()
                    .filter(players -> !players.getPlayerId().equals(actvPlayerId))
                    .findFirst()
                    .get();

            activePlayer.setManaSlots(10);
            if (activePlayer.getCardDeck().size() != 0) {
                Players players = this.selectedCard(activePlayer, passivePlayer);
                if (players.getPlayerHealth() == 0) {
                    return Arrays.asList(activePlayer);
                }
            } else {
                this.bleedingOut(passivePlayer, 1);
            }
        }

        Players player = playersList.stream()
                .filter(health -> health.getPlayerHealth() <= 0)
                .findFirst()
                .orElse(null);
        if (Objects.isNull(player)) {
            return selectedPlayer(playersList);
        }

        return Arrays.asList(player);
    }

    public Players selectedCard(Players activePlayer, Players passivePlayer) {
        if (activePlayer.getCardDeck().size() == 0 || activePlayer.getCardSlot().size() == 0) {
            activePlayer.setStatus(false);
            passivePlayer.setStatus(true);

            return activePlayer;
        }

        this.cardCheck(activePlayer);

        List<Cards> cards = this.randomCardSelected(activePlayer.getCardSlot(), 1);
        Cards card = cards.stream().findFirst().get();
        if (card.getCardMana() <= activePlayer.getManaSlots()) {
            passivePlayer = this.bleedingOut(passivePlayer, card.getCardMana());
            if (passivePlayer.getPlayerHealth() <= 0) {
                return activePlayer;
            }

            activePlayer.setManaSlots(activePlayer.getManaSlots() - card.getCardMana());

            final Cards minCardMana = activePlayer.getCardSlot().stream()
                    .min(Comparator.comparing(Cards::getCardMana))
                    .orElseThrow();
            if (minCardMana.getCardMana() != 0 || activePlayer.getManaSlots() != 0) {
                return selectedCard(activePlayer, passivePlayer);
            }
        }

        activePlayer.setStatus(false);
        passivePlayer.setStatus(true);

        return activePlayer;
    }

    public Players cardCheck(Players players) {
        List<Cards> cards = randomCardSelected(players.getCardDeck(), 1);
        List<Cards> cardDeck = players.getCardDeck();
        List<Cards> cardSlot = players.getCardSlot();

        final Long randomCardId = cards.stream().findFirst().get().getCardId();
        cardDeck.removeIf(card -> card.getCardId().equals(randomCardId));
        players.setCardDeck(cardDeck);

        cardSlot.addAll(cards);
        players.setCardSlot(cardSlot);

        return players;
    }

    public Players bleedingOut(Players passivePlayer, Integer lostHealth) {
        passivePlayer.setPlayerHealth(passivePlayer.getPlayerHealth() - lostHealth);

        return passivePlayer;
    }

}
