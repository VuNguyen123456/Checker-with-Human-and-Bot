/*
* Names: Vu Nguyen
* netID: vnguy7
* G#: 01390056
* Lecture section: 004
* Lab section: 213
*/
import java.util.ArrayList;
public class MiniCheckers {
    private char[][] board;
    private Player red;
    private Player black;

    /**
     * Contructor
     * @param red
     * @param black
     */
    public MiniCheckers(Player red, Player black) {
        /* Your code here! */
        this.red = red;
        this.black = black;
        board = new char[][]{
            {'r','.','r','.','r'},
            {'.','_','.','_','.'},

            {'_','.','_','.','_'},
            {'.','_','.','_','.'},
            {'b','.','b','.','b'},
        };
    }
    /**
     * Getter
     * @return
     */
    public char[][] getBoard() {
        return board;
    }
    /**
     * Setter
     * @return
     */
    public void setBoard(char[][] board) {
        this.board = board;
    }
    /**
     * Getter
     * @return
     */
    public Player getRed() {
        return this.red;
    }
    
    /**
     * Setter
     * @param red
     */
    public void setRed(Player red) {
        this.red = red;
    }
    /**
     * Getter
     * @return
     */
    public Player getBlack() {
        return black;
    }
    
    /**
     * Setter
     * @param black
     */
    public void setBlack(Player black) {
        this.black = black;
    }
    /**
     * Checking the count of a color
     * @param color
     * @return
     */
    public int countChecker(char color) {
        int count = 0;
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[i].length; j++){
                if(board[i][j] == color){
                    count += 1;
                }
            }
        }
        return count;
    }

    /**
     * Check if the player have win yet?
     * @param player
     * @return
     */
    public boolean checkWin(Player player) {
        char opponentChecker = 'r';
        char opponentKing = 'R';
        if(player == this.red){
            opponentChecker = 'b';
            opponentKing = 'B';
        }
        else if(player == this.black){
            opponentChecker = 'r';
            opponentKing = 'R';
        }
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[i].length; j++){
                if(board[i][j] == opponentChecker || board[i][j] == opponentKing){
                    return false;
                }
            }
        }
        return true;
    }
    /**
     * Check if the player has lost yet?
     * @param player
     * @return
     */
    public boolean checkLose(Player player) {
        Player opponent = red;
        if(player == red){
            opponent = black;
        }
        else if(player == black){
            opponent = red;
        }
        
        if(checkWin(opponent)){
            return true;
        }
        else{
            return false;
        }
    }
    
    public String toString() {
        String res = "";
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                res += board[i][j];
            }
            res += "\n";
        }
        return res;
    }
    /**
     * Make Clone of the board
     * @return
     */
    private MiniCheckers makeClone() {
        /* provided code. You may call this method, but you should NOT modify it */
        MiniCheckers newMiniCheckers = new MiniCheckers(this.red, this.black);
        char[][] newBoard = newMiniCheckers.getBoard();
        char[][] curBoard = this.getBoard();
        for (int i = 0; i < curBoard.length; i++) {
            for (int j = 0; j < curBoard[i].length; j++) {
                newBoard[i][j] = curBoard[i][j];
            }
        }
        newMiniCheckers.setBoard(newBoard);
        return newMiniCheckers;
    }
    /**
     * Move the Piece
     * @param startRow
     * @param startCol
     * @param endRow
     * @param endCol
     * @return
     */
    private MiniCheckers movePiece(int startRow, int startCol, int endRow, int endCol){
        /* provided code. You may call this method, but you should NOT modify it */
        MiniCheckers move = this.makeClone();
        char[][] newBoard = move.getBoard();
        char tmpPiece = newBoard[startRow][startCol];
        newBoard[startRow][startCol] = '_';
        newBoard[endRow][endCol] = tmpPiece;
        if((tmpPiece=='r' && endRow==newBoard.length-1)||(tmpPiece=='b'&&endRow==0)){
            newBoard[endRow][endCol] = Character.toUpperCase(newBoard[endRow][endCol]);
        }
        return move;
    }

    /**
     * Remove the Piece
     * @param board
     * @param i
     * @param j
     */
    private static void  removePiece(char[][] board, int i, int j){
        /* provided code. You may call this method, but you should NOT modify it */
        board[i][j] = '_';
    }

    /**
     * Check if the index is valid or not
     * @param board
     * @param i
     * @param j
     * @return
     */
    private static boolean validIndex(char[][] board, int i, int j){
        /* provided code. You may call this method, but you should NOT modify it */
        if(i<0 || j<0 || i>=board.length || j>=board[0].length) return false;
        return true;
    }

    /**
     * Check if the red checkers can move to a position
     * @param board
     * @param startRow
     * @param startCol
     * @param endRow
     * @param endCol
     * @return
     */
    private static boolean redCanMoveHere(char[][] board, int startRow, int startCol, int endRow, int endCol){
        //TODO: NOT provided Code
        if(!validIndex(board, startRow, startCol) || !validIndex(board,endRow,endCol)) return false;
        if(Math.abs(startRow-endRow)!=1 || Math.abs(startCol-endCol)!=1) return false;
        if(board[startRow][startCol] == 'r'){
            if(endRow != startRow+1) return false;
            if(board[endRow][endCol] != '_') return false;
            if((endCol == startCol + 1 || endCol == startCol - 1)){
                return true;
            } else {
                return false;
            }
        } 
        else if(board[startRow][startCol] == 'R'){
            if(board[endRow][endCol] != '_') return false;
            if(endRow > startRow && endCol > startCol){
                //down-right
                return true;
            }
            else if (endRow < startRow && endCol > startCol){
                //up-right
                return true;
            }
            else if (endRow > startRow && endCol < startCol){
                //down-left
                return true;
            }
            else if (endRow < startRow && endCol < startCol){
                //up-left
                return true;
            }
            else {
                return false;
            }
        }
        else {
            return false;
        }
    }
    /**
     * Check if a red check can jump to a location
     * @param board
     * @param startRow
     * @param startCol
     * @param endRow
     * @param endCol
     * @return
     */
    private static boolean redCanJumpHere(char[][] board, int startRow, int startCol, int endRow, int endCol){
        /* provided code. You may call this method, but you should NOT modify it */
        if(!validIndex(board, startRow, startCol) || !validIndex(board,endRow,endCol)) return false;
        if(Math.abs(startRow-endRow)!=2 || Math.abs(startCol-endCol)!=2) return false;
        if(board[startRow][startCol] == 'r'){
            if(endRow != startRow+2) return false;
            if(board[endRow][endCol] != '_') return false;
            if( (endCol == startCol+2 && (board[startRow+1][startCol+1] == 'b' || board[startRow+1][startCol+1] == 'B')) ||
                (endCol == startCol-2 && (board[startRow+1][startCol-1] == 'b' || board[startRow+1][startCol-1] == 'B'))){
                return true;
            } else {
                return false;
            }
        } else if(board[startRow][startCol] == 'R'){
            if(board[endRow][endCol] != '_') return false;
            if(endRow > startRow && endCol > startCol){
                //down-right
                if(board[startRow+1][startCol+1]=='b' || board[startRow+1][startCol+1]=='B') return true;
                else return false;
            } else if(endRow < startRow && endCol > startCol){
                //up-right
                if(board[startRow-1][startCol+1]=='b' || board[startRow-1][startCol+1]=='B') return true;
                else return false;
            } else if(endRow > startRow && endCol < startCol){
                //down-left
                if(board[startRow+1][startCol-1]=='b' || board[startRow+1][startCol-1]=='B') return true;
                else return false;
            } else if(endRow < startRow && endCol < startCol){
                //up-left
                if(board[startRow-1][startCol-1]=='b' || board[startRow-1][startCol-1]=='B') return true;
                else return false;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
    /**
     * Check if a black checker can move to a position
     * @param board
     * @param startRow
     * @param startCol
     * @param endRow
     * @param endCol
     * @return
     */
    private static boolean blackCanMoveHere(char[][] board, int startRow, int startCol, int endRow, int endCol){
        //TODO: NOT provided Code
        if(!validIndex(board, startRow, startCol) || !validIndex(board,endRow,endCol)) return false;
        if(Math.abs(startRow-endRow)!=1 || Math.abs(startCol-endCol)!=1) return false;
        if(board[startRow][startCol] == 'b'){
            if(endRow != startRow-1) return false;
            if(board[endRow][endCol] != '_') return false;
            if((endCol == startCol + 1 || endCol == startCol - 1)){
                return true;
            } else {
                return false;
            }
        } 
        else if(board[startRow][startCol] == 'B'){
            if(board[endRow][endCol] != '_') return false;
            if(endRow > startRow && endCol > startCol){
                //down-right
                return true;
            }
            else if (endRow < startRow && endCol > startCol){
                //up-right
                return true;
            }
            else if (endRow > startRow && endCol < startCol){
                //down-left
                return true;
            }
            else if (endRow < startRow && endCol < startCol){
                //up-left
                return true;
            }
            else {
                return false;
            }
        }
        else {
            return false;
        }
    }
    /**
     * Check if a black checker can jump into a position
     * @param board
     * @param startRow
     * @param startCol
     * @param endRow
     * @param endCol
     * @return
     */
    private static boolean blackCanJumpHere(char[][] board, int startRow, int startCol, int endRow, int endCol){
        /* provided code. You may call this method, but you should NOT modify it */
        if(!validIndex(board, startRow, startCol) || !validIndex(board,endRow,endCol)) return false;
        if(Math.abs(startRow-endRow)!=2 || Math.abs(startCol-endCol)!=2) return false;
        if(board[startRow][startCol] == 'b'){
            if(endRow != startRow-2) return false;
            if(board[endRow][endCol] != '_') return false;
            if( (endCol == startCol+2 && (board[startRow-1][startCol+1] == 'r' || board[startRow-1][startCol+1] == 'R')) ||
                (endCol == startCol-2 && (board[startRow-1][startCol-1] == 'r' || board[startRow-1][startCol-1] == 'R'))){
                return true;
            } else {
                return false;
            }
        } else if(board[startRow][startCol] == 'B'){
            if(board[endRow][endCol] != '_') return false;
            if(endRow > startRow && endCol > startCol){
                //down-right
                if(board[startRow+1][startCol+1]=='r' || board[startRow+1][startCol+1]=='R') return true;
                else return false;
            } else if(endRow < startRow && endCol > startCol){
                //up-right
                if(board[startRow-1][startCol+1]=='r' || board[startRow-1][startCol+1]=='R') return true;
                else return false;
            } else if(endRow > startRow && endCol < startCol){
                //down-left
                if(board[startRow+1][startCol-1]=='r' || board[startRow+1][startCol-1]=='R') return true;
                else return false;
            } else if(endRow < startRow && endCol < startCol){
                //up-left
                if(board[startRow-1][startCol-1]=='r' || board[startRow-1][startCol-1]=='R') return true;
                else return false;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
    /**
     * Return all the possible move for a player
     * @param player
     * @return
     */
    public ArrayList<MiniCheckers> possibleMoves(Player player) {
        /* provided code. You may call this method, but you should NOT modify it */
        char[][] curBoard = this.getBoard();
        ArrayList<MiniCheckers> rv = new ArrayList<MiniCheckers>();
        if(player == this.red){
            boolean couldJump = false;
            for(int i=0;i<curBoard.length;i++){
                for(int j=0;j<curBoard[i].length;j++){
                    if(board[i][j]=='r' || board[i][j] =='R'){
                        if(redCanJumpHere(board,i,j,i-2,j-2)){
                            MiniCheckers newBoard = movePiece(i,j,i-2,j-2);
                            removePiece(newBoard.board,i-1,j-1);
                            rv.add(newBoard);
                            couldJump = true;
                        }
                        if(redCanJumpHere(board,i,j,i-2,j+2)){
                            MiniCheckers newBoard = movePiece(i,j,i-2,j+2);
                            removePiece(newBoard.board,i-1,j+1);
                            rv.add(newBoard);
                            couldJump = true;
                        }
                        if(redCanJumpHere(board,i,j,i+2,j-2)){
                            MiniCheckers newBoard = movePiece(i,j,i+2,j-2);
                            removePiece(newBoard.board,i+1,j-1);
                            rv.add(newBoard);
                            couldJump = true;
                        }
                        if(redCanJumpHere(board,i,j,i+2,j+2)){
                            MiniCheckers newBoard = movePiece(i,j,i+2,j+2);
                            removePiece(newBoard.board,i+1,j+1);
                            rv.add(newBoard);
                            couldJump = true;
                        }
                    }
                }
            }
            if(!couldJump){
                for(int i=0;i<curBoard.length;i++){
                    for(int j=0;j<curBoard[i].length;j++){
                        if(board[i][j]=='r' || board[i][j]=='R'){                            
                            if(redCanMoveHere(board,i,j,i-1,j-1)){
                                rv.add(movePiece(i,j,i-1,j-1));
                            }
                            if(redCanMoveHere(board,i,j,i-1,j+1)){
                                rv.add(movePiece(i,j,i-1,j+1));
                            }
                            if(redCanMoveHere(board,i,j,i+1,j-1)){
                                rv.add(movePiece(i,j,i+1,j-1));
                            }
                            if(redCanMoveHere(board,i,j,i+1,j+1)){
                                rv.add(movePiece(i,j,i+1,j+1));
                            }
                        }
                    }
                }
            }
        } else if(player==this.black){
            boolean couldJump = false;
            //check for jumps first
            for(int i=0;i<curBoard.length;i++){
                for(int j=0;j<curBoard[i].length;j++){
                    if(board[i][j]=='b' || board[i][j] =='B'){
                        if(blackCanJumpHere(board,i,j,i-2,j-2)){
                            MiniCheckers newBoard = movePiece(i,j,i-2,j-2);
                            removePiece(newBoard.board,i-1,j-1);
                            rv.add(newBoard);
                            couldJump = true;
                        }
                        if(blackCanJumpHere(board,i,j,i-2,j+2)){
                            MiniCheckers newBoard = movePiece(i,j,i-2,j+2);
                            removePiece(newBoard.board,i-1,j+1);
                            rv.add(newBoard);
                            couldJump = true;
                        }
                        if(blackCanJumpHere(board,i,j,i+2,j-2)){
                            MiniCheckers newBoard = movePiece(i,j,i+2,j-2);
                            removePiece(newBoard.board,i+1,j-1);
                            rv.add(newBoard);
                            couldJump = true;
                        }
                        if(blackCanJumpHere(board,i,j,i+2,j+2)){
                            MiniCheckers newBoard = movePiece(i,j,i+2,j+2);
                            removePiece(newBoard.board,i+1,j+1);
                            rv.add(newBoard);
                            couldJump = true;
                        }
                    }
                }
            }
            if(!couldJump){
                for(int i=0;i<curBoard.length;i++){
                    for(int j=0;j<curBoard[i].length;j++){
                        if(board[i][j]=='b' || board[i][j]=='B'){
                            if(blackCanMoveHere(board,i,j,i-1,j-1)){
                                rv.add(movePiece(i,j,i-1,j-1));
                            }
                            if(blackCanMoveHere(board,i,j,i-1,j+1)){
                                rv.add(movePiece(i,j,i-1,j+1));
                            }
                            if(blackCanMoveHere(board,i,j,i+1,j-1)){
                                rv.add(movePiece(i,j,i+1,j-1));
                            }
                            if(blackCanMoveHere(board,i,j,i+1,j+1)){
                                rv.add(movePiece(i,j,i+1,j+1));
                            }
                        }
                    }
                }
            }
        } else {
            System.out.println("Unrecognized player?!");
        }
        return rv;
    }
}