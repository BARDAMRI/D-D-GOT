import java.util.LinkedList;
import java.util.List;

public class Clock {
    static int GameTick = 0;
    static List <Player> listeners = new LinkedList<>(); //TODO: Unit

    public static void Tick(){
        GameTick++;
        //notify everyone
        for(Player p : listeners)
            p.onTick();
    }

    public static void signToClock(Player player){
        listeners.add(player);
    }
}
