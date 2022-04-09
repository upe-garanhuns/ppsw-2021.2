package br.upe.ppsw.jabberpoint.modelo;

public class PresentationDefault extends Presentation {

	public PresentationDefault() {
		super();
		super.getSlides().add(new Slide("Add a title"));
		super.currentSlideNumber = 0;
	}
}
