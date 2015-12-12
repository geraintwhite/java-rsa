package Assignment2;

import java.io.*;
import Assignment2.BigInt;
import Assignment2.RSA;
import Assignment2.Key;
import Utils.ArgParse;

public class CLI {
  public static String method = "", input, output, inkey, outpub, outpriv;
  public static String USAGE = "\n" +
    "Usage: rsa [options]\n" +
    "Try 'rsa --help' for more information.";

  public static void parseOptions(String[] args) {
    for (ArgParse.Option option : ArgParse.parse(args)) {
      switch (option.flag) {
        case "--genkeys":
          method = "genkeys";
          break;
        case "--encrypt":
          method = "encrypt";
          break;
        case "--decrypt":
          method = "decrypt";
          break;
        case "--input":
          input = getOpt(option);
          break;
        case "--output":
          output = getOpt(option);
          break;
        case "--inkey":
          inkey = getOpt(option);
          break;
        case "--outpub":
          outpub = getOpt(option);
          break;
        case "--outpriv":
          outpriv = getOpt(option);
          break;
        case "--help":
          System.out.println(
            "Help:\n" +
            "--genkeys        generate RSA public/private key pair\n" +
            "--encrypt        encrypt the input using public key\n" +
            "--decrypt        decrypt the input using private key\n" +
            "--input    FILE  input file to read from (stdin by default)\n" +
            "--output   FILE  output file to write to (stdout by default)\n" +
            "--inkey    FILE  RSA key to use for encrypting/decrypting\n" +
            "--outpub   FILE  file to write RSA public key to\n" +
            "--outpriv  FILE  file to write RSA private key to\n" +
            "--help           show this help text"
          );
          break;
        default:
          System.out.printf("Unknown flag: %s\n", option.flag);
          throw new IllegalArgumentException();
      }
    }
  }

  public static String getOpt(ArgParse.Option option) {
    if (option.opt == null) {
      System.out.printf("Option required for flag: %s\n", option.flag);
      throw new IllegalArgumentException();
    }
    return option.opt;
  }

  public static String readInput(String path) {
    String out = "", delim = "", line;
    try {
      BufferedReader br = new BufferedReader(
        path == null ? new InputStreamReader(System.in) : new FileReader(path));
      while ((line = br.readLine()) != null) {
        out += delim + line;
        delim = "\n";
      }
      br.close();
    } catch (IOException e) {
      System.out.printf("Cannot read file %s\n", path);
    }
    return out;
  }

  public static void writeOutput(String data, String path) {
    try {
      PrintWriter out = new PrintWriter(path == null ? System.out : new FileOutputStream(path));
      out.println(data);
      out.close();
    } catch (FileNotFoundException e) {
      System.out.printf("Cannot write to file %s\n", path);
    }
  }

  public static Key readRSAKey(String path) {
    Key key = null;
    try {
      BufferedReader br = new BufferedReader(new FileReader(path));
      BigInt e = new BigInt(br.readLine());
      BigInt n = new BigInt(br.readLine());
      br.close();
      key = new Key(e, n);
    } catch (IOException e) {
      System.out.printf("Cannot read file %s\n", path);
    }
    return key;
  }

  public static void writeRSAKey(Key key, String path) {
    try {
      PrintWriter out = new PrintWriter(new FileOutputStream(path));
      out.println(key.exponent);
      out.println(key.modulus);
      out.close();
      System.out.printf("RSA key written to %s\n", path);
    } catch (IOException e) {
      System.out.printf("Cannot write to file %s\n", path);
    }
  }

  public static void main(String[] args) {
    try {
      parseOptions(args);
    } catch (IllegalArgumentException e) {
      method = "";
    }

    switch (method) {
      case "genkeys":
        if (outpriv == null || outpub == null) {
          System.out.println("--outpriv and --outpub must be supplied");
        } else {
          RSA rsa = new RSA();
          rsa.generateKeys(2048);
          writeRSAKey(rsa.getPrivateKey(), outpriv);
          writeRSAKey(rsa.getPublicKey(), outpub);
        }
        break;
      case "encrypt":
        if (inkey == null) {
          System.out.println("--inkey must be supplied");
        } else {
          RSA rsa = new RSA();
          rsa.importPublicKey(readRSAKey(inkey));
          writeOutput(rsa.encrypt(readInput(input)), output);
        }
        break;
      case "decrypt":
        if (inkey == null) {
          System.out.println("--inkey must be supplied");
        } else {
          RSA rsa = new RSA();
          rsa.importPrivateKey(readRSAKey(inkey));
          writeOutput(rsa.decrypt(readInput(input)), output);
        }
        break;
      default:
        System.out.println(USAGE);
    }
  }
}
