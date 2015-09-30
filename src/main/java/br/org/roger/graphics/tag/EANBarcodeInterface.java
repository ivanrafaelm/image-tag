package br.org.roger.graphics.tag;

import java.awt.Graphics2D;
import java.awt.Rectangle;

public interface EANBarcodeInterface {

	public void setData(String data);
	
	public void setShowText(Boolean showText);
	
	public void setUom(Integer uom);
	
	public void setX(float x);
	
	public void setY(float y);
	
	public void setLeftMargin(float leftMargin);
	
	public void setRightMargin(float rightMargin);
	
	public void setTopMargin(float topMargin);
	
	public void setBottomMargin(float bottomMargin);
	
	public void setResolution(int resolution);
	
	public void drawBarcode(Graphics2D g, Rectangle rect) throws Exception;
}
