package br.com.fiap.main;

import br.com.fiap.dao.CarroDAO;
import br.com.fiap.dao.ClienteDAO;
import br.com.fiap.dao.ConnectionFactory;
import br.com.fiap.dto.Carro;
import br.com.fiap.dto.Cliente;

import java.sql.Connection;
import java.util.ArrayList;

public class TesteRead {
    public static void main(String[] args) {
        Connection con = ConnectionFactory.abrirConexao();
        CarroDAO carroDAO = new CarroDAO(con);
        ArrayList<Carro> resultado = carroDAO.listarTodos();
        if (resultado != null) {
            for (Carro carro : resultado) {
                System.out.println("Placa: " + carro.getPlaca());
                System.out.println("Cor: " + carro.getCor());
                System.out.println("Descrição: " + carro.getDescricao());
                System.out.println();
            }
        }

        System.out.println("---------------------");

        ClienteDAO clienteDAO = new ClienteDAO(con);
        ArrayList<Cliente> resultadoCliente = clienteDAO.listarTodos();
        if(resultado != null){
            for(Cliente cliente : resultadoCliente){
                System.out.println("ID: " + cliente.getIdCliente());
                System.out.println("Nome: " + cliente.getNomeCliente());
                System.out.println("Placa: " + cliente.getPlaca());
                System.out.println();
            }
        }
        ConnectionFactory.fecharConexao(con);
    }
}
