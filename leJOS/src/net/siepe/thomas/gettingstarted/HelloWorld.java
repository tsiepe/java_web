package net.siepe.thomas.gettingstarted;

import lejos.nxt.Button;
import lejos.nxt.Motor;

public class HelloWorld {
	public static void main(String[] args) {
		System.out.println("Hallo Bobbel!");
		Motor.A.forward();
		Button.waitForAnyPress();
		Motor.A.stop();
	}
}