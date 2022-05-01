/**
 * <p>
 * UPE - Campus Garanhuns Curso de Engenharia de Software Disciplina de Padrões de Projeto de
 * Software Copyright 2021 the original author or authors.
 * </p>
 * 
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 * </p>
 *
 * @author Ian F. Darwin, hbarreiros
 */

package br.upe.ppsw.jabberpoint;

import javax.swing.JOptionPane;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.util.StringUtils;

import br.upe.ppsw.jabberpoint.apresentacao.Style;
import br.upe.ppsw.jabberpoint.apresentacao.visualizador.PresentationViewer;
import br.upe.ppsw.jabberpoint.controle.FileManager;
import br.upe.ppsw.jabberpoint.controle.HTMLFormat;
import br.upe.ppsw.jabberpoint.controle.IFilePresentationFormat;
import br.upe.ppsw.jabberpoint.controle.JSONFormat;
import br.upe.ppsw.jabberpoint.controle.XMLFormat;
import br.upe.ppsw.jabberpoint.modelo.Presentation;
import br.upe.ppsw.jabberpoint.modelo.PresentationDemo;

@SpringBootApplication
public class JabberPointApplication implements CommandLineRunner {

	protected static final String IOERR = "IO Error: ";
	protected static final String JABERR = "Jabberpoint Error ";
	protected static final String JABVERSION = "Jabberpoint 1.6 -";

	public static final int WIDTH = 1200;
	public static final int HEIGHT = 800;

	private Presentation presentation;
	private static IFilePresentationFormat fileFormat = new XMLFormat();

	public static void main(String[] argv) {
		SpringApplicationBuilder builder = new SpringApplicationBuilder(JabberPointApplication.class);
		builder.headless(false);
		builder.web(WebApplicationType.NONE);
		builder.run(argv);
	}

	public static final IFilePresentationFormat getFileManager() {
		return fileFormat;
	}

	@Override
	public void run(String... args) throws Exception {
		try {

			Style.createStyles();
			String file = args == null || args.length == 0 ? null : args[0];
//			// o file abaixo é para verificar o funcionamento do file manager
//			String file = "src/main/resources/ppsw.json";

			if (StringUtils.hasLength(file)) {
//				//Executando o FileManager
//				FileManager manager = new FileManager();
//				this.presentation = manager.load(file);
				this.presentation = fileFormat.load(file);

			} else {
//				//Teste do load do Html
//				HTMLFormat html = new HTMLFormat();
//				this.presentation = html.load("src/main/resources/teste.html");
//				
//				//Teste do load do Json
//				JSONFormat json = new JSONFormat();
//				this.presentation = json.load("src/main/resources/ppsw.json");
				
				this.presentation = new PresentationDemo();
			}

			new PresentationViewer(this.presentation);

		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, IOERR + ex, JABERR, JOptionPane.ERROR_MESSAGE);
		}
	}

}
