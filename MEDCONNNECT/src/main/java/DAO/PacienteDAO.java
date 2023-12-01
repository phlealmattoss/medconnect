package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import Conexao.conexao;
import org.w3c.dom.Text;
import MODEL.PacienteModel;

public class PacienteDAO {

    private static final String INSERIR_PACIENTE_SQL = "INSERT INTO Paciente (Id_Paciente, Nome_Completo, Idade, Celular, Email, Sexo, Dores_no_Corpo, Queixa_Principal_e_Duracao_da_Queixa, Alergias, Sintomas_de_Gripe, Endereco, Medicamentos_em_Uso, CEP, Sintomas_de_Febre, Numero, Cidade) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SELECIONAR_PACIENTES_SQL = "SELECT * FROM Paciente";

    public void cadastrarPaciente(PacienteModel pm) {

        try (Connection connection = Conexao.conexao.ConexaoSQL();
             PreparedStatement ps = connection.prepareStatement(INSERIR_PACIENTE_SQL)) {

            ps.setInt(1, pm.getId_Paciente());
            ps.setString(2, pm.getNome_Completo());
            ps.setInt(3, pm.getIdade());
            ps.setString(4, pm.getCelular());
            ps.setString(5, pm.getEmail());
            ps.setString(6, pm.getSexo());
            ps.setString(7, pm.getDores_no_Corpo());
            ps.setString(8, pm.getQueixa_Principal_e_Duracao_da_Queixa());
            ps.setString(9, pm.getAlergias());
            ps.setString(10, pm.getSintomas_de_Gripe());
            ps.setString(11, pm.getEndereco());
            ps.setString(12, pm.getMedicamento_em_Uso());
            ps.setString(13, pm.getCEP());
            ps.setString(14, pm.getEstado());
            ps.setString(15, pm.getSintomas_de_Febre());
            ps.setString(16, pm.getNumero());
            ps.setString(17, pm.getCidade());

            ps.execute();

            JOptionPane.showMessageDialog(null, "Triagem registrada com sucesso");

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public List<PacienteModel> mostrarPacientes() {

        List<PacienteModel> pacientes = new ArrayList<>();

        try (Connection connection = Conexao.conexao;
             PreparedStatement preparedStatement = connection.prepareStatement(SELECIONAR_PACIENTES_SQL);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                PacienteModel pm = new PacienteModel();

                pm.setId_Paciente(resultSet.getInt("Id_Paciente"));
                pm.setNome_Completo(resultSet.getString("Nome_Completo"));
                pm.setIdade(resultSet.getInt("Idade"));
                pm.setCelular(resultSet.getString("Celular"));
                pm.setEmail(resultSet.getString("Email"));
                pm.setSexo(resultSet.getString("Sexo"));
                pm.setDores_no_Corpo(resultSet.getString("Dores_no_Corpo"));
                pm.setQueixa_Principal_e_Duracao_da_Queixa(resultSet.getString("Queixa_Principal_e_Duracao_da_Queixa"));
                pm.setAlergias(resultSet.getString("Alergias"));
                pm.setSintomas_de_Gripe(resultSet.getString("Sintomas_de_Gripe"));
                pm.setEndereco(resultSet.getString("Endereco"));
                pm.setMedicamento_em_Uso(resultSet.getString("Medicamento_em_Uso"));
                pm.setCEP(resultSet.getString("CEP"));
                pm.setEstado(resultSet.getString("Estado"));
                pm.setSintomas_de_Febre(resultSet.getString("Sintomas_de_Febre"));
                pm.setNumero(resultSet.getString("Numero"));
                pm.setCidade(resultSet.getString("Cidade"));

                pacientes.add(pm);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return pacientes;
    }
}