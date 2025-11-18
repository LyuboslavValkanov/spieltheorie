package jpp.gametheory.generic;

import java.util.List;
import java.util.Objects;

public class Player<C extends IChoice> implements IPlayer<C> {

    String name ;
    IStrategy<C> strategy;

    public Player(String name, IStrategy<C> strategy) {
        if (name==null || strategy==null) throw  new NullPointerException( " Name und strategy sollen nicht null sein . ");

        this.name = name;
        this.strategy = strategy;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public IStrategy<C> getStrategy() {
        return strategy;
    }


    @Override
    public C getChoice(List<IGameRound<C>> previousRounds) {
        if (previousRounds==null ) throw  new NullPointerException("Die previousrounds sollen nicht null als Ruckgabe liefern. ");
        return strategy.getChoice(this,previousRounds);

    }

    @Override
    public int compareTo(IPlayer<C> o) {


        return getName().compareTo(o.getName());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player<?> player = (Player<?>) o;
        return name.equals(player.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return getName() + "(" + strategy.toString() + ")";

    }
}
