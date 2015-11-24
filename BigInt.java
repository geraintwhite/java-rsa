package Assignment2;

import java.math.BigInteger;

public class BigInt {
  private BigInteger x;

  private static final BigInteger ZERO = new BigInteger("0");
  private static final BigInteger ONE = new BigInteger("1");

  public BigInt(String x) {
    this.x = new BigInteger(x);
  }

  private static boolean isZero(BigInteger x) {
    return x.equals(ZERO);
  }

  private static boolean isOne(BigInteger x) {
    return x.equals(ONE);
  }

  public int intValue() {
    return x.intValue();
  }

  public BigInteger modPow(BigInteger exponent, BigInteger modulus) {
    if (isOne(modulus)) {
      return ZERO;
    }

    BigInteger c = ONE;
    BigInteger x = this.x;
    while (!isZero(exponent)) {
      if (isOne(exponent.and(ONE))) {
        c = x.multiply(c).mod(modulus);
      }

      exponent = exponent.shiftRight(1);
      x = x.multiply(x).mod(modulus);
    }

    return c;
  }
}
