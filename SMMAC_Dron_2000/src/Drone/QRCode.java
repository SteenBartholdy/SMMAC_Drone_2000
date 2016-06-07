package Drone;

import java.awt.image.BufferedImage;
import java.util.Hashtable;

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

    public String readQRCode(BufferedImage qrcodeImage) {
        Hashtable<DecodeHintType, Object> hintMap = new Hashtable<>();
        hintMap.put(DecodeHintType.TRY_HARDER, Boolean.TRUE);

        BufferedImageLuminanceSource source = new BufferedImageLuminanceSource(qrcodeImage);
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

        QRCodeReader reader = new QRCodeReader();
        Result result = null;

        try {
            result = reader.decode(bitmap, hintMap);
            System.out.println(result);
        } catch (NotFoundException e) {
            return "QR not found. Might have been partially detected but " +
                    "could not be confirmed.";
        } catch (ChecksumException e) {
            return "Successfully detected and decoded, but was not returned " +
                    "because its checksum feature failed.";
        } catch (FormatException e) {
            return "Detected, but some aspect did not conform to the format " +
                    "rules.";
        }

        return result != null ? result.getText() : "";
    }
}
