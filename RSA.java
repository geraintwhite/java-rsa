package Assignment2;

import java.security.SecureRandom;
import Assignment2.BigInt;
import Assignment2.Key;

/**
 * RSA class
 *
 * Has methods for generating key pairs, encrypting and decrypting data
 */
public class RSA {
  private Key privateKey, publicKey;

  /**
   * Create new RSA instance
   */
  public RSA() {}

  /**
   * Import public key object
   *
   * @param publicKey public key object to store
   */
  public void importPublicKey(Key publicKey) {
    this.publicKey = publicKey;
  }

  /**
   * Import private key object
   *
   * @param privateKey private key object to store
   */
  public void importPrivateKey(Key privateKey) {
    this.privateKey = privateKey;
  }

  /**
   * Import public/private key pair
   *
   * @param publicKey public key object to store
   * @param privateKey private key object to store
   */
  public void importKeyPair(Key publicKey, Key privateKey) {
    this.publicKey = publicKey;
    this.privateKey = privateKey;
  }

  /**
   * Generate new public/private key pair
   *
   * @param bits number of bits to generate keys from
   */
  public void generateKeys(int bits) {
    SecureRandom r = new SecureRandom();

    BigInt p = BigInt.probablePrime(bits, r);
    BigInt q = BigInt.probablePrime(bits, r);

    BigInt n = p.multiply(q);
    BigInt phi = p.subtract(BigInt.ONE).multiply(q.subtract(BigInt.ONE));

    BigInt e = BigInt.probablePrime(bits / 2, r);
    while (phi.gcd(e).compareTo(BigInt.ONE) > 0 && e.compareTo(phi) < 0) {
      e.add(BigInt.ONE);
    }
    BigInt d = e.modInverse(phi);

    this.publicKey = new Key(e, n);
    this.privateKey = new Key(d, n);
  }

  /**
   * Encrypt a message with the stored public key
   *
   * @param  message text to be encrypted
   * @return         encrypted text
   */
  public String encrypt(String message) {
    BigInt bytes = new BigInt(message.getBytes());
    return bytes.modPow(publicKey.exponent, publicKey.modulus).toString();
  }

  /**
   * Decrypt a message with the stored private key
   *
   * @param  message text to be decrypted
   * @return         decrypted text
   */
  public String decrypt(String message) {
    BigInt bytes = new BigInt(message);
    return new String(bytes.modPow(privateKey.exponent, privateKey.modulus).toByteArray());
  }

  /**
   * Export private key object
   *
   * @return private key
   */
  public Key getPrivateKey() {
    return this.privateKey;
  }

  /**
   * Export public key object
   *
   * @return public key
   */
  public Key getPublicKey() {
    return this.publicKey;
  }

  public static void main(String[] args) {
    RSA rsa = new RSA();
    rsa.generateKeys(2048);
    RSA rsa2 = new RSA();
    rsa2.importPublicKey(rsa.getPublicKey());

    String plaintext = "The quick brown fox jumps over the lazy dog";
    System.out.println("Plaintext: " + plaintext);

    String ciphertext = rsa2.encrypt(plaintext);
    System.out.println("Ciphertext: " + ciphertext);

    plaintext = rsa.decrypt(ciphertext);
    System.out.println("Plaintext: " + plaintext);
  }
}
