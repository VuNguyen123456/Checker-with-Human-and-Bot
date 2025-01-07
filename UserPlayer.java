import java.util.Scanner;
import java.util.ArrayList;
public class UserPlayer extends Player{
//Field
    private String name;
    private Scanner input = new Scanner(System.in);

    /**
     * Contructor
     * @param input
     * @param name
     */
    public UserPlayer(Scanner input,String name){
        this.name = name;
        this.input = input;
    }
    @Override
    public String toString(){
        return name;
    }
    @Override
    public MiniCheckers chooseMove(MiniCheckers board) {
        ArrayList<MiniCheckers> temp = board.possibleMoves(this);
        int playerChoose;
        System.out.println(board);
        System.out.println("This are your options: ");
        for(int i = 0; i < temp.size(); i++){
            System.out.println("Option: " + (i+1));
            System.out.println(temp.get(i).toString());
        }
        System.out.println("Chose your option index:");
        playerChoose = input.nextInt();
        return temp.get(playerChoose-1);
    }
    
}
