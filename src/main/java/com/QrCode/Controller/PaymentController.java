package com.QrCode.Controller;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.QrCode.Model.PaymentDetails;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
@Controller
@RequestMapping("/qr")
public class PaymentController {

	
	@PostMapping("/generate")
	public ResponseEntity<String> genrateQRCode(@RequestBody PaymentDetails paymentDetails) {
		
		try {
			BufferedImage bufferedImage = genrateQRCodeImage(paymentDetails);
			
			File output = new File(
					"C:\\Users\\dasud\\Documents\\workspace-spring-tool-suite-4-4.15.3.RELEASE\\QrCodeGenerator\\src\\main\\resources\\static\\"
							+ paymentDetails.getCustomerName() + ".jpg");
			
			ImageIO.write(bufferedImage, "jpg", output);
			
			
			
		} catch (WriterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<String>("SUCCESS",HttpStatus.CREATED);
	}

	public static BufferedImage genrateQRCodeImage(PaymentDetails qrPojo) throws WriterException {
		
		StringBuilder str = new StringBuilder();
		str = str.append("UPI ID:").append(qrPojo.getUpiId()).append("| |").append("CUSTOMER NAME:").append(qrPojo.getCustomerName())
				.append("| |").append("BANK NAME:").append(qrPojo.getBankName()).append("| |");
		
		QRCodeWriter codeWriter = new QRCodeWriter();
		
		BitMatrix bitMatrix = codeWriter.encode(str.toString(), BarcodeFormat.QR_CODE, 200, 200);
		
		return MatrixToImageWriter.toBufferedImage(bitMatrix);
	}

}
