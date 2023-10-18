package ifpr.paranavai.jogo.teste.hibernate;

import ifpr.paranavai.jogo.modelo.Jogador;
import ifpr.paranavai.jogo.servico.JogadorServico;

public class ServicoTeste {
    public static void main(String[] args) {
        Jogador local = new Jogador("Joaozinho 2");

        JogadorServico.inserir(local);
    }    
}
