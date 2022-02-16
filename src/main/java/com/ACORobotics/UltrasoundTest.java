package com.ACORobotics;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.RaspiPin;

public class UltrasoundTest implements Runnable
{
	//GpioBuilder gpio = new GpioBuilder(6, 23);
	static GpioController gpio;
	static GpioPinDigitalInput EchoPin;
	static GpioPinDigitalOutput TrigPin;
	private String name;
	Thread t;
	private boolean exit;
	
	public UltrasoundTest(String threadName)
	{
		//int Echo = 6, int Trig = 23 
		
		// gpio instance
		gpio = GpioFactory.getInstance();
		
		// pins for ultrasound
		TrigPin = gpio.provisionDigitalOutputPin(RaspiPin.getPinByAddress(23));
		EchoPin = gpio.provisionDigitalInputPin(RaspiPin.getPinByAddress(6), PinPullResistance.PULL_DOWN);
	
		// thread creation
		name = threadName;
		t = new Thread(this, name);
		//System.out.println("New thread = " + name);
		exit = false;
		t.start();
	}
	
	public int ultraSonic()
	{
		int distance = 0;
		long start_time, end_time;
		
		try
			{			
				//gpio.lowTrigPin();
				TrigPin.low();
				Thread.sleep((long) 0.00002);
				
				//gpio.highTrigPin();
				TrigPin.high();
				Thread.sleep((long) 0.00001);
				
				//gpio.lowTrigPin();
				TrigPin.low();
			}
			catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
			
			while (EchoPin.isLow())
			{			
				//System.out.println("Low!");
				//break;
			}
			start_time = System.nanoTime();
			
			while (EchoPin.isHigh())
			{
				//System.out.println("High!");
				//break;
			}
			
			end_time = System.nanoTime();
			
			//dist in mm
			distance = (int) ((end_time - start_time) / 5882.35294118);
			
			gpio.shutdown();
			
			return distance;
	}

	public void stop()
	{
		exit = true;
	}
	public void shutdown()
	{
		gpio.shutdown();
	}
	@Override
	public void run() 
	{	
		ultraSonic();
	}
}
