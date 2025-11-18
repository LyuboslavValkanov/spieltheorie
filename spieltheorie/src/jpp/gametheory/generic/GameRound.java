package jpp.gametheory.generic;

import java.util.*;
import java.util.stream.Collectors;

public class GameRound<C extends IChoice> implements IGameRound<C> {

    HashMap<IPlayer<C>, C > spielerHashMap; // <IPlayer<C> - key (player) ; C - value (choices)






    public GameRound(Map<IPlayer<C>, C> playerChoices) {
        if( playerChoices.isEmpty()) throw new IllegalArgumentException("Die Playerchoices sollen  auf jeden Fall nicht empty sein. ");
        spielerHashMap = new HashMap<>();
        spielerHashMap.putAll(playerChoices);


    }

    @Override
    public Map<IPlayer<C>, C> getPlayerChoices() {

        return spielerHashMap;

    }

    @Override
    public C getChoice(IPlayer<C> player) {
        if(player==null) throw new NullPointerException("Die Players sollen existieren !");
        if (!spielerHashMap.containsKey(player)) throw new IllegalArgumentException("falls der Spieler in der Runde nicht mitgespielt hat.");


        return  spielerHashMap.get(player); //return choice (value)  mit get(key )

    }

    @Override
    public Set<IPlayer<C>> getPlayers() {


        return Collections.unmodifiableSet(spielerHashMap.keySet());
    }

    @Override
    public Set<IPlayer<C>> getOtherPlayers(IPlayer<C> player) {
        if (player==null) throw  new NullPointerException("Die players sollen existieren !");
        if (!spielerHashMap.containsKey(player)) throw new IllegalArgumentException("falls der Spieler in der Runde nicht mitgespielt hat. ");

        HashSet<IPlayer <C>> spielerSet = new HashSet<>();
               spielerSet.addAll(spielerHashMap.keySet());
        spielerSet.remove(player);
        return spielerSet;

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("(");
        for (IPlayer<C> actPlayer:
             getPlayers().stream().sorted().collect(Collectors.toList())) {
            sb.append(actPlayer.getName());
            sb.append(" -> ");
            sb.append(getChoice(actPlayer).name());
            sb.append(", ");
            

        }
        sb.delete(sb.length()-2, sb.length());
        sb.append(")");
        return sb.toString();



    }
}
