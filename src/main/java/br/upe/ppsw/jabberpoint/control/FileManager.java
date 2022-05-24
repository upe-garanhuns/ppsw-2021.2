package br.upe.ppsw.jabberpoint.control;

import java.util.HashMap;

import org.apache.commons.io.FilenameUtils;

import br.upe.ppsw.jabberpoint.model.Presentation;

public class FileManager {
	
	
	private HashMap<String, IFilePresentationFormat> formats = new HashMap<String, IFilePresentationFormat>();
	
	 public FileManager() {

			XMLFormat xml = new XMLFormat();
			formats.put(xml.getExtension(), xml);
			
			JSONFormat json = new JSONFormat();
			formats.put(json.getExtension(), json);
			
			HTMLFormat html = new HTMLFormat();
			formats.put(html.getExtension(), html);
			
			
		}

		public Presentation load( String filename) throws Exception {
			
			return getTemplate(filename).load(filename);
		}
		
		public void save(Presentation presentation, String filename) throws Exception{
			getTemplate(filename).save(presentation, filename);
		}
		
		
		
		public IFilePresentationFormat getTemplate(String filename) {
			String extension = FilenameUtils.getExtension(filename);
			return formats.get(extension);
		}
}
