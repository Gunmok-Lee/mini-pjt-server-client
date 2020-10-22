package mini.pjt.server.client.MyServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

// 소켓통신용 서버 코드
public class MySocketServer2 extends Thread {
	static Map<String, Socket> list = new HashMap<String, Socket>(); // 유저 확인용
	static Socket socket = null;
	Scanner scanner = new Scanner(System.in);

	public MySocketServer2(Socket socket) {
		this.socket = socket;
		list.put(RandomWord.wordCreate(), socket);
	}

	public void run() {
		try {
			// 연결 확인용
			System.out.println("서버 : " + socket.getInetAddress() + " IP의 클라이언트와 연결되었습니다");

			// InputStream - 클라이언트에서 보낸 메세지 읽기
			InputStream input = socket.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(input));

			// OutputStream - 서버에서 클라이언트로 메세지 보내기
			OutputStream out = socket.getOutputStream();
			PrintWriter writer = new PrintWriter(out, true);

			// 클라이언트에게 연결되었다는 메세지 보내기
			// writer.println("서버에 연결되었습니다! ID를 입력해 주세요!");

			String readValue; // Client에서 보낸 값 저장
			String name = null; // 클라이언트 이름 설정용

			// 클라이언트가 메세지 입력시마다 수행
			while (true) {
				for (int i = 0; i < list.size(); i++) {
					out = list.get(i).getOutputStream();
					writer = new PrintWriter(out, true);
					writer.println(scanner.nextLine());
				}
			}
		} catch (Exception e) {
			e.printStackTrace(); // 예외처리
		}
	}

	public static void main(String[] args) {
		try {
			int socketPort = 8888; // 소켓 포트 설정용
			ServerSocket serverSocket = new ServerSocket(socketPort); // 서버 소켓 만들기
			// 서버 오픈 확인용
			System.out.println("socket : " + socketPort + "으로 서버가 열렸습니다");

			// 소켓 서버가 종료될 때까지 무한루프
			while (true) {
				Socket socketUser = serverSocket.accept(); // 서버에 클라이언트 접속 시
				// Thread 안에 클라이언트 정보를 담아줌
				Thread thd = new MySocketServer(socketUser);
				thd.start(); // Thread 시작
			}

		} catch (IOException e) {
			e.printStackTrace(); // 예외처리
		}

	}

}