package com.pratice.qrcode;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.EnumMap;
import java.util.Map;

public class QRCodeUtil {

    private static final int WIDTH = 300;
    private static final int HEIGHT = 300;
    private static final String FORMAT = "png";

    public static String generateQRCode(String content) throws IOException, WriterException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(content, BarcodeFormat.QR_CODE, WIDTH, HEIGHT, getEncodeHintMap());

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(bitMatrix, FORMAT, stream);
        byte[] qrCodeBytes = stream.toByteArray();

        return Base64.getEncoder().encodeToString(qrCodeBytes);
    }

    private static Map<EncodeHintType, Object> getEncodeHintMap() {
        Map<EncodeHintType, Object> hints = new EnumMap<>(EncodeHintType.class);
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
        hints.put(EncodeHintType.MARGIN, 2);
        return hints;
    }

}

