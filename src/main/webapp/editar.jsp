<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="model.Contato"%>
<%
Contato contato = (Contato)request.getAttribute("contato");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Agendas de contatos</title>
<link rel="icon" href="imagem/favicon.png">
<link rel="stylesheet" href="style.css">
</head>
<body>
<div align="center">
	<h1>Editar Contato</h1>
	<form name="frmContato" action="update">
		<table>
			<tr>
				<td><input type="hidden" name="idcon" value="<%=contato.getIdcon() %> " id="caixa3"></td>

			</tr>
			<tr>
				<td><input type="text" name="nome" value="<%=contato.getNome() %>" class="Caixa1"></td>

			</tr>
			<tr>
				<td><input type="text" name="fone" value="<%=contato.getFone() %>" class="Caixa2"></td>
			</tr>
			<tr>
				<td><input type="text" name="email" value="<%=contato.getEmail() %>" class="Caixa1"></td>
			</tr>
		</table>
		 <!--<input type="button" class="Botao1" value="Salvar" onclick="validar()"> -->
		<a	class="Botao1" onclick="validar()">Salvar</a> <span class="espaco">
		<a href="main"	class="Botao1">Cancelar</a>

	</form>
	<script src="scripts/validador.js"></script>
</div>
</body>
</html>