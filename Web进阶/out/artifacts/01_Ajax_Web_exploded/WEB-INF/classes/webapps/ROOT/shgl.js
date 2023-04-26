$(function() {
  $.ajax({
    url: hostUrl + "wxGetShop",
    data: {},
    type: "GET",
    dataType: "json",
    success: function(data) {
      var hang, str = "";
      "111" == data ? $("#ts").css("display", "block") : (hang = eval(data), $.each(hang.data, function(a, b) {
        var p, c = b.id,
          d = b.qaddress,
          e = b.qnotice,
          f = b.qphone,
          g = hostUrl + "img/" + b.qshopIcon,
          h = b.qshopName,
          i = b.qpassword,
          j = b.qbaozhen,
          k = b.qwxh,
          l = b.qbalance,
          m = b.qsum,
          n = b.qtixianStatus,
          o = b.qcity;
        p = 1 == n ? "申请中" : "已提现", str += "<tr><td>" + c + "</td><td>" + d + "</td><td>" + e + "</td><td>" + f + "</td><td><img src=" + g + " onclick='showLarge(event,this)' /></td><td>" + h + "</td><td>" + i + "</td><td>" + j + "</td><td>" + k + "</td><td>" + l + "</td><td>" + m + "</td><td>" + p + "</td><td>" + o + "</td><td><div class ='one'><button class='btn btn-danger btn-xs sc1sh' bs='" + c + "'><i class='fa fa-trash-o'></i></button>&nbsp;&nbsp;&nbsp;<a data-toggle='modal' href='shgl.html#shglModal' class='btn btn-primary btn-xs xiugaish' bs='" + c + "'><i class='fa fa-pencil'></i></a></div></td></tr>"
      }), $("#shgl").html(str), $(".sc1sh").click(function() {
        var a = $(this).attr("bs"),
          b = 1;
        confirm("确定删除么") && $.ajax({
          url: hostUrl + "updateShop",
          data: {
            id: a,
            qdelete: b
          },
          type: "POST",
          dataType: "json",
          success: function(a) {
            "200" == a.code ? (alert("删除成功"), location.reload()) : alert("删除失败")
          }
        })
      }), $(".xiugaish").click(function() {
        var vId = $(this).attr("bs");
        $(".qr").attr("bs", vId), $.ajax({
          url: hostUrl + "wxGetShop",
          data: {
            id: vId
          },
          type: "POST",
          dataType: "json",
          success: function(data) {
            var str = "",
              hang = eval(data);
            $.each(hang.data, function(a, b) {
              var n, c = b.id,
                d = b.qaddress,
                e = b.qnotice,
                f = b.qphone,
                g = hostUrl + "img/" + b.qshopIcon,
                h = b.qshopName,
                i = b.qpassword,
                j = b.qbaozhen,
                k = b.qwxh,
                l = b.qbalance,
                m = b.qsum;
              qtixianStatus = b.qtixianStatus, n = b.qcity, str += "<div class='bj-input-group'><label>ID</label><input id='shId' disabled type='text' value='" + c + "'></div><div class='bj-input-group'><label>地址</label><input id='shQaddress' type='text' value='" + d + "'></div><div class='bj-input-group'><label>介绍</label><input id='shQnotice' type='text' value='" + e + "'></div><div class='bj-input-group'><label>电话</label><input id='shQphone' type='text' value='" + f + "'></div><div class='bj-input-group'><label>店铺LOGO</label><div class='oldimg showimgbox' id='imgshBox'><img src=" + g + " onclick='showLarge(event,this)' /></div><form id='imgshForm'><input type='file' name='file' id='imgsh' /><p id='imgshUrl' class='urlbox'></p><input type='button' value='上传' onclick='doUpload(imgsh)' /></form></div><div class='bj-input-group'><label>店铺名称</label><input id='shQshopName' type='text' value='" + h + "'></div><div class='bj-input-group'><label>密码</label><input id='shQpassword' type='text' value='" + i + "'></div><div class='bj-input-group'><label>保证</label><input id='shQbaozhen' type='text' value='" + j + "'></div><div class='bj-input-group'><label>微信号</label><input id='shQwxh' type='text' value='" + k + "'></div><div class='bj-input-group'><label>可提现金额</label><input id='shQbalance' type='text' value='" + l + "'></div><div class='bj-input-group'><label>交易总金额</label><input id='shQsum' type='text' value='" + m + "'></div> <div class='bj-input-group'><label>提现状态</label><select id='shQtixianStatus'><option value='1'>申请中</option><option value='2'>已提现</option></select></div><div class='bj-input-group'><label>城市</label><input id='shQcity' type='text' value='" + n + "'></div>"
            }), $("#xgsh").html(str), $("#shQtixianStatus").val(qtixianStatus), $(".qr").click(function() {
              var a = $(this).attr("bs"),
                b = $("#shQaddress").val(),
                c = $("#shQnotice").val(),
                d = $("#shQphone").val(),
                e = $("#imgshUrl").text(),
                f = $("#shQshopName").val(),
                g = $("#shQpassword").val(),
                h = $("#shQbaozhen").val(),
                i = $("#shQwxh").val(),
                j = $("#shQbalance").val(),
                k = $("#shQsum").val(),
                l = $("#shQtixianStatus").val(),
                m = $("#shQcity").val();
              $.ajax({
                url: hostUrl + "updateShop",
                data: {
                  id: a,
                  qaddress: b,
                  qnotice: c,
                  qphone: d,
                  qshopIcon: e,
                  qshopName: f,
                  qpassword: g,
                  qbaozhen: h,
                  qwxh: i,
                  qbalance: j,
                  qsum: k,
                  qtixianStatus: l,
                  qcity: m
                },
                type: "POST",
                dataType: "json",
                success: function(a) {
                  "200" == a.code ? (alert("修改成功"), location.reload()) : alert("修改失败")
                }
              })
            })
          }
        })
      }))
    }
  })
});
