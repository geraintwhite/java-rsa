package RSATests;

import org.junit.Test;
import static org.junit.Assert.*;
import RSA.RSA;
import RSA.Key;
import RSA.BigInt;

public class RSATests {
  private static String plaintext = "The quick brown fox jumps over the lazy dog";

  private static BigInt e = new BigInt("54268981190359933835892413");
  private static BigInt d = new BigInt("9891450472976932552041561437604852711016103245476516320999909659153477903604590986948317037032965281929");
  private static BigInt n = new BigInt("18337099292535342907505870568603172004435217363747025576600880443733085345221229897433481398042413370049");

  private static Key publicKey = new Key(e, n);
  private static Key privateKey = new Key(d, n);

  @Test
  public void getPublicKey() {
    RSA rsa = new RSA();
    rsa.importPublicKey(publicKey);
    Key key = rsa.getPublicKey();
    assertEquals(e, key.exponent);
    assertEquals(n, key.modulus);
  }

  @Test
  public void getPrivateKey() {
    RSA rsa = new RSA();
    rsa.importKeyPair(publicKey, privateKey);
    Key key = rsa.getPrivateKey();
    assertEquals(d, key.exponent);
    assertEquals(n, key.modulus);
  }

  @Test
  public void generateKeys() {
    int bits = 128;
    RSA rsa = new RSA();
    rsa.generateKeys(128);
    BigInt e = rsa.getPublicKey().exponent;
    BigInt n = rsa.getPublicKey().modulus;
    BigInt two = new BigInt("2");

    assertEquals(1, e.compareTo(two.pow(bits / 2 - 1)));
    assertEquals(1, n.compareTo(two.pow(2 * (bits - 1))));
  }

  @Test
  public void encrypt() {
    RSA rsa = new RSA();
    rsa.importPublicKey(publicKey);

    String ciphertext =
      "1275967159186200292326728017576312259056904725393963606173652613920805856688384831556518334396764989782\n" +
      "3363752731410316226189204573338362889768641187507434297506172396794418275834428758631966560061198806960";

    assertEquals(ciphertext, BigInt.formatArray(rsa.encrypt(plaintext)));
  }

  @Test
  public void decrypt() {
    RSA rsa = new RSA();
    rsa.importKeyPair(publicKey, privateKey);
    RSA rsa2 = new RSA();
    rsa2.importPublicKey(rsa.getPublicKey());

    BigInt[] ciphertext = rsa2.encrypt(plaintext);
    assertEquals(plaintext, rsa.decrypt(ciphertext));
  }
}
