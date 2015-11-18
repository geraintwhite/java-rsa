package Assignment2.tests;

import org.junit.Test;
import static org.junit.Assert.*;
import Assignment2.BigInt;

public class BigIntTests {
  @Test
  public static void modPow() {
    assertEquals(8, BigInt.modPow(5, 3, 13));
  }
}
