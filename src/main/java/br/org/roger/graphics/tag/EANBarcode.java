package br.org.roger.graphics.tag;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import com.onbarcode.barcode.EAN13;
import com.onbarcode.barcode.EAN8;
import com.onbarcode.barcode.IBarcode;

public class EANBarcode {
	
	private String code;
	
	public EANBarcode withCode(String code) {
		this.code = code;
		return this;
	}

	public void buildGraphicBarcode(Graphics2D g) throws Exception {
		if (code.length() == 7) {
			EAN8 barcode = new EAN8();
			barcode.setData(code);
			barcode.setShowText(false);
			
			barcode.setUom(IBarcode.UOM_PIXEL);
			barcode.setX(3f);
			barcode.setY(30f);
			
			barcode.setLeftMargin(0f);
			barcode.setRightMargin(0f);
			barcode.setTopMargin(0f);
			barcode.setBottomMargin(0f);
			
			barcode.setResolution(72);
			
			barcode.drawBarcode(g, new Rectangle(2, 140, 180, 30));
		} else if (code.length() == 12) {
			EAN13 barcode = new EAN13();
			barcode.setData(code);
			barcode.setShowText(false);
			
			barcode.setUom(IBarcode.UOM_PIXEL);
			barcode.setX(2f);
			barcode.setY(30f);
			
			barcode.setLeftMargin(0f);
			barcode.setRightMargin(0f);
			barcode.setTopMargin(0f);
			barcode.setBottomMargin(0f);
			
			barcode.setResolution(72);
			
			barcode.drawBarcode(g, new Rectangle(15, 140, 180, 30));
		} else {
			throw new IllegalArgumentException("Product id must be 7 or 12 characters long");
		}
	}
	
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
}
