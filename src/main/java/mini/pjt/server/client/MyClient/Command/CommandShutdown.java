package mini.pjt.server.client.MyClient.Command;

import java.io.InputStream;

public class CommandShutdown {
  public static void main(String[] args) {
    String os = System.getProperty("os.name").toLowerCase();
    // os 구분자

    if (os.contains("linux")) {
      try {
        InputStream is;
        System.out.println("OS를 강제 종료합니다.");
        is = Runtime.getRuntime().exec("sudo shutdown -h now").getInputStream();
        is.close();
      } catch (Exception e) {
        e.printStackTrace();
      }

    } else if (os.contains("win")) {
      try {
        InputStream is;
        System.out.println("OS를 강제 종료합니다.");
        is = Runtime.getRuntime().exec("shutdown -s -f").getInputStream();
        is.close();
      } catch (Exception e) {
        e.printStackTrace();
      }

    } else if (os.contains("mac")) {
      try {
        InputStream is;
        System.out.println("OS를 강제 종료합니다.");
        is = Runtime.getRuntime().exec("sudo shutdown -h now").getInputStream();
        is.close();
      } catch (Exception e) {
        e.printStackTrace();
      }

    } else if (os.contains("nix") || os.contains("nux") || os.contains("aix")) {
      try {
        InputStream is;
        System.out.println("OS를 강제 종료합니다.");
        is = Runtime.getRuntime().exec("shutdown -F").getInputStream();
        is.close();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }
}
