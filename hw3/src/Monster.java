
import java.util.Random;

public class Monster extends Enemy {
    int visionRange;

    public Monster(char character, Position pos, int healthPool, int healthAmount, int attackPoints, int defencePoints, int experience, int visionRange,String name) {
        super(character, pos, healthPool, healthAmount, attackPoints, defencePoints, experience);
        this.visionRange = visionRange;
        this.name=name;
    }

    public int makeTurn(Player player){
        //1->up 2->down 3->left 4->right
        if(getPos().distance(player.getPos()) <= visionRange){
            int dx = getPos().getX()-player.getPos().getX();
            int dy = getPos().getY()-player.getPos().getY();
            if(Math.abs(dx) > Math.abs(dy)){
                if (dx>0) {return 1;}
                else return 2;
            }
            else {
                if (dy>0) return 3;
                else return 4;
            }
        }
        else {
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

    protected void onTick() {
        //nothing needed here
    }
}
