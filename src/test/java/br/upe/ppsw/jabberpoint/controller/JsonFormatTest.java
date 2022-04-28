package br.upe.ppsw.jabberpoint.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.upe.ppsw.jabberpoint.controle.JSONFormat;
import br.upe.ppsw.jabberpoint.modelo.Presentation;
import br.upe.ppsw.jabberpoint.modelo.PresentationDemo;

public class JsonFormatTest {
	private Presentation presentation = null;
	private JSONFormat jsonFormat = null;
	
	@BeforeEach
	private void init() {
		this.presentation = new PresentationDemo();
		this.jsonFormat = new JSONFormat();
	}
	
	@Test
	public void testeCriacaoArquivoJson() {
		this.jsonFormat.save(presentation, "src/main/resources/ppsw.json");
	}
}
