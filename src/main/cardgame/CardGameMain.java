package main.cardgame;

import main.cardgame.service.GameService;

public class CardGameMain {

    public static void main(String[] args) {
        GameService gameService = new GameService();
        final String winner = gameService.orderOfAttack("PlayerOne", "PlayerTwo");
        System.out.println("Game winner: " + winner);
    }
}