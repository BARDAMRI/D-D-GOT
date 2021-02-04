
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Mage extends Player {

    private int manaPool;
    private int currentMana;
    private int manaCost;
    private int spellPower;
    private int hitsCount;
    private int abilityRange;

    public Mage(Position pos, int healthPool, int healthAmount, int attackPoints, int defencePoints, int manaPool, int manaCost, int spellPower, int hitsCount, int abilityRange,String name) {
        super(pos, healthPool, healthAmount, attackPoints, defencePoints);
        this.manaPool = manaPool;
        this.currentMana = manaPool/4;
        this.manaCost = manaCost;
        this.spellPower = spellPower;
        this.hitsCount = hitsCount;
        this.abilityRange = abilityRange;
        this.name=name;
    }

    public void levelUp(){
        super.levelUp();
        manaPool += getLevel()*25;
        currentMana = Math.min(currentMana+manaPool/4, manaPool);
        spellPower += getLevel()*10;
    }

    @Override
    public void castAbility(ArrayList<Enemy> enemies) {

            currentMana =currentMana- manaCost;
            int hits = 0;
            List<Enemy> lis=enemies.stream().filter(s -> this.getPos().distance(s.getPos())<abilityRange).collect(Collectors.toList());
            Iterator<Enemy> iter= lis.iterator();
            while(iter.hasNext()){
                Enemy e=iter.next();
                int a=getRandom(enemies.size()-1);
                int defenceRoll = getRandom(e.getDefencePoints());
                cmdPrinter.sendMessage(this.name+" use Special ability activated, "+ this.name +" hit "+e.getName() +" with force of " + (spellPower) + " points");
                cmdPrinter.sendMessage(e.getName() + " rolled up  "+ defenceRoll+ " defence points");
                cmdPrinter.sendMessage(e.getName()+" lost "+ (spellPower-defenceRoll) + " points");
                if(spellPower-defenceRoll>0)
                e.getHit(spellPower-defenceRoll);
                hits++;
                if(e.getHealthAmount()==0){ //Player killed enemy
                    cmdPrinter.sendMessage(e.getName() +" has been killed. "+ this.getName()+ " earned "+e.getExperience()+" experience points");
                    this.updateExperience(e.getExperience());
                }
            }
    }

    @Override
    public void castAbility(Player player) {

    }

    @Override
    public String describe() {
        return this.name+"      Health: "+getHealthAmount()+"      Attack: "+getAttackPoints()+ "      Defence: "+getDefencePoints()+"        Level: "+getLevel()+"     Experience: "+getExperience()+"     Current Mana "+currentMana+"      Manacost: "+manaCost ;

    }

    @Override
    public boolean hasSpecialAbility() {
        return currentMana >= manaCost;
    }

    public boolean isClose(Enemy e){
        return getPos().distance((e.getPos())) <= abilityRange;
    }

    @Override
    protected void onTick() {
        currentMana = Math.min(manaPool, currentMana + getLevel());
    }

    public void combat(Tile attacker) { }

    private int getRandom (int size){
        Random rand = new Random();
        return rand.nextInt( size);
    }
}
