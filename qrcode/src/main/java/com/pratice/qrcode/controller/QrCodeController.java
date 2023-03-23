package com.pratice.qrcode.controller;

import com.google.zxing.WriterException;
import com.pratice.qrcode.service.QrCodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Base64;

import static org.springframework.http.MediaType.IMAGE_PNG_VALUE;
import static org.springframework.http.MediaType.TEXT_PLAIN_VALUE;

@RestController
@RequiredArgsConstructor
public class QrCodeController {

    private final QrCodeService qrCodeService;

    @GetMapping(value = "/qrcode/{id}", produces = IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> getQrCodeImageById(@PathVariable Long id) {
        byte[] qrCodeImage = qrCodeService.getQrCodeImageById(id);
        return ResponseEntity.ok(qrCodeImage);
    }

    @GetMapping(value = "/qrcode", produces = IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> generateAndSaveQrCode(@RequestParam String text) throws IOException, WriterException {
        Long id = qrCodeService.generateAndSaveQrCode(text);

        // ID에 해당하는 QR 코드 이미지를 가져옴
        byte[] qrCodeImage = qrCodeService.getQrCodeImageById(id);
        return ResponseEntity.ok(qrCodeImage);
    }

    @GetMapping(value = "/qrcode/base64", produces = TEXT_PLAIN_VALUE)
    public ResponseEntity<String> generateAndSaveQrCodeAsBase64(@RequestParam String text) throws IOException, WriterException {
        Long id = qrCodeService.generateAndSaveQrCode(text);

        // ID에 해당하는 QR 코드 이미지를 가져옴
        byte[] qrCodeImage = qrCodeService.getQrCodeImageById(id);

        // Base64 문자열로 변환하여 반환
        String base64Image = Base64.getEncoder().encodeToString(qrCodeImage);
        return ResponseEntity.ok(base64Image);
    }
}
