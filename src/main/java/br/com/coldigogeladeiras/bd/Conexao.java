package br.com.coldigogeladeiras.bd;

import java.sql.Connection;

public class Conexao {

	private Connection conexao;

	public Connection abrirConexao() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
			conexao = java.sql.DriverManager.getConnection("jdbc:mysql://localhost/dbcoldigo?"
					+ "user=root&password=root&useTimezone=true&serverTimezone=UTC");

		} catch (Exception error) {
			error.printStackTrace();
		}
		return conexao;
	}

	public void fecharConexao() {
		try {
			conexao.close();
		} catch (Exception error) {
			error.printStackTrace();
		}
	}

}
