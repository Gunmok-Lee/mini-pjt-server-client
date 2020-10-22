package mini.pjt.server.client.MyClient;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class ListeningThread extends Thread { // �������� ���� �޼��� �д� Thread
  Socket socket = null;
  String os = System.getProperty("os.name").toLowerCase();
  String userOs;

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
        if (reader.readLine() == "/myOs") {
          if (os.contains("mac")) {
            userOs = "Mac";
            System.out.printf("user os : %s\n", userOs);
          }
          if (os.contains("win")) {
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

        if (reader.readLine() == "/config") {
          if (os.contains("win")) {
            try {
              String line;
              InputStream is;
              is = Runtime.getRuntime().exec("ipconfig").getInputStream();
              BufferedReader br = new BufferedReader(new InputStreamReader(is, "MS949"));
              while((line = br.readLine()) != null) {
                System.out.println(line);
              }
              br.close();
              is.close();
            } catch (Exception e) {
              e.printStackTrace();
            }
          } else if (os.contains("nix") || os.contains("nux") || os.contains("aix") || os.contains("linux") || os.contains("mac")) {
            try {
              String line;
              InputStream is;
              is = Runtime.getRuntime().exec("ifconfig").getInputStream();
              BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
              while((line = br.readLine()) != null) {
                System.out.println(line);
              }
              br.close();
              is.close();
            } catch (Exception e) {
              e.printStackTrace();
            }
          }
        }
        System.out.println(reader.readLine());
      }



    } catch (Exception e) {
      e.printStackTrace();
    }

  }

}
