import java.awt.*;

/**
 * Created by penguinrage on 2/03/17.
 */
public class LetterPair {

    // the first letter
    public char left;

    // the co-ordinates of the letter in the Grid
    public Point pLeft, pRight;

    // the coded two letters
    public char[] swap = new char[2];;

    // Encode or Decode
    boolean code_type;

    public LetterPair(char[][] grid, char left, boolean code_type) {
        // store left letter to be switched
        this.left = left;
        // store Encode or Decode
        this.code_type = code_type;
        // p
        pLeft = findPos(grid, left);

    }

    public Point findPos(char[][] grid, char c) {
        // scan the grid for letter
        for(int i = 0; i < 5; i++)
            for(int j = 0; j<5; j++) {
                if (grid[i][j] == c)
                    return new Point(i,j);
            }
        throw new IllegalStateException("Error finding ALPHABETICAL LETTER IN: method findPos");
    }


    public void setRight(char[][] grid ,char c) {
        // find position in the grid
        pRight = findPos(grid, c);

        if(pLeft.x == pRight.x) // if in same row
            sameRow();
        else if (pLeft.y == pRight.y) // if in same column
            sameColumn();
        else
            diffRowCol();

        // Store the swapping values
        swap[0] = grid[pLeft.x][pLeft.y];
        swap[1] = grid[pRight.x][pRight.y];

    }

    private void sameColumn() {

    }

    private void sameRow() {

    }


}
