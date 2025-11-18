package jpp.gametheory.rockPaperScissors.strategies;

import jpp.gametheory.generic.IGameRound;
import jpp.gametheory.generic.IPlayer;
import jpp.gametheory.generic.IReward;
import jpp.gametheory.generic.IStrategy;
import jpp.gametheory.rockPaperScissors.RPSChoice;

import java.util.List;

public class MostSuccessful implements IStrategy<RPSChoice> {
    IStrategy<RPSChoice> alternate;
    IReward<RPSChoice> reward;

    public MostSuccessful(IStrategy<RPSChoice> alternate, IReward<RPSChoice> reward) {
        this.alternate = alternate;
        this.reward = reward;
    }

    @Override
    public String name() {
        return "Most Successful Choice (Alternate: " + alternate.name()+")";
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
                        s+= reward.getReward(aktuellePlayer,aktuelleRunde);
                        break;
                    case ROCK:
                        r+=reward.getReward(aktuellePlayer,aktuelleRunde);
                        break;
                    case PAPER:
                        p+=reward.getReward(aktuellePlayer,aktuelleRunde);
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
