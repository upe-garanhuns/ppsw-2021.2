package br.upe.ppsw.jabberpoint.control;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;

import br.upe.ppsw.jabberpoint.model.Presentation;

public class JSONFormat implements IFilePresentationFormat {

	public Presentation load(String fileName) {
		Path path = Paths.get(fileName);

        try (Reader reader = Files.newBufferedReader(path)) {
            Gson gson = new Gson();
        
          
            Presentation template = gson.fromJson(reader, Presentation.class);
            template.setCurrentSlideNumber(0);
            return template;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
	}

	
	public void save(Presentation presentation, String fileName) {
		Path path = Paths.get(fileName);

        try (Writer writer = Files.newBufferedWriter(path)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            JsonElement file = gson.toJsonTree(presentation);

            gson.toJson(file, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
		
	}

	
	public String getExtension() {
		
		return "json";
	}

	
	
	
	
	
	
	
	
	
	
	
	
}
