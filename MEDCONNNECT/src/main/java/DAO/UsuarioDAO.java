package DAO;

import MODEL.UsuarioModel;
import Conexao.conexao;
import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    public void CadastrarUsuario(UsuarioModel um) {

        String sql = "INSERT INTO Usuario (Id_Usuario, Nome, Sobrenome, Nascimento, Sexo, Email, Senha, Confirma_Senha) VALUES (?,?,?,?,?,?,?,?)";

        try (Connection connection = Conexao.conexao.ConexaoSQL();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, um.getId_Usuario());
            ps.setString(2, um.getNome());
            ps.setString(3, um.getSobrenome());
            ps.setDate(4, um.getNascimento());
            ps.setString(5, um.getSexo());
            ps.setString(6, um.getEmail());
            ps.setString(7, um.getSenha());
            ps.setString(8, um.getConfirma_Senha());

            ps.execute();

            JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso");

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
    
     public void AtualizarUsurio(UsuarioModel UsuarioModel) {
        String sql = "UPDATE Usuario SET Senha=? WHERE Confirma_Senha=?";

        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = conexao.ConexaoSQL()Connection();
            ps = con.prepareStatement(sql);

            ps.setString(1, UsuarioModel.getSenha());

            ps.setString(2, UsuarioModel.getConfirma_Senha());

            ps.execute();

        } catch (Exception e) {
            System.out.println("Erro ao atualizar (DAO) " + e);
        }
    }

    public List<UsuarioModel> mostrarUsuarios() {

        String sql = "SELECT * FROM Usuario";

        List<UsuarioModel> usuarios = new ArrayList<>();

        try (Connection connection = Conexao.conexao.ConexaoSQL();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {

                UsuarioModel um = new UsuarioModel();

                um.setId_Usuario(resultSet.getInt("Id_Usuario"));
                um.setNome(resultSet.getString("Nome"));
                um.setSobrenome(resultSet.getString("Sobrenome"));
                um.setNascimento(resultSet.getDate("Nascimento"));
                um.setSexo(resultSet.getString("Sexo"));
                um.setEmail(resultSet.getString("Email"));
                um.setSenha(resultSet.getString("Senha"));
                um.setConfirma_Senha(resultSet.getString("Confirma_Senha"));

                usuarios.add(um);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return usuarios;
    }


   

}
