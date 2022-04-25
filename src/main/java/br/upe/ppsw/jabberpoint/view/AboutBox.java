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
package br.upe.ppsw.jabberpoint.view;

import java.awt.Frame;
import javax.swing.JOptionPane;

/**
 * Representa a apresentação de informações sobre a aplicação na interface com o usuário
 */
public class AboutBox {

  public static void show(Frame parent) {
    JOptionPane.showMessageDialog(parent,
        "JabberPoint é um programa de apresentação de slides básico escrito em Java(tm).\n"
            + "Ele é disponibilizado como uma cópia livre desde que você mantenha esta informação de splash screen intacta.\n"
            + "Copyright (c) 1995-now by Ian F. Darwin, ian@darwinsys.com.\n"
            + "Adaptada por Helaine Barreiros fpara Universidade de Pernambuco, 2021 -- now.\n"
            + "A cópia original do autor está disponível em http://www.darwinsys.com/",
        "Sobre JabberPoint", JOptionPane.INFORMATION_MESSAGE);
  }
}
