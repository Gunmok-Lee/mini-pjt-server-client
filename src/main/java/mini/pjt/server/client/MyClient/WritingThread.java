package mini.pjt.server.client.MyClient;

import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class WritingThread extends Thread { // ������ �޼��� ������ Thread
	Socket socket = null;
	Scanner scanner = new Scanner(System.in); // ä�ÿ� Scanner
	
	public WritingThread(Socket socket) { // ������
		this.socket = socket; // �޾ƿ� Socket Parameter�� �ش� Ŭ���� Socket�� �ֱ�
	}
	
	public void run() {
		try {
			// OutputStream - Ŭ���̾�Ʈ���� Server�� �޼��� �߼� 
			OutputStream out = socket.getOutputStream(); // socket�� OutputStream ������ OutputStream out�� ���� ��
			PrintWriter writer = new PrintWriter(out, true); // PrintWriter�� �� OutputStream�� ��� ���
			
			while(true) { // ���ѹݺ�
				writer.println(scanner.nextLine()); // �Է��� �޼��� �߼�
			}
			
		} catch (Exception e) {
			e.printStackTrace(); // ����ó��
		}
		
		
	}


}
