<%-- 
    Document   : cabecalho
    Created on : 25 de abr de 2022, 13:50:47
    Author     : KGe
--%>

<!-- Inicio cabecalho-->
<br></br>
<div id="home"></span><h1><span class="glyphicon glyphicon-book" > BookStoreWeb</h1></div>  


<br></br>

<br></br>
<p>
    <a href="<%=request.getContextPath()%>/login" class="btn btn-primary">
        <span class="glyphicon glyphicon-plus"></span>login</a>

        <a href="<%=request.getContextPath()%>/bstore/list" class="btn btn-default">
        <span class="glyphicon glyphicon-th-list"></span>
        Lista de Usuarios</a>
</p>

<p>
    <a href="<%=request.getContextPath()%>/bstore/new" class="btn btn-primary">
        <span class="glyphicon glyphicon-plus"></span>Adicionar novo Livro</a>

        <a href="<%=request.getContextPath()%>/bstore/list" class="btn btn-default">
        <span class="glyphicon glyphicon-th-list"></span>
        Lista todos Livros</a>
</p>
<br></br>
<style>
    #home{
        background-color: #4682B4;
        color: white;
        text-align: center;
        padding:  50px;
        border: 2px solid black;
        }
        
        
    
    
</style>
<!-- Fim cabecalho-->
