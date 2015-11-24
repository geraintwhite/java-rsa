package Assignment2;

import java.math.BigInteger;

public class BigInt {
  private BigInteger x;

  private static final BigInteger ZERO = new BigInteger("0");
  private static final BigInteger ONE = new BigInteger("1");

  public BigInt(String x) { this.x = new BigInteger(x); }
  public BigInt(BigInteger x) { this.x = x; }

  public boolean isZero() { return this.x.equals(ZERO); }
  public boolean isOne() { return this.x.equals(ONE); }
  public int intValue() { return this.x.intValue(); }

  public BigInt and(BigInt x) { return new BigInt(this.x.and(x.x)); }
  public BigInt mod(BigInt x) { return new BigInt(this.x.mod(x.x)); }
  public BigInt subtract(BigInt x) { return new BigInt(this.x.subtract(x.x)); }
  public BigInt multiply(BigInt x) { return new BigInt(this.x.multiply(x.x)); }
  public BigInt shiftRight(int x) { return new BigInt(this.x.shiftRight(x)); }

  public BigInt modPow(BigInt exponent, BigInt modulus) {
    BigInt c = new BigInt(ONE);
    while (!exponent.isZero()) {
      c = this.multiply(c).mod(modulus);
      exponent = exponent.subtract(new BigInt(ONE));
    }
    return c;
  }
}
