
import java.util.Random;

public abstract class Enemy extends Unit {
    protected int experience; //how much we get from defeating

    public Enemy(char character, Position pos, int healthPool, int healthAmount, int attackPoints, int defencePoints, int experience) {
        super(character, pos, healthPool, healthAmount, attackPoints, defencePoints);
        this.experience = experience;
    }

    int getExperience(){return experience;}

    abstract int makeTurn (Player player);

    void getHit(int damage){
        setHealthAmount(getHealthAmount()-damage);
        cmdPrinter.sendMessage(this.name+" remains with "+ getHealthAmount() + " health amount");
        if(getHealthAmount() < 0)
            setHealthAmount(0);
    }

    public void engage (Empty defender){
        Position p =  new Position(this.getPos().getX(),this.getPos().getY());
        this.getPos().setLocation(defender.getPos().getX(), defender.getPos().getY());
        defender.getPos().setLocation(p.getX(),p.getY());
    }

    public void engage (Wall defender){ }

    public void engage ( Enemy defender){  }

    public void engage (Player defender){
        cmdPrinter.sendMessage(this.getName() + " engaged in combat with "+ defender.getName());
        int attackRoll = getRandom(this.getAttackPoints());
        cmdPrinter.sendMessage(this.getName() + " rolled up "+ attackRoll+ " attack points");
        int defenceRoll = getRandom(defender.getDefencePoints());
        cmdPrinter.sendMessage(defender.getName() + " rolled up "+ defenceRoll+ " defence points");
        if(attackRoll-defenceRoll>0) {
            defender.getHit(attackRoll-defenceRoll);
            cmdPrinter.sendMessage(this.getName() + " dealt " + (attackRoll - defenceRoll) + " damage points to " + defender.getName());
        }
    }

    private static int getRandom (int upperValue){
        Random rand = new Random();
        return rand.nextInt(upperValue + 1);
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
