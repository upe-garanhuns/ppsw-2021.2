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

import java.io.IOException;
import javax.swing.JOptionPane;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import br.upe.ppsw.jabberpoint.controller.DemoPresentation;
import br.upe.ppsw.jabberpoint.controller.XMLDataKeeper;
import br.upe.ppsw.jabberpoint.model.Presentation;
import br.upe.ppsw.jabberpoint.view.SlideViewerFrame;
import br.upe.ppsw.jabberpoint.view.Style;

/**
 * Classe principal que inicializa a aplicação de apresentação de slides
 */
@SpringBootApplication
public class JabberPointApplication implements CommandLineRunner {

  protected static final String IOERR = "IO Error: ";
  protected static final String JABERR = "Jabberpoint Error ";
  protected static final String JABVERSION = "Jabberpoint 1.6 -";

  public static void main(String[] argv) {
    SpringApplicationBuilder builder = new SpringApplicationBuilder(JabberPointApplication.class);
    builder.headless(false);
    builder.web(WebApplicationType.NONE);
    builder.run(argv);
  }

  /**
   * Inicializa os dados da apresentação. Caso não seja informada uma apresentação em específico
   * através do parâmetro de argumento da aplicação será carregada uma apresentação padrão.
   */
  @Override
  public void run(String... args) throws Exception {
    Style.createStyles();

    Presentation presentation = new Presentation();

    new SlideViewerFrame(JABVERSION, presentation);

    try {
      if (args.length == 0) {
        DemoPresentation.getDemoAccessor().loadFile(presentation, "");
      } else {
        new XMLDataKeeper().loadFile(presentation, args[0]);
      }

      presentation.setSlideNumber(0);

    } catch (IOException ex) {
      JOptionPane.showMessageDialog(null, IOERR + ex, JABERR, JOptionPane.ERROR_MESSAGE);
    }
  }

}
