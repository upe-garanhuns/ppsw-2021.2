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
package br.upe.ppsw.jabberpoint.apresentacao.Controllers;

import java.io.IOException;

import br.upe.ppsw.jabberpoint.apresentacao.Models.Presentation;


/**
 * Representação mais abstrata de um arquivo que armazena os dados de uma {@link Presentation}
 */
public interface  Accessor {


//  public Accessor() {}

  /**
   * Implementa a recuperação de dados salvas no arquivo que serão carregadas na apresentação.
   * 
   * @param presentation A instância de {@link Presentation} que receberá os dados da apresentação
   *        salva no arquivo.
   * @param fileName uma instância de {@link String} contendo o caminho completo contendo o nome do
   *        arquivo que armazena os dados da apresentação.
   * @throws IOException caso ocorra algum erro ao localizar ou carregar os dados da
   *         {@link Presentation}
   */
  abstract public void loadFile(Presentation presentation, String fileName) throws IOException;

  /**
   * Salva os dados da apresentação em um arquivo.
   * 
   * @param presentation A instância de {@link Presentation} que contém os dados da apresentação a
   *        ser salva no arquivo.
   * @param fileName uma instância de {@link String} contendo o caminho completo do arquivo que
   *        armazenar[a os dados da apresentação.
   * @throws IOException caso ocorra algum erro ao salvar os dados da {@link Presentation}s
   */
  abstract public void saveFile(Presentation presentation, String fileName) throws IOException;

}
