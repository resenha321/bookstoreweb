<%-- 
    Document   : BookList
    Created on : 25 de abr de 2022, 10:10:36
    Author     : devsys-a
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
    <div id="header">
         <jsp:include page="./contents/headerTags.jsp"/>
    </div>
        <title>Aplicação BookStoreWeb</title>
         
        <style>
            container{
                background-color: #4682B4
            }
        </style>
    </head>
    <body>
        <div class="container">
            <jsp:include page="./contents/cabecalho.jsp"/>
            <div class="table-responsive">
            <table class="table table-hover">
                <caption><h2>List of Books</h2></caption>
                <tr>
                    <th>ID</th>
                    <th>Titulo</th>
                    <th>Autor</th>
                    <th>Preco</th>
                    <th>Ações</th>
                </tr>

                <c:forEach var="book" items="${listaBook}">
                    <tr>
                        <td><c:out value="${book.id}" /></td>
                        <td><c:out value="${book.titulo}" /></td>
                        <td><c:out value="${book.autor}" /></td>
                        <td><c:out value="${book.preco}" /></td>
                        <td>
                            <a href="<%=request.getContextPath()%>/bstore/edit?id=<c:out value='${book.id}' />">
                                <span class="glyphicon glyphicon-pencil"></span>
                            </a>
                            &nbsp;&nbsp;&nbsp;&nbsp;
                            <a
                                href="<%=request.getContextPath()%>/bstore/delete?id=<c:out value='${book.id}' />">
                                <span class="glyphicon glyphicon-trash"></span>
                            </a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
            </div>
            <jsp:include page="./contents/rodape.jsp" />
        </div>
       
    </body>
</html>
