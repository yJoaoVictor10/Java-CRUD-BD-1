package br.com.fiap.dao;

import br.com.fiap.dto.Carro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CarroDAO {
    private Connection con;

    public CarroDAO(Connection con) {
        this.con = con;
    }

    public Connection getCon() {
        return con;
    }

    public String inserir(Carro carro){
        String sql = "INSERT INTO ddd_carro(placa, cor, descricao) VALUES(?,?,?)";
        try(PreparedStatement ps = getCon().prepareStatement(sql);) { // try-with-resources
            ps.setString(1, carro.getPlaca());
            ps.setString(2, carro.getCor());
            ps.setString(3, carro.getDescricao());
            if(ps.executeUpdate() > 0){ //se conseguiu preencher pelo menos uma linha
                return "Inserido com sucesso";
            }else{
                return "Erro ao inserir";
            }

        } catch (SQLException e) {
            return "Erro de SQL\n" + e.getMessage();
        }
    }

    public String alterar(Carro carro){
        String sql = "UPDATE ddd_carro SET cor = ?, descricao = ? WHERE placa = ?";
        try(PreparedStatement ps = getCon().prepareStatement(sql);) {
            ps.setString(1, carro.getCor());
            ps.setString(2, carro.getDescricao());
            ps.setString(3, carro.getPlaca());
            if(ps.executeUpdate() > 0){
                return "Alterado com sucesso";
            }else{
                return "Erro ao alterar";
            }
        }catch (SQLException e){
            return "Erro de SQL\n" + e.getMessage();
        }
    }

    public String excluir(Carro carro){
        String sql = "DELETE FROM ddd_carro WHERE placa = ?";
        try(PreparedStatement ps = getCon().prepareStatement(sql);) {
            ps.setString(1, carro.getPlaca());
            if(ps.executeUpdate() > 0){
                return "Excluído com sucesso";
            }else{
                return "Erro ao excluír";
            }
        }catch (SQLException e){
            return "Erro del SQL\n" + e.getMessage();
        }
    }

    public ArrayList<Carro> listarTodos(){
        String sql = "SELECT * FROM ddd_carro ORDER BY placa";
        ArrayList<Carro> listaCarro = new ArrayList<>();
        try(PreparedStatement ps = getCon().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();){
            if(rs != null){
                while(rs.next()){ // Enquanto tiver linhas para ler
                    Carro carro = new Carro();
                    carro.setPlaca(rs.getString(1));
                    carro.setCor(rs.getString(2));
                    carro.setDescricao(rs.getString(3));
                    listaCarro.add(carro);
                }
                return listaCarro;

            }else{
                return null;
            }

        }catch(SQLException e){
            System.out.println("Erro de SQL\n" + e.getMessage());
            return null;
        }
    }
}