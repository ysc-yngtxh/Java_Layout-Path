<%--
  Created by IntelliJ IDEA.
  User: 游诗成
  Date: 2020/9/2
  Time: 2:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>结合jQuery和Ajax技术实现级联查询</title>
    <script type="text/javascript" src="js/jquery-3.4.1.js"></script>
    <script type="text/javascript">
      function loadDataAjax() {
        $.ajax({
          url: "province",
          dataType: "json",
          success: function(resp) {
            $("#province").empty();
            $.each(resp, function(i,n) {
              $("#province").append("<option value='"+n.id+"'>"+n.name+"</option>");
            })
          }
        })
      }

      $(function(){
        // 这一步的作用是全局加载完后，直接显示省份信息，就不需要按钮点击后才显示。这样做用户体验会更好。
        loadDataAjax();

        $("#btn").click(function() {
          loadDataAjax()
        })

        // 给省份的select绑定一个change事件，当select内容发生变化时，触发事件
        $("#province").change(function() {
          let  obj = $("#province>option:selected");
          let provinceId = obj.val();
          $.ajax({
              url:"city",
              data: {
                "proid": provinceId
              },
              dataType: "json",
              success: function(resp) {
                $("#city").empty();
                $.each(resp, function(i,n){
                  $("#city").append("<option value='"+n.id+"'>"+n.name+"</option>");
                })
              }
          });
        })
      })
    </script>
  </head>
  <body>
        <table border="1" cellspacing="0" cellpadding="0" align="center">
          <tr>
            <td>省份:</td>
            <td>
              <select id="province">
                <option value="0">请选择......</option>
              </select>
              <input type="button" value="按钮" id="btn"/>
            </td>
          </tr>
          <tr>
            <td>城市:</td>
            <td>
              <select id="city">
                <option value="0">请选择......</option>
              </select>
            </td>
          </tr>
        </table>
  </body>
</html>
