package br.com.fiap.main;

import br.com.fiap.dao.ClienteDAO;
import br.com.fiap.dao.ConnectionFactory;
import br.com.fiap.dto.Cliente;

import java.sql.Connection;
import java.util.ArrayList;

public class TesteClienteCRUD {
    public static void main(String[] args) {
        Connection con = ConnectionFactory.abrirConexao();
        ClienteDAO clienteDAO = new ClienteDAO(con);
        Cliente cliente = new Cliente();

        //Criando 1° cliente
        cliente.setIdCliente(4);
        cliente.setNomeCliente("Astrogildo");
        cliente.setPlaca("JKK1902");
        System.out.println(clienteDAO.inserir(cliente));

        cliente.setIdCliente(5);
        cliente.setNomeCliente("Berisvaldo");
        cliente.setPlaca("AMR1980");
        System.out.println(clienteDAO.inserir(cliente));

        cliente.setIdCliente(6);
        cliente.setNomeCliente("Gumercindo");
        cliente.setPlaca("JAO1930");
        System.out.println(clienteDAO.inserir(cliente));

        cliente.setIdCliente(7);
        cliente.setNomeCliente("Pafúncia");
        cliente.setPlaca("AMR1980");
        System.out.println(clienteDAO.inserir(cliente));

        //Alterando um cliente
        cliente.setIdCliente(3);
        cliente.setNomeCliente("Josenelson");
        cliente.setPlaca("JKK1903");
        System.out.println(clienteDAO.alterar(cliente));

        //Excluindo um cliente
        cliente.setIdCliente(5);
        System.out.println(clienteDAO.excluir(cliente));

        //Listar todos os clientes
        ArrayList<Cliente> resultado = clienteDAO.listarTodos();
        if(resultado != null){
            for(Cliente cliente1 : resultado){
                System.out.printf("Id: %d\nNome: %s\nPlaca: %s\n", cliente1.getIdCliente(), cliente1.getNomeCliente(), cliente1.getPlaca());
            }
        }

        ConnectionFactory.fecharConexao(con);
    }
}
