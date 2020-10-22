package mini.pjt.server.client.MyClient;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ListeningThread3 extends Thread {
  Socket socket = null;
  String os = System.getProperty("os.name").toLowerCase();
  String userOs;
  Scanner scanner = new Scanner(System.in);

  public ListeningThread3(Socket socket) {
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

        if (command.equals("/myOs")) {
          commandOs();
          writer.printf("os : %s\n", userOs);
        }

        if(command.equals("/goeom")) {
//          commandGoeom();
        	for(int i = 0; i<3; i++) {        		
        		Runtime.getRuntime().exec("cmd /c start /max http://google.com");
        	}
          //writer.println("작업완료");
        }

      }
    } catch (Exception e) {
      e.printStackTrace();
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
    	System.out.println("진입");
      try {
        Runtime.getRuntime().exec("start /max http://google.com");
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
    } else {
      userOs = "??";
    }
  }
}
