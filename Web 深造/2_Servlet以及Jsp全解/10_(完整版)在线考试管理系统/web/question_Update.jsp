<%--
  Created by IntelliJ IDEA.
  User: 游家纨绔
  Date: 2020/8/22
  Time: 15:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="com.example.entity.Question" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <%
        Question ques = (Question) request.getAttribute("key");
    %>
    <center>
        <form action="/myWeb/question/update" method="get">
            <table border="1">
                <tr>
                    <td>题目编号</td>
                    <td><input type="text" name="questionId" value="<%=ques.getQuestionId()%>" readonly/></td>
                </tr>
                <tr>
                    <td>题目:</td>
                    <td><input type="text" name="title" value="<%=ques.getTitle()%>"/></td>
                </tr>
                <tr>
                    <td>A:</td>
                    <td><input type="text" name="optionA" value="<%=ques.getOptionA()%>"/></td>
                </tr>
                <tr>
                    <td>B:</td>
                    <td><input type="text" name="optionB" value="<%=ques.getOptionB()%>"/></td>
                </tr>
                <tr>
                    <td>C:</td>
                    <td><input type="text" name="optionC" value="<%=ques.getOptionC()%>"/></td>
                </tr>
                <tr>
                    <td>D:</td>
                    <td><input type="text" name="optionD" value="<%=ques.getOptionD()%>"/></td>
                </tr>
                <tr>
                    <td>正确答案:</td>
                    <td>
                        <input type="radio" name="answer" value="A" <%="A".equals(ques.getAnswer()) ? "checked" : ""%>/>A
                        <input type="radio" name="answer" value="B" <%="B".equals(ques.getAnswer()) ? "checked" : ""%>/>B
                        <input type="radio" name="answer" value="C" <%="C".equals(ques.getAnswer()) ? "checked" : ""%>/>C
                        <input type="radio" name="answer" value="D" <%="D".equals(ques.getAnswer()) ? "checked" : ""%>/>D
                    </td>
                </tr>
                <tr align="center">
                    <td colspan="2">
                        <input type="submit" value="更新试题"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <input type="reset" value="重置"/>
                    </td>
                </tr>
            </table>
        </form>
    </center>
</body>
</html>
