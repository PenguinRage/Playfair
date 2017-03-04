import static org.junit.Assert.*;

/**
 * Created by penguinrage on 3/03/17.
 */
public class PlayfairTest {
    @org.junit.Test
    public void showGrid() throws Exception {
        Playfair pf = new Playfair("PLAYFAIR EXAMPLE");
        char[][] exp = {{'P','L','A','Y','F'},
                        {'I','R','E','X','M'},
                        {'B','C','D','G','H'},
                        {'K','N','O','Q','S'},
                        {'T','U','V','W','Z'}};
        assertArrayEquals(exp, pf.getGrid());

    }

    @org.junit.Test
    public void encode() throws Exception {
        Playfair pf = new Playfair("PLAYFAIR EXAMPLE");
        String encryption_txt = pf.encode("Hell my Friend");
        System.out.println(encryption_txt);

    }

    @org.junit.Test
    public void decode() throws Exception {
        Playfair pf = new Playfair("PLAYFAIR EXAMPLE");
        String encryption_txt = pf.encode("My Friend");
        String decrypted_txt = pf.decode(encryption_txt);
        System.out.println(decrypted_txt);

    }
}