$(function() {
  $.ajax({
    url: hostUrl + "getCoupon",
    data: {
      qshopid: shUserId
    },
    type: "GET",
    dataType: "json",
    success: function(data) {
      var str = "",
        hang = eval(data);
      $.each(hang.data, function(a, b) {
        var c = b.id,
          d = hostUrl + "img/" + b.qimg,
          e = b.qcaption,
          f = b.qprice,
          g = b.qjifen,
          h = b.qstart,
          i = b.qend;
        b.qdelete, str += "<tr><td>" + c + "</td><td><img width='60' height='60' src=" + d + " onclick='showLarge(event,this)' /></td><td>" + e + "</td><td>" + f + "</td><td>" + g + "</td><td>" + h + "</td><td>" + i + "</td><td><div class ='one'><button class='btn btn-danger btn-xs sctg' bs='" + c + "'><i class='fa fa-trash-o'></i></button>&nbsp;&nbsp;&nbsp;<a data-toggle='modal' href='yhqlb.html#yhqlbModal' class='btn btn-primary btn-xs xiugai' bs='" + c + "'><i class='fa fa-pencil'></i></a></div></td></tr>"
      }), $("#yhqlb").html(str), $(".sctg").click(function() {
        var a = $(this).attr("bs"),
          b = 1;
        confirm("确定删除么") && $.ajax({
          url: hostUrl + "updateCoupon",
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
          url: hostUrl + "getCoupon",
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
                d = b.qcaption,
                e = b.qprice,
                f = b.qstart,
                g = b.qend,
                h = b.qjifen,
                i = hostUrl + "img/" + b.qimg;
              b.qdelete, str += "<div class='bj-input-group'><label>ID</label><input id='yhqid' disabled type='text' value='" + c + "'></div><div class='bj-input-group'><label>图1</label><div class='oldimg showimgbox' id='yhqimgBox'><img src=" + i + " /></div><form id='yhqimgForm'><input type='file' name='file' id='yhqimg' /><div></div><p id='yhqimgUrl' class='urlbox'></p><input type='button' value='上传' onclick='doUpload(yhqimg)' /></form></div><div class='bj-input-group'><label>分类名</label><input id='yhqcaption' type='text' disabled value='" + d + "'></div><div class='bj-input-group'><label>价格</label><input id='yhqprice' type='text' value='" + e + "'></div><div class='bj-input-group'><label>积分</label><input id='yhqjifen' type='text' value='" + h + "'></div><div class='bj-input-group'><label>开始时间</label><input id='yhqstart' type='text' value='" + f + "'></div><div class='bj-input-group'><label>结束时间</label><input id='yhqend' type='text' value='" + g + "'></div><div class='bj-input-group'><label>是否删除</label><select id='yhqdelete'><option value='0'>不删除</option><option value='1'>删除</option></select></div>"
            }), $("#bjyhq").html(str), $(".qr").click(function() {
              var a = $(this).attr("bs"),
                b = $("#yhqprice").val(),
                c = $("#yhqjifen").val(),
                d = $("#yhqstart").val(),
                e = $("#yhqend").val(),
                f = $("#yhqdelete").val(),
                g = $("#yhqimgUrl").text();
              $.ajax({
                url: hostUrl + "updateCoupon",
                data: {
                  id: a,
                  qprice: b,
                  qstart: d,
                  qend: e,
                  qjifen: c,
                  qimg: g,
                  qdelete: f
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
}), $("#tjyhq").click(function() {
  var a = $("#yhqcaption").val(),
    b = $("#yhqprice").val(),
    c = $("#yhqjifen").val(),
    d = $("#yhqstart").val(),
    e = $("#yhqend").val(),
    f = $("#yhqimgUrl").text();
  $.ajax({
    url: hostUrl + "saveCoupon",
    data: {
      qshopid: shUserId,
      qimg: f,
      qprice: b,
      qcaption: a,
      qstart: d,
      qend: e,
      qjifen: c,
      qdelete: 0
    },
    type: "POST",
    dataType: "json",
    success: function(a) {
      "200" == a.code ? (alert("添加成功"), location.reload()) : alert("添加失败")
    }
  })
});
