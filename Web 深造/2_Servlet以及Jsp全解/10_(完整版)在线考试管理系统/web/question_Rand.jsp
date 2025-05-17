<%--
  Created by IntelliJ IDEA.
  User: 游家纨绔
  Date: 2020/8/22
  Time: 21:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.util.List" %>
<%@ page import="com.example.dao.QuestionDao" %>
<%@ page import="com.example.entity.Question" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <center>
        <form action="/myWeb/exam">
            <table border="1">
                <tr align="center">
                    <td>题目编号</td>
                    <td>题目</td>
                    <td>A</td>
                    <td>B</td>
                    <td>C</td>
                    <td>D</td>
                    <td>正确选项</td>
                </tr>
                <%
                    List<Question> questionList = (List) session.getAttribute("key");
                    for (Question qu : questionList) {
                %>
                <tr align="center">
                    <td><%=qu.getQuestionId()%></td>
                    <td><%=qu.getTitle()%></td>
                    <td><%=qu.getOptionA()%></td>
                    <td><%=qu.getOptionB()%></td>
                    <td><%=qu.getOptionC()%></td>
                    <td><%=qu.getOptionD()%></td>
                    <td>
                        <input type="radio" name="answer_<%=qu.getQuestionId()%>" value="A"/>A
                        <input type="radio" name="answer_<%=qu.getQuestionId()%>" value="B"/>B
                        <input type="radio" name="answer_<%=qu.getQuestionId()%>" value="C"/>C
                        <input type="radio" name="answer_<%=qu.getQuestionId()%>" value="D"/>D
                    </td>
                </tr>
                <%
                    }
                %>
                <tr>
                    <td colspan="7" align="center">
                        <input type="submit" value="提交试卷"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <input type="reset" value="重做"/>
                    </td>
                </tr>
            </table>
        </form>
    </center>
</body>
</html>
