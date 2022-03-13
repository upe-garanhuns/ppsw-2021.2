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

/**
 * Implementação dos mecanismos de controle navegacional através de um menu superior de uma
 * {@link Presentation}.
 */
public class MenuController extends MenuBar {

  private Frame frame;
  private Presentation presentation;
  private MenuItem menuItem;

  private static final long serialVersionUID = 227L;


  protected static final String ABOUT = "Sobre";
  protected static final String FILE = "Arquivo";
  protected static final String EXIT = "Sair";
  protected static final String GOTO = "Pular para";
  protected static final String HELP = "Ajuda";
  protected static final String NEW = "Novo";
  protected static final String NEXT = "Próximo";
  protected static final String OPEN = "Abrir";
  protected static final String PAGENR = "Numero do Slide";
  protected static final String PREV = "Anterior";
  protected static final String SAVE = "Salvar";
  protected static final String VIEW = "Visualizar";

  protected static final String TESTFILE = "classpath:test.xml";
  protected static final String SAVEFILE = "classpath:dump.xml";

  protected static final String IOEX = "IO Exception: ";
  protected static final String LOADERR = "Erro ao carregar";
  protected static final String SAVEERR = "Erro ao salvar";

  /**
   * Representa o menu superior da tela de {@link Presentation}
   * 
   * @param frame A instância de {@link Frame} que contém os dados exibidos ao usuário.
   * @param presentation A instância da {@link Presentation} que está sendo exibida
   */
  public MenuController(Frame frame, Presentation presentation) {

    this.frame = frame;
    this.presentation = presentation;

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

  public MenuItem mkMenuItem(String name) {
    return new MenuItem(name, new MenuShortcut(name.charAt(0)));
  }

  /**
   *
   * Funcões para adicionar itens ao menu
   *
   */

  private void addNewInFile(Menu menu){

    menu.add(this.menuItem = mkMenuItem(NEW));
    this.menuItem.addActionListener(actionEvent -> {
      this.presentation.clear();
      this.frame.repaint();
    });

  }

  private void addOpenInFile(Menu menu) {

    menu.add(this.menuItem = mkMenuItem(OPEN));

    menuItem.addActionListener(ActionEvent -> {
      presentation.clear();

      Accessor xmlAcessor = new XMLAccessor();
      try{
        xmlAcessor.loadFile(presentation, ResourceUtils.getFile(TESTFILE).getAbsolutePath());
      } catch (IOException exc) {
        JOptionPane.showMessageDialog(frame, IOEX + exc, LOADERR, JOptionPane.ERROR_MESSAGE);
      }

      frame.repaint();
    });

  }

  private void addSaveInFile(Menu menu) {

    menu.add(menuItem = mkMenuItem(SAVE));

    menuItem.addActionListener(ActionEvent -> {
      Accessor xmlAcessor = new XMLAccessor();
      try {
        xmlAcessor.saveFile(presentation, SAVEFILE);
      }catch (IOException exc) {
        JOptionPane.showMessageDialog(frame, IOEX + exc, SAVEERR, JOptionPane.ERROR_MESSAGE);
      }
    });

  }

  private void addExitInFile(Menu menu) {

    menu.add(menuItem = mkMenuItem(EXIT));

    menuItem.addActionListener(ActionEvent -> {
      presentation.exit(0);
    });

  }

  private void addNextInView(Menu menu) {

    menu.add(menuItem = mkMenuItem(NEXT));

    menuItem.addActionListener(ActionEvent -> {
      presentation.nextSlide();
    });

  }

  private void addPrevInView(Menu menu) {

    menu.add(menuItem = mkMenuItem(PREV));

    menuItem.addActionListener(ActionEvent -> {
      presentation.prevSlide();
    });

  }

  private void addGoToInView(Menu menu) {

    menu.add(menuItem = mkMenuItem(GOTO));

    menuItem.addActionListener(ActionEvent -> {
      String pageNumberStr = JOptionPane.showInputDialog((Object) PAGENR);
      int pageNuber = Integer.parseInt(pageNumberStr);
      presentation.setSlideNumber(pageNuber - 1);
    });

  }

  private void addAboutInHelp(Menu menu){

    menu.add(menuItem = mkMenuItem(ABOUT));

    menuItem.addActionListener(ActionEvent -> {
      AboutBox.show(frame);
    });

  }

}
