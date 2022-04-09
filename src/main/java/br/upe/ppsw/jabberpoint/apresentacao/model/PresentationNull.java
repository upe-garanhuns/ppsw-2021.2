package br.upe.ppsw.jabberpoint.apresentacao.model;

public class PresentationNull extends Presentation {
	
	public int getSize() {
		return 0;
	}
	
	public String getTitle() {
		return "";
	}
	
	public int getSlideNumber() {
		return 0;
	}
	
	public Slide getSlide() {
		return null;
	}
	
	public Slide getCurrentSlide() {
		return null;
	}
	
}
