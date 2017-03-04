import jdk.internal.util.xml.impl.Pair;

import java.awt.*;

/**
 * Created by penguinrage on 2/03/17.
 */
public class LetterPair {
    // grid length
    int n = 5;
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
        for(int i = 0; i < n; i++)
            for(int j = 0; j<n; j++) {
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

    // Switch columns
    private void diffRowCol() {
        int temp = pRight.y;
        pRight.y =  pLeft.y;
        pLeft.y = temp;
    }

    private void sameColumn() {
        if (code_type) {
            // to the right
            pLeft.x++;
            pRight.x++;
            // contain within grid borders
            pRight.x %= n;
            pLeft.x %= n;
        } else {
            // to the left
            pLeft.x--;
            pRight.x--;
            if (pLeft.x < 0) pLeft.x = 4;
            if (pRight.x < 0) pRight.x = 4;
        }
    }

    /*
     * Encoding to the Right
     * Decoding to the Left
     */
    private void sameRow() {
        if (code_type) {
            // to the right
            pLeft.y++;
            pRight.y++;
            // contain within grid borders
            pRight.y %= n;
            pLeft.y %= n;
        } else {
            // to the left
            pLeft.y--;
            pRight.y--;
            if (pLeft.y < 0) pLeft.y = 4;
            if (pRight.y < 0) pRight.y = 4;
        }
    }

    public String getPair() {
        return new String(swap);
    }
}
