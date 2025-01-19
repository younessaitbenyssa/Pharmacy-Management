package org.example;

import javax.swing.*;

public class Controller {
    JFrame principale;
    Login login;
    Inscription inscription;
    Cote3 cote3;
    Cote2 cote2;

    public Controller(JFrame principale) {
        this.principale = principale;
        this.login = new Login(this, principale);
        this.inscription = new Inscription(this);
        this.cote3 = new Cote3();
        this.cote2 = new Cote2();
    }

    public void showLogin() {
        principale.getContentPane().removeAll();
        principale.getContentPane().add(login.getLogin());
        principale.getContentPane().add(cote2.getPanel());
        principale.revalidate();
        principale.repaint();
    }

    public void showInscription() {
        principale.getContentPane().removeAll();
        principale.getContentPane().add(inscription.getInscription());
        principale.getContentPane().add(cote3.getPanel());
        principale.revalidate();
        principale.repaint();
    }
}
