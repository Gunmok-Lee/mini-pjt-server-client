package mini.pjt.server.client.MyClient.Command;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Config {
  public static void main(String[] args) {
    String os = System.getProperty("os.name").toLowerCase();
    // os 구분자

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

    } else if (os.contains("linux") || os.contains("mac")) {
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
  }
}
