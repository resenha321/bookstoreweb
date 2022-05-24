<%-- 
    Document   : Login
    Created on : 9 de mai de 2022, 09:39:01
    Author     : devsys-a
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>

        <meta charset="utf-8">

        <title>Bookstore Website</title>

    </head>
    <body>


        <div id="login" style="text-align: center">

            <h1>Admin Login</h1>
            <form action="login" method="post">
                <label  for="email">Email:</label>
                <input  name="email" size="30" />
                <br><br>
                <label for="password">Password:</label>
                <input type="password" name="password" size="15" />
                <!--
                Esse atributo MESSAGE será utilizado como retorno de
               mensagem ao usuário caso
                login inválido.
                -->
                <br>${message}<br><br>
                <button type="submit">Login</button>

            </form>
        </div> 

        <style>

            #login{
                background-color:#4682B4;
                border: 2px solid black;
                border-color:black;
                width: 22%;
                height: 40%;
                text-align: center;
                position:absolute; 
                left: 39%;
                top: 15%;
                border-radius: 1%;
            }
            form{
                position: absolute;
                text-align: center;
                left: 8%;
                top:50%;
            }
            h1{
                position: absolute;
                top: 25%;
                left: 8%;
            }
            button{
                position: absolute;
                left: 40%; 

            }
        </style>

    </body> 

</html>
