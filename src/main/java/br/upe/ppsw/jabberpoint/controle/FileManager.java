package br.upe.ppsw.jabberpoint.controle;

import org.apache.commons.io.FilenameUtils;
import org.springframework.util.StringUtils;
import org.w3c.dom.Node;

import br.upe.ppsw.jabberpoint.base.JabberPointException;
import br.upe.ppsw.jabberpoint.modelo.Presentation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FileManager {
	private IFilePresentationFormat file;
	
	private static final String PCE = "Parser Configuration Exception";
	private static final String PCE_FILE_NOT_INFORMED = "File path was not informed.";
	private static final String PCE_PRESENTATION_NOT_INFORMED = "Presentation was not informed.";

	public Presentation load(String filePath) {
		Presentation presentation = null;
	
		validateObject(filePath, "The file path was not informed.", PCE_FILE_NOT_INFORMED);
		getInstanceFile(filePath);
		
		presentation = file.load(filePath);
		return presentation;
	}
	
	public void save(Presentation presentation, String filePath ) {
		validateObject(presentation, "Could not save presentation, the presentation is null",
				PCE_PRESENTATION_NOT_INFORMED);

		validateObject(presentation, "Could not save presentation, the presentation file name is null",
				PCE_FILE_NOT_INFORMED);
		
		getInstanceFile(filePath);
		file.save(presentation, filePath);
	}
	
	private void getInstanceFile(String filePath) {
		String extension = FilenameUtils.getExtension(filePath);
		FactoryFile factory = new FactoryFile(extension);
		this.file = factory.getFile();		
	}
	
	private void validateObject(Object object, String logMessage, String systemMessage) {
		if (object == null || object instanceof Node && !StringUtils.hasText(((Node) object).getTextContent())) {
			throwException(logMessage, systemMessage);
		}
	}
	
	private void throwException(String logMessage, String systemMessage) {
		log.error(logMessage);
		throw new JabberPointException(PCE + " - " + systemMessage);
	}
}
