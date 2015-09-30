package br.org.roger.graphics.tag;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import com.google.common.collect.Lists;

public class ImageTag {
	
	private static final String IMAGE_TYPE_JPEG = "jpg";
	private static final int DEFAULT_HEIGHT = 176;
	private static final int DEFAULT_WIDTH = 264;
	
	private Integer width;
	private Integer height;
	
	private List<GraphicText> graphicTexts = Lists.newArrayList();
	private EANBarcode eanBarcode;
	
	private BufferedImage img;
	private Graphics2D g;
	
	public ImageTag() {
		this(DEFAULT_WIDTH, DEFAULT_HEIGHT, 0x000000, 0xffffff);
	}
	
	public ImageTag(int width, int height) {
		this(width, height, 0x000000, 0xffffff);
	}
	
	public ImageTag(int width, int height, int penColor, int backgroundColor) {
		img = new BufferedImage(width,
				height,
    		    BufferedImage.TYPE_INT_RGB);
		g = img.createGraphics();
		g.setColor(new Color(backgroundColor));
		g.fillRect(0, 0, width-1, height-1);
		g.setColor(new Color(penColor));
		g.drawRect(0, 0, width-1, height-1);
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		this.width = width;
		this.height = height;
	}
	
	public GraphicText addGraphicText() {
		GraphicText graphicText = new GraphicText();
		
		graphicTexts.add(graphicText);
		
		return graphicText;
	}
	
	public EANBarcode addGraphicBarcode() {
		if (this.eanBarcode != null) {
			throw new IllegalStateException("Only one barcode per tag.");
		}
		
		EANBarcode eanBarcode = new EANBarcode();
		
		this.eanBarcode = eanBarcode;
		
		return eanBarcode;
	}

	public byte[] generate() throws Exception {
		
		for (GraphicText graphicText : graphicTexts) {
			graphicText.buildGraphicText(g);
			g.setFont(graphicText.getFont());
			g.drawString(graphicText.getText(), graphicText.getxCoordinate(), graphicText.getyCoordinate());
		}
		
		eanBarcode.buildGraphicBarcode(g);
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write(img, IMAGE_TYPE_JPEG, baos);
		
		return baos.toByteArray();
	}
	
	public class GraphicText {
		
		public static final String FONT_BEBAS_NEUE = "/fonts/Bebas Neue.ttf";
		public static final String FONT_BEATLES = "/fonts/Beatles.ttf";
		public static final String FONT_AVANTAGE_CONDENSED = "/fonts/AvantageCondensed Bold.ttf";
		
		private String text;
		private FontFile fontFile;
		private Float fontSize;
		private Font font;
		private Alignment alignment = Alignment.LEFT;
		private Integer xCoordinate;
		private Integer yCoordinate;
		private Boolean strikeOblique = false;
		
		public GraphicText() {
			
		}
		
		public GraphicText withText(String text) {
			this.text = text;
			return this;
		}
		
		public GraphicText withFont(FontFile fontFile) {
			this.fontFile = fontFile;
			return this;
		}
		
		public GraphicText withFontSize(Float fontSize) {
			this.fontSize = fontSize;
			return this;
		}
		
		public GraphicText withAlignment(Alignment alignment) {
			this.alignment = alignment;
			return this;
		}
		
		public GraphicText withXCoordinate(Integer xCoordinate) {
			this.xCoordinate = xCoordinate;
			return this;
		}
		
		public GraphicText withYCoordinate(Integer yCoordinate) {
			this.yCoordinate = yCoordinate;
			return this;
		}
		
		public GraphicText withStrikeOblique() {
			this.strikeOblique = true;
			return this;
		}
		
		public void buildGraphicText(Graphics2D g) throws FontFormatException, IOException {
			font = Font.createFont(Font.TRUETYPE_FONT,
					getClass().getResourceAsStream(fontFile.getFontFileName()));
			font = font.deriveFont(fontSize);
			
			FontRenderContext fc = g.getFontRenderContext();
			Rectangle2D bounds = font.getStringBounds(text, fc);
			
			if (Alignment.CENTER.equals(alignment)) {
				xCoordinate = (width - ((int) bounds.getWidth())) / 2;
			}
			
			if (strikeOblique) {
				int x1 = xCoordinate;
				int y1 = yCoordinate;
				int x2 = xCoordinate + Double.valueOf(bounds.getWidth()).intValue();
				int y2 = yCoordinate - Double.valueOf(bounds.getHeight()-0.3*bounds.getHeight()).intValue();

				g.drawLine(x1, y1, x2, y2);
			}
		}

		public String getText() {
			return text;
		}

		public void setText(String text) {
			this.text = text;
		}

		public Float getFontSize() {
			return fontSize;
		}

		public void setFontSize(Float fontSize) {
			this.fontSize = fontSize;
		}

		public Font getFont() {
			return font;
		}

		public void setFont(Font font) {
			this.font = font;
		}

		public Integer getxCoordinate() {
			return xCoordinate;
		}

		public void setxCoordinate(Integer xCoordinate) {
			this.xCoordinate = xCoordinate;
		}

		public Integer getyCoordinate() {
			return yCoordinate;
		}

		public void setyCoordinate(Integer yCoordinate) {
			this.yCoordinate = yCoordinate;
		}

		public Boolean getStrikeOblique() {
			return strikeOblique;
		}

		public void setStrikeOblique(Boolean strikeOblique) {
			this.strikeOblique = strikeOblique;
		}
	}
	
	public enum FontFile {
		FONT_BEBAS_NEUE("/fonts/Bebas Neue.ttf"),
		FONT_BEATLES("/fonts/Beatles.ttf"),
		FONT_AVANTAGE_CONDENSED("/fonts/AvantageCondensed Bold.ttf");
		
		private String fontFileName;
		
		FontFile(String fontFileName) {
			this.fontFileName = fontFileName;
		}
		
		public String getFontFileName() {
			return this.fontFileName;
		}
	}
	
	public enum Alignment {
		LEFT, CENTER, RIGHT;
	}
}
