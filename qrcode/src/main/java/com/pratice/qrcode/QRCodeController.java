package com.pratice.qrcode;

import com.google.zxing.WriterException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Base64;

@RestController
public class QRCodeController {

    @GetMapping("/qrcode/{content}")
    public ResponseEntity<byte[]> generateQRCode(@PathVariable String content, HttpServletResponse response) throws IOException, WriterException {
        String base64Image = QRCodeUtil.generateQRCode(content);
        byte[] imageBytes = Base64.getDecoder().decode(base64Image);

        response.setContentType(MediaType.IMAGE_PNG_VALUE);

        return ResponseEntity.ok().body(imageBytes);
    }
}

