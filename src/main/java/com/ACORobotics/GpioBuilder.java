package com.ACORobotics;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.RaspiPin;

public class GpioBuilder 
{
	GpioController gpio;
	GpioPinDigitalInput EchoPin;
	GpioPinDigitalOutput motors, TrigPin;
	int Echo, Trig;
	
	public GpioBuilder()
	{
		// Movement CONSTRUCTOR
		gpio = GpioFactory.getInstance();
		motors = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_25, "m1E");	
	}
	public GpioBuilder(int Echo, int Trig)
	{
		// Ultrasound CONSTRUCTOR
		gpio = GpioFactory.getInstance();
		
		TrigPin = gpio.provisionDigitalOutputPin(RaspiPin.getPinByAddress(Trig));
		EchoPin = gpio.provisionDigitalInputPin(RaspiPin.getPinByAddress(Echo), PinPullResistance.PULL_DOWN);
	}
	boolean isLowPin()
	{
		EchoPin.isLow();
		return false;
	}
	boolean isHighPin()
	{
		EchoPin.isHigh();
		return true;
	}
	void turnOnPin()
	{
		TrigPin.high();
	}
	void turnOffPin()
	{
		TrigPin.low();
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
		gpio.shutdown();
	}
}
