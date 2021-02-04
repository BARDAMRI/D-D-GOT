

public class Wall extends Tile {
    public Wall(Position pos) {
        super('#', pos);
    }

    @Override
    protected void engage(Enemy enemy) {

    }

    @Override
    protected void engage(Empty enemy) {

    }

    @Override
    protected void engage(Wall enemy) {

    }

    @Override
    protected void engage(Player enemy) {

    }

    @Override
    public void combat(Player  attacker) {

        attacker.engage(this);
    }

    @Override
    public void combat(Enemy  attacker) {

        attacker.engage(this);
    }

    @Override
    public void combat(Wall  attacker) {

    }

    @Override
    public void combat(Empty  attacker) {

    }
}
