package com.ACORobotics;

import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.wiringpi.SoftPwm;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;

public class RobotMovement implements Runnable
{
	final GpioController gpio = GpioFactory.getInstance();
	private GpioPinDigitalOutput motors;
	private int LEFT_Motor_Forward = 14, RIGHT_Motor_Forward = 12;
	private String name;
	Thread t;
	private boolean exit;
	
	public RobotMovement(String threadName)
	{
		motors = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_25, "m1E");
		
		name = threadName;
		t = new Thread(this, name);
		System.out.println("New thread = " + name);
		exit = false;
		t.start();
	}
	public void stop()
	{
		exit = true;
	}
	public void Forward()
	{
		int Velocity = 100;
				
		motors.high();

		SoftPwm.softPwmCreate(LEFT_Motor_Forward, 0, 100);
		SoftPwm.softPwmCreate(RIGHT_Motor_Forward, 0, 100);
		
		SoftPwm.softPwmWrite(LEFT_Motor_Forward, Velocity);
		SoftPwm.softPwmWrite(RIGHT_Motor_Forward, Velocity);		
	}
	public void LeftTurn()
	{
		motors.high();

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
		motors.low();
		gpio.shutdown();
	}
	public void RightTurn()
	{
		motors.high();

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
		motors.low();
		gpio.shutdown();
	}
	public void shutdown()
	{
		SoftPwm.softPwmWrite(LEFT_Motor_Forward, 0);
		SoftPwm.softPwmWrite(RIGHT_Motor_Forward, 0);
		
		System.out.println("Shutting down!");
		motors.low();
		gpio.shutdown();
	}
	@Override
	public void run() 
	{
		System.out.println("Moving forward");
		
		Forward();
	}
}
