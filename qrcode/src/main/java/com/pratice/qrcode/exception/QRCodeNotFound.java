package com.pratice.qrcode.exception;

public class QRCodeNotFound extends Exception {

    private static final String MESSAGE = "QRCode가 존재하지 않습니다.";

    public QRCodeNotFound() {
        super(MESSAGE);
    }

    @Override
    public int getStatus() {
        return 404;
    }
}
