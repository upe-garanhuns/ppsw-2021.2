package br.upe.ppsw.jabberpoint.control;

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.io.FilenameUtils;


import br.upe.ppsw.jabberpoint.model.Presentation;

public class FileManager {
	
	private static Map<String, IFilePresentationFormat> fileFormato = new HashMap<String, IFilePresentationFormat>();
	
	public FileManager( ) {
		XMLFormat xml = new XMLFormat();
		fileFormato.put(xml.getExtension(), xml);
		JSONFormat json = new JSONFormat();
		fileFormato.put(json.getExtension(), json);
		HTMLFormat html = new HTMLFormat();
		fileFormato.put(html.getExtension(), html);
	}
	
	   public static  Presentation load(String fileName) {
		String extensionName =  FilenameUtils.getExtension(fileName); 
		return getTemplate(fileName).load(fileName);	
		}

	   public void save(Presentation presentation, String fileName) {
		String extensionName =  FilenameUtils.getExtension(fileName); 
		getTemplate(fileName).save(presentation, fileName);	
		}
	

		public static IFilePresentationFormat getTemplate(String filename) {
			String extension = FilenameUtils.getExtension(filename);
			return fileFormato.get(extension);
		}
	
}
