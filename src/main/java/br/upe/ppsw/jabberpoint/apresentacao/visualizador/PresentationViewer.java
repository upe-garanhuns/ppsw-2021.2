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
package br.upe.ppsw.jabberpoint.apresentacao.visualizador;

import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import br.upe.ppsw.jabberpoint.JabberPointApplication;
import br.upe.ppsw.jabberpoint.controle.KeyController;
import br.upe.ppsw.jabberpoint.controle.MenuController;
import br.upe.ppsw.jabberpoint.modelo.Presentation;
import br.upe.ppsw.jabberpoint.modelo.Slide;

/**
 * Representa a janela de exibição ao usuário dos {@link Slide} de uma
 * {@link Presentation};
 */
public class PresentationViewer extends JFrame {

	private static final long serialVersionUID = 3227L;

	private static final String JABTITLE = "Jabberpoint 1.6";

	private Presentation presentation;
	private SlideViewer slideViewer;

	public PresentationViewer(Presentation presentation) {
		super();

		this.presentation = presentation;
		this.slideViewer = new SlideViewer();

		setupWindow();
	}

	public final Presentation currentPresentation() {
		return this.presentation;
	}

	/**
	 * Configura a interface com o usuário para a exibição de uma
	 * {@link Presentation}
	 * 
	 * @param slideViewerComponent A instância de {@link SlideViewer}, o componente
	 *                             de controle da visualização e navegação dos
	 *                             {@link Slide} de uma {@link Presentation}
	 * @param presentation         A instância de {@link Presentation} que contém os
	 *                             dados da apresentação.
	 */
	private void setupWindow() {
		setTitle(JABTITLE + " => " + this.presentation.getTitle());

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		super.getContentPane().add(this.slideViewer);

		addKeyListener(new KeyController(this));
		setMenuBar(new MenuController(this));

		setSize(new Dimension(JabberPointApplication.WIDTH, JabberPointApplication.HEIGHT));

		this.slideViewer.update(this.presentation.getCurrentSlide(), this.presentation.currentStatus());

		setVisible(true);
	}

	public final void loadPresentation(Presentation presentation) {
		this.presentation = presentation;

		presentation.setCurrentSlideNumber(0);
		this.slideViewer.update(this.presentation.getCurrentSlide(), this.presentation.currentStatus());

		this.repaint();
	}

	public final void nextSlide() {
		this.slideViewer.update(presentation.nextSlide(), presentation.currentStatus());
	}

	public final void previousSlide() {
		this.slideViewer.update(presentation.prevSlide(), presentation.currentStatus());
	}

	public final void goToSlide(int slide) {
		presentation.setCurrentSlideNumber(--slide);
		this.slideViewer.update(presentation.getCurrentSlide(), presentation.currentStatus());
	}
}
