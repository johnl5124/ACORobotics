package com.ACORobotics;

public class UltrasoundTest implements Runnable
{
	GpioBuilder gpio = new GpioBuilder(6, 23);
	
	public int ultrasoundDist() 
	{	
		int distance = 0;
		long start_time, end_time, rejection_1 = 1000, rejection_2 = 1000; //ns	
		
		try
		{
			Thread.sleep(1000);
			
			gpio.lowPin();
			Thread.sleep((long) 0.00002);
			
			gpio.highPin();
			Thread.sleep((long) 0.00001);
			
			gpio.lowPin();	
		}
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
		
		while (gpio.isLowPin())
		{
			try
			{
				Thread.sleep((long) 0.0000001);
				rejection_1++;
				
				gpio.end();
				if (rejection_1 == 100000) return -1;
			}
			catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
		}
		start_time = System.nanoTime();
		
		while (gpio.isHighPin())
		{
			try
			{
				Thread.sleep((long) 0.0000001);
				rejection_2++;
				
				gpio.end();
				if (rejection_2 == 100000) return -10;
			}
			catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
		}
		end_time = System.nanoTime();
		
		//dist in mm
		distance = (int) ((end_time - start_time) / 5882.35294118);
		
		gpio.end();
		
		return distance;
	}
	@Override
	public void run() 
	{
		System.out.println("Thread 2!");
	}

}
