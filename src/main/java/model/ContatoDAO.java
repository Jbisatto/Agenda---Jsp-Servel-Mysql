package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ContatoDAO {
	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://localhost:3306/dbagenda?useTimezone=true&serverTimezone=UTC";
	private String user = "root";
	private String passorwd = "bisatto";

	private Connection conectar() {
		Connection con = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, passorwd);
			return con;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	/** CRUD CREATE **/
	public void inserirContato(Contato contato) {
		String create = "insert into contatos (nome,fone,email) values(?,?,?)";
		try {

			/** Abre a conexão **/
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(create);

			/** Substitui os paramentros da Query */
			pst.setString(1, contato.getNome());
			pst.setString(2, contato.getFone());
			pst.setString(3, contato.getEmail());

			/** Executar a query */
			pst.execute();

			/** Encerrar conexão com bd **/

			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	/** CRUD READ **/
	public ArrayList<Contato> listarContatos(){
		String read ="select * from contatos order by nome";
		ArrayList<Contato> contatos = new ArrayList<>();
		try {

			/** Abre a conexão **/
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(read);
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()) {
				String idcon = rs.getString(1);
				String nome = rs.getString(2);
				String fone = rs.getString(3);
				String email = rs.getString(4);	
				
				contatos.add(new Contato(idcon,nome,fone,email));
			}
			con.close();
			return contatos;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
	
	
//	public void testeConexao() {
//		try {
//			Connection con = conectar();
//			System.out.println(con);
//			con.close();
//		} catch (Exception e) {
//			System.out.println(e);
//		}
//	}

	/** CRUD Update **/
//	select * from contatos where idcon=4;
	public void selecionarContato(Contato contato) {
		String read2 = "select * from contatos where idcon = ?";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(read2);

			/** Substitui os paramentros da Query */
			pst.setString(1, contato.getIdcon());
			
			ResultSet rs = pst.executeQuery();

		while(rs.next()) {
				contato.setIdcon(rs.getString(1));
				contato.setNome(rs.getString(2));
				contato.setFone(rs.getString(3));
				contato.setEmail(rs.getString(4));
	}
			
			/** Encerrar conexão com bd **/
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		
	}

	//Crud Update
	public void alterarContato(Contato contato) {
		String alterar = "update contatos set nome= ?,fone= ?,email=? where idcon= ?";
		
		try {

			/** Abre a conexão **/
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(alterar);

			/** Substitui os paramentros da Query */
			pst.setString(1, contato.getNome());
			pst.setString(2, contato.getFone());
			pst.setString(3, contato.getEmail());
			pst.setString(4, contato.getIdcon());

			/** Executar a query */
			pst.execute();

			/** Encerrar conexão com bd **/

			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	//Crud Delete
		public void deleteContato(Contato contato) {
			String delete = "delete from contatos where idcon= ?";
			
			try {

				/** Abre a conexão **/
				Connection con = conectar();
				PreparedStatement pst = con.prepareStatement(delete);

				/** Substitui os paramentros da Query */

				pst.setString(1, contato.getIdcon());

				/** Executar a query */
				pst.execute();

				/** Encerrar conexão com bd **/

				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
//		select * from contatos where idcon=4;
		public ArrayList<Contato> listarNomeContatos(String aux){
			String read ="select * from contatos where nome like ? '%' order by nome";
			ArrayList<Contato> contatos = new ArrayList<>();
			try {

				/** Abre a conexão **/
				Connection con = conectar();
				PreparedStatement pst = con.prepareStatement(read);
				
				pst.setString(1, aux);
				ResultSet rs = pst.executeQuery();
				
				while(rs.next()) {
					String idcon = rs.getString(1);
					String nome = rs.getString(2);
					String fone = rs.getString(3);
					String email = rs.getString(4);	
					
					contatos.add(new Contato(idcon,nome,fone,email));
				}
				con.close();
				return contatos;
			} catch (Exception e) {
				System.out.println(e);
				return null;
			}
		}
	
}
