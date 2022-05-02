package br.upe.ppsw.jabberpoint.control;

import java.util.HashMap;

import br.upe.ppsw.jabberpoint.model.Presentation;

public class FileManager {
	
	
	private HashMap<String, IFilePresentationFormat> formats = new HashMap<String, IFilePresentationFormat>();
	
	 public FileManager() {

			XMLFormat xml = new XMLFormat();
			formats.put(xml.getExtension(), xml);
		}

		public Presentation load( String filename) throws Exception {
			
			return getTemplate(filename).load(filename);
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
		
		public IFilePresentationFormat getTemplate(String filename) {
			String extension = getFileExtension(filename);
			return formats.get(extension);
		}
}
