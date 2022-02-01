package controller;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.ContatoDAO;
import model.Contato;

@WebServlet(urlPatterns = { "/Controller", "/main", "/insert", "/select","/update","/delete","/sn"})
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ContatoDAO contatoDao = new ContatoDAO();
	Contato contato = new Contato();

	public Controller() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();
		System.out.println(action);
		if (action.equals("/main")) {
			contatos(request, response);
		} else if (action.equals("/insert")) {
			novoContato(request, response);
		} else if (action.equals("/select")) {
			editarContato(request, response);
		} else if (action.equals("/update")) {
			alterarContato(request, response);
		}else if (action.equals("/delete")) {
			deleteContato(request, response);
		}else if (action.equals("/sn")) {
			selectContato(request, response);
		} else {
			response.sendRedirect("index.html");
		}
		// dao.testeConexao();
	}

	// Listar Contatos
	protected void contatos(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ArrayList<Contato> lista = contatoDao.listarContatos();
//		for (int i = 0; i < lista.size(); i++) {
//			System.out.println(lista.get(i).getNome());
//			
//		}
		request.setAttribute("contatos", lista);
		RequestDispatcher rd = request.getRequestDispatcher("agenda.jsp");
		rd.forward(request, response);

	}

	// Inserir Contato
	protected void novoContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

//		System.out.println(request.getParameter("nome"));
//		System.out.println(request.getParameter("fone"));
//		System.out.println(request.getParameter("email"));
//		

		contato.setNome(request.getParameter("nome"));
		contato.setFone(request.getParameter("fone"));
		contato.setEmail(request.getParameter("email"));

		contatoDao.inserirContato(contato);

		// ir para agenda jsp

		response.sendRedirect("main");

	}
	
	// Editar Contato
	protected void editarContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idcon = request.getParameter("idcon");
//		System.out.println(idcon);
		contato.setIdcon(idcon);
		contatoDao.selecionarContato(contato);
//		System.out.println(contato.getIdcon());
//		System.out.println(contato.getNome());
//		System.out.println(contato.getFone());
//		System.out.println(contato.getEmail());
		request.setAttribute("contato", contato);
		RequestDispatcher rd = request.getRequestDispatcher("editar.jsp");
		rd.forward(request, response);
	}

	// Alterar Contato
	protected void alterarContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("id= "+request.getParameter("idcon"));
		contato.setIdcon(request.getParameter("idcon"));
		contato.setNome(request.getParameter("nome"));
		contato.setFone(request.getParameter("fone"));
		contato.setEmail(request.getParameter("email"));

		contatoDao.alterarContato(contato);
		response.sendRedirect("main");
	}
	
	// Remover Contato
	protected void deleteContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idcon= request.getParameter("idcon");
		contato.setIdcon(idcon);
		contatoDao.deleteContato(contato);
		response.sendRedirect("main");
		System.out.println(idcon);
		
	}
	// select nome Contato
	protected void selectContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
	String aux = request.getParameter("nome");
	contatoDao.listarNomeContatos(aux);
	ArrayList<Contato> lista = contatoDao.listarNomeContatos(aux);
//	for (int i = 0; i < lista.size(); i++) {
//		System.out.println(lista.get(i).getNome());
//		
//	}
	request.setAttribute("contatos", lista);
	request.setAttribute("aux", aux);
	RequestDispatcher rd = request.getRequestDispatcher("agenda.jsp");
	rd.forward(request, response);
	}
}

























