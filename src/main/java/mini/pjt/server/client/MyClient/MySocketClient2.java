package mini.pjt.server.client.MyClient;

import java.net.Socket;

public class MySocketClient2 {

  public static void main(String[] args) {
    try {
      Socket socket = null;
      // 소켓 서버에 접속
      socket = new Socket("localhost", 8888);
      System.out.println("서버에 접속 성공!"); // 접속 확인용

      // 서버에서 보낸 메세지 읽는 Thread
      ListeningThread2 t1 = new ListeningThread2(socket);
      WritingThread t2 = new WritingThread(socket); // 서버로 메세지 보내는 Thread

      t1.start(); // ListeningThread Start
      t2.start(); // WritingThread Start

    } catch (Exception e) {
      e.printStackTrace(); // 예외처리
    }
  }
}