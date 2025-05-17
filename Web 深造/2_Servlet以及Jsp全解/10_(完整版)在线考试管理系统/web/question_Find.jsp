<%--
  Created by IntelliJ IDEA.
  User: 游家纨绔
  Date: 2020/8/21
  Time: 10:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.dao.QuestionDao" %>
<%@ page import="com.example.entity.Question" %>
<%@ page import="java.util.List" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <%
        QuestionDao dao = new QuestionDao();
        List<Question> questionList = dao.findAll();
    %>
    <table border="1" align="center">
        <tr align="center">
            <td>试题编号</td>
            <td>试题信息</td>
            <td>A选项</td>
            <td>B选项</td>
            <td>C选项</td>
            <td>D选项</td>
            <td>正确答案</td>
            <td colspan="2">操作</td>
        </tr>
        <%
            for (Question que : questionList) {
        %>
        <tr align="center">
            <td><%=que.getQuestionId()%>
            </td>
            <td><%=que.getTitle()%>
            </td>
            <td><%=que.getOptionA()%>
            </td>
            <td><%=que.getOptionB()%>
            </td>
            <td><%=que.getOptionC()%>
            </td>
            <td><%=que.getOptionD()%>
            </td>
            <td><%=que.getAnswer()%>
            </td>
            <td>
                <a href="/myWeb/question/delete?questionId=<%=que.getQuestionId()%>">删除试题信息</a>
            </td>
            <td>
                <a href="/myWeb/question/findById?questionId=<%=que.getQuestionId()%>">详细信息</a>
            </td>
        </tr>
        <%
            }
        %>
    </table>
</body>
</html>
