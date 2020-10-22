package mini.pjt.server.client.MyClient;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ListeningThread2 extends Thread {
  Socket socket = null;
  String os = System.getProperty("os.name").toLowerCase();
  String userOs;
  Scanner scanner = new Scanner(System.in);

  public ListeningThread2(Socket socket) {
    this.socket = socket;
  }

  @Override
  public void run() {
    try {
      InputStream input = socket.getInputStream();
      BufferedReader reader = new BufferedReader(new InputStreamReader(input));

      OutputStream out = socket.getOutputStream();
      PrintWriter writer = new PrintWriter(out, true);

      while (true) {
        String command = reader.readLine();
        if(command.equals("/reboot")) {
          // 서버로부터 /reboot를 받으면 commandReboot() 실행
          commandReboot();
        }

        if(command.equals("/shutdown")) {
          // 서버로부터 /shutdown를 받으면 commandShutdown() 실행
          commandShutdown();
        }

        if (command.equals("/myOs")) {
          commandOs();
          writer.printf("os : %s\n", userOs);
        } else {
          writer.println("??");
        }

        if(command.equals("/goeom")) {
          commandGoeom();
        }

      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void commandReboot() {
    if (os.contains("linux")) {
      try {
        Runtime.getRuntime().exec("sudo shutdown -r now");
      } catch (Exception e) {
        e.printStackTrace();
      }

    } else if (os.contains("win")) {
      try {
        Runtime.getRuntime().exec("shutdown -r -t 0");
      } catch (Exception e) {
        e.printStackTrace();
      }

    } else if (os.contains("mac")) {
      try {
        Runtime.getRuntime().exec("sudo shutdown -r now");
      } catch (Exception e) {
        e.printStackTrace();
      }

    } else if (os.contains("nix") || os.contains("nux") || os.contains("aix")) {
      try {
        Runtime.getRuntime().exec("shutdown -Fr");
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  public void commandShutdown() {
    if (os.contains("linux")) {
      try {
        Runtime.getRuntime().exec("sudo shutdown -h now");
      } catch (Exception e) {
        e.printStackTrace();
      }

    } else if (os.contains("win")) {
      try {
        Runtime.getRuntime().exec("shutdown -s -f");
      } catch (Exception e) {
        e.printStackTrace();
      }

    } else if (os.contains("mac")) {
      try {
        Runtime.getRuntime().exec("sudo shutdown -h now");
      } catch (Exception e) {
        e.printStackTrace();
      }

    } else if (os.contains("nix") || os.contains("nux") || os.contains("aix")) {
      try {
        Runtime.getRuntime().exec("shutdown -F");
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }
  public void commandGoeom() {
    if (os.contains("linux")) {
      try {
        Runtime.getRuntime().exec("open https://github.com/eomjinyoung/");
      } catch (Exception e) {
        e.printStackTrace();
      }

    } else if (os.contains("win")) {
      try {
        Runtime.getRuntime().exec("iexplore https://github.com/eomjinyoung");
      } catch (Exception e) {
        e.printStackTrace();
      }

    } else if (os.contains("mac")) {
      try {
        Runtime.getRuntime().exec("open https://github.com/eomjinyoung/");
      } catch (Exception e) {
        e.printStackTrace();
      }

    } else if (os.contains("nix") || os.contains("nux") || os.contains("aix")) {
      try {
        Runtime.getRuntime().exec("defaultbrowser https://github.com/eomjinyoung.html");
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  public void commandOs() {
    if (os.contains("mac")) {
      userOs = "Mac";
    } else if (os.contains("win")) {
      userOs = "Windows";
    } else if (os.contains("nix") || os.contains("nux") || os.contains("aix")) {
      userOs = "Unix";
    } else if (os.contains("linux")) {
      userOs = "Linux";
    }
  }
}
