import java.io.IOException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;
import com.pi4j.wiringpi.SoftPwm;

public class Test_Alpha 
{
	static Test_Alpha Test1;
	private int Trig, Echo;
	
	private static GpioController gpio;
	private static GpioPinDigitalOutput TrigPin, PinA, PinB, PinC, PinD, turnOnMotors;
	private static GpioPinDigitalInput EchoPin;
	
	public static void main(String[] args) throws InterruptedException
	{		
		Test_Alpha Test1 = new Test_Alpha(6, 23);
		
		System.out.println("Test of Movement and Ultrasound");
		//setup();

		System.out.println("Initial ultrasonic reading: " + Test1.ultrasoundDist() + " mm");
		Thread.sleep(3000);
		System.out.println("Going!");

		while (true)
		{
			System.out.println("Distance: " + Test1.ultrasoundDist() + " mm");
			
			if (Test1.ultrasoundDist() > 250)
			{
				System.out.println("I'm safe!");
				WheelVelocity(75, 75, 350);
			}
			else if (Test1.ultrasoundDist() >= 150 && Test1.ultrasoundDist() <= 250)
			{
				System.out.println("Its getting dangerous... powering down slightly");
				WheelVelocity(50, 50, 350);
			}
			else
			{
				System.out.println("Theres a wall! I'm turning...");
				WheelVelocity(0, 100, 1200);
				//WheelVelocity(0, 100, 500);
			}
			Thread.sleep(100);
		}
	}
	public Test_Alpha(int Echo, int Trig)
	{
		gpio = GpioFactory.getInstance();
		
		// pins for ultrasound
		TrigPin = gpio.provisionDigitalOutputPin(RaspiPin.getPinByAddress(Trig));
		EchoPin = gpio.provisionDigitalInputPin(RaspiPin.getPinByAddress(Echo), PinPullResistance.PULL_DOWN);
		
		// pins for lights
		PinA = gpio.provisionDigitalOutputPin(RaspiPin.getPinByAddress(2));
		PinB = gpio.provisionDigitalOutputPin(RaspiPin.getPinByAddress(3));
		PinC = gpio.provisionDigitalOutputPin(RaspiPin.getPinByAddress(4));
		// doesn't exist
		//PinD = gpio.provisionDigitalOutputPin(RaspiPin.getPinByAddress(1));
		
		turnOnMotors = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_25, "m1E");	
	}
	public static void setup() 
	{
		System.out.println("Running Tests");

		int time = 2000;

		try 
		{
			System.out.println("LED A on");
			PinA.high();
		    Thread.sleep(time);
			System.out.println("LED B on");
			PinB.high();
		    Thread.sleep(time);
			System.out.println("LED C on");
			PinC.high();
		    Thread.sleep(time);
			System.out.println("LED D on");
			PinD.high();
		    Thread.sleep(time);
		    
			System.out.println("LED A off");
			PinA.low();
			System.out.println("LED B off");
			PinB.low();
			System.out.println("LED C off");
			PinC.low();
			System.out.println("LED D off");
			PinD.low();
			
		    Thread.sleep(time/2);
			//System.out.println("Turning on motor!");
			
			/*Below is the test for motors I'll use
			if (turnOnMotors.isHigh())
			{
				System.out.println("My motors are on!");
			}
			else
			{
				System.out.println("My motors are not on!");
			}
			*/
			
		    Thread.sleep(time);
		} 
		catch(InterruptedException e)
		{
		     System.out.println("Something went wrong!");
		}
		
		gpio.shutdown();
		System.out.println("Setup Test Completed!");
	}
	public static void WheelVelocity(int LeftVelocity, int RightVelocity, int time)
	{
		// motors
		//GpioPinDigitalOutput turnOnMotors = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_25, "m1E");	
		
		turnOnMotors.high();
		
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
		turnOnMotors.low();
	    gpio.shutdown();
		//gpio.unprovisionPin(turnOnMotors);
	}
	public int ultrasoundDist()
	{
		int distance = 0;
		long start_time, end_time, rejection_1 = 1000, rejection_2 = 1000; //ns	
		
		try
		{
			TrigPin.low();
			Thread.sleep((long) 0.00002);
			
			TrigPin.high();
			Thread.sleep((long) 0.00001);
			
			TrigPin.low();	
		}
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
		
		while (EchoPin.isLow())
		{
			//System.out.println("Echo is low");
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
		
		while (EchoPin.isHigh())
		{
			//System.out.println("Echo is high");
		}
		end_time = System.nanoTime();
		
		//dist in mm
		distance = (int) ((end_time - start_time) / 5882.35294118);
		
		gpio.shutdown();
		
		return distance;
	}
}
