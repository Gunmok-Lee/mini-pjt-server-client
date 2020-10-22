package mini.pjt.server.client.MyClient;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ListeningThread2 extends Thread { // �������� ���� �޼��� �д� Thread
	Socket socket = null;
	String os = System.getProperty("os.name").toLowerCase();
	String userOs;
	Scanner scanner = new Scanner(System.in);

	public ListeningThread2(Socket socket) { // ������
		this.socket = socket; // �޾ƿ� Socket Parameter�� �ش� Ŭ���� Socket�� �ֱ�
	}

	@Override
	public void run() {
		try {
			// InputStream - Server���� ���� �޼����� Ŭ���̾�Ʈ�� ������
			InputStream input = socket.getInputStream(); // socket�� InputStream ������ InputStream in�� ���� ��
			BufferedReader reader = new BufferedReader(new InputStreamReader(input)); // BufferedReader�� ��
																						// InputStream�� ��� ���

			OutputStream out = socket.getOutputStream(); // socket�� OutputStream ������ OutputStream out�� ���� ��
			PrintWriter writer = new PrintWriter(out, true); // PrintWriter�� �� OutputStream�� ��� ���

			while (true) { // ���ѹݺ�
				String command = reader.readLine();

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

				writer.println(scanner.nextLine());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
