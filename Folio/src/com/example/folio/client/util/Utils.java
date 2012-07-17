package com.example.folio.client.util;

import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Image;

public class Utils {

	
	public static void scaleImage(AbsolutePanel container, Image image, Dimension area) {
		float blockRatio = (float)area.width/(float)area.height;
		int imgHeight = image.getHeight();
		int imgWidth = image.getWidth();
		float imgRatio = (float)imgWidth/(float)imgHeight;
		
		int resultWidth = 0;
		int resultHeight = 0;
		if (blockRatio > imgRatio) {
			resultWidth = area.width;
			resultHeight = (int) ((float)imgHeight * ((float)resultWidth/(float)imgWidth));
		} else {
			resultHeight = area.height;
			resultWidth = (int) ((float)imgWidth * ((float)resultHeight/(float)imgHeight));
		}
		image.setPixelSize(resultWidth, resultHeight);
		
		int xOffset = 0;
		int yOffset = 0;
		if (resultWidth > area.width) {
			xOffset = (resultWidth - area.width) / 2;
		}
		if (resultHeight > area.height) {
			yOffset = (resultHeight - area.height) / 2;
		}
		
		container.setWidgetPosition(image, -xOffset, -yOffset);
	}
	
}
