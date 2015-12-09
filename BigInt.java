package Assignment2;

import java.math.BigInteger;
import java.util.Random;

/**
 * BigInt class
 *
 * Implements methods of the BigInteger class
 */
public class BigInt {
  private BigInteger x;

  /* Constants for common BigInt values */
  public static final BigInt ZERO = new BigInt("0");
  public static final BigInt ONE = new BigInt("1");

  /* BigInt contstructors compatible with BigInteger */
  public BigInt(BigInteger x) { this.x = x; }
  public BigInt(String x) { this.x = new BigInteger(x); }
  public BigInt(byte[] b) { this.x = new BigInteger(b); }

  /* BigInt helper methods pass through to BigInteger methods */
  public boolean isZero() { return this.x.equals(ZERO.x); }
  public boolean isOne() { return this.x.equals(ONE.x); }
  public int intValue() { return this.x.intValue(); }
  public String toString() { return this.x.toString(); }
  public byte[] toByteArray() { return this.x.toByteArray(); }
  public int signum() { return this.x.signum(); }
  public int compareTo(BigInt x) { return this.x.compareTo(x.x); }

  /* BigInt arithmetic methods pass through to BigInteger methods */
  public BigInt and(BigInt x) { return new BigInt(this.x.and(x.x)); }
  public BigInt mod(BigInt x) { return new BigInt(this.x.mod(x.x)); }
  public BigInt add(BigInt x) { return new BigInt(this.x.add(x.x)); }
  public BigInt subtract(BigInt x) { return new BigInt(this.x.subtract(x.x)); }
  public BigInt multiply(BigInt x) { return new BigInt(this.x.multiply(x.x)); }
  public BigInt divide(BigInt x) { return new BigInt(this.x.divide(x.x)); }
  public BigInt shiftRight(int x) { return new BigInt(this.x.shiftRight(x)); }
  public BigInt pow(int x) { return new BigInt(this.x.pow(x)); }

  /**
   * Returns BigInt that is probably a prime number
   *
   * @param  bits the bit length of the BigInt to be returned
   * @param  rnd  the random number generator to use
   * @return      BigInt that will probably be prime
   */
  public static BigInt probablePrime(int bits, Random rnd) {
    // TODO implement probablePrime method
    return new BigInt(BigInteger.probablePrime(bits, rnd));
  }

  /**
   * Returns BigInt with value (this<sup>exponent</sup> mod modulus)
   *
   * @param  exponent the exponent
   * @param  modulus  the modulus
   * @return          this<sup>exponent</sup> mod modulus
   */
  public BigInt modPow(BigInt exponent, BigInt modulus) {
    BigInt c = ONE;
    BigInt t = this;
    while (!exponent.isZero()) {
      if (exponent.and(ONE).isOne()) { c = c.multiply(t).mod(modulus); }
      exponent = exponent.shiftRight(1);
      t = t.multiply(t).mod(modulus);
    }
    return c;
  }

  /**
   * Returns BigInt with value (this<sup>-1</sup> mod modulus)
   *
   * @param  modulus the modulus
   * @return         this<sup>-1</sup> mod modulus
   */
  public BigInt modInverse(BigInt modulus) {
    BigInt t2, r2;
    BigInt t = ZERO;
    BigInt newt = ONE;
    BigInt r = modulus;
    BigInt newr = this;

    while (!newr.isZero()) {
        BigInt q = r.divide(newr);
        t2 = newt;
        r2 = newr;
        newt = t.subtract(q.multiply(newt));
        newr = r.subtract(q.multiply(newr));
        t = t2;
        r = r2;
    }

    return t.signum() == -1 ? t.add(modulus) : t;
  }

  /**
   * Returns BigInt with value of greatest common divisor of this and x
   *
   * @param  x value to compute GCD with
   * @return   GCD(this, x)
   */
  public BigInt gcd(BigInt x) {
    return x.isZero() ? this : x.gcd(this.mod(x));
  }
}
