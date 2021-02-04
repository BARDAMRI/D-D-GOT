import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Controller {
    Board board;
    Player player;
    ArrayList<Enemy> enemies;
    boolean won;

    public Controller(Board board){
        this.board = board;
        won=false;
        enemies = new ArrayList<>();
        Tile[][] tiles = board.getBoard();
        for(int i=0; i<tiles.length; i++){
            for(int j=0; j<tiles[0].length; j++){
                if(tiles[i][j].getCharacter()!='.' & tiles[i][j].getCharacter()!='#' & tiles[i][j].getCharacter()!='@')
                    enemies.add((Enemy) tiles[i][j]);
                if(tiles[i][j].getCharacter()=='@')
                    player = (Player)tiles[i][j];
            }
        }
    }

    public void checkDeads(){
        List<Enemy> toDelete = new LinkedList<>();
        for (Enemy e : enemies){
            if(e.getHealthAmount()<=0){
                board.getBoard()[e.getPos().getX()][e.getPos().getY()] = new Empty(new Position(e.getPos().getX(), e.getPos().getY()));
                toDelete.add(e);
            }
        }
        for (Enemy e : toDelete) enemies.remove(e);
        if (player.getHealthAmount()==0){
            player.setCharacter('X');
        }
    }


    public void Turn(String move){
        PlayerTurn(move);
        checkDeads();
        if(!enemies.isEmpty()) {
            EnemiesTurn();
        }
        checkDeads();
        if(enemies.isEmpty()) {
            won = true;
        }
        Clock.Tick();
    }

    public void PlayerTurn(String move){
        switch (move){
            case "w":
               board.getBoard()[player.getPos().getX() - 1][player.getPos().getY()].combat(player);
               updateboard( board.getBoard()[player.getPos().getX() ][player.getPos().getY()],player);
                break;
            case "s":
                 board.getBoard()[player.getPos().getX() + 1][player.getPos().getY()].combat( player);
                updateboard(board.getBoard()[player.getPos().getX() ][player.getPos().getY()],player);
                 break;
            case "a":
                 board.getBoard()[player.getPos().getX()][player.getPos().getY() - 1].combat( player);
                updateboard(board.getBoard()[player.getPos().getX()][player.getPos().getY()],player);
                break;
            case "d":
                 board.getBoard()[player.getPos().getX()][player.getPos().getY() + 1].combat( player);
                updateboard(board.getBoard()[player.getPos().getX()][player.getPos().getY()],player);
                break;
            case "q":
                break;
            case "e":
                player.castAbility(enemies);
                break;
        }
    }

    private void updateboard(Tile tile, Unit unit) {

        if(!(tile instanceof Wall)) {
            board.getBoard()[tile.getPos().getX()][tile.getPos().getY()] = tile;
            board.getBoard()[unit.getPos().getX()][unit.getPos().getY()] = unit;
        }

    }

    public void EnemiesTurn(){
        for (Enemy e : enemies) {
            if (e.getHealthAmount() != 0) {
                int a = e.makeTurn(player);
                switch (a) {
                    case 1:
                        board.getBoard()[e.getPos().getX() - 1][e.getPos().getY()].combat(e);
                        updateboard(board.getBoard()[e.getPos().getX()][e.getPos().getY()], e);
                        break;
                    case 2:
                        board.getBoard()[e.getPos().getX() + 1][e.getPos().getY()].combat(e);
                        updateboard(board.getBoard()[e.getPos().getX()][e.getPos().getY()], e);
                        break;
                    case 3:
                        board.getBoard()[e.getPos().getX()][e.getPos().getY() - 1].combat(e);
                        updateboard(board.getBoard()[e.getPos().getX()][e.getPos().getY()], e);
                        break;
                    case 4:
                        board.getBoard()[e.getPos().getX()][e.getPos().getY() + 1].combat(e);
                        updateboard(board.getBoard()[e.getPos().getX()][e.getPos().getY()], e);
                        break;
                    case 0:
                        break;
                    case -1:
                        break;
                }
            }
        }
    }

    public boolean isDead() {
        return player.getCharacter()=='X';
    }

    public Board getBoard(){return board;}
}
