package com.pratice.qrcode.repository;

import com.pratice.qrcode.entity.QrCode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QrCodeRepository extends JpaRepository<QrCode, Long> {
}
