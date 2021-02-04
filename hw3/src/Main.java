
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

    static Controller con;
    static cmdPrinter ui;
    static Scanner reader= new Scanner(System.in);



    public static void main(String[] args) {
        cmdPrinter.sendMessage("select player: \n 1.Jon Snow \n 2.The Hound \n 3.Melisandre \n 4.Thoros Of Myr \n 5.Arya Stark  \n 6.Bronn \n 7.The Hunter");

        int a = reader.nextInt();
        ArrayList<Board> levelsBoards=new ArrayList<>();
        try{

            List<String> levels= Files.list(Paths.get(args[0])).sorted().map(Path::toString).collect(Collectors.toList());
            for(String level:levels)
            {
                levelsBoards.add(new Board(toBoard(level),a));
            }
        }
        catch (IOException e) { e.printStackTrace(); }

        con = new Controller(levelsBoards.get(0));
        cmdPrinter.sendMessage(con.player.describe()+"\n");
        cmdPrinter.sendMessage(con.getBoard().toString());
        int counter=0;
        while (counter<levelsBoards.size()){
            if(con.won && counter<levelsBoards.size()-1) {
                counter++;
                cmdPrinter.sendMessage("You won the stage! Staring stage "+ (counter+1) +"\n");
                con = new Controller(levelsBoards.get(counter));
                Clock.GameTick=0;
                cmdPrinter.sendMessage(con.player.describe()+"\n");
                cmdPrinter.sendMessage(con.getBoard().toString());
            }
            String move = reader.next();
            if(move.length()!=1) {cmdPrinter.sendMessage("bad input"); continue;}
            if(move.charAt(0)=='e'&& !con.player.hasSpecialAbility()) { cmdPrinter.sendMessage("canno use special ability yet");continue;}
            con.Turn(move);
            cmdPrinter.sendMessage(con.getBoard().toString());
            cmdPrinter.sendMessage(con.player.describe()+"\n");
            if(con.isDead())break;
        }
        if(counter>=levelsBoards.size()-1&&con.won )
            cmdPrinter.sendMessage("you won!");
        else
            if(con.player.getCharacter()=='X')
                cmdPrinter.sendMessage("you lose!");
    }

    private static char[][] toBoard(String level) {
        List<String> lines=Collections.EMPTY_LIST;
        try
        {
            lines=Files.readAllLines(Paths.get(level));
            char [][] board=new char[lines.size()][longestRowLength(lines)];
            int rowPlace = 0;
            for (String row : lines) {
                for (int i = 0; i < row.length(); i++) {
                    char c = row.charAt(i);
                    board[rowPlace][i] = c;
                }
                rowPlace++;
            }
            return board;
        }
        catch (IOException e)
        {
            e.printStackTrace();
            System.exit(-1);
        }
        return null;
    }

    public static int longestRowLength(List<String> lines)
    {
        int a=0;
        for(String row:lines)
        {
            if(row.length()>a)
                a=row.length();
        }
        return a;
    }

    public static cmdPrinter getUI(){ return ui; }
}
