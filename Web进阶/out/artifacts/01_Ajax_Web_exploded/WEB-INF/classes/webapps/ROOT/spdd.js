function cxdd(a) {
  $.ajax({
    url: hostUrl + "getOrder",
    data: {
      qshopid: shUserId,
      qstatus: a
    },
    type: "GET",
    dataType: "json",
    success: function(data) {
      var str = "",
        hang = eval(data);
      $.each(hang.data, function(a, b) {
        var p, c = b.id,
          d = b.qsubmitterName,
          e = b.qsubmitterIcon,
          f = b.qprocedureName,
          g = b.qsum,
          h = b.qtotal,
          i = b.qstatus,
          j = b.qusername,
          k = b.qphone,
          l = b.qaddress,
          m = b.qnote,
          n = hostUrl + "img/" + b.qprocedureImg,
          o = localStorage.userName;
        p = 1 == i ? "待付款" : 2 == i ? "已付款" : 3 == i ? "已发货" : "已收货", str += "<tr id='" + c + "box'><td>" + c + "</td><td>" + d + "</td><td><img width='60' height='60' style='border-radius:60px' src='" + e + "'></td><td>" + f + "</td><td>" + g + "</td><td>" + h + "</td><td><input type='text' id='qstatus" + c + "' value='" + p + "' readonly='readonly'></td><td>" + j + "</td><td>" + k + "</td><td>" + l + "</td><td><input type='text' id='qnote" + c + "' value='" + m + "' readonly='readonly'></td><td><img src='" + n + "' width='60' height='60'></td><td>" + o + "</td><td><div><button class='btn btn-danger btn-xs xiugaidd' bs='" + c + "'><i class='fa fa-pencil v-white'></i></button>&nbsp;&nbsp;&nbsp;<button class='btn btn-primary btn-xs qrdd hide' id='qrdd" + c + "' bs='" + c + "'><i class='fa fa-check v-white'></i></button></div></td><td><a data-toggle='modal' href='ddgl.html#ckxqModal' class='btn btn-info btn-xs ckddxx' id='cxddxq" + c + "' bs='" + c + "'>查看详情</a></td></tr>"
      }), $("#spddlb").html(str), $(".xiugaidd").click(function() {
        var a = $(this).attr("bs"),
          b = "qstatus" + a,
          c = a + "box",
          d = "qrdd" + a,
          e = $("#" + b).val();
        3 != e ? alert("只能修改已付款订单!") : ($("#" + c).find("input").attr("readonly", !1).addClass("inputborder"), $("#" + d).removeClass("hide"), $(".qrdd").click(function() {
          var a = $(this).attr("bs"),
            b = "qstatus" + a,
            c = "qnote" + a,
            d = $("#" + b).val(),
            e = $("#" + c).val();
          $.ajax({
            url: hostUrl + "updateOrder",
            data: {
              id: a,
              qstatus: d,
              qnote: e
            },
            type: "POST",
            dataType: "json",
            success: function(a) {
              "200" == a.code ? (alert("修改成功"), location.reload()) : alert("修改失败")
            }
          })
        }))
      }), $(".ckddxx").click(function() {
        var a = $(this).attr("bs");
        $.ajax({
          url: hostUrl + "getOrderInfo",
          data: {
            id: a
          },
          type: "GET",
          dataType: "JSON",
          success: function(a) {
            var b = "",
              c = "",
              d = a.data,
              e = d.qprocedureName,
              f = d.qtotal,
              g = d.detail;
            $.each(g, function(a, b) {
              var d = b.qprocedureType,
                e = b.qprice,
                f = b.qnum,
                g = b.qcost;
              c += "<tr><td>" + d + "</td><td>" + e + "</td><td>" + f + "</td><td>" + g + "</td></tr>"
            }), b += "商品名称:&nbsp;" + e + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 总价:&nbsp;" + f, $("#xxtitle").html(b), $("#spddxqbox").html(c)
          }
        })
      })
    }
  })
}

function ddtj() {
  $.ajax({
    url: hostUrl + "getOrderSum",
    data: {
      qshopid: shUserId
    },
    type: "GET",
    dataType: "JSON",
    success: function(a) {
      var b = "",
        c = a.data,
        d = c.yongjin,
        e = void 0 != c.status1 ? c.status1 : 0,
        f = void 0 != c.status2 ? c.status2 : 0,
        g = void 0 != c.status3 ? c.status3 : 0,
        h = void 0 != c.status4 ? c.status4 : 0,
        i = c.cash;
      b += "<p>佣金比例:" + d + "&nbsp;&nbsp;&nbsp;待付款订单:" + e + "&nbsp;&nbsp;&nbsp;已付款订单:" + f + "&nbsp;&nbsp;&nbsp;已发货订单:" + g + "&nbsp;&nbsp;&nbsp;已收货订单:" + h + "&nbsp;&nbsp;&nbsp;可取现:" + i + "&nbsp;&nbsp;&nbsp;</p>", $("#spddtjbox").html(b)
    }
  })
}
$(function() {
  cxdd(0), ddtj()
});
