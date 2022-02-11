package com.ACORobotics;

public class AlphaACORobotics
{
	static int[] node;
	static int turnCounter, turnDecision = 0;
	
	public static void main(String[] args) throws InterruptedException 
	{		
		System.out.println("Test of Movement, Camera and Ultrasound");

		RPiCam.takePhoto();
		//RPiCam.qrScan();
		
//		RobotMovement movementObj = new RobotMovement();
//		Thread movementThread = new Thread(movementObj);
//		movementThread.start();
//		
//		UltrasoundTest ultrasoundObj = new UltrasoundTest();
//		Thread ultrasoundThread = new Thread(ultrasoundObj);
//		ultrasoundThread.start();
		
//		UltrasoundTest test1 = new UltrasoundTest();
//		
//		if (test1.ultrasoundDist() == -1)
//		{
//			System.out.println("Pins are constantly low!");
//		}
//		else if (test1.ultrasoundDist() == -10)
//		{
//			System.out.println("Pins are constantly high!");
//		}
//		else 
//		{
//			System.out.println("Distance: " + test1.ultrasoundDist() + "mm");
//		}
	}
}
		
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

