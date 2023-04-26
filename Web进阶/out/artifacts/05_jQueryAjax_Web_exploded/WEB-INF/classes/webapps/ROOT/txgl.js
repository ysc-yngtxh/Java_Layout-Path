function cxtx(a) {
  var spId;
  spId = 1 != shUserId ? shUserId : "", $.ajax({
    url: hostUrl + "getPayRecord",
    data: {
      qshopid: spId,
      qstatus: a
    },
    type: "GET",
    dataType: "json",
    success: function(data) {
      var cQstatus, str = "",
        hang = eval(data);
      $.each(hang.data, function(a, b) {
        var l, c = b.id,
          d = b.qshopid,
          e = b.qshopName,
          f = b.qpayWxh,
          g = b.qpayNum,
          h = b.qpayTime,
          i = b.qstatus,
          j = b.qyongjin,
          k = b.qcash;
        l = 1 == i ? "待转账" : "已转账", str += "<tr id='" + c + "box'><td>" + c + "</td><td>" + d + "</td><td>" + e + "</td><td>" + f + "</td><td>" + g + "</td><td>" + h + "</td><td><input type='text' id='qstatus" + c + "' class='status' value='" + l + "' readonly='readonly'></td><td>" + j + "</td><td>" + k + "</td><td class='glytx hide'><div><button class='btn btn-danger btn-xs xiugaitx' bs='" + c + "'><i class='fa fa-pencil v-white'></i></button>&nbsp;&nbsp;&nbsp;<button class='btn btn-primary btn-xs qrtx hide' id='qrdd" + c + "' bs='" + c + "'><i class='fa fa-check v-white'></i></button></div></td></tr>"
      }), 1 == shUserId ? ($("#txlb").html(str), $(".glytx").removeClass("hide")) : $("#txlb").html(str), cQstatus = $(".status").val(), 2 == cQstatus ? $(".xiugaitx").attr("disabled", !0) : $(".xiugaitx").click(function() {
        var a = $(this).attr("bs"),
          c = a + "box",
          d = "qrdd" + a;
        $("#" + c).find("input").attr("readonly", !1).addClass("inputborder"), $("#" + d).removeClass("hide"), $(".qrtx").click(function() {
          var a = $(this).attr("bs"),
            b = "qstatus" + a,
            d = $("#" + b).val();
          1 != d && 2 != d ? alert("出错了") : $.ajax({
            url: hostUrl + "updatePayRecord",
            data: {
              id: a,
              qstatus: d
            },
            type: "POST",
            dataType: "json",
            success: function(a) {
              "200" == a.code ? (alert("修改成功"), location.reload()) : alert("修改失败")
            }
          })
        })
      })
    }
  })
}
$(function() {
  cxtx(0)
});
