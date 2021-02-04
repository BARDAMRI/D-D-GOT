
public class Trap extends Enemy {
    private int visibilityTime; //amount of ticks that the trap remains visible
    private int invisibilityTime; //amount of ticks that the trap remains invisible
    int ticksCount; //counts the number of ticks since last visibility state change
    boolean visible;

    public Trap(char character, Position pos, int healthPool, int healthAmount, int attackPoints, int defencePoints, int experience, int visibilityTime, int invisibilityTime, String name) {
        super(character, pos, healthPool, healthAmount, attackPoints, defencePoints, experience);
        this.visibilityTime = visibilityTime;
        this.invisibilityTime = invisibilityTime;
        ticksCount = 0;
        visible = true;
        this.name=name;
    }

    int makeTurn(Player player) {
        //updateTicks
        visible = ticksCount < visibilityTime;
        if(ticksCount==visibilityTime+invisibilityTime)
            ticksCount = 0;
        else {
            ticksCount++;
            if (getPos().distance(player.getPos()) < 2) {
                cmdPrinter.sendMessage(this.name+" engaged in combat with "+player.name);
                cmdPrinter.sendMessage(this.name+" attack "+player.name+" with "+ getAttackPoints());
                player.setHealthAmount(player.getHealthAmount() - getAttackPoints());
            }
        }
        return -1;
    }

    public String toString(){
        if(visible) return String.valueOf(getCharacter());
        return ".";
    }

    @Override
    public char getCharacter() {
        if(visible) return super.getCharacter();
        return '.';
    }
}
