package br.upe.ppsw.jabberpoint.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.upe.ppsw.jabberpoint.controle.HTMLFormat;
import br.upe.ppsw.jabberpoint.modelo.Presentation;
import br.upe.ppsw.jabberpoint.modelo.PresentationDemo;

public class HTMLFormatTest {
	private Presentation presentation = null;
	private HTMLFormat htmlformat = null;
	
	@BeforeEach
	private void init() {
		this.presentation = new PresentationDemo();
		this.htmlformat = new HTMLFormat();
	}
	
	@Test
	public void testeCriacaoArquivoHtml() {
		this.htmlformat.save(presentation, "src/main/resources/ppsw.html");
	}
}
