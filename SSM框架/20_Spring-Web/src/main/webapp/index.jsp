<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
      <p>注册学生</p>
      <form action="/register" method="post" style="align-content: center">
          <table>
              <tr>
                  <td>id</td>
                  <td><label>
                      <input type="text" name="id"/>
                  </label></td>
              </tr>
              <tr>
                  <td>姓名</td>
                  <td><label>
                      <input type="text" name="name"/>
                  </label></td>
              </tr>
              <tr>
                  <td>email</td>
                  <td><label>
                      <input type="text" name="email"/>
                  </label></td>
              </tr>
              <tr>
                  <td>年龄</td>
                  <td><label>
                      <input type="text" name="age"/>
                  </label></td>
              </tr>
              <tr>
                  <td><input type="submit" value="注册学生"/></td>
              </tr>
          </table>
      </form>
</body>
</html>
