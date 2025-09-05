package br.com.fiap.main;

import br.com.fiap.dao.CarroDAO;
import br.com.fiap.dao.ClienteDAO;
import br.com.fiap.dao.ConnectionFactory;
import br.com.fiap.dto.Carro;
import br.com.fiap.dto.Cliente;

import java.sql.Connection;

public class TesteCreate {
    public static void main(String[] args) {
        Connection con = ConnectionFactory.abrirConexao();
        Carro carro = new Carro();
        CarroDAO carroDAO = new CarroDAO(con);
        carro.setPlaca("BBBBBB");
        carro.setCor("Azul");
        carro.setDescricao("Nissan GTR");
        System.out.println(carroDAO.inserir(carro));

        Cliente cliente = new Cliente();
        ClienteDAO clienteDAO = new ClienteDAO(con);
        cliente.setIdCliente(3);
        cliente.setNomeCliente("Laura");
        cliente.setPlaca("BBBBBB");
        System.out.println(clienteDAO.inserir(cliente));
        ConnectionFactory.fecharConexao(con);



    }
}
