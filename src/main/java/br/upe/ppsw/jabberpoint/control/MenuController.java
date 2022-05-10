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
package br.upe.ppsw.jabberpoint.control;

import java.awt.Frame;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.MenuShortcut;

import javax.swing.JOptionPane;

import org.springframework.util.NumberUtils;

import br.upe.ppsw.jabberpoint.model.Presentation;
import br.upe.ppsw.jabberpoint.model.PresentationDefault;
import br.upe.ppsw.jabberpoint.presentation.viewer.PresentationViewer;

/**
 * Implementação dos mecanismos de controle navegacional através de um menu
 * superior de uma {@link Presentation}.
 */
public class MenuController extends MenuBar {

	private static final long serialVersionUID = 227L;

	protected static final String ABOUT = "Sobre";
	protected static final String FILE = "Arquivo";
	protected static final String EXIT = "Sair";
	protected static final String GOTO = "Pular para";
	protected static final String HELP = "Ajuda";
	protected static final String NEW = "Novo";
	protected static final String NEXT = "Próximo";
	protected static final String OPEN = "Abrir";
	protected static final String PAGENR = "Npumero do Slide?";
	protected static final String PREV = "Anterior";
	protected static final String SAVE = "Salvar";
	protected static final String VIEW = "Visualizar";

	protected static final String TESTFILE = "src/main/resources/test.json";
	protected static final String SAVEFILE = "src/main/resources/";

	protected static final String IOEX = "IO Exception: ";
	protected static final String LOADERR = "Erro ao carregar";
	protected static final String SAVEERR = "Erro ao salvar";
	private FileManager filemanager = new FileManager();
	/**
	 * Representa o menu superior da tela de {@link Presentation}
	 * 
	 * @param frame A instância de {@link Frame} que contém os dados exibidos ao
	 *              usuário.
	 * @param pres  A instância da {@link Presentation} que está sendo exibida
	 */
	public MenuController(PresentationViewer parent) {
		super();

		createMenuFile(parent);
		createMenuView(parent);
		createMenuHelp(parent);
	}

	private void createMenuFile(PresentationViewer parent) {

		MenuItem menuItem;
		Menu fileMenu = new Menu(FILE);

		menuItem = createMenuItem(OPEN);
		menuItem.addActionListener(actionEvent -> {
			try {
				parent.loadPresentation(filemanager.load(TESTFILE));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});

		fileMenu.add(menuItem);

		menuItem = createMenuItem(NEW);
		menuItem.addActionListener(actionEvent -> {
			parent.loadPresentation(new PresentationDefault());
		});

		fileMenu.add(menuItem);

		menuItem = createMenuItem(SAVE);
		menuItem.addActionListener(actionEvent -> {
			String fileName = JOptionPane.showInputDialog("Enter the file name", "demo.json");
			try {
				filemanager.save(parent.currentPresentation(), SAVEFILE + fileName);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			JOptionPane.showMessageDialog(parent,"Your presentation was saved in src/main/resources directory");
		});

		fileMenu.add(menuItem);

		fileMenu.addSeparator();

		menuItem = createMenuItem(EXIT);
		menuItem.addActionListener(actionEvent -> {
			System.exit(0);
		});

		fileMenu.add(menuItem);

		add(fileMenu);
	}
	
	private void createMenuView(PresentationViewer parent) {
		MenuItem menuItem;
		Menu viewMenu = new Menu(VIEW);

		menuItem = createMenuItem(NEXT);
		menuItem.addActionListener(actionEvent -> parent.nextSlide());

		viewMenu.add(menuItem);

		menuItem = createMenuItem(PREV);
		menuItem.addActionListener(actionEvent -> parent.previousSlide());

		viewMenu.add(menuItem);

		menuItem = createMenuItem(GOTO);
		menuItem.addActionListener(actionEvent -> {
			String pageNumberStr = JOptionPane.showInputDialog(PAGENR, Integer.valueOf(1));
			int pageNumber = NumberUtils.parseNumber(pageNumberStr, Integer.class);
			parent.goToSlide(pageNumber);
		});

		viewMenu.add(menuItem);

		super.add(viewMenu);
	}

	private void createMenuHelp(PresentationViewer parent) {
		MenuItem menuItem;
		Menu helpMenu = new Menu(HELP);

		menuItem = createMenuItem(ABOUT);
		menuItem.addActionListener(actionEvent -> {
			JOptionPane.showMessageDialog(parent,
					"JabberPoint é um programa de apresentação de slides básico escrito em Java(tm).\n"
							+ "Ele é disponibilizado como uma cópia livre desde que você mantenha esta informação de splash screen intacta.\n"
							+ "Copyright (c) 1995-now by Ian F. Darwin, ian@darwinsys.com.\n"
							+ "Adaptada por Helaine Barreiros fpara Universidade de Pernambuco, 2021 -- now.\n"
							+ "A cópia original do autor está disponível em http://www.darwinsys.com/",
					"Sobre JabberPoint", JOptionPane.INFORMATION_MESSAGE);
		});

		helpMenu.add(menuItem);

		setHelpMenu(helpMenu);
	}
	
	private MenuItem createMenuItem(String name) {
		return new MenuItem(name, new MenuShortcut(name.charAt(0)));
	}

}
