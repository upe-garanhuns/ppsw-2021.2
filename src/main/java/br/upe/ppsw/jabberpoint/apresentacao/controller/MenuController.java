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

import java.awt.Frame;

import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.MenuShortcut;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JOptionPane;
import org.springframework.util.ResourceUtils;

import br.upe.ppsw.jabberpoint.apresentacao.model.Presentation;
import br.upe.ppsw.jabberpoint.apresentacao.view.AboutBox;
import br.upe.ppsw.jabberpoint.apresentacao.view.SlideViewerComponent;
import br.upe.ppsw.jabberpoint.apresentacao.model.*;

public class MenuController extends MenuBar {

	private static final long serialVersionUID = 227L;

	private Frame parent;
	private Presentation presentation;
	private SlideViewerComponent slideViewerComponent;

	protected static final String ABOUT = "Sobre";
	protected static final String FILE = "Arquivo";
	protected static final String EXIT = "Sair";
	protected static final String GOTO = "Pular para";
	protected static final String HELP = "Ajuda";
	protected static final String NEW = "Novo";
	protected static final String NEXT = "Próximo";
	protected static final String OPEN = "Abrir";
	protected static final String PAGENR = "Número do Slide?";
	protected static final String PREV = "Anterior";
	protected static final String SAVE = "Salvar";
	protected static final String VIEW = "Visualizar";

	protected static final String TESTFILE = "classpath:test.xml";
	protected static final String SAVEFILE = "classpath:dump.xml";

	protected static final String IOEX = "IO Exception: ";
	protected static final String LOADERR = "Erro ao carregar";
	protected static final String SAVEERR = "Erro ao salvar";

	public MenuController(Frame frame, Presentation pres, SlideViewerComponent slideViewerComponent) {
		parent = frame;
		presentation = pres;
		this.slideViewerComponent = slideViewerComponent;

		MenuItem menuItem;

		Menu fileMenu = new Menu(FILE);
		fileMenu.add(menuItem = mkMenuItem(OPEN));

		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				presentation.clear();

				DataAccessor xmlAccessor = new XMLAccessor();
				try {
					xmlAccessor.loadFile(presentation, ResourceUtils.getFile(TESTFILE).getAbsolutePath());
					presentation.setSlideNumber(0);
				} catch (IOException exc) {
					JOptionPane.showMessageDialog(parent, IOEX + exc, LOADERR, JOptionPane.ERROR_MESSAGE);
				}

				parent.repaint();
			}
		});

		fileMenu.add(menuItem = mkMenuItem(NEW));

		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				presentation.clear();
				parent.repaint();
			}
		});

		fileMenu.add(menuItem = mkMenuItem(SAVE));

		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DataAccessor xmlAccessor = new XMLAccessor();
				try {
					xmlAccessor.saveFile(presentation, SAVEFILE);
				} catch (IOException exc) {
					JOptionPane.showMessageDialog(parent, IOEX + exc, SAVEERR, JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		fileMenu.addSeparator();

		fileMenu.add(menuItem = mkMenuItem(EXIT));

		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				//presentation.exit(0);
				System.exit(0);
			}
		});

		add(fileMenu);

		Menu viewMenu = new Menu(VIEW);
		viewMenu.add(menuItem = mkMenuItem(NEXT));

		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				presentation.nextSlide();
				slideViewerComponent.update();
			}
		});

		viewMenu.add(menuItem = mkMenuItem(PREV));

		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				presentation.prevSlide();
				slideViewerComponent.update();
			}
		});

		viewMenu.add(menuItem = mkMenuItem(GOTO));

		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				String pageNumberStr = JOptionPane.showInputDialog((Object) PAGENR);
				int pageNumber = Integer.parseInt(pageNumberStr);
				if (pageNumber <= presentation.getSize()) {
					presentation.setSlideNumber(pageNumber - 1);
					slideViewerComponent.update();
				} else {
					JOptionPane.showMessageDialog(parent, "Não é possível navegar para esse slide.",
							"Operação inválida", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		add(viewMenu);

		Menu helpMenu = new Menu(HELP);
		helpMenu.add(menuItem = mkMenuItem(ABOUT));

		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				AboutBox.show(parent);
			}
		});

		setHelpMenu(helpMenu);
	}

	public MenuItem mkMenuItem(String name) {
		return new MenuItem(name, new MenuShortcut(name.charAt(0)));
	}
}
