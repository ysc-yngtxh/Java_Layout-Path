$(function() {
  var loc = location.href,
    n1 = loc.length,
    n2 = loc.indexOf("="),
    id = decodeURI(loc.substr(n2 + 1, n1 - n2)),
    vId = id;
  $.ajax({
    url: hostUrl + "getType",
    data: {
      qprocedureId: vId
    },
    type: "GET",
    dataType: "json",
    success: function(data) {
      var str = "",
        hang = eval(data);
      $.each(hang.data, function(a, b) {
        var e, f, g, h, c = b.id;
        b.qprocedureId, e = b.qtypeName, f = b.qprice, g = b.qnote, h = b.qgroupId, str += "<tr id='" + c + "box'><td><p id='qId" + c + "'>" + c + "</p></td><td><input type='text' id='qtypeName" + c + "' value='" + e + "' readonly='readonly'></td><td><input type='text' id='qprice" + c + "' value='" + f + "' readonly='readonly'></td><td><input type='text' id='qnote" + c + "' value='" + g + "' readonly='readonly'></td><td><input type='text' id='qgroupId" + c + "' value='" + h + "' readonly='readonly'></td><td><div><button class='btn btn-danger btn-xs xggg' bs='" + c + "'><i class='fa fa-pencil v-white'></i></button>&nbsp;&nbsp;&nbsp;<button class='btn btn-primary btn-xs tjgg hide' id='tjgg" + c + "'><i class='fa fa-check v-white'></i></button></div></td></tr>"
      }), $("#spggbox").html(str), $(".xggg").click(function() {
        var a, b;
        code = $(this).attr("bs"), a = code + "box", b = "tjgg" + code, $("#" + a).find("input").attr("readonly", !1).addClass("inputborder"), $("#" + b).removeClass("hide")
      }), $(".tjgg").click(function() {
        var a = code + "box",
          b = "tjgg" + code,
          d = "qprocedureId" + code,
          e = "qtypeName" + code,
          f = "qprice" + code,
          g = "qnote" + code,
          h = "qgroupId" + code,
          i = code,
          j = $("#" + d).val(),
          k = $("#" + e).val(),
          l = $("#" + f).val(),
          m = $("#" + g).val(),
          n = $("#" + h).val();
        $.ajax({
          url: hostUrl + "updateType",
          data: {
            id: i,
            qprocedureId: j,
            qtypeName: k,
            qprice: l,
            qnote: m,
            qgroupId: n
          },
          type: "POST",
          dataType: "json",
          success: function(c) {
            "200" == c.code ? (alert("修改成功"), $("#" + a).find("input").attr("readonly", !0).removeClass("inputborder"), $("#" + b).addClass("hide")) : alert("修改失败")
          }
        })
      })
    }
  }), $("#tjspgg").click(function() {
    var a = $("#qtypeNamegg").val(),
      b = $("#qpricegg").val(),
      c = $("#qnotegg").val(),
      d = $("#qgroupIdgg").val();
    $.ajax({
      url: hostUrl + "saveType",
      data: {
        qprocedureId: vId,
        qtypeName: a,
        qprice: b,
        qnote: c,
        qgroupId: d
      },
      type: "POST",
      dataType: "json",
      success: function(a) {
        "200" == a.code ? (alert("添加成功"), location.reload()) : alert("添加失败")
      }
    })
  })
});
