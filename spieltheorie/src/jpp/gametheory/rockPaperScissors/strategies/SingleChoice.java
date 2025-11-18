package jpp.gametheory.rockPaperScissors.strategies;

import jpp.gametheory.generic.IGameRound;
import jpp.gametheory.generic.IPlayer;
import jpp.gametheory.generic.IStrategy;
import jpp.gametheory.rockPaperScissors.RPSChoice;

import java.util.List;

public class SingleChoice implements IStrategy<RPSChoice> {
    RPSChoice auswahl;

    public SingleChoice(RPSChoice auswahl) {
        this.auswahl = auswahl;
    }

    @Override
    public String name() {
        return "Always " + auswahl.name();
    }

    @Override
    public RPSChoice getChoice(IPlayer<RPSChoice> player, List<IGameRound<RPSChoice>> previousRounds) {
        return auswahl;
    }

    @Override
    public String toString() {
        return name();
    }
}
