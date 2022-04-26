package br.upe.ppsw.jabberpoint.apresentacao.controller;

import java.util.HashMap;

import br.upe.ppsw.jabberpoint.apresentacao.model.IData;

public class TemplatePresentation {

	private HashMap<String,IData> templates = new HashMap<String,IData>();
	
	
	public TemplatePresentation() {
		templates.put(null, null);
	}

}
