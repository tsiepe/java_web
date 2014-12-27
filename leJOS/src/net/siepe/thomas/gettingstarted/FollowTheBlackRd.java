package net.siepe.thomas.gettingstarted;

import lejos.nxt.Button;
import lejos.nxt.ColorSensor;
import lejos.nxt.LCD;
import lejos.nxt.Motor;
import lejos.nxt.SensorPort;

public class FollowTheBlackRd {

	/**
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {
		ColorSensor colorSensor = new ColorSensor(SensorPort.S1);
		String colorMsg = "Brightness %: ";

		OUTER: for (;;) {
			Motor.A.forward();
			Motor.C.forward();

			while (colorSensor.getLightValue() > 15) {
				Motor.C.stop();

				if (Button.ESCAPE.isDown()) {
					Motor.A.stop();

					break OUTER;
				}

				LCD.drawString(colorMsg, 0, 0);
				LCD.drawInt(colorSensor.getLightValue(), 2, colorMsg.length(),
						0);

				Thread.sleep(20);
			}

			if (Button.ESCAPE.isDown()) {
				Motor.A.stop();
				Motor.C.stop();

				break;
			}

			Thread.sleep(20);
		}
	}
}
