package br.org.roger.graphics.tag;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import com.onbarcode.barcode.EAN13;

public class EAN13Barcode implements EANBarcodeInterface {
	
	private EAN13 ean13 = new EAN13();

	@Override
	public void setData(String data) {
		ean13.setData(data);
	}

	@Override
	public void setShowText(Boolean showText) {
		ean13.setShowText(showText);
	}

	@Override
	public void setUom(Integer uom) {
		ean13.setUom(uom);
	}

	@Override
	public void setX(float x) {
		ean13.setX(x);
	}

	@Override
	public void setY(float y) {
		ean13.setY(y);
	}

	@Override
	public void setLeftMargin(float leftMargin) {
		ean13.setLeftMargin(leftMargin);
	}

	@Override
	public void setRightMargin(float rightMargin) {
		ean13.setRightMargin(rightMargin);
	}

	@Override
	public void setTopMargin(float topMargin) {
		ean13.setTopMargin(topMargin);
	}

	@Override
	public void setBottomMargin(float bottomMargin) {
		ean13.setBottomMargin(bottomMargin);
	}

	@Override
	public void setResolution(int resolution) {
		ean13.setResolution(resolution);
	}

	@Override
	public void drawBarcode(Graphics2D g, Rectangle rect) throws Exception {
		ean13.drawBarcode(g, rect);
	}

}
