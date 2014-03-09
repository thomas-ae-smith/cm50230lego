package com.taes22;
import lejos.nxt.Button;
import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import lejos.nxt.TouchSensor;
import lejos.nxt.UltrasonicSensor;
import lejos.robotics.Touch;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.navigation.Move;
import lejos.robotics.navigation.MoveListener;
import lejos.robotics.navigation.MoveProvider;
import lejos.robotics.objectdetection.Feature;
import lejos.robotics.objectdetection.FeatureDetector;
import lejos.robotics.objectdetection.FeatureListener;
import lejos.robotics.objectdetection.FusorDetector;
import lejos.robotics.objectdetection.RangeFeatureDetector;
import lejos.robotics.objectdetection.TouchFeatureDetector;

public class Titch implements FeatureListener, MoveListener {


	DifferentialPilot pilot;
	FusorDetector sensors;
	UltrasonicSensor sonar;
	Touch leftT, rightT;
	boolean suppressed;
	
	Titch() {
		
		pilot = new DifferentialPilot(5.5f, 9.0f, Motor.A, Motor.B);
		pilot.setTravelSpeed(15);		//half the default
		
		sonar = new UltrasonicSensor(SensorPort.S1);

		rightT = new TouchSensor(SensorPort.S2);
		leftT = new TouchSensor(SensorPort.S3);

		sensors = new FusorDetector();
//		sensors.addDetector(new RangeFeatureDetector(sonar, 60, 200));
		sensors.addDetector(new TouchFeatureDetector(rightT, 4, 3));
		sensors.addDetector(new TouchFeatureDetector(leftT, -4, 3));

		sensors.addListener(this);
		pilot.addMoveListener(this);

	}

	public void go() {
		pilot.travel(100, true);
		System.out.println(" "+pilot.getMovement().getDistanceTraveled());
		Button.waitForAnyPress();
	}

	public static void main(String[] args) {
		Titch traveler = new Titch();
		traveler.go();
	}

	@Override
	public void featureDetected(Feature feature, FeatureDetector detector) {
		suppressed = true;
		System.out.println("Level1");
		pilot.travelArc(20, -20);
		System.out.println(feature.getRangeReading().getAngle());
		
	}

	@Override
	public void moveStarted(Move event, MoveProvider mp) {
		// TODO Auto-generated method stub
		System.out.println("Started");
		
	}

	@Override
	public void moveStopped(Move event, MoveProvider mp) {
		if (suppressed) {
			suppressed = !suppressed;
			System.out.println("Suppressed");
			return;
		}
		System.out.println("Level0");
//		pilot.arc(40, 360, true);
		pilot.forward();

		System.out.println("finished");
		
	}
}

