package br.org.roger.graphics.tag;


public class EANBarcodeFactory {

	public static EANBarcodeInterface build(String code) {
		if (code.length() == 7) {
			return new EAN8Barcode();
		} else if (code.length() == 11) {
			return new EAN13Barcode();
		} else {
			throw new IllegalArgumentException("Product id must be 7 or 12 characters long");
		}
	}
}
