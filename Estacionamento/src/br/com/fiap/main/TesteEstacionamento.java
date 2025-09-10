package br.com.fiap.main;

import br.com.fiap.dao.CarroDAO;
import br.com.fiap.dao.ClienteDAO;
import br.com.fiap.dao.ConnectionFactory;
import br.com.fiap.dto.Carro;
import br.com.fiap.dto.Cliente;

import javax.swing.*;
import java.sql.Connection;
import java.util.ArrayList;

public class TesteEstacionamento {
    public static void main(String[] args) {
    String resultadoMenu;
    int idCliente;

    try {
        do{
            resultadoMenu = JOptionPane.showInputDialog("1 - Manipular carro\n2 - Manipular cliente");
            switch (resultadoMenu){
                case "1":
                    manipularCarro();
                    break;
                case "2":
                    manipularCliente();
                    break;
                default:
                    JOptionPane.showInputDialog("Erro ao escolher");
                    break;
            }

        }while (JOptionPane.showConfirmDialog(null, "Deseja continuar?", "Atenção", JOptionPane.YES_NO_OPTION) == 0);
        JOptionPane.showMessageDialog(null, "Obrigado por usar o sistema!");



    }catch (Exception e){
        JOptionPane.showMessageDialog(null, e.getMessage());
    }

    }
    public static void manipularCarro(){
        String placa, cor, descricao;
        Connection con = ConnectionFactory.abrirConexao();
        Carro carro = new Carro();
        CarroDAO carroDAO = new CarroDAO(con);
        String opcao = JOptionPane.showInputDialog("1 - Inserir\n2 - Alterar\n 3 - Excluir");
        switch (opcao){
            case "1":
                placa = JOptionPane.showInputDialog("Digite a placa do carro");
                cor = JOptionPane.showInputDialog("Digite a cor do carro");descricao = JOptionPane.showInputDialog("Digite a descrição");
                carro.setPlaca(placa);
                carro.setCor(cor);
                carro.setDescricao(descricao);
                JOptionPane.showMessageDialog(null, carroDAO.inserir(carro));
                break;

            case "2":
                placa = JOptionPane.showInputDialog("Digite a placa do carro");
                cor = JOptionPane.showInputDialog("Digite a nova cor do carro");
                descricao = JOptionPane.showInputDialog("Digite a nova descrição");
                carro.setPlaca(placa);
                carro.setCor(cor);
                carro.setDescricao(descricao);
                JOptionPane.showMessageDialog(null, carroDAO.alterar(carro));
                break;

            case "3":
                placa = JOptionPane.showInputDialog("Digite a placa do carro à ser excluído");
                carro.setPlaca(placa);
                JOptionPane.showMessageDialog(null, carroDAO.excluir(carro));
                break;

            default:
                break;
        }

        ArrayList<Carro> listarCarros = carroDAO.listarTodos();
        if(listarCarros != null){
            String listaCar = "Carros cadastrados:\\n\\n";
            for(Carro carro1 : listarCarros){
                listaCar += "Placa: " + carro1.getPlaca() + "\nCor:" + carro1.getCor() + "\nDescrição: " + carro1.getDescricao();
            }
            JOptionPane.showMessageDialog(null, listaCar);
        }

        ConnectionFactory.fecharConexao(con);

    }

    public static void manipularCliente(){
        String nomeCliente, placa;
        int idCliente;
        Connection con = ConnectionFactory.abrirConexao();
        Cliente cliente = new Cliente();
        ClienteDAO clienteDAO = new ClienteDAO(con);
        String opcao = JOptionPane.showInputDialog("1 - Inserir\n2 - Alterar\n 3 - Excluir");
        switch (opcao){
            case "1":
                idCliente = Integer.parseInt(JOptionPane.showInputDialog("Digite o ID do cliente"));
                nomeCliente = JOptionPane.showInputDialog("Digite o nome do cliente");
                placa = JOptionPane.showInputDialog("Digite a placa do carro");
                cliente.setIdCliente(idCliente);
                cliente.setNomeCliente(nomeCliente);
                cliente.setPlaca(placa);
                JOptionPane.showMessageDialog(null, clienteDAO.inserir(cliente));
                break;

            case "2":
                idCliente = Integer.parseInt(JOptionPane.showInputDialog("Digite o ID do cliente que deseja alterar"));
                nomeCliente = JOptionPane.showInputDialog("Digite o novo nome do cliente");
                placa = JOptionPane.showInputDialog("Digite a nova placa do carro");
                cliente.setIdCliente(idCliente);
                cliente.setNomeCliente(nomeCliente);
                cliente.setPlaca(placa);
                JOptionPane.showMessageDialog(null, clienteDAO.alterar(cliente));
                break;

            case "3":
                idCliente = Integer.parseInt(JOptionPane.showInputDialog("Digite o ID do cliente à ser excluído"));
                cliente.setIdCliente(idCliente);
                JOptionPane.showMessageDialog(null, clienteDAO.excluir(cliente));
                break;

            default:
                JOptionPane.showMessageDialog(null, "Opção incorreta");
                break;
            }

            ArrayList<Cliente> listarClientes = clienteDAO.listarTodos();
            if(listarClientes != null){
                String listaCli = "Clientes cadastrados:\\n\\n";
                for(Cliente cliente1 : listarClientes){
                    listaCli += "ID: " + cliente1.getIdCliente() + "\nNome: " + cliente1.getNomeCliente() + "\nPlaca: " + cliente1.getPlaca() + "\n-----------------\n";
                }
                JOptionPane.showMessageDialog(null, listaCli);
            }


        ConnectionFactory.fecharConexao(con);
    }
}
