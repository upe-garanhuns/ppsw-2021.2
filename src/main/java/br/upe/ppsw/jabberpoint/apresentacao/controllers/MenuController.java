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
package br.upe.ppsw.jabberpoint.apresentacao.controllers;

import java.awt.Frame;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.MenuShortcut;
import java.io.IOException;
import javax.swing.JOptionPane;

import br.upe.ppsw.jabberpoint.apresentacao.models.Presentation;
import br.upe.ppsw.jabberpoint.apresentacao.views.SlideViewerComponent;
import org.springframework.util.ResourceUtils;

/**
 * Implementação dos mecanismos de controle navegacional através de um menu superior de uma
 * {@link Presentation}.
 */
public class MenuController extends MenuBar {

  private Frame frame;
  private Presentation presentation;
  private MenuItem menuItem;
  private SlideViewerComponent slideViewerComponent;

  private static final long serialVersionUID = 227L;


  protected static final String ABOUT = "Sobre";
  protected static final String FILE = "Arquivo";
  protected static final String EXIT = "Sair";
  protected static final String GOTO = "Pular para";
  protected static final String HELP = "Ajuda";
  protected static final String NEW = "Novo";
  protected static final String NEXT = "Próximo";
  protected static final String OPEN = "Abrir";
  protected static final String PAGENR = "Número do Slide";
  protected static final String PREV = "Anterior";
  protected static final String SAVE = "Salvar";
  protected static final String VIEW = "Visualizar";

  protected static final String TESTFILE = "classpath:test.xml";
  protected static final String SAVEFILE = "dump.xml";

  protected static final String IOEX = "IO Exception: ";
  protected static final String LOADERR = "Erro ao carregar";
  protected static final String SAVEERR = "Erro ao salvar";

  /**
   * Representa o menu superior da tela de {@link Presentation}
   * 
   * @param frame A instância de {@link Frame} que contém os dados exibidos ao usuário.
   * @param presentation A instância da {@link Presentation} que está sendo exibida
   */
  public MenuController(Frame frame, Presentation presentation, SlideViewerComponent slideViewerComponent) {

    this.frame = frame;
    this.presentation = presentation;
    this.slideViewerComponent = slideViewerComponent;

    Menu fileMenu = new Menu(FILE);
    addOpenInFile(fileMenu);
    addNewInFile(fileMenu);
    addSaveInFile(fileMenu);
    fileMenu.addSeparator();
    addExitInFile(fileMenu);
    add(fileMenu);

    Menu viewMenu = new Menu(VIEW);
    addNextInView(viewMenu);
    addPrevInView(viewMenu);
    addGoToInView(viewMenu);
    add(viewMenu);

    Menu helpMenu = new Menu(HELP);
    addAboutInHelp(helpMenu);
    setHelpMenu(helpMenu);
  }

  public MenuItem mkMenuItem(String name, String letraAtalho) {
    return new MenuItem(name, new MenuShortcut(letraAtalho.charAt(0)));
  }

  /**x
   *
   * Funcões para adicionar itens ao menu
   *
   */

  private void addNewInFile(Menu menu){

    menu.add(menuItem = mkMenuItem(NEW, "N"));
    menuItem.addActionListener(actionEvent -> {
      presentation.clear();
      frame.repaint();
    });

  }

  private void addOpenInFile(Menu menu) {

    menu.add(this.menuItem = mkMenuItem(OPEN, "O"));

    menuItem.addActionListener(ActionEvent -> {
      presentation.clear();

      IDataPresentation XMLDataPresentation = new XMLDataPresentation();

      try{
        XMLDataPresentation.loadFile(presentation, ResourceUtils.getFile(TESTFILE).getAbsolutePath());
      } catch (IOException exc) {
        JOptionPane.showMessageDialog(frame, IOEX + exc, LOADERR, JOptionPane.ERROR_MESSAGE);
      }

      frame.repaint();
      presentation.nextSlide();
    });

  }

  private void addSaveInFile(Menu menu) {

    menu.add(menuItem = mkMenuItem(SAVE, "S"));

    menuItem.addActionListener(ActionEvent -> {
      IDataPresentation xmlAcessor = new XMLDataPresentation();
      try {
        if(presentation.getSize() > 0) {
          xmlAcessor.saveFile(this.presentation, SAVEFILE);
        } else {
          JOptionPane.showMessageDialog(frame, "Não há slides para salvar", SAVEERR, JOptionPane.ERROR_MESSAGE);
        }
      }catch (IOException exc) {
        JOptionPane.showMessageDialog(frame, IOEX + exc, SAVEERR, JOptionPane.ERROR_MESSAGE);
      }
    });

  }

  private void addExitInFile(Menu menu) {

    menu.add(menuItem = mkMenuItem(EXIT, "T"));

    menuItem.addActionListener(ActionEvent -> {
     System.exit(0);
    });

  }

  private void addNextInView(Menu menu) {

    menu.add(menuItem = mkMenuItem(NEXT, "D"));

    menuItem.addActionListener(ActionEvent -> {
      presentation.nextSlide();
      slideViewerComponent.update();
    });

  }

  private void addPrevInView(Menu menu) {

    menu.add(menuItem = mkMenuItem(PREV, "A"));

    menuItem.addActionListener(ActionEvent -> {
      presentation.prevSlide();
      slideViewerComponent.update();
    });

  }

  private void addGoToInView(Menu menu) {

    menu.add(menuItem = mkMenuItem(GOTO, "G"));

    menuItem.addActionListener(ActionEvent -> {
      String pageNumberStr = JOptionPane.showInputDialog((Object) PAGENR);
      try {
        int pageNumber = Integer.parseInt(pageNumberStr);
        if (pageNumber >= 1 && pageNumber <= presentation.getSize()) { presentation.setSlideNumber(pageNumber - 1); slideViewerComponent.update(); }
        else { JOptionPane.showMessageDialog(frame, "Não é possível acessar este slide", "ERROR", JOptionPane.ERROR_MESSAGE); }
      }catch (NumberFormatException exc) {
        JOptionPane.showMessageDialog(frame, PAGENR + " deve ser um número inteiro", "Erro", JOptionPane.ERROR_MESSAGE);
      }
    });

  }

  private void addAboutInHelp(Menu menu){

    menu.add(menuItem = mkMenuItem(ABOUT, "K"));

    menuItem.addActionListener(ActionEvent -> {
      JOptionPane.showMessageDialog(frame,
              "JabberPoint é um programa de apresentação de slides básico escrito em Java(tm).\n"
                      + "Ele é disponibilizado como uma cópia livre desde que você mantenha esta informação de splash screen intacta.\n"
                      + "Copyright (c) 1995-now by Ian F. Darwin, ian@darwinsys.com.\n"
                      + "Adaptada por Helaine Barreiros para Universidade de Pernambuco, 2021 -- now.\n"
                      + "A cópia original do autor está disponível em http://www.darwinsys.com/",
              "Sobre JabberPoint", JOptionPane.INFORMATION_MESSAGE); });

  }

}
