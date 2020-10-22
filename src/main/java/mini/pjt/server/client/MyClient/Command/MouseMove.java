package mini.pjt.server.client.MyClient.Command;

import java.awt.Robot;

public class MouseMove {
  public static void randomMouse() {
    try {
      for (int i = 0; i < 100; i++) {
        Robot robot = new Robot();
        robot.mouseMove((int)(Math.random()*1720), (int)(Math.random()*880));
        Thread.sleep(200);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}