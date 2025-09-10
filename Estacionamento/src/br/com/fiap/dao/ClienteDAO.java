package br.com.fiap.dao;

import br.com.fiap.dto.Carro;
import br.com.fiap.dto.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ClienteDAO {
    private Connection con;

    public ClienteDAO(Connection con) {
        this.con = con;
    }

    public Connection getCon() {
        return con;
    }

    public String inserir(Cliente cliente){
        String sql = "INSERT INTO ddd_cliente(id_cliente, nome_cliente, placa) VALUES(?,?,?)";
        try(PreparedStatement ps = getCon().prepareStatement(sql);) { // try-with-resources
            ps.setInt(1, cliente.getIdCliente());
            ps.setString(2, cliente.getNomeCliente());
            ps.setString(3, cliente.getPlaca());
            if(ps.executeUpdate() > 0){ //se conseguiu preencher pelo menos uma linha
                return "Inserido com sucesso";
            }else{
                return "Erro ao inserir";
            }

        } catch (SQLException e) {
            return "Erro de SQL\n" + e.getMessage();
        }
    }

    public String alterar(Cliente cliente){
        String sql = "UPDATE ddd_cliente SET nome_cliente = ?, placa = ? WHERE id_cliente = ?";
        try(PreparedStatement ps = getCon().prepareStatement(sql);) {
            ps.setString(1, cliente.getNomeCliente());
            ps.setString(2, cliente.getPlaca());
            ps.setInt(3, cliente.getIdCliente());
            if(ps.executeUpdate() > 0){
                return "Alterado com sucesso";
            }else{
                return "Erro ao alterar";
            }
        }catch (SQLException e){
            return "Erro de SQL\n" + e.getMessage();
        }
    }

    public String excluir(Cliente cliente){
        String sql = "DELETE FROM ddd_cliente WHERE id_cliente = ?";
        try(PreparedStatement ps = getCon().prepareStatement(sql);) {
            ps.setInt(1, cliente.getIdCliente());
            if(ps.executeUpdate() > 0){
                return "Excluído com sucesso";
            }else{
                return "Erro ao excluír";
            }
        }catch (SQLException e){
            return "Erro del SQL\n" + e.getMessage();
        }
    }

    public ArrayList<Cliente> listarTodos(){
        String sql = "SELECT * FROM ddd_cliente ORDER BY id_cliente";
        ArrayList<Cliente> listaCliente = new ArrayList<>();
        try(PreparedStatement ps = getCon().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();){
            if(rs != null){
                while(rs.next()){ // Enquanto tiver linhas para ler
                    Cliente cliente = new Cliente();
                    cliente.setIdCliente(rs.getInt(1));
                    cliente.setNomeCliente(rs.getString(2));
                    cliente.setPlaca(rs.getString(3));
                    listaCliente.add(cliente);
                }
                return listaCliente;

            }else{
                return null;
            }

        }catch(SQLException e){
            System.out.println("Erro de SQL\n" + e.getMessage());
            return null;
        }
    }
}
