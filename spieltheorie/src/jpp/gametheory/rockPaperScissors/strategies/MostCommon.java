package jpp.gametheory.rockPaperScissors.strategies;

import jpp.gametheory.generic.IGameRound;
import jpp.gametheory.generic.IPlayer;
import jpp.gametheory.generic.IStrategy;
import jpp.gametheory.generic.Player;
import jpp.gametheory.rockPaperScissors.RPSChoice;


import java.util.List;


public class MostCommon implements IStrategy<RPSChoice> {

IStrategy<RPSChoice> alternate ;

    public MostCommon(IStrategy<RPSChoice>alternate) {

        this.alternate =alternate;
    }

    @Override
    public String name() {
      return   "Most Common Choice (Alternate: "+alternate+")" ;
    }

    @Override
    public RPSChoice getChoice(IPlayer<RPSChoice> player, List<IGameRound<RPSChoice>> previousRounds) {
        int r = 0;
        int p= 0;
        int s= 0;
        for (IGameRound<RPSChoice> aktuelleRunde:
                previousRounds) {
            for (IPlayer<RPSChoice> aktuellePlayer:
                    aktuelleRunde.getPlayers() ) {
                switch (aktuelleRunde.getChoice(aktuellePlayer)){
                    case SCISSORS :
                        s++;
                        break;
                    case ROCK:
                        r++;
                        break;
                    case PAPER:
                        p++;
                        break;
                }

            }


        }
        if(s>r&&s>p) return RPSChoice.SCISSORS;
        if(r>s&&r>p) return RPSChoice.ROCK;
        if(p>r&&p>s) return RPSChoice.PAPER;

        return alternate.getChoice(player,previousRounds);

    }

    @Override
    public String toString() {
        return name();
    }
}
