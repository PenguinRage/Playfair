/**
 * Created by penguinrage on 27/02/17.
 */

import java.util.ArrayList;
import java.util.Scanner;

public class Playfair {

    private char[][] grid;


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

        char[] single = new char[26];
        int count = 0;
        boolean[] done = new  boolean[26];

        char[] keyDigit = key.toCharArray();

        for (char c : keyDigit) {

            if(c < 'A' || c > 'Z')
                continue;

            char val = c;
            if (val == 'J')
                val = 'I';

            int idx = val - 'A';

            if(done[idx])
                continue;

            done[idx] = true;
            single[count++] = val;

        }
        // If key isn't valid within the parameters A-Z simply make grid alphabetical
        if (count == 0) {
            single[count++] = 'A';
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
            single[count++] = c;
        }

        // Now add the items to our grid
        int idx = 0;

        for (int i = 0; i < 5; i++)
            for (int j = 0; j < 5; j++)
                grid[i][j] = single[idx++];

    }

}
