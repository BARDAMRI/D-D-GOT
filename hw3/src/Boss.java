
import java.util.ArrayList;
import java.util.Random;

public class Boss extends  Monster implements HeroicUnit {

    private int abilityFrequency;
    private  int combatTicks;


    public Boss(char character, Position pos, int healthPool, int healthAmount, int attackPoints, int defencePoints, int experience, int visionRange, String name,int abilityFrequency) {
        super(character, pos, healthPool, healthAmount, attackPoints, defencePoints, experience, visionRange, name);
        this.abilityFrequency=abilityFrequency;
        this.combatTicks=0;
    }

    @Override
    public int makeTurn(Player player) {

        //1->up 2->down 3->left 4->right
        if(getPos().distance(player.getPos()) <= visionRange) {
            if (combatTicks == abilityFrequency) {
                castAbility(player);
            }
            else {
                combatTicks++;
                int dx = getPos().getX() - player.getPos().getX();
                int dy = getPos().getY() - player.getPos().getY();
                if (Math.abs(dx) > Math.abs(dy)) {
                    if (dx > 0) {
                        return 1;
                    } else return 2;
                } else {
                    if (dy > 0) return 3;
                    else return 4;
                }
            }
        }
        else {
            combatTicks=0;
            Random rand = new Random();
            int value = rand.nextInt(5); //value between 0-4
            if (value == 1) return 1;//up
            if (value == 2)  return 2; //down
            if (value == 3)  return 3; //left
            if (value == 4)  return 4; //right
            // if got 0 stay in place (do nothing here)
        }

        return 0;
    }
    private static int getRandom (int upperValue){
        Random rand = new Random();
        return rand.nextInt(upperValue + 1);
    }

    @Override
    public void engage(Empty empty) {

    }

    @Override
    public void castAbility(ArrayList<Enemy> enemies) {


    }

    @Override
    public void castAbility(Player player) {

        combatTicks = 0;
        cmdPrinter.sendMessage(this.name+" use Special ability activated, "+ this.name +" hits "+ player.name +" with force of " + (getAttackPoints()) + " points");
        int defenceRoll = getRandom(player.getDefencePoints());
        cmdPrinter.sendMessage(player.getName() + " rolled up " + defenceRoll + " defence points");
        cmdPrinter.sendMessage(player.getName()+" lost "+ (getAttackPoints()-defenceRoll) + " points");
        if (this.getAttackPoints() - defenceRoll > 0) {
            player.getHit(getAttackPoints());
            cmdPrinter.sendMessage(this.getName() + " dealt " + (this.getAttackPoints() - defenceRoll) + " damage points to " + player.getName());
        }
    }
}
