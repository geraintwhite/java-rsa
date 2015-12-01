package Assignment2;

import java.security.SecureRandom;
import Assignment2.BigInt;

/**
 * RSA class
 *
 * Has methods for generating key pairs, encrypting and decrypting data
 */
public class RSA {
  private BigInt n, d, e;
  private int bits;

  /**
   * Create instance with someone else's public key
   *
   * @param n component of public key
   * @param e component of public key
   */
  public RSA(BigInt n, BigInt e) {
    this.n = n;
    this.e = e;
  }

  /**
   * Create instance with new keyset
   *
   * @param bits number of bits to generate keys from
   */
  public RSA(int bits) {
    this.bits = bits;
    this.generateKeys();
  }

  /**
   * Generate public/private key pair
   */
  public void generateKeys() {
    SecureRandom r = new SecureRandom();

    BigInt p = new BigInt(this.bits / 2, 100, r);
    BigInt q = new BigInt(this.bits / 2, 100, r);

    this.n = p.multiply(q);
    BigInt phi = (p.subtract(BigInt.ONE)).multiply(q.subtract(BigInt.ONE));

    this.e = new BigInt("3");
    while (phi.gcd(this.e).intValue() > 1) {
      this.e = this.e.add(new BigInt("2"));
    }
    this.d = this.e.modInverse(phi);
  }

  /**
   * Encrypt a message with the stored public key
   *
   * @param  message text to be encrypted
   * @return         encrypted text
   */
  public String encrypt(String message) {
    BigInt bytes = new BigInt(message.getBytes());
    return bytes.modPow(this.e, this.n).toString();
  }

  /**
   * Decrypt a message with the stored private key
   *
   * @param  message text to be decrypted
   * @return         decrypted text
   */
  public String decrypt(String message) {
    BigInt bytes = new BigInt(message);
    return new String(bytes.modPow(this.d, this.n).toByteArray());
  }

  public static void main(String[] args) {
    RSA rsa = new RSA(2048);

    String plaintext = "The quick brown fox jumps over the lazy dog";
    System.out.println("Plaintext: " + plaintext);

    String ciphertext = rsa.encrypt(plaintext);
    System.out.println("Ciphertext: " + ciphertext);

    plaintext = rsa.decrypt(ciphertext);
    System.out.println("Plaintext: " + plaintext);
  }
}
