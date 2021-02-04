
import static java.lang.String.valueOf;

public abstract class Tile {
    private char character;
    private Position pos;

    public Tile (char character, Position pos){
        this.character=character;
        this.pos=pos;
    }

    public String toString() {return valueOf(character);} //cast to String

    public char getCharacter() {
        return character;
    }
    public Position getPos() {
        return pos;
    }

    public void setCharacter(char character) {
        this.character = character;
    }

    protected abstract void engage(Enemy enemy);
    protected abstract void engage(Empty enemy);
    protected abstract void engage(Wall enemy);
    protected abstract void engage(Player enemy);

    public abstract void combat(Player player);
    public abstract void combat(Enemy player);
    public abstract void combat(Wall player);
    public abstract void combat(Empty player);
}
