package com.ACORobotics;

import com.pi4j.wiringpi.SoftPwm;

public class RobotMovement implements Runnable
{
	GpioBuilder gpio = new GpioBuilder();
	static long TravelTime;
	
	public void Forward()
	{
		// i think i can remove 'time'... but we'll see
		
		int time = 2000, Velocity = 100;
				
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
	public void LeftTurn()
	{
		gpio.turnOnMotors();

		int time = 575;
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
		gpio.turnOffMotors();
	    gpio.end();
	}
	public void RightTurn()
	{
		gpio.turnOnMotors();

		int time = 575;
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
		gpio.turnOffMotors();
	    gpio.end();
	}
	@Override
	public void run() 
	{
		System.out.println("Moving forward for 2 secs!");
		
		Forward();		
	}
}
