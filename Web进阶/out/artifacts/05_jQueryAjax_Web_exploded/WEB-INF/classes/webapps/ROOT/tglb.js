$(function() {
  $.ajax({
    url: hostUrl + "getGroup",
    data: {
      qshopid: shUserId
    },
    type: "GET",
    dataType: "json",
    success: function(data) {
      var str = "",
        hang = eval(data);
      $.each(hang.data, function(a, b) {
        var j, k, c = b.id,
          d = b.qname,
          e = b.qgroupPrice,
          f = b.qgroupStart,
          g = b.qgroupEnd,
          h = b.qgroupSum;
        b.qdelete, j = b.qdiscount, k = hostUrl + "img/" + b.qimage1, str += "<tr><td>" + c + "</td><td>" + d + "</td><td><img width='80' src=" + k + " onclick='showLarge(event,this)' /></td><td>" + e + "</td><td>" + f + "</td><td>" + g + "</td><td>" + h + "</td><td>" + j + "</td><td><div class ='one'><button class='btn btn-danger btn-xs sctg' bs='" + c + "'><i class='fa fa-trash-o'></i></button>&nbsp;&nbsp;&nbsp;<a data-toggle='modal' href='tglb.html#tglbModal' class='btn btn-primary btn-xs xiugai' bs='" + c + "'><i class='fa fa-pencil'></i></a></div></td></tr>"
      }), $("#tglb").html(str), $(".sctg").click(function() {
        var a = $(this).attr("bs"),
          b = 1;
        confirm("确定删除么") && $.ajax({
          url: hostUrl + "editGroup",
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
          url: hostUrl + "getGroup",
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
                d = b.qname,
                e = b.qgroupPrice,
                f = b.qgroupStart,
                g = b.qgroupEnd,
                h = b.qgroupSum,
                i = b.qdiscount,
                j = hostUrl + "img/" + b.qimage1;
              str += "<div class='bj-input-group'><label>ID</label><input id='bjId' disabled type='text' value='" + c + "'></div><div class='bj-input-group'><label>商品名称</label><input id='bjQname' type='text' disabled value='" + d + "'></div><div class='bj-input-group'><label>图1</label><div class='oldimg showimgbox'><img src=" + j + " /></div></div><div class='bj-input-group'><label>团购价格</label><input id='bqgroupprice' type='text' value='" + e + "'></div><div class='bj-input-group'><label>开始时间</label><input id='bqgroupstart' type='text' value='" + f + "'></div><div class='bj-input-group'><label>结束时间</label><input id='bgroupend' type='text' value='" + g + "'></div><div class='bj-input-group'><label>团长返利</label><input id='bdiscount' type='text' value='" + i + "'></div><div class='bj-input-group'><label>多少人成团</label><input id='bgroupsum' type='text' value='" + h + "'></div>"
            }), $("#tggl").html(str), $(".qr").click(function() {
              var a = $(this).attr("bs"),
                b = $("#bqgroupprice").val(),
                c = $("#bqgroupstart").val(),
                d = $("#bdiscount").val(),
                e = $("#bgroupend").val(),
                f = $("#bgroupsum").val();
              $.ajax({
                url: hostUrl + "editGroup",
                data: {
                  id: a,
                  qgroupPrice: b,
                  qgroupStart: c,
                  qgroupEnd: e,
                  qdiscount: d,
                  qgroupSum: f
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
}), $(".qxzsp").click(function() {
  $.ajax({
    async: !1,
    url: hostUrl + "getProcedure",
    data: {
      qlick: 1,
      qshopid: shUserId
    },
    type: "GET",
    dataType: "json",
    success: function(data) {
      var str = "",
        hang = eval(data);
      $.each(hang.data, function(a, b) {
        var c = b.id,
          d = b.qname,
          e = b.qprice,
          f = b.qimage1;
        str += "<div class='col-xs-4 mb'><div class='product-panel-2 pn'><div class='badge badge-hot' id='id'>ID:" + c + "</div><img width='120' height='120' src='" + hostUrl + "img/" + f + "' id='qimage'><h5 class='mt' id='qname'>商品名称:" + d + "</h5><h6 id='qprice'>商品价格:" + e + "</h6><button class='btn btn-small btn-theme04 qrxxtg' data-dismiss='modal' bs='" + c + "'>选择</button></div></div>"
      }), $("#tgsp").html(str), $(".qrxxtg").click(function() {
        var a = $(this).attr("bs"),
          b = "";
        b += "商品ID:&nbsp;<span id='spId'>" + a + "<span>", $("#xzspbox").html(b)
      })
    }
  })
}), $("#tjtg").click(function() {
  var a = $("#spId").text(),
    b = $("#qgroupPrice").val(),
    c = $("#qgroupStart").val(),
    d = $("#qgroupEnd").val(),
    e = $("#qgroupSum").val();
  $.ajax({
    url: hostUrl + "saveGroup",
    data: {
      qprocedureid: a,
      qgroupPrice: b,
      qgroupStart: c,
      qgroupEnd: d,
      qgroupSum: e,
      qgroupNum: 0,
      qdelete: 0
    },
    type: "POST",
    dataType: "json",
    success: function(a) {
      "200" == a.code ? (alert("添加成功"), location.reload()) : alert("添加失败")
    }
  })
});
