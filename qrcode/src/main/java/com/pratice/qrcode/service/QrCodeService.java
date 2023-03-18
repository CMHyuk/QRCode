package com.pratice.qrcode.service;

import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.pratice.qrcode.entity.QrCode;
import com.pratice.qrcode.exception.QRCodeNotFound;
import com.pratice.qrcode.repository.QrCodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static com.google.zxing.BarcodeFormat.QR_CODE;

@Service
@RequiredArgsConstructor
public class QrCodeService {

    private final QrCodeRepository qrCodeRepository;

    public Long generateAndSaveQrCode(String text) {
        try {
            BitMatrix bitMatrix = new QRCodeWriter().encode(text, QR_CODE, 200, 200);

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            MatrixToImageWriter.writeToStream(bitMatrix, "PNG", outputStream);

            byte[] qrCodeImage = outputStream.toByteArray();

            QrCode qrCode = new QrCode();
            qrCode.setText(text);
            qrCode.setQrCodeImage(qrCodeImage);

            return qrCodeRepository.save(qrCode).getId();
        } catch (WriterException | IOException e) {
            // QR 코드 생성 실패
            throw new IllegalArgumentException();
        }
    }

    public byte[] getQrCodeImageById(Long id) {
        QrCode qrCode = qrCodeRepository.findById(id)
                .orElseThrow(QRCodeNotFound::new);

        return qrCode.getQrCodeImage();
    }

}

