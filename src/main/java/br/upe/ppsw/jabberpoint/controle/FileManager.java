package br.upe.ppsw.jabberpoint.controle;

import org.apache.commons.io.FilenameUtils;

public class FileManager {
	private String path;
	private IFilePresentationFormat file;
	
	public FileManager(String path) {
		this.path = path;
	}
	
	public void getFileFormat() {
		String extension = FilenameUtils.getExtension(path);
		FactoryFile factory = new FactoryFile(extension);
		this.file = factory.getFile();
	}
}
