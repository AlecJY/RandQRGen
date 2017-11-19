package com.alebit.randqrgen

import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel
import com.google.zxing.qrcode.encoder.Encoder
import java.awt.image.BufferedImage
import java.io.File
import java.util.*
import javax.imageio.ImageIO

fun main(args: Array<String>) {
    val rand = Random();
    val data = ByteArray(20);
    for (i in 0..9 ) {
        rand.nextBytes(data);
        val qr = Encoder.encode(String(data), ErrorCorrectionLevel.L);
        val image = BufferedImage(400, 240, BufferedImage.TYPE_INT_RGB);
        for (j in 0..qr.matrix.width-1) {
            for (k in 0..qr.matrix.height-1) {
                image.setRGB(j * 2 + getCentral(qr.matrix.width, image.width), k * 2 + getCentral(qr.matrix.height, image.height), 0xFFFFFF * qr.matrix[j, k]);
                image.setRGB(j * 2 + getCentral(qr.matrix.width, image.width) + 1, k * 2 + getCentral(qr.matrix.height, image.height), 0xFFFFFF * qr.matrix[j, k]);
                image.setRGB(j * 2 + getCentral(qr.matrix.width, image.width), k * 2 + getCentral(qr.matrix.height, image.height) + 1, 0xFFFFFF * qr.matrix[j, k]);
                image.setRGB(j * 2 + getCentral(qr.matrix.width, image.width) + 1, k * 2 + getCentral(qr.matrix.height, image.height) + 1, 0xFFFFFF * qr.matrix[j, k]);
            }
        }
        for (j in 0..image.width-1) {
            for (k in 0..image.height-1) {
                image.setRGB(j, k, image.getRGB(j, k) xor 0xFFFFFF);
            }
        }
        ImageIO.write(image, "PNG", File("D:\\" + i + ".png"));
    }
}

fun getCentral(qrl: Int, il: Int): Int {
    return il/2 - qrl;
}