package ifpr.paranavai.jogo.visao;
import javax.swing.JFrame;

import ifpr.paranavai.jogo.controle.FaseUm;

public class Principal extends JFrame{
    public Principal(){
    FaseUm fase = new FaseUm();
    super.add(fase);
    super.setTitle("Space Invaders Clone");
    super.setSize(800, 640);
    super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    super.setLocationRelativeTo(null);
    super.setResizable(false);
    super.setVisible(true);
    }
    
    
    public static void main(String[] args) {
        //Session sessao = HibernateUtil.getSession();
        new Principal();
        
    }
}
