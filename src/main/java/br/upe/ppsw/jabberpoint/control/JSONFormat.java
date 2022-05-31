package br.upe.ppsw.jabberpoint.control;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;

import br.upe.ppsw.jabberpoint.model.Presentation;

public class JSONFormat implements IFilePresentationFormat {

	@Override
	public Presentation load(String fileName) {
		Path path = Paths.get(fileName);
		
		try (Reader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
			Gson gson = new Gson();
			Presentation presentation= gson.fromJson(reader, Presentation.class);
			return presentation;
			
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	
	}

	@Override
	public void save(Presentation presentation, String fileName) {
		Path path = Paths.get(fileName);

		try (Writer wr = Files.newBufferedWriter(path, StandardCharsets.UTF_8)) {
	        Gson gson = new GsonBuilder().create();
	        JsonElement json = gson.toJsonTree(presentation);      
	        gson.toJson(json, wr);
	        
		} catch (IOException e) {
			e.printStackTrace();
			
		}

	}

	@Override
	public String getExtension() {
		return "json";
	}
}