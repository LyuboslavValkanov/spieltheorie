package jpp.gametheory.rockPaperScissors.strategies;

import jpp.gametheory.generic.IGameRound;
import jpp.gametheory.generic.IPlayer;
import jpp.gametheory.generic.IStrategy;
import jpp.gametheory.rockPaperScissors.RPSChoice;

import java.util.List;


public class CircleChoice implements IStrategy<RPSChoice> {
    public CircleChoice(){}

    @Override
    public String name() {
        return "Circle Choice";
    }

    @Override
    public RPSChoice getChoice(IPlayer<RPSChoice> player, List<IGameRound<RPSChoice>> previousRounds) {
        if(previousRounds.isEmpty()) return RPSChoice.ROCK;


         switch (previousRounds.get(previousRounds.size()-1).getChoice(player)){
             case ROCK :return RPSChoice.PAPER;
             case SCISSORS:return RPSChoice.ROCK;
             case PAPER:return RPSChoice.SCISSORS;

         }
         return RPSChoice.ROCK;
    }

    @Override
    public String toString() {
        return name();
    }
}
