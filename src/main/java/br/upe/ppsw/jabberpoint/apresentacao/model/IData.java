package br.upe.ppsw.jabberpoint.apresentacao.model;


public interface IData {

	public void save(Presentation presentation, String filename) throws Exception;
	public void load(Presentation presentation, String filename) throws Exception;
	public String getExtension();
	
}
