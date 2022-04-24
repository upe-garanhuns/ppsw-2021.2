package br.upe.ppsw.jabberpoint.controle;

public class FactoryFile {
	private String extension;

	public FactoryFile(String extension) {
		this.extension = extension;
	}

	public IFilePresentationFormat getFile() {
		IFilePresentationFormat file = null;
		
		if (extension == "xml") {
			file = new XMLFormat();
		} else if (extension == "html") {
			file =  new HTMLFormat();
		} else if (extension == "json") {
			file = new JSONFormat();
		}
		
		return file;
	}
}
