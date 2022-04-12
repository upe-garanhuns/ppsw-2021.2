package br.upe.ppsw.jabberpoint.modelo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SlideTest {
	private Slide slideConstrutorVazio = null;
	private Slide slide = null;
	private SlideItem item0 = null;

	@BeforeEach
	private void init() {
		this.slideConstrutorVazio = new Slide();
		this.slide = new Slide("slide-teste");
		this.item0 = new SlideItem(0);
		this.slide.add(0, item0);
	}

	@Test
	public void testeConstrutorVazioIniciadoComSucesso() {
		Assertions.assertNotNull(slide.items, "verificando se array foi iniciado");
	}

	@Test
	public void testeConstrutorPreenchidoComTituloComSucesso() {
		Assertions.assertNotNull(slide.title, "Verificando se o titulo não é nulo");
	}

	@Test
	public void testeAppendSlideItemComSucesso() {
		Assertions.assertEquals(1, this.slide.getItemsSize(),
				"Verficiando se ao adicionar um slideItem, aumenta a quantidade");
	}

	@Test
	public void testeAddSlideItemComSucesso() {
		Assertions.assertEquals(1, this.slide.getItemsSize(),
				"Verificando se ao adicionar um slideItem com level, aumenta a quantidade");
	}

	@Test
	public void testeGetSlideItemComSucesso() {
		Assertions.assertEquals(item0, this.slide.getSlideItem(0), "Verificando se retorna o slide pedido");
	}

	@Test
	public void testeGetSlideItemsSizeComSucesso() {
		Assertions.assertEquals(1, this.slide.getItemsSize(), "Verificando se o tamanho da lista está certo");
	}

}
