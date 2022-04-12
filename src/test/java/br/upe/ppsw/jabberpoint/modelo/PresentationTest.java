package br.upe.ppsw.jabberpoint.modelo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PresentationTest {
	private Presentation presentationConstrutorVazio = null;
	private Presentation presentationSetandoTitulo = null;
	private Slide slide0 = null;
	private Slide slide1 = null;
	private Slide slide2 = null;
	
	@BeforeEach
	private void init() {
		this.presentationConstrutorVazio = new Presentation();
		this.presentationSetandoTitulo = new Presentation("apresentacao-teste");
		this.slide0 = new Slide();
		this.slide1 = new Slide();
		this.slide2 = new Slide();
		
		this.presentationSetandoTitulo.add(slide0);
		this.presentationSetandoTitulo.add(slide1);
	}
	
	@Test
	public void testeClearPresentationComSucesso() {
		this.presentationConstrutorVazio.clear();
		Assertions.assertNotNull(presentationConstrutorVazio.getSlides(), "Os slides não deveriam estar nulos");
		Assertions.assertEquals(-1, presentationConstrutorVazio.getCurrentSlideNumber(), "O slide atual deveria ser -1");
	}
	
	@Test
	public void testePresentationComTituloPreenchido() {
		Assertions.assertEquals("apresentacao-teste", this.presentationSetandoTitulo.getTitle(), "O título não deve estar nulo");
	}
	
	@Test
	public void testeGetSlideSizeComSucesso() {
		Assertions.assertEquals(2, this.presentationSetandoTitulo.getSlidesSize(), "O tamanho do array deve ser 1");
	}
	
	@Test
	public void testePrevSlideComSucesso() {
		this.presentationSetandoTitulo.setCurrentSlideNumber(1);
		this.presentationSetandoTitulo.prevSlide();
		Assertions.assertEquals(0, presentationSetandoTitulo.getCurrentSlideNumber());
	}
	
	@Test
	public void testeNextSlideComSucesso() {
		this.presentationSetandoTitulo.setCurrentSlideNumber(0);
		this.presentationSetandoTitulo.nextSlide();
		Assertions.assertEquals(1, this.presentationSetandoTitulo.getCurrentSlideNumber());
	}
	
	@Test
	public void testeGetCurrentSlideComSucesso(){
		this.presentationSetandoTitulo.setCurrentSlideNumber(0);
		Assertions.assertEquals(slide0, this.presentationSetandoTitulo.getCurrentSlide());
	}
	
	@Test
	public void testeGetSlideComSucesso() {
		Assertions.assertEquals(slide1, this.presentationSetandoTitulo.getSlide(1));
	}
	
	@Test
	public void testeAddSlideComSucesso() {
		this.presentationSetandoTitulo.add(slide2);
		Assertions.assertEquals(slide2, this.presentationSetandoTitulo.getSlide(2));
	}
	
	@Test
	public void testeCurrentStatusComSucesso() {
		this.presentationSetandoTitulo.setCurrentSlideNumber(0);
		Assertions.assertEquals("Slide 1 de 2", this.presentationSetandoTitulo.currentStatus());
	}
	
	@Test
	public void testeIsValidComSucesso() {
		this.presentationSetandoTitulo.setCurrentSlideNumber(2);
		Assertions.assertEquals(true, this.presentationSetandoTitulo.isValid());
	}
	
}
