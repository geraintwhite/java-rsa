package Assignment2.tests;

import org.junit.Test;
import static org.junit.Assert.*;
import Assignment2.Key;
import Assignment2.BigInt;

public class KeyTests {
  @Test
  public void initKey() {
    Key key = new Key(new BigInt("7"), new BigInt("33"));
    assertEquals(7, key.exponent.intValue());
    assertEquals(33, key.modulus.intValue());
  }
}
