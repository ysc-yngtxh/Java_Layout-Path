$(function() {
  $.ajax({
    url: hostUrl + "getProcedureType",
    data: {
      qshopid: shUserId
    },
    type: "GET",
    dataType: "json",
    success: function(data) {
      var str = "",
        hang = eval(data);
      $.each(hang.data, function(a, b) {
        var g, h, i, c = b.id,
          d = hostUrl + "img/" + b.qicon,
          e = b.qcaption;
        b.qposition, g = b.qdelete, h = localStorage.userName, i = 0 == g ? "不删除" : "删除", str += "<tr><td>" + c + "</td><td><img src=" + d + " onclick='showLarge(event,this)' /></td><td>" + e + "</td><td>" + h + "</td><td><div class ='one'><button class='btn btn-danger btn-xs sc1' bs='" + c + "'><i class='fa fa-trash-o'></i></button>&nbsp;&nbsp;&nbsp;<a data-toggle='modal' href='spnb.html#xgnbModal' class='btn btn-primary btn-xs xiugai' bs='" + c + "'><i class='fa fa-pencil'></i></a></div></td></tr>"
      }), $("#bjspnb").html(str), $(".sc1").click(function() {
        var a = $(this).attr("bs"),
          b = 1;
        confirm("确定删除么") && $.ajax({
          url: hostUrl + "updateProcedureType",
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
      }), $(".xiugai").click(function() {
        var vId = $(this).attr("bs");
        $(".qr").attr("bs", vId), $.ajax({
          url: hostUrl + "getProcedureType",
          data: {
            qshopid: shUserId,
            id: vId
          },
          type: "GET",
          dataType: "json",
          success: function(data) {
            var str = "",
              hang = eval(data);
            $.each(hang.data, function(a, b) {
              var c = b.id,
                d = hostUrl + "img/" + b.qicon,
                e = b.qcaption;
              b.qposition, b.qdelete, b.qshopid, str += "<div class='bj-input-group'><label>ID</label><input id='nbId' disabled type='text' value='" + c + "'></div><div class='bj-input-group'><label>类别ICON</label><div class='oldimg showimgbox' id='img1Box'><img src=" + d + " onclick='showLarge(event,this)' /></div><form id='img1Form'><input type='file' name='file' id='img1' /><div></div><p id='img1Url' class='urlbox'></p><input type='button' value='上传' onclick='doUpload(img1)' /></form></div><div class='bj-input-group'><label>商品类型</label><input id='qcaption' type='text' value='" + e + "'></div><div class='bj-input-group'><label>是否删除</label><select id='nbDelete'><option value='0'>不删除</option><option value='1'>删除</option></select></div>"
            }), $("#xgnb").html(str), $(".qr").click(function() {
              var a = $(this).attr("bs"),
                b = $("#img1Url").text(),
                c = $("#qcaption").val(),
                d = $("#nbDelete").val();
              $.ajax({
                url: hostUrl + "updateProcedureType",
                data: {
                  id: a,
                  qicon: b,
                  qcaption: c,
                  qdelete: d,
                  qshopid: shUserId
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
      })
    }
  })
}), $("#tjnb").click(function() {
  var a = $("#tjimg1Url").text(),
    b = $("#tjqcaption").val(),
    c = $("#tjqdelete").val();
  $.ajax({
    url: hostUrl + "saveProcedureType",
    data: {
      qicon: a,
      qcaption: b,
      qdelete: c,
      qposition: 0,
      qshopid: shUserId
    },
    type: "POST",
    dataType: "json",
    success: function(a) {
      "200" == a.code ? (alert("添加成功"), location.reload()) : alert("添加失败")
    }
  })
});
