package Assignment2;

import java.math.BigInteger;

public class BigInt {
  public static long modPow(long base, long exponent, long modulus) {
    if (modulus == 1) return 0;

    long c = 1;
    for (long i = 0; i < exponent; i++) {
      c = c * base % modulus;
    }

    return c;
  }
}
