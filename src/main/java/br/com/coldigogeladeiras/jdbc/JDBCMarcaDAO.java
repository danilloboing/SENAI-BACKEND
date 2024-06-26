package br.com.coldigogeladeiras.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonObject;

import br.com.coldigogeladeiras.jdbcinterface.MarcaDAO;
import br.com.coldigogeladeiras.modelo.Marca;
import br.com.coldigogeladeiras.modelo.Produto;


public class JDBCMarcaDAO implements MarcaDAO {

	private Connection conexao;

	public JDBCMarcaDAO(Connection conexao) {
		this.conexao = conexao;
	}

	public List<Marca> buscar() {
		String comando = "SELECT * FROM marcas";;
		List<Marca> listMarcas = new ArrayList<Marca>();
		Marca marca = null;

		try {
			Statement stmt = conexao.createStatement();
			ResultSet rs = stmt.executeQuery(comando);

			while (rs.next()) {
				marca = new Marca();
				int id = rs.getInt("id");
				String nome = rs.getString("nome");
				marca.setId(id);
				marca.setNome(nome);
				listMarcas.add(marca);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return listMarcas;
	}

	public boolean inserir(Marca marca) {

		String comando = "INSERT INTO marcas " + "(id, nome) " + "VALUES (?,?)";
		PreparedStatement p;

		try {
			p = this.conexao.prepareStatement(comando);

			p.setInt(1, marca.getId());
			p.setString(2, marca.getNome());
			p.execute();

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public List<JsonObject> buscarPorNome(String nome) {

		String comando = "SELECT * FROM marcas ";

		if (!nome.equals("")) {
			comando += " WHERE nome LIKE '%" + nome + "%'";
		}

		comando += " ORDER BY id ASC";

		System.out.println(comando);

		List<JsonObject> listaMarcas = new ArrayList<JsonObject>();
		JsonObject marca = null;

		try {
			Statement stmt = conexao.createStatement();
			ResultSet rs = stmt.executeQuery(comando);

			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("nome");

				marca = new JsonObject();
				marca.addProperty("id", id);
				marca.addProperty("nome", name);

				listaMarcas.add(marca);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return listaMarcas;
	}


	public boolean deletar(int id) {
		String verifyDeleteMarca = "SELECT COUNT(*) FROM produtos WHERE marcas_id = ?";
		String comando = "DELETE FROM marcas WHERE id = ?";
		PreparedStatement p;

		try {

			p = this.conexao.prepareStatement(verifyDeleteMarca);
			p.setInt(1, id);
			ResultSet resultSet = p.executeQuery();
			if (resultSet.next()) {
				int count = resultSet.getInt(1);
				if (count > 0) {
					System.out.println("Existem produtos associados a esta marca. Não é possível deletar.");
					return false;
				}
			}

			p = this.conexao.prepareStatement(comando);
			p.setInt(1, id);
			p.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public Marca buscarPorId(int id) {
		String comando = "SELECT * FROM marcas WHERE marcas.id = ?";
		Marca marca = new Marca();

		try {
			PreparedStatement p = this.conexao.prepareStatement(comando);
			p.setInt(1, id);
			ResultSet rs = p.executeQuery();

			while (rs.next()) {
				// System.out.println(comando + " id=> entrou aqui"+id);
				String nome = rs.getString("nome");

				marca.setId(id);
				marca.setNome(nome);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return marca;

	}

	public boolean alterar(Marca marca) {

		String comando = "UPDATE marcas " + " SET nome=?" + " WHERE id=?";
		PreparedStatement p;

		try {

			p = this.conexao.prepareStatement(comando);
			p.setString(1, marca.getNome());
			p.setInt(2, marca.getId());
			p.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;

	}

}
