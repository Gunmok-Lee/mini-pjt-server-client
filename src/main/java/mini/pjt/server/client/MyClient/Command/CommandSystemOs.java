package mini.pjt.server.client.MyClient.Command;

public class CommandSystemOs {
  public static void main(String[] args) throws Exception {
    String os = System.getProperty("os.name").toLowerCase();
    String userOs;

    if (os.contains("mac")) {
      userOs = "Mac";
      System.out.printf("user os : %s\n", userOs);
    } else if (os.contains("win")) {
      userOs = "Windows";
      System.out.printf("user os : %s\n", userOs);
    } else if (os.contains("nix") || os.contains("nux") || os.contains("aix")) {
      userOs = "Unix";
      System.out.printf("user os : %s\n", userOs);
    } else if (os.contains("linux")) {
      userOs = "Linux";
      System.out.printf("user os : %s\n", userOs);
    }
  }
}
