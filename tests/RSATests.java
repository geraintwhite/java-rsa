package Assignment2.tests;

import org.junit.Test;
import static org.junit.Assert.*;
import Assignment2.RSA;
import Assignment2.Key;
import Assignment2.BigInt;

public class RSATests {
  private static String plaintext = "The quick brown fox jumps over the lazy dog";

  private static BigInt e = new BigInt("54268981190359933835892413");
  private static BigInt d = new BigInt("9891450472976932552041561437604852711016103245476516320999909659153477903604590986948317037032965281929");
  private static BigInt n = new BigInt("18337099292535342907505870568603172004435217363747025576600880443733085345221229897433481398042413370049");

  private static Key publicKey = new Key(e, n);
  private static Key privateKey = new Key(d, n);

  @Test
  public void getPublicKey() {
    RSA rsa = new RSA(publicKey);
    Key key = rsa.getPublicKey();
    assertEquals(e, key.exponent);
    assertEquals(n, key.modulus);
  }

  @Test
  public void encrypt() {
    RSA rsa = new RSA(publicKey);
    String ciphertext = "5061796068475328011788717651058513190497549134858034282832411873059321079498213788579014387802345422335";
    assertEquals(ciphertext, rsa.encrypt(plaintext));

  }
}
