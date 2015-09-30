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

			final HttpHeaders headers = new HttpHeaders();
			headers.setContentType(new MediaType("image", "jpeg"));			
			return new ResponseEntity(imageInByte, headers, HttpStatus.CREATED);
		} catch (IOException e) {
			
		} catch (FontFormatException e) {
			
		} catch (Exception e) {
			
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