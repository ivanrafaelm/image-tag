package br.org.roger.graphics.controller;

import java.awt.FontFormatException;
import java.io.IOException;

import javax.validation.Valid;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import br.org.roger.graphics.model.ImageParametersJson;
import br.org.roger.graphics.tag.ImageTag;

/**
 * Generate price tag from GET request.
 * 
 * @author Roger
 *
 */
@Controller
@Configuration
@EnableAutoConfiguration
public class ImageController extends SpringBootServletInitializer {

    @RequestMapping(value = "/image", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity generateImage(@Valid final ImageParametersJson imageParameters) throws Exception {
    	System.out.println(imageParameters);
    	
    	ImageTag imageTag = new ImageTag();
    	
    	//TODO Se a largura do texto desenhado for maior que a largura da imagem, exibir mensagem.
    	
    	try {
	    	imageTag.addGraphicText()
	    		.withText(imageParameters.getItemDescription())
	    		.withFont(ImageTag.FontFile.FONT_BEBAS_NEUE)
	    		.withFontSize(20.0f)
	    		.withAlignment(ImageTag.Alignment.CENTER)
	    		.withYCoordinate(40);
	    	
	    	imageTag.addGraphicText()
	    		.withText("R$")
	    		.withFont(ImageTag.FontFile.FONT_BEATLES)
	    		.withFontSize(24.0f)
	    		.withXCoordinate(115)
	    		.withYCoordinate(95);
	    	
	    	imageTag.addGraphicText()
    			.withText(imageParameters.getItemPrice())
    			.withFont(ImageTag.FontFile.FONT_BEATLES)
    			.withFontSize(40.0f)
    			.withXCoordinate(140)
    			.withYCoordinate(105);
	    	
	    	imageTag.addGraphicText()
				.withText("R$")
				.withFont(ImageTag.FontFile.FONT_BEATLES)
				.withFontSize(12.0f)
				.withXCoordinate(40)
				.withYCoordinate(100);
	    	
	    	imageTag.addGraphicText()
				.withText(imageParameters.getItemOldPrice())
				.withFont(ImageTag.FontFile.FONT_BEATLES)
				.withFontSize(20.0f)
				.withXCoordinate(55)
				.withYCoordinate(105)
				.withStrikeOblique();
	    	
	    	imageTag.addGraphicBarcode()
	    		.withCode(imageParameters.getItemId());
	    	
	    	
	    	byte[] imageInByte = imageTag.generate();
    	/*
    	int width = 264;
    	int height = 176;
    	Color penColor = new Color(0x000000);
    	Color backgroundColor = new Color(0xffffff);
    	
    	BufferedImage img =
    		    new BufferedImage(width,
    		                      height,
    		                      BufferedImage.TYPE_INT_RGB);
    	Graphics2D g = img.createGraphics();
		g.setColor(backgroundColor);
		g.fillRect(0, 0, width-1, height-1);
		g.setColor(penColor);
		g.drawRect(0, 0, width-1, height-1);
		
		String text = imageParameters.getItemDescription();
		String price = imageParameters.getItemPrice();
		String oldPrice = imageParameters.getItemOldPrice();
		String fontFileName1 = "/fonts/Bebas Neue.ttf";
		float fontSize1 = 20.0f;
		String fontFileName2 = "/fonts/Beatles.ttf";
		float fontSize2 = 24.0f;
		float fontSize3 = 40.0f;
		float fontSize4 = 12.0f;
		float fontSize5 = 20.0f;
		
		try {
			//Descricao do produto
			Font font1 = Font.createFont(Font.TRUETYPE_FONT,
					getClass().getResourceAsStream(fontFileName1));
			font1 = font1.deriveFont(fontSize1);
			
			//Preco principal
			Font font2 = Font.createFont(Font.TRUETYPE_FONT,
					getClass().getResourceAsStream(fontFileName2));
			
			//TODO Se a largura do texto desenhado for maior que a largura da imagem, exibir mensagem.
			FontRenderContext fc = g.getFontRenderContext();
			Rectangle2D bounds = font1.getStringBounds(text, fc);
			int leftMargin = (264 - ((int) bounds.getWidth())) / 2;
			
			g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g.setFont(font1);
			g.drawString(text, leftMargin, 40);
			
			//Simbolo de moeda do preco principal
			font2 = font2.deriveFont(fontSize2);
			g.setFont(font2);
			g.drawString("R$", 115, 95);
			
			//Preco principal
			font2 = font2.deriveFont(fontSize3);
			g.setFont(font2);
			g.drawString(price, 140, 105);
			
			if (oldPrice != null) {
				//Simbolo de moeda do preco antigo
				font2 = font2.deriveFont(fontSize4);
				g.setFont(font2);
				g.drawString("R$", 40, 100);
				
				//Preco antigo
				font2 = font2.deriveFont(fontSize5);
				g.setFont(font2);
				g.drawString(oldPrice, 55, 105);
				
				g.drawLine(35, 110, 100, 90);
			}
			
			if (imageParameters.getItemId().length() == 7) { 
				EAN8 barcode = new EAN8();
				barcode.setData(imageParameters.getItemId());
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
			} else if (imageParameters.getItemId().length() == 12) {
				EAN13 barcode = new EAN13();
				barcode.setData(imageParameters.getItemId());
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
			
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(img, "jpg", baos);
			byte[] imageInByte = baos.toByteArray();
		*/

			final HttpHeaders headers = new HttpHeaders();
			headers.setContentType(new MediaType("image", "jpeg"));			
			return new ResponseEntity(imageInByte, headers, HttpStatus.CREATED);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (FontFormatException e) {
			e.printStackTrace();
//		} catch (Exception e) {
//			e.printStackTrace();
		}
		return null;
    }
    
    @RequestMapping(value = "/generate_tag.html", method = RequestMethod.GET)
    public ModelAndView generateTag() {
    	System.out.println("generateTag()");
        return new ModelAndView("generate_tag");
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(ImageController.class, args);
    }

    @Override
    protected final SpringApplicationBuilder configure(final SpringApplicationBuilder application) {
        return application.sources(ImageController.class);
    }
}