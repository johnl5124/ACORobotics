package com.ACORobotics;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import com.hopding.jrpicam.RPiCamera;
import com.hopding.jrpicam.enums.Exposure;
import com.hopding.jrpicam.exceptions.FailedToRunRaspistillException;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;
import com.pi4j.wiringpi.SoftPwm;

public class AlphaACORobotics implements Runnable
{
	static GpioBuilder gpio = new GpioBuilder();
	int Trig, Echo;
	static int[] node;
	static int turnCounter, turnDecision = 0;
	static long time1 = 0, time2 = 0, TravelTime = 0;
	static GpioPinDigitalOutput PinA, PinB, PinC, PinD;
	
	public static void main(String[] args) throws InterruptedException 
	{		
		System.out.println("Test of Movement, Camera and Ultrasound");
		//CamTest();
		
		UltrasoundTest test1 = new UltrasoundTest();
		System.out.println("Distance: " + test1.ultrasoundDist() + "mm");
		
		//new Thread(() -> Movement(100, 5000)).start();

//		UltrasoundTest obj = new UltrasoundTest();
//		Thread thread = new Thread(obj);
//		thread.start();
		
//		System.out.println("Initial ultrasonic reading: " + Test1.ultrasoundDist() + " mm");
//		Thread.sleep(3000);
//		System.out.println("Going!");
//
//		time1 = System.currentTimeMillis();
//		while (turnCounter <= 3)
//		{
//			System.out.println("Distance: " + Test1.ultrasoundDist() + " mm");
//
//			if (Test1.ultrasoundDist() > 250 && Test1.ultrasoundDist() <= 850)
//			{
//				//System.out.println("I'm safe!");
//				Movement(100, 350);
//			}
//			else if (Test1.ultrasoundDist() >= 150 && Test1.ultrasoundDist() <= 250)
//			{
//				//System.out.println("Its getting dangerous... powering down slightly");
//				Movement(50, 350);
//			}
//			else if (Test1.ultrasoundDist() > 850)
//			{
//				System.out.println("END");
//				turnOnMotors.low();
//				gpio.shutdown();
//				break;
//			}
//			else
//			{
//				Movement(0, 350);
//				time2 = System.currentTimeMillis();
//				TravelTime = (time2 - time1);
//				//System.out.println("Theres a wall! I'm turning...");
//				System.out.println("Travel time: " + TravelTime + " mm");
//				time1 = System.currentTimeMillis();
//				turnCounter ++;
//				node = new int[]{turnDecision, turnCounter, (int) TravelTime};
//				System.out.println("How many turns have I done? = " + turnCounter);
//				TravelTime = 0;
//				if (turnDecision == 0)
//				{
//					LeftTurn();
//					Thread.sleep(2000);
//					if (Test1.ultrasoundDist() < 200)
//					{
//						System.out.println("Wall too close!");
//						RightTurn();
//						Thread.sleep(2000);
//						RightTurn();
//						
//						Thread.sleep(2000);
//
//						if (Test1.ultrasoundDist() < 200)
//						{
//							System.out.println("I'm in a deadend!");
//							turnOnMotors.low();
//							gpio.shutdown();
//							break;
//						}
//						else
//						{
//							turnDecision = 0;
//						}
//					}
//					else 
//					{
//						turnDecision = 1;
//					}
//				}
//				else if (turnDecision == 1)
//				{
//					RightTurn();
//					turnDecision = 0;
//				}
//			}
//			Thread.sleep(100);
//		}
//
//		/*System.out.print("Final: ");
//		for (int i = 0; i < node.length; i++) 
//		{
//			System.out.print(node[i]);
//			System.out.println(", ");
//		}
//		*/

		//turnOnMotors.low();
	    //gpio.shutdown();
	}
	public static void LeftTurn()
	{
		//turnOnMotors.high();

		int time = 650;
		int LEFT_Motor_Backward = 10;
		int RIGHT_Motor_Forward = 12;
		SoftPwm.softPwmCreate(LEFT_Motor_Backward, 0, 100);
		SoftPwm.softPwmCreate(RIGHT_Motor_Forward, 0, 100);
				
		if (time > 0)
		{
			try
			{
				SoftPwm.softPwmWrite(LEFT_Motor_Backward, 100);
				SoftPwm.softPwmWrite(RIGHT_Motor_Forward, 100);

				Thread.sleep(time);
			}
			catch (InterruptedException e) 
			{
			e.printStackTrace();
			}
		}
		else
		{
			System.out.println("Error time is too low");
		}

		SoftPwm.softPwmWrite(LEFT_Motor_Backward, 0);
		SoftPwm.softPwmWrite(RIGHT_Motor_Forward, 0);
		//turnOnMotors.low();
	    gpio.end();
	}
	public static void RightTurn()
	{
		//turnOnMotors.high();

		int time = 650;
		int LEFT_Motor_Forward = 14;
		int RIGHT_Motor_Backward = 13;
		SoftPwm.softPwmCreate(LEFT_Motor_Forward, 0, 100);
		SoftPwm.softPwmCreate(RIGHT_Motor_Backward, 0, 100);
				
		if (time > 0)
		{
			try
			{
				SoftPwm.softPwmWrite(LEFT_Motor_Forward, 100);
				SoftPwm.softPwmWrite(RIGHT_Motor_Backward, 100);
				Thread.sleep(time);
			}
			catch (InterruptedException e) 
			{
			e.printStackTrace();
			}
		}
		else
		{
			System.out.println("Error time is too low");
		}

		SoftPwm.softPwmWrite(LEFT_Motor_Forward, 0);
		SoftPwm.softPwmWrite(RIGHT_Motor_Backward, 0);
		//turnOnMotors.low();
	    gpio.end();
	}
	public static void Movement(int Velocity, int time)
	{
		// MAY ADD ULTRASOUND IN HERE TOO? so that the robot movements constantly and checks ultrasound... more efficient electronics-wise(?)

		gpio.turnOnMotors();
		TravelTime = 0;

		int LEFT_Motor_Forward = 14;
		int RIGHT_Motor_Forward = 12;
		SoftPwm.softPwmCreate(LEFT_Motor_Forward, 0, 100);
		SoftPwm.softPwmCreate(RIGHT_Motor_Forward, 0, 95);

		//long time1 = System.currentTimeMillis();

		if (time > 0)
		{
			try
			{
				SoftPwm.softPwmWrite(LEFT_Motor_Forward, Velocity);
				SoftPwm.softPwmWrite(RIGHT_Motor_Forward, Velocity);

				Thread.sleep(time);

				//long time2 = System.currentTimeMillis();
				//System.out.println("Time travelled: " + (time2 - time1) + " ms");
			}
			catch (InterruptedException e) 
			{
			e.printStackTrace();
			}
		}
		else
		{
			System.out.println("Error time is too low");
		}

		SoftPwm.softPwmWrite(LEFT_Motor_Forward, 0);
		SoftPwm.softPwmWrite(RIGHT_Motor_Forward, 0);
		//turnOnMotors.low();
	    gpio.end();
	}
	public static void WheelVelocity(int LeftVelocity, int RightVelocity, int time)
	{
		// motors
		//GpioPinDigitalOutput turnOnMotors = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_25, "m1E");	
		
		//turnOnMotors.high();
		
		// these are the correct pins!
		int LEFT_Motor_Forward = 14;
		int LEFT_Motor_Backward = 10;
		int RIGHT_Motor_Forward = 12;
		int RIGHT_Motor_Backward = 13;
		
		//pulse width modulation, from 0 to 100 (limits)
		SoftPwm.softPwmCreate(LEFT_Motor_Forward, 0, 100);
		SoftPwm.softPwmCreate(LEFT_Motor_Backward, 0, 100);
		SoftPwm.softPwmCreate(RIGHT_Motor_Forward, 0, 100);
		SoftPwm.softPwmCreate(RIGHT_Motor_Backward, 0, 100);
		
		// forwards
		if (LeftVelocity > 0 && RightVelocity > 0)
		{	
			SoftPwm.softPwmWrite(LEFT_Motor_Forward, LeftVelocity);
			SoftPwm.softPwmWrite(RIGHT_Motor_Forward, RightVelocity);
		}
		else if (LeftVelocity < 0 && RightVelocity < 0)
		{
			SoftPwm.softPwmWrite(LEFT_Motor_Backward, -LeftVelocity);
			SoftPwm.softPwmWrite(RIGHT_Motor_Backward, -RightVelocity);
		}
		else if (LeftVelocity > 0 && RightVelocity == 0)
		{
			SoftPwm.softPwmWrite(LEFT_Motor_Forward, LeftVelocity);
		}
		else if (LeftVelocity == 0 && RightVelocity > 0)
		{
			SoftPwm.softPwmWrite(RIGHT_Motor_Forward, RightVelocity);
		}
		
		if (time > 0)
		{
			try
			{
				Thread.sleep(time);
			}
			catch (InterruptedException e) 
			{
			e.printStackTrace();
			}
		}
		else
		{
			System.out.println("Error time is too low");
		}
		
		SoftPwm.softPwmWrite(LEFT_Motor_Forward, 0);
		SoftPwm.softPwmWrite(LEFT_Motor_Backward, 0);
		SoftPwm.softPwmWrite(RIGHT_Motor_Forward, 0);
		SoftPwm.softPwmWrite(RIGHT_Motor_Backward, 0);
		
		System.out.println("Stopping");
		//turnOnMotors.low();
		gpio.end();
		//gpio.unprovisionPin(turnOnMotors);
	}

//	public static void CamTest()
//	{
//		System.out.println("Taking picture!");
//		
//		String directory = "/home/pi/John/CamTests";
//		
//		try
//		{
//			RPiCamera piCamera = new RPiCamera(directory);
//
//			piCamera.setWidth(500).setHeight(500) // Set Camera to produce 500x500 images.
//			.setBrightness(75)                // Adjust Camera's brightness setting.
//			.setExposure(Exposure.AUTO)       // Set Camera's exposure.
//			.setTimeout(2)                    // Set Camera's timeout.
//			.setAddRawBayer(true);            // Add Raw Bayer data to image files created by Camera.
//			
//			piCamera.takeStill("testPicture.png");
//			System.out.println("Picture taken! And saved in: " + directory);
//		}
//		catch (FailedToRunRaspistillException|java.io.IOException|InterruptedException e)
//		{
//			
//			e.printStackTrace();
//		}
//	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
}
