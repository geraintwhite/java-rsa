package RSA;

/**
 * Key class
 *
 * Holds an RSA public or private key for encrypting and decrypting
 */
public class Key {
  public final BigInt exponent, modulus;

  public Key(BigInt exponent, BigInt modulus) {
    this.exponent = exponent;
    this.modulus = modulus;
  }
}
