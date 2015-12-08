package Assignment2.tests;

import org.junit.Test;
import static org.junit.Assert.*;
import Assignment2.RSA;
import Assignment2.Key;
import Assignment2.BigInt;

public class RSATests {
  @Test
  public void getPublicKey() {
    RSA rsa = new RSA(new Key(new BigInt("7"), new BigInt("33")));
    Key key = rsa.getPublicKey();
    assertEquals(7, key.exponent.intValue());
    assertEquals(33, key.modulus.intValue());
  }

  @Test
  public void encrypt() {
    BigInt e = new BigInt("58526141510869204035840419");
    BigInt m = new BigInt("15337720597404335770817004203243347680990093413285641791961208026434705304926829890429073136938836703339");
    RSA rsa = new RSA(new Key(e, m));

    String ciphertext = rsa.encrypt("The quick brown fox jumps over the lazy dog");
    assertEquals("13927730793476586151179250538330884705396863722440195585453050622417519852477443521259472374067520832386", ciphertext);
  }
}
