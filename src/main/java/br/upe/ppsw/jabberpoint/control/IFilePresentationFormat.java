
package br.upe.ppsw.jabberpoint.control;

import br.upe.ppsw.jabberpoint.model.Presentation;

public interface IFilePresentationFormat {

	Presentation load(String fileName);

	void save(Presentation presentation, String fileName);
	
	public String getExtension();

}
