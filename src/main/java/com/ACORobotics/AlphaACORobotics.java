package com.ACORobotics;

import java.util.Arrays;

public class AlphaACORobotics
{
	static int[] node;
	static int turnCounter = 0, turnDecision = 0;
	static long time1, time2, traveltime;
	
	public static void main(String[] args) throws InterruptedException
	{		
		System.out.println("-------------- ACORobotics Test --------------");
		
		UltrasoundTest t1 = new UltrasoundTest("USThread");
		
		System.out.println("---------- Initial distance reading ----------");
		System.out.println("-------------------- " + t1.ultraSonic() + "mm" + " -------------------");
		System.out.println("----------------------------------------------");
		
		Thread.sleep(1000);
		
		RobotMovement t2 = new RobotMovement("MovementThread");
		
		// start time
		time1 = System.currentTimeMillis();
		while (turnCounter <= 3)
		{
			if (t1.ultraSonic() >= 150 && t1.ultraSonic() <= 1500)
			{
				t2.Forward();
				System.out.println("Distance = " + t1.ultraSonic() + "mm");
				Thread.sleep(350);
			}
			else if (t1.ultraSonic() >= 1500)
			{
				t1.stop();
				t2.stop();
				t1.shutdown();
				t2.shutdown();
				break;
			}
			else
			{
				// stop movement
				t2.shutdown();
				
				// record stop time
				time2 = System.currentTimeMillis();
				traveltime = (time2 - time1);
				
				System.out.println("Travel time: " + traveltime + " ms");
				Thread.sleep(500);
				
				turnCounter++;
				System.out.println("How many turns have I done? = " + turnCounter);
				
				node = new int[]{turnDecision, turnCounter, (int) traveltime};
				
				time1 = System.currentTimeMillis();
				traveltime = 0;
				if (turnDecision == 0)
				{
					// left turn
					t2.LeftTurn();
					
					turnDecision = 1;
				}
				else if (turnDecision == 1)
				{
					// right turn
					t2.RightTurn();
					
					turnDecision = 0;
				}
				Thread.sleep(250);
			}
		}
		System.out.println("----------------------------------------------");
		System.out.println("------------------ Exiting -------------------");
		
		System.out.println(Arrays.toString(node));
		
		
		// terminate threads and turn of GPIO ports
		t1.stop();
		t2.stop();
		t1.shutdown();
		t2.shutdown();

//		RPiCam.takePhoto();
//		RPiCam.qrScan();
		
	}
}
