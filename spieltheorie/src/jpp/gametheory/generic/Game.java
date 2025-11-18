package jpp.gametheory.generic;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class  Game<C extends IChoice> {

    Set<IPlayer<C>> players;
    IReward<C> reward;
    List<IGameRound<C>> gameliste;

    public Game(Set<IPlayer<C>> players, IReward<C> reward) {

        for (IPlayer<C> player:
             players) {
            if(player==null) throw new NullPointerException( "Das Spieler muss existieren");

        }

        if (reward == null) throw new NullPointerException("Reward muss existieren");

        if(players.isEmpty()) throw new IllegalArgumentException( "Die players sollen existieren !");
        this.players = players;
        this.reward = reward;
        gameliste = new ArrayList<>();

    }


    public Set<IPlayer<C>> getPlayers() {
        return players;

    }

    public IGameRound<C> playRound() {
        HashMap<IPlayer<C>,C>  neueHashMap = new HashMap<>();
        for (IPlayer<C> aktuellspieler :players
             ) {

           neueHashMap.put(aktuellspieler,aktuellspieler.getChoice(gameliste));



        }

        IGameRound<C>neuerunde =new GameRound<C>(neueHashMap);
        gameliste.add(neuerunde);
        return neuerunde;
    }

    public void playNRounds(int n) {
        if(n<1) throw new IllegalArgumentException("N soll nicht auf jeden Fall 0 oder negative Zahl sein  ");
        for (int i = 0; i <n ; i++) {
            playRound();

        }
    }

    public Optional<IGameRound<C>> undoRound() {
        if(gameliste.isEmpty()) return Optional.empty();
        return Optional.of(gameliste.remove(gameliste.size()-1));



    }

    public void undoNRounds(int n) {
        if(n<1 ) throw  new IllegalArgumentException(" N soll nicht auf jeden Fall 0 oder negative Zahl sein ");
        for (int i = 0; i < n ; i++) {
            undoRound();

        }

    }

    public List<IGameRound<C>> getPlayedRounds() {

        return  Collections.unmodifiableList(gameliste);
    }

    public int getPlayerProfit(IPlayer<C> player) {
        if(player==null) throw  new NullPointerException("Player muss  am Spiel teilnehmen");
        int spielerprofit = 0;
        for (IGameRound <C> round:gameliste
             ) {
            spielerprofit +=reward.getReward(player ,round);


        }
return spielerprofit;

    }

    public Optional<IPlayer<C>> getBestPlayer() {
        int profitmax = 0;
        int secondprofitmax = -1;
        IPlayer<C> bestplayer = null;

        for (IPlayer<C> randomplayer:players
             ) {
            if (profitmax <getPlayerProfit(randomplayer)) {
                profitmax = getPlayerProfit(randomplayer);
                bestplayer = randomplayer;
            }else if (secondprofitmax < getPlayerProfit(randomplayer))
                secondprofitmax = getPlayerProfit(randomplayer);
        }
        if(profitmax ==secondprofitmax) return Optional.empty();
        return Optional.of(bestplayer);

    }

    public String toString() {
        StringBuilder toReturn = new StringBuilder();
        toReturn.append("Spiel nach " + gameliste.size() + " Runden:");
        toReturn.append("\nProfit : Spieler" );

        ArrayList<IPlayer<C>> sortedPlayers = new ArrayList<>();
        sortedPlayers.addAll(players);
        Collections.sort(sortedPlayers);


        int x = players.size();
        for (int i = 0; i < x; i++) {
            int best = Integer.MIN_VALUE;
            IPlayer<C> bestplayer = null;
            for (IPlayer<C> player:
                 sortedPlayers) {
                int act = getPlayerProfit(player);
                if(act > best) {
                    best=act;
                    bestplayer = player;
                }
            }
            toReturn.append("\n"+best+ " : " + bestplayer.toString());
            sortedPlayers.remove(bestplayer);
        }


        return toReturn.toString();
    }
}
