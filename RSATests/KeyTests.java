package RSATests;

import org.junit.Test;
import static org.junit.Assert.*;
import RSA.Key;
import RSA.BigInt;

public class KeyTests {
  @Test
  public void initKey() {
    Key key = new Key(new BigInt("7"), new BigInt("33"));
    assertEquals(7, key.exponent.intValue());
    assertEquals(33, key.modulus.intValue());
  }
}
