package com.ACORobotics;

public class UltrasoundTest
{
	static GpioBuilder gpio = new GpioBuilder(6, 23);
	
	public int ultrasoundDist()
	{
		System.out.println("Reading ultrasound!!");
		
		int distance = 0;
		long start_time, end_time, rejection_1 = 1000, rejection_2 = 1000; //ns	
		
		try
		{
			gpio.turnOffPin();
			Thread.sleep((long) 0.00002);
			
			gpio.turnOnPin();
			Thread.sleep((long) 0.00001);
			
			gpio.turnOffPin();	
		}
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
		
		while (gpio.isLowPin())
		{
			System.out.println("Echo is low");
			/*try
			{
				Thread.sleep((long) 0.0000001);
				rejection_1++;
				
				if (rejection_1 == 100000) return -1;
			}
			catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
			*/
		}
		start_time = System.nanoTime();
		
		while (gpio.isHighPin())
		{
			System.out.println("Echo is high");
		}
		end_time = System.nanoTime();
		
		//dist in mm
		distance = (int) ((end_time - start_time) / 5882.35294118);
		
		gpio.end();
		
		return distance;
	}

}
