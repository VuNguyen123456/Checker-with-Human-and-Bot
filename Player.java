public abstract class Player {
    /**
     * Abstract method for userPLayer and Ai
     * @param board
     * @return
     */
    public abstract MiniCheckers chooseMove(MiniCheckers board);

    /*
     * The default implementation for this method should be to 
     * return 1.0 if this player has won this board, 
     * -1.0 if this player has lost this board, 
     * 0.0 otherwise
     */
    /**
     * Return the board value
     * @param argument
     * @return
     */
    public double boardValue(MiniCheckers argument){
        if(argument.checkWin(this)){
            return 1.0;
        }
        else if(argument.checkLose(this)){
            return -1.0;
        }
        else {
            return 0.0;
        } 
    }
}
