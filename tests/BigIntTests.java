package Assignment2.tests;

import org.junit.Test;
import static org.junit.Assert.*;
import Assignment2.BigInt;

public class BigIntTests {
  @Test
  public void modPow() {
    BigInt x = new BigInt("5");
    assertEquals(8,  x.modPow(new BigInt("3"),  new BigInt("13")).intValue());
    assertEquals(5,  x.modPow(new BigInt("13"), new BigInt("20")).intValue());
    assertEquals(61, x.modPow(new BigInt("8"),  new BigInt("76")).intValue());
  }

  @Test
  public void modInverse() {
    BigInt x = new BigInt("7");
    assertEquals(15, x.modInverse(new BigInt("26")).intValue());
    assertEquals(3,  x.modInverse(new BigInt("10")).intValue());
    assertEquals(11, x.modInverse(new BigInt("19")).intValue());
  }

  @Test
  public void gcd() {
    BigInt x = new BigInt("8");
    assertEquals(4, x.gcd(new BigInt("12")).intValue());
    assertEquals(1, x.gcd(new BigInt("13")).intValue());
    assertEquals(2, x.gcd(new BigInt("90")).intValue());
  }
}
