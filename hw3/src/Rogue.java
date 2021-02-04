
import java.util.ArrayList;
import java.util.Random;

public class Rogue extends Player {
    private int cost;
    private int currentEnergy;

    public Rogue(Position pos, int healthPool, int healthAmount, int attackPoints, int defencePoints, int cost,String name) {
        super(pos, healthPool, healthAmount, attackPoints, defencePoints);
        this.cost = cost;
        this.currentEnergy = 100;
        this.name=name;
    }

    public void levelUp(){
        super.levelUp();
        currentEnergy = 100;
        setAttackPoints(getAttackPoints()+getLevel()*3);
    }

    @Override
    public void castAbility(ArrayList<Enemy> enemies) {

            currentEnergy -= cost;
            for (Enemy enemy : enemies){
                if(getPos().distance(enemy.getPos())<2){
                    int defenceRoll = getRandom(enemy.getDefencePoints());
                    cmdPrinter.sendMessage(this.name+" use Special ability activated, "+ this.name +" hit "+enemy.getName() +" with force of " + (getAttackPoints()) + " points");
                    cmdPrinter.sendMessage(enemy.getName() + " rolled up  "+ defenceRoll+ " defence points");
                    cmdPrinter.sendMessage(enemy.getName()+" lost "+ (getAttackPoints()-defenceRoll) + " points");
                    if(getAttackPoints()-defenceRoll>0)
                        enemy.getHit(getAttackPoints()-defenceRoll);
                    if(enemy.getHealthAmount()==0){ //Player killed enemy
                        cmdPrinter.sendMessage(enemy.getName() +" has been killed. "+ this.getName()+ " earned "+enemy.getExperience()+" experience points");
                        this.updateExperience(enemy.getExperience());
                    }
                }
            }
    }

    @Override
    public void castAbility(Player player) {

    }

    @Override
    protected void onTick() {
        currentEnergy = Math.max(currentEnergy+10, 100);
    }

    public void combat(Tile attacker)
    {

    }
    @Override
    public String describe(){

        return this.name+"      Health: "+getHealthAmount()+"      Attack: "+getAttackPoints()+ "      Defence: "+getDefencePoints()+"        Level: "+getLevel()+"     Experience: "+getExperience()+"     Current energy "+currentEnergy+"      cost: "+cost ;
    }

    @Override
    public boolean hasSpecialAbility() {
        return currentEnergy >= cost;
    }

    private int getRandom (int size){
        Random rand = new Random();
        return rand.nextInt( size);
    }
}
