package br.upe.ppsw.jabberpoint.apresentacao.controller;

import java.util.HashMap;
import java.util.List;

import br.upe.ppsw.jabberpoint.apresentacao.model.IData;
import br.upe.ppsw.jabberpoint.apresentacao.model.Presentation;

public class PresentationDataAssistant {
	private HashMap<String, IData> supportedExtensions;
	
	public List<String> supportedExtensions() {
		// TODO
		return null;
	}
	
	public Presentation create() {
		//TODO
		return null;
	}
	
	public Presentation load() {
		//TODO
		return null;
	}
	
	public void save(Presentation presentation, String file) {
		//TODO 
	}
	
	protected final IData getPresentationDataFromExtension(String file) {
		//TODO
		return null;
	}
	
	protected final String getExtension(String filename) {
		//TODO
		return null;
	}
	
	
}
