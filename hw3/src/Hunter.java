
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Hunter extends Player  {

    private  int range;
    private int arrowCount;
    private int ticksCount;
    private int arrowAmount;

    public Hunter(Position pos, int healthPool, int healthAmount, int attackPoints, int defencePoints, int range, String name) {
        super(pos, healthPool, healthAmount, attackPoints, defencePoints);
        this.name=name;
        ticksCount=0;
        arrowAmount=10*getLevel();
        arrowCount=arrowAmount;

    }

    public void levelUp(){
        super.levelUp();
        arrowAmount=10*getLevel();
        arrowCount=arrowCount+ 10*getLevel();
        setAttackPoints(getAttackPoints()+getLevel()*2);
        setDefencePoints(getDefencePoints()+getLevel()+getLevel());
    }

    @Override
    protected void onTick() {

        if(ticksCount==10)
        {
            arrowCount+=getLevel();
            ticksCount=0;
        }
        ticksCount++;
    }

    @Override
    public boolean hasSpecialAbility() {
        return arrowCount>0;
    }

    @Override
    public void castAbility(ArrayList<Enemy> enemies) {

        List<Enemy> inRange=enemies.stream().filter(x-> this.getPos().distance(x.getPos())<range).collect(Collectors.toList()); //find all enemies in range
        if(inRange.size()==0)
            cmdPrinter.sendMessage("no enemies in range for special ability");
        else
            while(arrowCount>0) {
                Enemy closestEnemy = inRange.get(0);
                for (Enemy e : inRange) {     //find closest enemy
                    if (this.getPos().distance(e.getPos()) < this.getPos().distance(closestEnemy.getPos()))
                        closestEnemy = e;
                }
                int defenceRoll = this.getRandom(closestEnemy.getDefencePoints());
                cmdPrinter.sendMessage(closestEnemy.getName() + " rolled up  " + defenceRoll + " defence points");
                if (getAttackPoints() - defenceRoll > 0) {
                    closestEnemy.getHit(getAttackPoints());
                    cmdPrinter.sendMessage(this.getName() + " dealt " + (getAttackPoints() - defenceRoll) + " dammage points to " + closestEnemy.getName());

                    if (closestEnemy.getHealthAmount() == 0) { //Player killed enemy
                        cmdPrinter.sendMessage(closestEnemy.getName() + " has been killed." + this.getName() + " earned " + closestEnemy.getExperience() + " experience points");
                        this.updateExperience(closestEnemy.getExperience());
                    }
                }
            }
    }
    private static int getRandom (int upperValue) {
        Random rand = new Random();
        return rand.nextInt(upperValue + 1);
    }
    @Override
    public String describe(){

        return this.name+"      Health: "+getHealthAmount()+"      Attack: "+getAttackPoints()+ "      Defence: "+getDefencePoints()+"        Level: "+getLevel()+"     Experience: "+getExperience()+"      Arrow Amount: "+arrowAmount+"      Remaining Arrows: "+arrowCount +"      Ticks Count: "+ticksCount;
    }

    @Override
    public void castAbility(Player player) {

    }
}
