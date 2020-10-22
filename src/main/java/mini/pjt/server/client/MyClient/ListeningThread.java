package mini.pjt.server.client.MyClient;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

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
        }

        if (reader.readLine() == "/config") {
          new CommandConfig();
        }
        System.out.println(reader.readLine());
      }



    } catch (Exception e) {
      e.printStackTrace();
    }

  }

}
