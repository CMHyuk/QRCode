package com.pratice.qrcode.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Getter @Setter
public class QrCode {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String text;

    @Lob
    private byte[] qrCodeImage;

}


