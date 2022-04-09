package br.upe.ppsw.jabberpoint.model;

public class PresentationDefault extends Presentation {

	public PresentationDefault() {
		super();
		super.getSlides().add(new Slide("Add a title"));
		super.setCurrentSlideNumber(0);
	}
}
