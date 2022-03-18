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
package br.upe.ppsw.jabberpoint.apresentacao.controller;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import br.upe.ppsw.jabberpoint.apresentacao.model.Presentation;;

public class KeyController extends KeyAdapter {

	private Presentation presentation;

	public KeyController(Presentation p) {
		presentation = p;
	}

	public void keyPressed(KeyEvent keyEvent) {
		switch (keyEvent.getKeyCode()) {
		case KeyEvent.VK_PAGE_DOWN:
		case KeyEvent.VK_DOWN:
		case KeyEvent.VK_ENTER:
		case '+':
			presentation.nextSlide();
			break;
		case KeyEvent.VK_PAGE_UP:
		case KeyEvent.VK_UP:
		case '-':
			presentation.prevSlide();
			break;
		case 'q':
		case 'Q':
			System.exit(0);
			break; // fix?
		default:
			break;
		}
	}

}
