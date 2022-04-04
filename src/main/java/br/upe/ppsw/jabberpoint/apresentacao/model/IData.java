package br.upe.ppsw.jabberpoint.apresentacao.model;

public interface IData {
	
	public String supportedExtension();
	
	public Presentation load(String file);
	
	public void save(Presentation presentation, String file);
	
}
