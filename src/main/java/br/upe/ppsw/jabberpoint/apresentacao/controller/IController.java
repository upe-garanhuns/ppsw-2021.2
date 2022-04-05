package br.upe.ppsw.jabberpoint.apresentacao.controller;

import br.upe.ppsw.jabberpoint.apresentacao.model.Presentation;
import br.upe.ppsw.jabberpoint.apresentacao.view.SlideViewerComponent;

public interface IController {
		
	static void next(Presentation presentation, SlideViewerComponent slideViewerComponent) {
		presentation.nextSlide();
		slideViewerComponent.update();
	}
	
	static void prev(Presentation presentation, SlideViewerComponent slideViewerComponent) {
		presentation.prevSlide();
		slideViewerComponent.update();
	}
	
	static void goTo(Presentation presentation, SlideViewerComponent slideViewerComponent, int pageNumber) {
		presentation.setSlideNumber(pageNumber - 1);
		slideViewerComponent.update();
	}
	
	static void exitPresentation() {
		System.exit(0);
	}
	
}
