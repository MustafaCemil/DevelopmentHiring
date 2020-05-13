package main.cardgame.model;

public class Cards {

    private Long cardId;
    private Integer cardMana;

    public Cards() {

    }

    public Cards(Long cardId, Integer cardMana) {
        this.cardId = cardId;
        this.cardMana = cardMana;
    }

    public Long getCardId() {
        return cardId;
    }

    public void setCardId(Long cardId) {
        this.cardId = cardId;
    }

    public Integer getCardMana() {
        return cardMana;
    }

    public void setCardMana(Integer cardMana) {
        this.cardMana = cardMana;
    }

    @Override
    public String toString() {
        return "CardModel{" +
                "cardId=" + cardId +
                ", cardMana=" + cardMana +
                '}';
    }
}
