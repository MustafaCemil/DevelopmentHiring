package main.cardgame.model;

import java.util.List;

public class Players {

    private Long playerId;
    private String playerName;
    private Integer playerHealth;
    private Boolean status;
    private Integer manaSlots;
    private List<Cards> cardSlot;
    private List<Cards> cardDeck;

    public Players() {
        this.playerHealth = 30;
        this.status = false;
        this.manaSlots = 0;
    }

    public Long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Long playerId) {
        this.playerId = playerId;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public Integer getPlayerHealth() {
        return playerHealth;
    }

    public void setPlayerHealth(Integer playerHealth) {
        this.playerHealth = playerHealth;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Integer getManaSlots() {
        return manaSlots;
    }

    public void setManaSlots(Integer manaSlots) {
        this.manaSlots = manaSlots;
    }

    public List<Cards> getCardSlot() {
        return cardSlot;
    }

    public void setCardSlot(List<Cards> cardSlot) {
        this.cardSlot = cardSlot;
    }

    public List<Cards> getCardDeck() {
        return cardDeck;
    }

    public void setCardDeck(List<Cards> cardDeck) {
        this.cardDeck = cardDeck;
    }

    @Override
    public String toString() {
        return "Players{" +
                "playerId=" + playerId +
                ", playerName='" + playerName + '\'' +
                ", playerHealth=" + playerHealth +
                ", status=" + status +
                ", manaSlots=" + manaSlots +
                ", cardSlot=" + cardSlot +
                ", cardDeck=" + cardDeck +
                '}';
    }
}
