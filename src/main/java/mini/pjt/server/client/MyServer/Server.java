package mini.pjt.server.client.MyServer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.google.common.collect.Multiset.Entry;

public class Server {
	static ExecutorService executorService; // 스레드풀
	static ServerSocket serverSocket;
	static List<Client> connections = new Vector<Client>();
	static ArrayList<String> wordList = new ArrayList<>(Arrays.asList(new String[] { "가마", "밥솥", "햄버거", "충치" }));
	static boolean gameStatus = false;
	static int readyCount = 0;
	static Map<Socket, Integer> pointList = new HashMap<Socket, Integer>();

	static void startServer() { // 서버 시작 시 호출
		// 스레드풀 생성
		executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

		// 서버 소켓 생성 및 바인딩
		try {
			serverSocket = new ServerSocket();
			serverSocket.bind(new InetSocketAddress("localhost", 5001));
		} catch (Exception e) {
			if (!serverSocket.isClosed()) {
				stopServer();
			}
			return;
		}

		// 수락 작업 생성
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				System.out.println("[서버 시작]");
				while (true) {
					try {
						// 연결 수락
						Socket socket = serverSocket.accept();
						System.out.println("[연결 수락: " + socket.getRemoteSocketAddress() + ": "
								+ Thread.currentThread().getName() + "]");
						// 클라이언트 접속 요청 시 객체 하나씩 생성해서 저장
						Client client = new Client(socket);
						connections.add(client);
						System.out.println("[연결 개수: " + connections.size() + "]");
					} catch (Exception e) {
						if (!serverSocket.isClosed()) {
							stopServer();
						}
						break;
					}
				}
			}
		};
		// 스레드풀에서 처리
		executorService.submit(runnable);
	}

	static void stopServer() { // 서버 종료 시 호출
		try {
			// 모든 소켓 닫기
			Iterator<Client> iterator = connections.iterator();
			while (iterator.hasNext()) {
				Client client = iterator.next();
				client.socket.close();
				iterator.remove();
			}
			// 서버 소켓 닫기
			if (serverSocket != null && !serverSocket.isClosed()) {
				serverSocket.close();
			}
			// 스레드풀 종료
			if (executorService != null && !executorService.isShutdown()) {
				executorService.shutdown();
			}
			System.out.println("[서버 멈춤]");
		} catch (Exception e) {
		}
	}

	static class Client {
		Socket socket;

		Client(Socket socket) {
			this.socket = socket;
			receive();
		}

		void receive() {
			// 받기 작업 생성
			Runnable runnable = new Runnable() {
				@Override
				public void run() {
					try {
						while (true) {
							byte[] byteArr = new byte[100];
							InputStream inputStream = socket.getInputStream();

							// 데이터 read
							int readByteCount = inputStream.read(byteArr);

							// 클라이언트가 정상적으로 Socket의 close()를 호출했을 경우
							if (readByteCount == -1) {
								throw new IOException();
							}

							System.out.println("[요청 처리: " + socket.getRemoteSocketAddress() + ": "
									+ Thread.currentThread().getName() + "]");

							// 문자열로 변환
							String data = new String(byteArr, 0, readByteCount, "UTF-8");

							if (!gameStatus) {
								// 클라이언트가 stop server라고 보내오면 서버 종료
								System.out.println("1진입");
								if (data.equals("/start")) {
									readyCount += 1;
									if (readyCount == connections.size()) {
										gameStatus = true;
										for (Client client : connections) {
											client.send("게임이 시작됩니다.");
											for (Client client1 : connections) {
												client.send(wordList.toString() + "\n");
											}
										}
									} else {
										for (Client client : connections) {
											client.send("준비중..(" + readyCount + "/" + connections.size() + ")");
										}
									}
									continue;
								}
//								if(data.equals("stop server")) 
//								{
//									stopServer();
//								}
//								
//								// 모든 클라이언트에게 데이터 보냄
//								for(Client client : connections) {
//									client.send(data); 
//								}
							} else {
								if (wordList.size() <= 0) {
									for (Client client : connections) {
										client.send("축하합니다! 모든 단어를 입력 하셨습니다.\n게임을 종료합니다.");
									}
									gameStatus = false;
									readyCount = 0;
									wordList.add("가마");
									wordList.add("충치");
									wordList.add("가마솥밥");
									wordList.add("돼지두루치기");
									wordList.add("보쌈");
								}
								int index = wordList.indexOf(data);
								if (index != -1) {
									wordList.remove(index);
									if (pointList.containsKey(socket)) {
										int nowPoint = pointList.get(socket);
										pointList.put(socket, nowPoint + 1);

									} else {
										pointList.put(socket, 1);
									}
								}
								
								for (Client client : connections) {
									client.send(wordList.toString() + "\n");
								}
								
								if(data.substring(0,1).equals("/")) {
									for (Client client : connections) {
										client.send(data);
									}
								}
								
								if (wordList.size() <= 0) {
									for (Client client : connections) {
										client.send("축하합니다! 모든 단어를 입력 하셨습니다.\n게임을 종료합니다.");
									}
									gameStatus = false;
									readyCount = 0;
									wordList.add("가마");
									wordList.add("충치");
									wordList.add("가마솥밥");
									wordList.add("돼지두루치기");
									wordList.add("보쌈");
								}
							}

						}
					} catch (Exception e) {
						try {
							connections.remove(Client.this);
							System.out.println("[클라이언트 통신 안됨: " + socket.getRemoteSocketAddress() + ": "
									+ Thread.currentThread().getName() + "]");
							socket.close();
						} catch (IOException e2) {
						}
					}
				}
			};
			// 스레드풀에서 처리
			executorService.submit(runnable);
		}

		void send(String data) {
			// 보내기 작업 생성
			Runnable runnable = new Runnable() {
				@Override
				public void run() {
					try {
						// 클라이언트로 데이터 보내기
						byte[] byteArr = data.getBytes("UTF-8");
						OutputStream outputStream = socket.getOutputStream();
						// 데이터 write
						outputStream.write(byteArr);
						outputStream.flush();
					} catch (Exception e) {
						try {
							System.out.println("[클라이언트 통신 안됨: " + socket.getRemoteSocketAddress() + ": "
									+ Thread.currentThread().getName() + "]");
							connections.remove(Client.this);
							socket.close();
						} catch (IOException e2) {
						}
					}
				}
			};
			// 스레드풀에서 처리
			executorService.submit(runnable);
		}
	}

	public static void main(String[] args) {
		startServer();
	}
}