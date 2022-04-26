package br.upe.ppsw.jabberpoint.apresentacao.model;

public interface IData {

	public void save(Presentation presentation, String filename);
	public void load(Presentation presentation, String filename);
	public String getExtension();
	
}
