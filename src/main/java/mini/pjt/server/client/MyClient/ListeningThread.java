package mini.pjt.server.client.MyClient;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import mini.pjt.server.client.MyClient.Command.CommandConfig;
import mini.pjt.server.client.MyClient.Command.CommandReboot;
import mini.pjt.server.client.MyClient.Command.CommandShutdown;
import mini.pjt.server.client.MyClient.Command.CommandSystemOs;

public class ListeningThread extends Thread { // �������� ���� �޼��� �д� Thread
  Socket socket = null;

  public ListeningThread(Socket socket) { // ������
    this.socket = socket; // �޾ƿ� Socket Parameter�� �ش� Ŭ���� Socket�� �ֱ�
  }

  @Override
  public void run() {
    try {
      // InputStream - Server���� ���� �޼����� Ŭ���̾�Ʈ�� ������
      InputStream input = socket.getInputStream(); // socket�� InputStream ������ InputStream in�� ���� ��
      BufferedReader reader = new BufferedReader(new InputStreamReader(input)); // BufferedReader�� �� InputStream�� ��� ���
      while(true) { // ���ѹݺ�
        String command = reader.readLine();
        if (command.equals("/systemos")) {
        	String os = System.getProperty("os.name").toLowerCase();
            // os 구분자
            String userOs;
            // 클라이언트의 os표시

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
        } else if (command.equals("/config")) {
          new CommandConfig();
        } else if (command.equals("/shutdown")) {
          new CommandShutdown();
        } else if (command.equals("/reboot")) {
          new CommandReboot();
        } else
          System.out.println(command);
      }



    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
