package Assignment2;

import org.junit.Test;
import static org.junit.Assert.*;

public class Maths {
  public static long modPow(long base, long exponent, long modulus) {
    if (modulus == 1) return 0;

    long c = 1;
    for (long i = 0; i < exponent; i++) {
      c = (c * base) % modulus;
    }

    return c;
  }

  public static void main(String[] args) {
    assertEquals(8, Maths.modPow(5, 3, 13));
  }
}
