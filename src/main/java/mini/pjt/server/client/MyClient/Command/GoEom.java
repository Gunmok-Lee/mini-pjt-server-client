package mini.pjt.server.client.MyClient.Command;

public class GoEom {
	public static void goeomWeb() {
		String os = System.getProperty("os.name").toLowerCase();
		// os 구분자
		
		if (os.contains("linux") || os.contains("mac")) {
			try {
				Runtime.getRuntime().exec("open https://github.com/eomjinyoung/");
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} else if (os.contains("win")) {
			try {
				Runtime.getRuntime().exec("cmd /c start /max https://github.com/eomjinyoung");
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
		}
	}
}
