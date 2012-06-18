<%-- 
    Document   : index
    Created on : Jun 13, 2012, 3:44:57 PM
    Author     : Tej
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Spam Mail Checker home page</title>
    </head>
    <body>
        <h1>Naives Bayes spam filter</h1>
        <form name="myForm" action="SpamCheckerServlet">
            <input type="text" name="email" value="" size="200" />
            <input type="submit" value="CheckSpam" name="checkSpam" />
        </form>
    </body>
</html>
