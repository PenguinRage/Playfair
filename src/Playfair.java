/**
 * Created by penguinrage on 27/02/17.
 */


import java.util.ArrayList;


public class Playfair {

    private char[][] grid;


    Playfair(String key) {

        // Grid for encrypt and decryption purposes
        grid = new char[5][5];
        setKey(key);
    }

    private void setKey(String key) {
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
    public char[][] getGrid() {
        return grid;
    }
    // print grid
    public void showGrid() {
        for (char[] row : grid) {
            for (char c : row)
                System.out.print(" " + c + " ");
            System.out.println();
        }
    }

    public String encode(String text) {

        // If text is empty return nothing
        if (text == null)
            return "";

        // convert text into uppercase
        char[] text_array = text.toUpperCase().toCharArray();

        // create an ArrayList to store the pairs of letters
        int idx = 0;
        int i = 0;
        ArrayList<LetterPair> swapList = new ArrayList<LetterPair>();

        LetterPair pairs = null;

        while (i < text_array.length)
        {
            // check that char is within parameters
            if (text_array[i] < 'A' || text_array[idx] > 'Z') {
                i++;
                continue;
            }

            if (text_array[i] == 'J')
                text_array[i] = 'I';

            if (idx == 0) {
                pairs = new LetterPair(grid, text_array[i++], true);
                swapList.add(pairs);
                idx = 1;
                continue;
            }

            if (pairs.left == text_array[i]) {
                pairs.setRight(grid, 'X');
            }
            else {
                pairs.setRight(grid, text_array[i++]);
            }

            // next pair so index returns to 0
            idx = 0;
        }

        if (idx == 1)
            pairs.setRight(grid , 'X');

        return list_To_String(swapList);

    }

    public String decode(String cipher_text) {
        if (cipher_text == null) return "";

        char[] text_array = cipher_text.toUpperCase().toCharArray();

        StringBuilder sb = new StringBuilder(text_array.length);

        for (int i = 0; i < text_array.length; i++) {
            if (text_array[i] < 'A' || text_array[i] > 'Z')
                continue;
            if (text_array[i] == 'J')
                text_array[i] = 'I';
            sb.append(text_array[i]);
        }

        if (sb.length() % 2 != 0)
            return "Stringbuilder Message must be even to properly decode";

        ArrayList<LetterPair>  pairList = new ArrayList<LetterPair>();
        text_array = sb.toString().toCharArray();

        for (int i = 0; i < sb.length(); i+=2) {
            //create a decoding pair
            LetterPair pair = new LetterPair(grid, text_array[i], false);
            pair.setRight(grid, text_array[i+1]);
            pairList.add(pair);
        }

        // return message in lower case
        return list_To_String(pairList).toLowerCase();

    }



    private String list_To_String(ArrayList<LetterPair> swapList) {
        if (swapList.size() == 0) return "";

        // String builder puts the elements together
        StringBuilder sb = new StringBuilder();

        // put the first element
        for (int i = 0; i < swapList.size(); i++)
            sb.append(swapList.get(i).getPair());

        return sb.toString();

    }


}
