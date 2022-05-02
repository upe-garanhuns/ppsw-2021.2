package br.upe.ppsw.jabberpoint.controle;

import java.util.HashMap;

import org.apache.commons.io.FilenameUtils;

import br.upe.ppsw.jabberpoint.modelo.Presentation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FileManager {
	private IFilePresentationFormat file;
	private HashMap<String, IFilePresentationFormat> templates = new HashMap<String, IFilePresentationFormat>();

	private static final String PCE = "Parser Configuration Exception";
	private static final String PCE_FILE_NOT_INFORMED = "File path was not informed.";
	private static final String PCE_PRESENTATION_NOT_INFORMED = "Presentation was not informed.";

	public FileManager() {
		XMLFormat xmlformat = new XMLFormat();
		templates.put("xml", xmlformat);

		HTMLFormat htmlformat = new HTMLFormat();
		templates.put("html", htmlformat);

		JSONFormat jsonformat = new JSONFormat();
		templates.put("json", jsonformat);
	}

	public Presentation load(String filePath) {
		String extension = FilenameUtils.getExtension(filePath);
		return getTemplate(extension).load(filePath);
	}

	public void save(Presentation presentation, String filePath) {
		String extension = FilenameUtils.getExtension(filePath);
		getTemplate(extension).save(presentation, filePath);
	}

	private IFilePresentationFormat getTemplate(String extension) {
		return templates.get(extension);
	}
}
