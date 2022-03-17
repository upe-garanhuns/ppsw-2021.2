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
package br.upe.ppsw.jabberpoint.apresentacao;

import java.io.FileNotFoundException;
import org.springframework.util.ResourceUtils;

public class DemoData extends DataAccessor {

	public void loadFile(Presentation presentation, String unusedFilename) throws FileNotFoundException {

		presentation.setTitle("Apresentação de Demonstração");

		Slide slide;
		slide = new Slide();

		slide.setTitle("JabberPoint");
		slide.appendTextItem(1, "Ferramenta de Apresentação de Slides");
		slide.appendTextItem(2, "Copyright (c) 1996-now: Ian Darwin");
		slide.appendTextItem(2, "Copyright (c) 2021-now:");
		slide.appendTextItem(2, "Helaine Barreiros");
		slide.appendTextItem(4, "JabberPoint execução de demonstração sem arquivos persistidos");
		slide.appendTextItem(4, "exibição de apresentação com dados apenas em memória");
		slide.appendTextItem(1, "Navegação:");
		slide.appendTextItem(3, "Próximo slide: PgDn ou Enter");
		slide.appendTextItem(3, "Slide Anterior: PgUp ou up-arrow");
		slide.appendTextItem(3, "Parar: q ou Q");
		presentation.append(slide);

		slide = new Slide();
		slide.setTitle("Demonstração dos níveis e estilos de uma apresentação");
		slide.appendTextItem(1, "Nível 1");
		slide.appendTextItem(2, "Nível 2");
		slide.appendTextItem(1, "Novamente um item de Nível 1");
		slide.appendTextItem(1, "Nível 1 tem Estilo número 1");
		slide.appendTextItem(2, "Nível 2 tem Estilo número 2");
		slide.appendTextItem(3, "Este é um ítem de Nível 3");
		slide.appendTextItem(4, "E este é um ítem de Nível 4");
		presentation.append(slide);

		slide = new Slide();
		slide.setTitle("Terceiro Slide");
		slide.appendTextItem(1, "Para abrir uma nova apresentação,");
		slide.appendTextItem(2, "utilize o menu File->Open.");
		slide.appendTextItem(1, " ");
		slide.appendTextItem(1, "Fim da Apresentação");
		slide.appendSlideItem(new BitmapItem(1, ResourceUtils.getFile("classpath:JabberPoint.jpg").getAbsolutePath()));
		presentation.append(slide);
	}

	public void saveFile(Presentation presentation, String unusedFilename) {
		throw new IllegalStateException("Não é possível salvar arquivo na versão demo!");
	}

}
