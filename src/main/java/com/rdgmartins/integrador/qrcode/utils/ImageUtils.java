package com.rdgmartins.integrador.qrcode.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

public class ImageUtils {

    public static byte[] toByteArray(BufferedImage bi, String format)
            throws IOException {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(bi, format, baos);
        byte[] bytes = baos.toByteArray();
        return bytes;

    }

    public static BufferedImage toBufferedImage(byte[] bytes)
            throws IOException {

        InputStream is = new ByteArrayInputStream(bytes);
        BufferedImage bi = ImageIO.read(is);
        return bi;

    }

    public static boolean saveFile(String location, String format,  byte[] bytes) {
        try {
            ImageIO.write(toBufferedImage(bytes), format, new File(location));
        } catch (IOException e) {
            return false;
        }

        return true;
    }

    public static byte[] generateQRCodeImage(String content){
        QRCodeWriter barcodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = null;
        try {
            bitMatrix = barcodeWriter.encode(content, BarcodeFormat.QR_CODE, 360, 360);
            BufferedImage bi = MatrixToImageWriter.toBufferedImage(bitMatrix);
            return toByteArray(bi, "png");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
