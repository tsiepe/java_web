package net.siepe.thomas.gettingstarted;

import lejos.nxt.Button;
import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import lejos.nxt.UltrasonicSensor;

public class Grounded {

	public static void main(String[] args) throws InterruptedException {
		int distance = 255; // indicates error
		UltrasonicSensor ultraSensor = new UltrasonicSensor(SensorPort.S2);

		for (;;) {
			if (0 == ultraSensor.continuous()) {
				distance = ultraSensor.getDistance();

				if (distance != 255 && distance < 30) {
					Motor.A.stop();
					Motor.C.stop();
					Motor.A.backward();
					Motor.C.forward();
				} else {
					Motor.A.forward();
					Motor.C.forward();
				}
			}

			if (Button.ESCAPE.isDown()) {
				Motor.A.stop();
				Motor.C.stop();

				break;
			}

			Thread.sleep(10);
		}
	}
}
