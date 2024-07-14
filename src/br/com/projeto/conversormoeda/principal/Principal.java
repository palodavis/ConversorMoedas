package br.com.projeto.conversormoeda.principal;

import br.com.projeto.conversormoeda.conexao.InterfaceComUsuario;

public class Principal {
    public static void main(String[] args) {
        InterfaceComUsuario interfaceUsuario = new InterfaceComUsuario();
        interfaceUsuario.menu();
    }
}
