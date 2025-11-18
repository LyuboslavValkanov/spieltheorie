package jpp.gametheory.rockPaperScissors;

import jpp.gametheory.generic.IGameRound;
import jpp.gametheory.generic.IPlayer;
import jpp.gametheory.generic.IReward;

public class RPSReward implements IReward<RPSChoice> {

    @Override
    public int getReward(IPlayer<RPSChoice> player, IGameRound<RPSChoice> gameRound) {
        int x= 0;
        for (IPlayer<RPSChoice> player2:
             gameRound.getOtherPlayers(player)) {
            x += choicevergleich(gameRound.getChoice(player),gameRound.getChoice(player2));

        }
        return x;
    }


    private int choicevergleich (RPSChoice  player1 , RPSChoice player2){
        if(player1==player2) return 0;

        switch (player1){
            case ROCK:
                if (player2==RPSChoice.PAPER) return -1;
                else return 2;

            case PAPER:
                if(player2 ==RPSChoice.ROCK) return 2;
                else return -1;
            case SCISSORS:
                if (player2==RPSChoice.ROCK) return -1;
                else return 2;

        }


        return 0 ;

    }
}
