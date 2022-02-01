<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="java.util.ArrayList"%>
<%@page import="model.Contato"%>
<%
@SuppressWarnings("unchecked")
ArrayList<Contato> lista = (ArrayList<Contato>) request.getAttribute("contatos");
//	for(int i=0;i<lista.size();i++){
//	out.println(lista.get(i).getNome());
//	}
String aux= (String)request.getAttribute("aux");
if(aux==null){
	aux="";
}
%>

<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="ISO-8859-1">
<title>Agenda de contatos</title>
<link rel="icon" href="imagem/favicon.png">
<link rel="stylesheet" href="style.css">
</head>
<body>
	<div align="center">
		<h1>Agenda de Contatos</h1>

		<a href="novo.html" class="Botao1"><img src="imagem/maiss.png"
			height="15" width="15"><span class="espaco2">Novo
				contato</a> 
				<br> <br> 
		<input autofocus="autofocus" type="text" name="nomep" id="nomepp" value="<%=aux%>"
			placeholder="Pesquisar nome" onkeyup="buscar()" class="Caixa1"> <br>
		<br>
		<table id="tabela" class="tabela">
			<thead>
				<tr>
					<td>Id</td>
					<td>Nome</td>
					<td>Fone</td>
					<td>Email</td>
					<td style="text-align: center; vertical-align: middle;">Opções</td>
				</tr>
			</thead>
			<tbody>
				<%
				for (int i = 0; i < lista.size(); i++) {
				%>
				<tr>
					<td><%=lista.get(i).getIdcon()%></td>
					<td><%=lista.get(i).getNome()%></td>
					<td><%=lista.get(i).getFone()%></td>
					<td><%=lista.get(i).getEmail()%></td>
					<td><a href="select?idcon=<%=lista.get(i).getIdcon()%>"
						class="Botaoeditar">Editar</a> <a
						href="javascript:confirmar(<%=lista.get(i).getIdcon()%>)"
						class="Botao2">Excluir</a></td>
				</tr>
				<%
				}
				%>
			</tbody>

		</table>

	</div>
	<script src="scripts/confirmador.js"></script>
	<script src="scripts/busca.js"></script>
</body>
</html>