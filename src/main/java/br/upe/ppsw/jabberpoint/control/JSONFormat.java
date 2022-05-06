package br.upe.ppsw.jabberpoint.control;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.google.gson.Gson;

import br.upe.ppsw.jabberpoint.model.Presentation;

public class JSONFormat implements IFilePresentationFormat {

	@Override
	public Presentation load(String fileName) {
		Path path = Paths.get(fileName);
		
		try (Reader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
			Gson gson = new Gson();
			Presentation presentationjson = gson.fromJson(reader, Presentation.class);
			return presentationjson;
			
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	
	}

	@Override
	public void save(Presentation presentation, String fileName) {
		
	}

	@Override
	public String getExtension() {
		return "json";
	}

	
	
}
