package br.upe.ppsw.jabberpoint.apresentacao.controller;

import java.util.HashMap;


import br.upe.ppsw.jabberpoint.apresentacao.model.IData;
import br.upe.ppsw.jabberpoint.apresentacao.model.Presentation;

public class TemplatePresentation {

	private HashMap<String,IData> templates = new HashMap<String,IData>();
	
	
	public TemplatePresentation() {
		DemoPresentation demo = new DemoPresentation();
		templates.put(demo.getExtension(), demo);
		XMLPresentation xml = new XMLPresentation();
		templates.put(xml.getExtension(), xml);
	}

	public void load(Presentation presentation, String filename) throws Exception {
		getTemplate(filename).load(presentation, filename);
	}
	
	public void save(Presentation presentation, String filename) throws Exception{
		getTemplate(filename).save(presentation, filename);
	}
	
	public String getFileExtension(String fileName) {
        if (fileName == null) {
            throw new IllegalArgumentException("fileName must not be null!");
        }

        String extension = "";

        int index = fileName.lastIndexOf('.');
        if (index > 0) {
            extension = fileName.substring(index + 1);
        }

        return extension;

    }
	
	public IData getTemplate(String filename) {
		String extension = getFileExtension(filename);
		return templates.get(extension);
	}
}
