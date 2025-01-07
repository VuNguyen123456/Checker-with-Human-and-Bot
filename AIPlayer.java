/*
* Names: Vu Nguyen
* netID: vnguy7
* G#: 01390056
* Lecture section: 004
* Lab section: 213
*/
import java.util.ArrayList;
public class AIPlayer extends Player{
    private String name;
    private Player opponent;

    /**
     * Getter
     * @return
     */
    public Player getOpponent(){
        return opponent;
    }
    /**
     * Setter
     * @param opponent
     */
    public void setOpponent(Player opponent){
        this.opponent = opponent;
    }
    /**
     * Get your number of checker
     * @param game
     * @return
     */
    public double getNumMyChecker(MiniCheckers game){
        char color = 'r';
        double value = 0.0;
        if(this == game.getBlack()){
            color = 'b';
        }
        else if(this == game.getRed()){
            color = 'r';
        }
        for(int i = 0; i < game.getBoard().length; i++){
            for(int j = 0; j < game.getBoard()[i].length; j++){
                if(game.getBoard()[i][j] == color){
                    value += 1;
                }
            }
        }
        return value;
    }
    /**
     * Get your number of king
     * @param game
     * @return
     */
    public double getNumMyKing(MiniCheckers game){
        char color = 'R';
        double value = 0.0;
        if(this == game.getBlack()){
            color = 'B';
        }
        else if(this == game.getRed()){
            color = 'R';
        }
        for(int i = 0; i < game.getBoard().length; i++){
            for(int j = 0; j < game.getBoard()[i].length; j++){
                if(game.getBoard()[i][j] == color){
                    value += 1;
                }
            }
        }
        return value;
    }
    /**
     * Get opponent number of checkers
     * @param game
     * @return
     */
    public double getNumOppChecker(MiniCheckers game){
        char color = 'r';
        double value = 0.0;
        if(this == game.getBlack()){
            color = 'r';
        }
        else if(this == game.getRed()){
            color = 'b';
        }
        for(int i = 0; i < game.getBoard().length; i++){
            for(int j = 0; j < game.getBoard()[i].length; j++){
                if(game.getBoard()[i][j] == color){
                    value += 1;
                }
            }
        }
        return value;
    }
    /**
     * Get opponent number of King
     * @param game
     * @return
     */
    public double getNumOppKing(MiniCheckers game){
        char color = 'R';
        double value = 0.0;
        if(this == game.getBlack()){
            color = 'R';
        }
        else if(this == game.getRed()){
            color = 'B';
        }
        for(int i = 0; i < game.getBoard().length; i++){
            for(int j = 0; j < game.getBoard()[i].length; j++){
                if(game.getBoard()[i][j] == color){
                    value += 1;
                }
            }
        }
        return value;
    }


    @Override
    public String toString(){
        return name + " (AI)";
    }
    /**
     * Contructor
     * @param name
     * @param opponent
     */
    public AIPlayer(String name,Player opponent){
        this.name = name;
        this.opponent = opponent;
    }
    /**
     * Get minValue of a board
     * @param game
     * @param depth
     * @return
     */
    public double minValue(MiniCheckers game, int depth){
        // System.out.println(game);
        // System.out.println(this.getNumMyChecker(game));
        // System.out.println(this.getNumMyKing(game));
        // System.out.println(this.getNumOppChecker(game));
        // System.out.println(this.getNumOppKing(game));
        double lowestValue = 0;
        //Base case
        if(game.checkWin(this)){
            return 10.0;
        }
        else if(game.checkLose(this)){
            return -10.0;
        }
        else if(depth < 1){
            double retVal = (this.getNumMyChecker(game) + 3*this.getNumMyKing(game)) - (this.getNumOppChecker(game) + 3*this.getNumOppKing(game));
            return retVal;
        }
        else{
        //Recursive case
            ArrayList<MiniCheckers> temp = game.possibleMoves(opponent);
            for(MiniCheckers move : temp){
                double val = maxValue(move, depth-1); 
                if(lowestValue >= val){
                    lowestValue = val;
                }
            }
        }
        return lowestValue;
    }

    /**
     * Get maxValue of a board
     * @param game
     * @param depth
     * @return
     */
    public double maxValue(MiniCheckers game, int depth){
        // System.out.println(game);
        // System.out.println(this.getNumMyChecker(game));
        // System.out.println(this.getNumMyKing(game));
        // System.out.println(this.getNumOppChecker(game));
        // System.out.println(this.getNumOppKing(game));
        double highestValue = 0;
        //Base case
            if (game.checkWin(this)) {
                return 10.0;
            } else if (game.checkLose(this)) {
                return -10.0;
            } else if (depth < 1) {
                double retVal = (this.getNumMyChecker(game) + 3 * this.getNumMyKing(game)) - (this.getNumOppChecker(game) + 3 * this.getNumOppKing(game));
                return retVal;
            } else {
                // Recursive case
                ArrayList<MiniCheckers> temp = game.possibleMoves(this);
                for (MiniCheckers move : temp) {
                    double val = minValue(move, depth - 1);
                    if (highestValue <= val) {
                        highestValue = val;
                    }
                }
            }
        
        return highestValue;
    }

    @Override
    public MiniCheckers chooseMove(MiniCheckers board){
        ArrayList<MiniCheckers> temp = board.possibleMoves(this);
        MiniCheckers move = temp.get(0);
        double higestVal = 0;
        int depth = 10;
        for(int i = 0; i < temp.size(); i++){
            double val = minValue(temp.get(i), depth-1);
            if(higestVal <= val ){
                higestVal = val;
                move = temp.get(i);
            }
        }
        // System.out.println(board);
        // System.out.println(this.getNumMyChecker(board));
        // System.out.println(this.getNumMyKing(board));
        // System.out.println(this.getNumOppChecker(board));
        // System.out.println(this.getNumOppKing(board));
        // System.out.println(this.boardValue(board));
        return move;
    }

    @Override
    public double boardValue(MiniCheckers argument){
        //Check Piazza for the returning value of the board if 1 step away from winning
        double result = maxValue(argument, 10);
        if( result == 10){
            return 1;
        }
        else if(result == -10){
            return -1;
        }
        else{
            return 0;
        }
    }
}
//:(((