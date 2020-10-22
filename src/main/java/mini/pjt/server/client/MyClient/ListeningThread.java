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

        if (reader.readLine() == "/SystemOs") {
          new CommandSystemOs();
        } else if (reader.readLine() == "/config") {
          new CommandConfig();
        } else if (reader.readLine() == "/shutdown") {
          new CommandShutdown();
          break;
        } else if (reader.readLine() == "/reboot") {
          new CommandReboot();
          break;
        } else
          System.out.println(reader.readLine());
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
