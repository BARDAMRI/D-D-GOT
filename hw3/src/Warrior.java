

import java.util.ArrayList;

public class Warrior extends Player {
    private int abilityCoolDown; //The number of game ticks required to pass before the warrior can cast the ability again.
    private int remainingCoolDown;  //Represents the number of ticks remained until the warrior can cast its special ability

    public Warrior(Position pos, int healthPool, int healthAmount, int attackPoints, int defencePoints, int abilityCoolDown, String name) {
        super(pos, healthPool, healthAmount, attackPoints, defencePoints);
        this.abilityCoolDown = abilityCoolDown;
        this.remainingCoolDown = 0;
        this.name=name;
    }

    public void levelUp(){
        super.levelUp();
        remainingCoolDown = 0;
        setHealthPool(getHealthPool()+getLevel()*5);
        setAttackPoints(getAttackPoints()+getLevel()*2);
        setDefencePoints(getDefencePoints()+getLevel());
    }

    @Override
    public void castAbility(ArrayList<Enemy> enemies) {

            remainingCoolDown = abilityCoolDown;
            for(Enemy e : enemies) {
                double a=getPos().distance(e.getPos());
                if (Math.abs(a) < 3) {
                    Double damage = getHealthPool()*0.1;
                    cmdPrinter.sendMessage("Special ability activated, "+this.name  +" hitted "+e.getName() +" with force of " + damage + " points");
                    e.getHit(damage.intValue());
                    setHealthAmount(getHealthAmount() + getDefencePoints() * 10);
                    if(e.getHealthAmount()==0){ //Player killed enemy
                        cmdPrinter.sendMessage(e.getName() +" has been killed. "+ this.getName()+ " earned "+e.getExperience()+" experience points");
                        this.updateExperience(e.getExperience());
                    }
                    break;
                }
            }
    }

    @Override
    protected void onTick() {
        remainingCoolDown--;
        if(remainingCoolDown<0) remainingCoolDown=0;
    }
    @Override
    public String describe(){

        return this.name+"      Health: "+getHealthAmount()+"      Attack: "+getAttackPoints()+ "      Defence: "+getDefencePoints()+"        Level: "+getLevel()+"     Experience: "+getExperience()+"      Ability CoolDown: "+abilityCoolDown+"      Remaining CoolDown: "+remainingCoolDown ;
    }

    @Override
    public boolean hasSpecialAbility() {
        return remainingCoolDown <= 0;
    }

    @Override
    public void castAbility(Player player) {

    }
}
