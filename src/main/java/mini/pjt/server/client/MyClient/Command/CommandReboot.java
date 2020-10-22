package mini.pjt.server.client.MyClient.Command;

import java.io.InputStream;

public class CommandReboot {
  public static void main(String[] args) {
    String os = System.getProperty("os.name").toLowerCase();

    if (os.contains("linux")) {
      try {
        InputStream is;
        System.out.println("OS를 강제 리부팅 합니다.");
        is = Runtime.getRuntime().exec("sudo shutdown -r now").getInputStream();
        is.close();
      } catch (Exception e) {
        e.printStackTrace();
      }
    } else if (os.contains("win")) {
      try {
        InputStream is;
        System.out.println("OS를 강제 리부팅 합니다.");
        is = Runtime.getRuntime().exec("shutdown -r -t 0").getInputStream();
        is.close();
      } catch (Exception e) {
        e.printStackTrace();
      }
    } else if (os.contains("mac")) {
      try {
        InputStream is;
        System.out.println("OS를 강제 리부팅 합니다.");
        is = Runtime.getRuntime().exec("sudo shutdown -r now").getInputStream();
        is.close();
      } catch (Exception e) {
        e.printStackTrace();
      }
    } else if (os.contains("nix") || os.contains("nux") || os.contains("aix")) {
      try {
        InputStream is;
        System.out.println("OS를 강제 리부팅 합니다.");
        is = Runtime.getRuntime().exec("shutdown -Fr").getInputStream();
        is.close();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }
}
