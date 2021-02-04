
public class Board {
    private Tile[][] tiles;

    public Board (char [][] board, int p){
        tiles = new Tile[board.length][board[0].length];

        for(int i=0; i<board.length; i++){
            for(int j=0; j<board[i].length; j++){
                tiles[i][j] = createTile(board[i][j], new Position(i,j), p);
            }
        }
        //Main.getUI().sendMessage(toString());
    }

    public Tile createTile(char c, Position pos, int p){
        if(c=='.') return new Empty(pos);
        else if (c=='#') return new Wall(pos);
        else if (c=='@' && p==1) return new Warrior(pos, 300, 300, 30, 4, 3,"Jon Snow");
        else if (c=='@' && p==2) return new Warrior(pos, 400, 400, 20, 6, 5, "The Hound");
        else if (c=='@' && p==3) return new Mage(pos, 100, 100, 5, 1, 300, 30, 15, 5, 6,"Melisandre");
        else if (c=='@' && p==4) return new Mage(pos, 250, 250, 25, 4, 150, 20, 20, 3, 4,"Thoros Of Myr");
        else if (c=='@' && p==5) return new Rogue(pos, 150, 150, 40, 2, 20, "Arya Stark");
        else if (c=='@' && p==6) return new Rogue(pos, 250, 250, 35, 3, 50,"Bronn");
        else if (c=='@' && p==7) return new Hunter(pos, 220, 220, 30, 2, 6,"Ygritte");

        else if (c=='s') return new Monster('s', pos, 80, 80, 8, 3, 25, 3,"Lannister Solider");
        else if (c=='k') return new Monster('k', pos, 200, 200, 14, 8, 50, 4,"Lannister Knight");
        else if (c=='q') return new Monster('q', pos, 400, 400, 20, 15, 100, 5,"Queen’s Guard");
        else if (c=='z') return new Monster('z', pos, 600, 600, 30, 15, 100, 3,"Wright");
        else if (c=='b') return new Monster('b', pos, 1000, 1000, 75, 30, 250, 4,"Bear-Wrigh");
        else if (c=='g') return new Monster('g', pos, 1500, 1500, 100, 40, 500, 5,"Giant-Wright");
        else if (c=='w') return new Monster('w', pos, 2000, 2000, 150, 50, 1000, 6,"White Walker");
        else if (c=='M') return new Boss('M', pos, 1000, 1000, 60, 25, 500, 6,"The Mountain",5);
        else if (c=='C') return new Boss('C', pos, 100, 100, 10, 10, 1000, 1,"Queen Cersei",6);
        else if (c=='K') return new Boss('K', pos, 5000, 5000, 300, 150, 500, 8,"Night’s King",3);
        else if (c=='B') return new Trap('B', pos, 1 ,1, 1, 1, 250, 1, 5,"Bonus Trap");
        else if (c=='Q') return new Trap('Q', pos, 250 ,250, 50, 10, 100, 3, 7,"Queen’s Trap");
        else if (c=='D') return new Trap('D', pos, 500 ,500, 100, 20, 250, 1, 10,"Death Trap");
        return null;
    }

    public String toString() {
        String output="";
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[i].length; j++) {
//                output += String.valueOf(tiles[i][j].getCharacter());
                output += tiles[i][j].getCharacter()+"";
            }
            output+="\n";
        }
        return output;
    }

    public Tile[][] getBoard(){
        return tiles;
    }
}
