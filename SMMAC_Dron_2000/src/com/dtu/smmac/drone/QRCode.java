package com.dtu.smmac.drone;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.ChecksumException;
import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeReader;

public class QRCode {
	
	private List<String> circleQR = new ArrayList<String>();

	public QRCode(int circleAmount) {
		for (int i = 1; i <= circleAmount; i++) {
			circleQR.add("P." + String.format("%02d", i));
		}
	}
	
    public String readQRCode(BufferedImage qrcodeImage) {
        Hashtable<DecodeHintType, Object> hintMap = new Hashtable<>();
        hintMap.put(DecodeHintType.TRY_HARDER, Boolean.TRUE);

        BufferedImageLuminanceSource source = new BufferedImageLuminanceSource(qrcodeImage);
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

        QRCodeReader reader = new QRCodeReader();
        Result result = null;

        try {
            result = reader.decode(bitmap, hintMap);
        } catch (NotFoundException e) {
            return "None";
        } catch (ChecksumException e) {
            return "Can't decode";
        } catch (FormatException e) {
            return "Unknow format";
        }

        return result != null ? result.getText() : "";
    }
    
    public boolean isCircle(BufferedImage image) {
    	for(String str : circleQR) {
    		if (str.equals(readQRCode(image))) 
        		return true;
    	}
    	
    	return false;
    }
    
}
