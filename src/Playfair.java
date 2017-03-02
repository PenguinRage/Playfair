/**
 * Created by penguinrage on 27/02/17.
 */

import java.util.ArrayList;


public class Playfair {

    public char[][] grid;


    public Playfair(String key) {

        // Grid for encrypt and decryption purposes
        grid = new char[5][5];
        setKey(key);
    }

    public void setKey(String key) {
        if (key == null)
            key = "A";
        else
            key = key.toUpperCase();

        char[] cipher_key = new char[26];
        int count = 0;
        boolean[] done = new boolean[26];

        char[] keyDigit = key.toCharArray();

        for (char c : keyDigit) {

            if (c < 'A' || c > 'Z')
                continue;

            char val = c;
            if (val == 'J')
                val = 'I';

            int idx = val - 'A';

            if (done[idx])
                continue;

            done[idx] = true;
            cipher_key[count++] = val;

        }
        // If key isn't valid within the parameters A-Z simply make grid alphabetical
        if (count == 0) {
            cipher_key[count++] = 'A';
            done[0] = true;
        }

        // fill the remainder of the array with non duplicates
        for (char c = 'A'; c <= 'Z'; c++) {
            // ignore J
            if (c == 'J')
                continue;
            //test if already used in given password
            if (done[c - 'A'])
                continue;
            // else add it in
            cipher_key[count++] = c;
        }

        // Now add the items to our grid
        int idx = 0;

        for (int i = 0; i < 5; i++)
            for (int j = 0; j < 5; j++)
                grid[i][j] = cipher_key[idx++];

    }

    // print grid
    public void showGrid() {
        for (char[] row : grid) {
            for (char c : row)
                System.out.println(" " + c + " ");
            System.out.println();
        }
    }

    public String encode(String text) {

        // If text is empty return nothing
        if (text == null)
            return "";

        // convert text into uppercase
        char[] new_text = text.toUpperCase().toCharArray();

        // create an ArrayList to store the pairs of letters
        int idx = 0;
        int i = 0;
        ArrayList<LetterPair> swapList = new ArrayList<LetterPair>();

        LetterPair pairs = null;

        while (i < new_text.length)
        {
            // check that char is within parameters
            if (new_text[i] < 'A' || new_text[idx] > 'Z') {
                i++;
                continue;
            }

            if (new_text[i] == 'J')
                new_text[i] = 'I';

            if (idx == 0) {
                pairs = new LetterPair(grid, new_text[i++], true);
                swapList.add(pairs);
                idx = 1;
                continue;
            }

            if (pairs.left == new_text[i]) {
                pairs.setRight(grid, 'X');
            }
            else {
                pairs.setRight(grid, new_text[i++]);
            }

            // next pair so index returns to 0
            idx = 0;
        }

        if (idx == 1)
            pairs.setRight(grid , 'X');

        return list_To_String(swapList);

    }


}
