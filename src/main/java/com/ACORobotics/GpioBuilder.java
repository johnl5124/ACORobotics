package com.ACORobotics;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.RaspiPin;

public class GpioBuilder
{
	final GpioController gpio = GpioFactory.getInstance();
	private GpioPinDigitalOutput motors;
	
	public GpioBuilder()
	{
		// Movement CONSTRUCTOR
		motors = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_25, "m1E");	
	}
	void turnOnMotors()
	{
		motors.high();
	}
	void turnOffMotors()
	{
		motors.low();
	}
	void end()
	{
		System.out.println("Shutting down");
		gpio.shutdown();
	}
}

//public GpioBuilder(int echo, int trig)
//{	
//	// Ultrasound CONSTRUCTOR
//	
//	trigPin = gpio.provisionDigitalOutputPin(RaspiPin.getPinByAddress(trig), PinState.LOW);	
//	echoPin = gpio.provisionDigitalInputPin(RaspiPin.getPinByAddress(echo), PinPullResistance.PULL_DOWN);
//}
//void getEchoPinState()
//{
//	echoPin.getState();
//}
//void getTrigPinState()
//{
//	trigPin.getState();
//}
//boolean isEchoPinLow()
//{
//	echoPin.isLow();
//	return false;
//}
//boolean isEchoPinHigh()
//{
//	echoPin.isHigh();
//	return true;
//}
//void highTrigPin()
//{
//	trigPin.high();
//}
//void lowTrigPin()
//{
//	trigPin.low();
//}
