package com.ACORobotics;

import com.pi4j.wiringpi.SoftPwm;

public class RobotMovement 
{
	GpioBuilder gpio = new GpioBuilder();
	static long TravelTime;
	
	public void movement(int Velocity, int time)
	{
		// MAY ADD ULTRASOUND IN HERE TOO? so that the robot movements constantly and checks ultrasound... more efficient electronics-wise(?)
		System.out.println("Speed: " + Velocity);
		System.out.println("Time: " + time);
		
		gpio.turnOnMotors();
		TravelTime = 0;

		int LEFT_Motor_Forward = 14;
		int RIGHT_Motor_Forward = 12;
		SoftPwm.softPwmCreate(LEFT_Motor_Forward, 0, 100);
		SoftPwm.softPwmCreate(RIGHT_Motor_Forward, 0, 100);

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
		gpio.turnOffMotors();
		gpio.end();
	}
}
