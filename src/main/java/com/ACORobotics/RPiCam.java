package com.ACORobotics;

import com.hopding.jrpicam.RPiCamera;
import com.hopding.jrpicam.enums.Exposure;
import com.hopding.jrpicam.exceptions.FailedToRunRaspistillException;

public class RPiCam 
{
	public static void CamTest()
	{
		System.out.println("Taking picture!");
		
		String directory = "/home/pi/John/";
		
		try
		{
			RPiCamera piCamera = new RPiCamera(directory);

			piCamera.setWidth(500).setHeight(500) // Set Camera to produce 500x500 images.
			.setBrightness(75)                // Adjust Camera's brightness setting.
			.setExposure(Exposure.AUTO)       // Set Camera's exposure.
			.setTimeout(2)                    // Set Camera's timeout.
			.setAddRawBayer(true);            // Add Raw Bayer data to image files created by Camera.
			
			piCamera.takeStill("testPicture.png");
			System.out.println("Picture taken! And saved in: " + directory);
		}
		catch (FailedToRunRaspistillException|java.io.IOException|InterruptedException e)
		{
			
			e.printStackTrace();
		}
	}
}
