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
        String command = reader.readLine();
        String os = System.getProperty("os.name").toLowerCase();
        if (command.equals("/systemos")) {
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
          if (os.contains("win")) {
            try {
              String line;
              InputStream is;
              is = Runtime.getRuntime().exec("ipconfig").getInputStream();
              BufferedReader br = new BufferedReader(new InputStreamReader(is, "MS949"));
              while((line = br.readLine()) != null) {
                System.out.println(line); // ipconfig 명령 결과 출력
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
                System.out.println(line); // ifcongfig 명령 결과 출력
              }
              br.close();
              is.close();
            } catch (Exception e) {
              e.printStackTrace();
            }
          }
        } else if (command.equals("/shutdown")) {
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
        } else if (command.equals("/reboot")) {
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
        } else {
          System.out.println(command);
        }
      }



    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
