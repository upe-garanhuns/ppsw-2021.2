package br.upe.ppsw.jabberpoint.modelo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PresentationTest {
	private Presentation presentationConstrutorVazio = null;
	private Presentation presentation = null;
	private Slide slide0 = null;
	private Slide slide1 = null;
	private Slide slide2 = null;

	@BeforeEach
	private void init() {
		this.presentationConstrutorVazio = new Presentation();
		this.presentation = new Presentation("apresentacao-teste");
		this.slide0 = new Slide();
		this.slide1 = new Slide();
		this.slide2 = new Slide();

		this.presentation.add(slide0);
		this.presentation.add(slide1);
	}

	@Test
	public void testeClearPresentationComSucesso() {
		this.presentationConstrutorVazio.clear();
		Assertions.assertNotNull(presentationConstrutorVazio.getSlides(), "Os slides não deveriam estar nulos");
		Assertions.assertEquals(-1, presentationConstrutorVazio.getCurrentSlideNumber(),
				"O slide atual deveria ser -1");
	}

	@Test
	public void testePresentationComTituloPreenchido() {
		Assertions.assertEquals("apresentacao-teste", this.presentation.getTitle(), "O título não deve estar nulo");
	}

	@Test
	public void testeGetSlideSizeComSucesso() {
		Assertions.assertEquals(2, this.presentation.getSlidesSize(), "O tamanho do array deve ser 1");
	}

	@Test
	public void testePrevSlideComSucesso() {
		this.presentation.setCurrentSlideNumber(1);
		this.presentation.prevSlide();
		Assertions.assertEquals(0, presentation.getCurrentSlideNumber(), "O slide atual deve ser o 0");
	}

	@Test
	public void testeNextSlideComSucesso() {
		this.presentation.setCurrentSlideNumber(0);
		this.presentation.nextSlide();
		Assertions.assertEquals(1, this.presentation.getCurrentSlideNumber(), "O slide atual deve ser o 1");
	}

	@Test
	public void testeGetCurrentSlideComSucesso() {
		this.presentation.setCurrentSlideNumber(0);
		Assertions.assertEquals(slide0, this.presentation.getCurrentSlide(),
				"O slide deve ser o slide atual da presentation");
	}

	@Test
	public void testeGetSlideComSucesso() {
		Assertions.assertEquals(slide1, this.presentation.getSlide(1), "O slide deve ser o segundo da lista de slide");
	}

	@Test
	public void testeAddSlideComSucesso() {
		this.presentation.add(slide2);
		Assertions.assertEquals(slide2, this.presentation.getSlide(2),
				"O slide deve ser o que foi adicionado a presentation");
	}

	@Test
	public void testeCurrentStatusComSucesso() {
		this.presentation.setCurrentSlideNumber(0);
		Assertions.assertEquals("Slide 1 de 2", this.presentation.currentStatus(),
				"Deve retornar o current status da presentation");
	}

	@Test
	public void testeIsValidComSucesso() {
		this.presentation.setCurrentSlideNumber(2);
		Assertions.assertEquals(true, this.presentation.isValid(), "Deve verificar se uma presentation é válida");
	}

}
