/**
 * Created by penguinrage on 27/02/17.
 */

import java.util.ArrayList;
import java.util.Scanner;

public class Playfair {

    private char[][] grid;
    String key;

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




    }

}
