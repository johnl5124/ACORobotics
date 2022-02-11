package com.ACORobotics;

import boofcv.abst.fiducial.QrCodeDetector;
import boofcv.alg.fiducial.qrcode.QrCode;
import boofcv.factory.fiducial.ConfigQrCode;
import boofcv.factory.fiducial.FactoryFiducial;
import boofcv.gui.feature.VisualizeShapes;
//import boofcv.gui.image.ShowImages;
import boofcv.io.UtilIO;
import boofcv.io.image.ConvertBufferedImage;
import boofcv.io.image.UtilImageIO;
import boofcv.struct.image.GrayU8;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;
import com.hopding.jrpicam.RPiCamera;
import com.hopding.jrpicam.enums.Exposure;
import com.hopding.jrpicam.exceptions.FailedToRunRaspistillException;

public class RPiCam 
{
	public static void takePhoto()
	{
		System.out.println("Taking picture!");
		
		String directory = "/home/pi/John/";
		
		try
		{
			RPiCamera piCamera = new RPiCamera(directory);

			piCamera.setWidth(500);
			piCamera.setHeight(500); // Set Camera to produce 500x500 images.
			piCamera.setBrightness(40);                // Adjust Camera's brightness setting.
			piCamera.setExposure(Exposure.AUTO);       // Set Camera's exposure.
			piCamera.setTimeout(2);                    // Set Camera's timeout.
			piCamera.setAddRawBayer(true);            // Add Raw Bayer data to image files created by Camera.
			
			piCamera.takeStill("testPicture.png");
			System.out.println("Picture taken! And saved in: " + directory);
		}
		catch (FailedToRunRaspistillException|java.io.IOException|InterruptedException e)
		{
			
			e.printStackTrace();
		}
	}
	public static void qrScan()
	{
		System.out.println("Scanning for QR!");
		
		BufferedImage input = UtilImageIO.loadImageNotNull(UtilIO.pathExample("/home/pi/John/testPicture.png"));
		GrayU8 gray = ConvertBufferedImage.convertFrom(input, (GrayU8)null);

		var config = new ConfigQrCode();
//		config.considerTransposed = false; // by default, it will consider incorrectly encoded markers. Faster if false
		QrCodeDetector<GrayU8> detector = FactoryFiducial.qrcode(config, GrayU8.class);

		detector.process(gray);

		// Gets a list of all the qr codes it could successfully detect and decode
		List<QrCode> detections = detector.getDetections();

		Graphics2D g2 = input.createGraphics();
		int strokeWidth = Math.max(4, input.getWidth()/200); // in large images the line can be too thin
		g2.setColor(Color.GREEN);
		g2.setStroke(new BasicStroke(strokeWidth));
		
		for (QrCode qr : detections) 
		{
			
			
			// The message encoded in the marker
			System.out.println("Message: '" + qr.message + "'");

			// Visualize its location in the image
			VisualizeShapes.drawPolygon(qr.bounds, true, 1, g2);
		}

		// List of objects it thinks might be a QR Code but failed for various reasons
		List<QrCode> failures = detector.getFailures();
		g2.setColor(Color.RED);
		for (QrCode qr : failures) 
		{
			System.out.println("No QR found...");
			
			// If the 'cause' is ERROR_CORRECTION or higher, then there's a decent chance it's a real marker
			if (qr.failureCause.ordinal() < QrCode.Failure.ERROR_CORRECTION.ordinal())
				continue;

			VisualizeShapes.drawPolygon(qr.bounds, true, 1, g2);
		}

		//ShowImages.showWindow(input, "Example QR Codes", true);
	}
}
