package com.taes22;

import lejos.nxt.Button;
import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import lejos.nxt.TouchSensor;
import lejos.nxt.UltrasonicSensor;

public class HelloNewWorld {
    /**
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("Hello New World!");
        UltrasonicSensor sonar = new UltrasonicSensor(SensorPort.S1);
        TouchSensor right = new TouchSensor(SensorPort.S2);
        TouchSensor left = new TouchSensor(SensorPort.S3);
        boolean quit = false;
        
        
//        while ( !right.isPressed () ) {
//        	System.out.println(" " + sonar.getDistance ( ));
//        }
        Motor.A.forward();
        Motor.B.forward();
//        while (sonar.getDistance() > 10) {}
        while (!left.isPressed() && !right.isPressed()) {}
        Motor.A.flt();
        Motor.B.flt();
        
//        while(!quit) {
//        	if (left.isPressed()) {
//        		System.out.println("Left");
//        	}
//        	if (right.isPressed()) {
//        		System.out.println("Right");
//        	}
//        }
//        Button.waitForAnyPress();
    }
}