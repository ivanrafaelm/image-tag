package br.org.roger.graphics.tag;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import com.onbarcode.barcode.EAN8;

public class EAN8Barcode implements EANBarcodeInterface {
	
	private EAN8 ean8 = new EAN8();

	@Override
	public void setData(String data) {
		ean8.setData(data);
	}

	@Override
	public void setShowText(Boolean showText) {
		ean8.setShowText(showText);
	}

	@Override
	public void setUom(Integer uom) {
		ean8.setUom(uom);
	}

	@Override
	public void setX(float x) {
		ean8.setX(x);
	}

	@Override
	public void setY(float y) {
		ean8.setY(y);
	}

	@Override
	public void setLeftMargin(float leftMargin) {
		ean8.setLeftMargin(leftMargin);
	}

	@Override
	public void setRightMargin(float rightMargin) {
		ean8.setRightMargin(rightMargin);
	}

	@Override
	public void setTopMargin(float topMargin) {
		ean8.setTopMargin(topMargin);
	}

	@Override
	public void setBottomMargin(float bottomMargin) {
		ean8.setBottomMargin(bottomMargin);
	}

	@Override
	public void setResolution(int resolution) {
		ean8.setResolution(resolution);
	}

	@Override
	public void drawBarcode(Graphics2D g, Rectangle rect) throws Exception {
		ean8.drawBarcode(g, rect);
	}

}
