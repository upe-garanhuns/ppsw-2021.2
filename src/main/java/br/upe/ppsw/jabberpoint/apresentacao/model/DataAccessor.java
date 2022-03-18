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
package br.upe.ppsw.jabberpoint.apresentacao.model;

import java.io.IOException;

public abstract class DataAccessor {

	public static final String DEMO_NAME = "Apresentação de Demonstração";
	public static final String DEFAULT_EXTENSION = ".xml";

	public static DataAccessor getDemoAccessor() {
		return new DemoData();
	}

	public DataAccessor() {
	}

	abstract public void loadFile(Presentation presentation, String fileName) throws IOException;

	abstract public void saveFile(Presentation presentation, String fileName) throws IOException;

}
