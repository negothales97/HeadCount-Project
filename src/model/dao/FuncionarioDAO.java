package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.connection.Database;
import model.vo.Funcionario;

public class FuncionarioDAO {


	public void create(String nome, String matricula, String cargo, String salario, String cpf, String datanasc, String setor, String epi, String status ) throws SQLException {
		try (Connection con = Database.getConnection()) {

			String sql = "INSERT INTO FILIAL (nome,matricula,cargo,salario,cpf,datanasc,setor,epi,status  ) values (?, ?, ?)";

			try (PreparedStatement stmt = con.prepareStatement(sql)) {

				stmt.setString(1, nome);
				stmt.setString(2, matricula);
				stmt.setString(3, cargo);
				stmt.setString(4, salario);
				stmt.setString(5, cpf);
				stmt.setString(6, datanasc);
				stmt.setString(7, setor);
				stmt.setString(8, epi);
				stmt.setString(9, status);
				
				stmt.execute();
			}
		}
	}

	public List<Funcionario> read(String nomeSearch) throws SQLException {
		List<Funcionario> funcionario = new ArrayList<>();
		try (Connection con = Database.getConnection()) {
			String sql = "SELECT * FROM FUNCIONARIO WHERE nome LIKE %?%";
			try (PreparedStatement stmt = con.prepareStatement(sql)) {
				stmt.setString(1, nomeSearch);
				stmt.execute();
				ResultSet rs = stmt.getResultSet();
				while (rs.next()) {
					String nome = rs.getString("nome");
					String matricula = rs.getString("matricula");
					String cargo = rs.getString("cargo");
					String salario = rs.getString("salario");
					String cpf = rs.getString("cpf");
					String datanasc = rs.getString("datanasc");
					String setor = rs.getString("setor");
					String epi = rs.getString("epi");
					String status = rs.getString("status");
					Funcionario funcionario1 = new Funcionario();

					funcionario1.setNome(nome);
					funcionario1.setMatricula(matricula);
					funcionario1.setCargo(cargo);
					funcionario1.setSalario(salario);
					funcionario1.setCpf(cpf);
					funcionario1.setDatanasc(datanasc);
					funcionario1.setSetor(setor);
					funcionario1.setStatus(status);

					funcionario.add(funcionario1);
				}
			}

		}
		return funcionario;
	}

	public void update(Funcionario funcionario) throws SQLException {
		try (Connection con = Database.getConnection()) {
			String sql = "UPDATE FILIAL SET nome=?, matricula=?, cargo=?, salario=?, cpf=?, datanasc=?, setor=?, epi=?, status=? WHERE matricula=?";

			try (PreparedStatement stmt = con.prepareStatement(sql)) {
				stmt.setString(1, funcionario.getNome());
				stmt.setString(2, funcionario.getMatricula());
				stmt.setString(3, funcionario.getCargo());
				stmt.setString(5, funcionario.getSalario());
				stmt.setString(6, funcionario.getCpf());
				stmt.setString(7, funcionario.getDatanasc());
				stmt.setString(8, funcionario.getSetor());
				stmt.setString(9, funcionario.getEpi());
				stmt.setString(10, funcionario.getStatus());

				stmt.execute();
			}
		}
	}

	public void delete(Funcionario funcionario) throws SQLException {
		try (Connection con = Database.getConnection()) {
			String sql = "DELETE FROM FUNCIONARIO WHERE id=?";

			try (PreparedStatement stmt = con.prepareStatement(sql)) {
				stmt.setString(1, funcionario.getMatricula());
				stmt.execute();
			}
		}
	}
}