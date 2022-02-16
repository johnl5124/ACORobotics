package com.ACORobotics;

import java.util.ArrayList;
import java.util.Arrays;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.ds.v4l4j.V4l4jDriver;

public class AlphaACORobotics
{
	static int[] node;
	static ArrayList<String> nodeList = new ArrayList<String>();
	static int turnCounter = 0, currentTurn = 0, nextTurn = 0;
	static long time1, time2, traveltime;
	
	static 
	{
	    Webcam.setDriver(new V4l4jDriver()); // this is important
	}
	
	public static void main(String[] args) throws InterruptedException
	{		
		System.out.println("-------------- ACORobotics Test --------------");
		


    	Webcam test = Webcam.getDefault();
    	
    	if (test != null) 
    	{
			System.out.println("Webcam: " + test.getName());
		} 
    	else 
    	{
			System.out.println("No webcam detected");
		}
		
		//RPiCam.takePhoto();
		//RPiCam.qrScan();
		
//		UltrasoundTest t1 = new UltrasoundTest("USThread");
//		
//		System.out.println("---------- Initial distance reading ----------");
//		System.out.println("-------------------- " + t1.ultraSonic() + "mm" + " -------------------");
//		System.out.println("----------------------------------------------");
//		
//		RobotMovement t2 = new RobotMovement("MovementThread");
//		
//		// start time
//		time1 = System.currentTimeMillis();
//		while (turnCounter < 3)
//		{
//			if (t1.ultraSonic() >= 175 && t1.ultraSonic() <= 1500)
//			{
//				t2.forward();
//				System.out.println("Distance = " + t1.ultraSonic() + "mm");
//				Thread.sleep(280);
//			}
//			else if (t1.ultraSonic() >= 1500)
//			{
//				t1.stop();
//				t2.stop();
//				t1.shutdown();
//				t2.shutdown();
//				break;
//			}
//			else
//			{
//				// stop movement
//				t2.shutdown();
//				
//				System.out.println("------------------ " + "TIME STATS" + " ------------------");
//				
//				// timer calculation
//				time2 = System.currentTimeMillis();
//				
//				System.out.println("TRAVEL TIME = " + traveltime);
//				
//				//time1 = System.currentTimeMillis();
//				
//				// turn counter increment 
//				turnCounter++;
//				
//				Thread.sleep(500);
//				
//				System.out.println("-------------------- " + "RESET" + " --------------------");
//				traveltime = 0;
//				
////				node = new int[]{turnCounter, currentTurn, (int) traveltime};
//				
//				// left turn
//				if (nextTurn == 0)
//				{								
//					// left turn is 0
//					t2.leftTurn();
//					System.out.println("Turn number " + turnCounter + " distance: " + t1.ultraSonic() + "mm");
//					Thread.sleep(2000);
//					
//					// turns left and detects if a wall is there...
//					if (t1.ultraSonic() <= 175)
//					{
//						
//						// turns 180 degrees
//						t2.rightTurn();
//						Thread.sleep(500);
//						t2.rightTurn();
//						Thread.sleep(1000);
//
//						System.out.println("Turn number " + turnCounter + " distance: " + t1.ultraSonic() + "mm");
//						Thread.sleep(1000);
//						
//						// detects if wall is there, if so then its a deadend
//						if (t1.ultraSonic() <= 175)
//						{
//							System.out.println("Deadend! Exiting...");
//							
//							t1.stop();
//							t2.stop();
//							t1.shutdown();
//							t2.shutdown();
//							break;
//						}
//						else
//						{
//							// THIS IS A RIGHT TURN IF WALL IS THERE ON LEFT TURN
//							
//							currentTurn = 1;
//							nextTurn = 0;
//				
//							traveltime = (time2 - time1);
//
//							// time starts again (?)
//							System.out.println("---------------- " + "TIME RESTARTS" + " ----------------");
//							time1 = System.currentTimeMillis();
//						}
//					}
//					else
//					{
//						// THIS IS A NORMAL LEFT TURN
//						
//						currentTurn = 0;
//						nextTurn = 1;
//						
//						traveltime = (time2 - time1);
//						// time starts again (?)
//						System.out.println("---------------- " + "TIME RESTARTS" + " ----------------");
//						time1 = System.currentTimeMillis();
//					}
//				}
//				// right turn...
//				else if (nextTurn == 1)
//				{
//					currentTurn = 1;
//					nextTurn = 0;
//					
//					// right turn
//					t2.rightTurn();
//					System.out.println("Turn number " + turnCounter + " distance: " + t1.ultraSonic() + "mm");
//					Thread.sleep(1000);
//					
//					traveltime = (time2 - time1);
//					// time starts again (?)
//					System.out.println("---------------- " + "TIME RESTARTS" + " ----------------");
//					time1 = System.currentTimeMillis();
//				}
//				node = new int[]{turnCounter, currentTurn, (int) traveltime};
//				
//				// arraylist to store the int array
//				nodeList.add(Arrays.toString(node));
//				Thread.sleep(250);
//			}
//		}
//			
//		System.out.println("----------------------------------------------");
//		System.out.println("------------------ Exiting -------------------");
//		
//		System.out.println("Node information = {turnCounter, turnDecision (0/1), traveltime (ms)}");
//		System.out.println(nodeList);
//		
//		// terminate threads and turn of GPIO ports
//		t1.stop();
//		t2.stop();
//		t1.shutdown();
//		t2.shutdown();
	}
}
