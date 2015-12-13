package Utils;

import java.util.ArrayList;

public class ArgParse {
  public static class Option {
    public String flag, opt;

    public Option(String flag) {
      this(flag, null);
    }

    public Option(String flag, String opt) {
      this.flag = flag;
      this.opt = opt;
    }
  }

  public static ArrayList<Option> parse(String[] args) {
    ArrayList<Option> options = new ArrayList<>();
    int i = 0;
    String arg;

    while (i < args.length && args[i].startsWith("-")) {
      arg = args[i++];
      if (i == args.length || args[i].startsWith("-")) {
        options.add(new Option(arg));
      } else {
        options.add(new Option(arg, args[i++]));
      }
    }

    return options;
  }
}
