
public abstract class Unit extends Tile {

    public String name;
    private int healthPool;
    private int healthAmount;
    private int attackPoints;
    private int defencePoints;

    public Unit(char character, Position pos, int healthPool, int healthAmount, int attackPoints, int defencePoints) {
        super(character, pos);
        this.healthPool = healthPool;
        this.healthAmount = healthAmount;
        this.attackPoints = attackPoints;
        this.defencePoints = defencePoints;
    }

    String getName() {return name;}

    int getHealthPool() {return healthPool;}
    int getHealthAmount() {return healthAmount;}
    int getAttackPoints() {return attackPoints;}
    int getDefencePoints() {return defencePoints;}

    void setHealthPool(int healthPool) {this.healthPool = healthPool;}
    void setHealthAmount(int healthAmount) {
        this.healthAmount = Math.min(healthAmount,healthPool);
        if(healthAmount<0)
            this.healthAmount = 0;
    }
    void setAttackPoints(int attackPoints) {this.attackPoints = attackPoints;}
    void setDefencePoints(int defencePoints) {this.defencePoints = defencePoints;}

    abstract void getHit (int damage);
    
}
