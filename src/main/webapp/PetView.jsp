<!DOCTYPE html>
<%@page import="java.util.ArrayList"%>
<%@page import="jpa.Pet"%>
<html>

  <head>
    <title>Pets</title>
  </head>

  <body>
    <h1>Pet</h1>
    <form>
      <table>
        <tr>
          <td>Cod:</td>
          <td><input name="cod"></td>
        </tr>
        <tr>
          <td>Nome:</td>
          <td><input name="nome"></td>
        </tr> 
         <tr>
          <td>Apelido:</td>
          <td><input name="apelido"></td>
        </tr>        
         <tr>
          <td>Raça:</td>
          <td><input name="raca"></td>
        </tr>        
         <tr>
          <td>Descrição:</td>
          <td><input name="descricao"></td>
        </tr>        
        <tr>
          <td>Dono:</td>
          <td><input name="dono"></td>
        </tr>
        <tr>
          <td>Telefone:</td>
          <td><input name="telefone"></td>
        </tr>     
      </table>
      <button name="operacao" value="incluir">Incluir</button>
      <button name="operacao" value="excluir">Excluir</button>
      <button name="operacao" value="alterar">Alterar</button>
    </form>
    <b>${msg}</b>
    <hr>
    <table border="1">
      <tr>
        <th>Codigo</th>
        <th>Nome</th>
        <th>Ações</th>
      </tr>
      <%
      @SuppressWarnings("unchecked")
      ArrayList<Pet> pets = (ArrayList<Pet>) request.getAttribute("pets");
      for (Pet pet : pets) {
      %>
      <tr>
        <td><%=pet.getCod()%></td>
        <td><%=pet.getNome()%></td>
        <!--  <td><a href="pets?operacao=excluir&cod=<%=pet.getCod()%>">Excluir</a></td> -->
      </tr>
      <%}%>
    </table>
  </body>

</html>