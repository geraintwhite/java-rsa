package Assignment2.tests;

import org.junit.Test;
import static org.junit.Assert.*;
import Assignment2.Key;
import Assignment2.BigInt;

public class KeyTests {
  @Test
  public void initKey() {
    Key key = new Key(new BigInt("1337"), new BigInt("8080"));
    assertEquals(1337, key.exponent.intValue());
    assertEquals(8080, key.modulus.intValue());
  }
}
