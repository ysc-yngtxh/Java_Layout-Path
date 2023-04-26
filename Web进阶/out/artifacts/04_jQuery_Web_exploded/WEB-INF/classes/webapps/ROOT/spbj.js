function deleteProduce(a) {
  var b = $(a).attr("bs"),
    c = 1;
  confirm("确定删除么") && $.ajax({
    url: hostUrl + "updateProcedure",
    data: {
      id: b,
      qdelete: c
    },
    type: "POST",
    dataType: "json",
    async: !1,
    success: function(a) {
      "200" == a.code ? (alert("删除成功"), location.reload()) : alert("删除失败")
    }
  })
}

function produceDetail(obj) {
  var vId = $(obj).attr("bs");
  $(".qr").attr("bs", vId), $.ajax({
    url: hostUrl + "getProcedure",
    data: {
      qlick: 1,
      qshopid: shUserId,
      id: vId
    },
    type: "POST",
    dataType: "json",
    async: !1,
    success: function(data) {
      var str = "",
        hang = eval(data);
      produceCategory(), $.each(hang.data, function(a, b) {
        var c = hostUrl + "img/" + b.qimage1,
          d = hostUrl + "img/" + b.qimage2,
          e = hostUrl + "img/" + b.qimage3,
          f = hostUrl + "img/" + b.qdetail;
        $("#bjQname").val(b.qname), $("#bjQtype").val(b.qtype), $("#bjQprice").val(b.qprice), $("#bjQlike").val(b.qlike), $("#img1Box").children()[0].src = c, $("#img2Box").children()[0].src = d, $("#img3Box").children()[0].src = e, $("#imgdeBox").children()[0].src = f
      })
    }
  })
}

function updateDetail(a) {
  var b = $(a).attr("bs"),
    c = $("#bjQname").val(),
    d = $("#bjQtype").val(),
    e = $("#bjQprice").val(),
    f = $("#bjQlike").val(),
    g = $("#bjDelete").val(),
    h = shUserId,
    i = $("#img1Box").children()[0].src,
    j = $("#img2Box").children()[0].src,
    k = $("#img3Box").children()[0].src,
    l = $("#imgdeBox").children()[0].src,
    m = i.substring(i.lastIndexOf("/") + 1, i.length),
    n = j.substring(j.lastIndexOf("/") + 1, j.length),
    o = k.substring(k.lastIndexOf("/") + 1, k.length),
    p = l.substring(l.lastIndexOf("/") + 1, l.length);
  $.ajax({
    url: hostUrl + "updateProcedure",
    data: {
      id: b,
      qname: c,
      qtype: d,
      qprice: e,
      qlike: f,
      qimage1: m,
      qimage2: n,
      qimage3: o,
      qdelete: g,
      qdetail: p,
      qshopid: h
    },
    type: "POST",
    dataType: "json",
    async: !1,
    success: function(a) {
      "200" == a.code ? (alert("修改成功"), location.reload()) : alert("修改失败")
    }
  })
}

function produceCategory() {
  $(".producetype").empty(), $.ajax({
    url: hostUrl + "getProcedureType",
    data: {
      qshopid: shUserId
    },
    type: "GET",
    dataType: "json",
    async: !1,
    success: function(res) {
      var hang = eval(res);
      $.each(hang.data, function(a, b) {
        qtypeid = b.id, qtypename = b.qcaption, $(".producetype").append("<option value='" + qtypeid + "'>" + qtypename + "</option>")
      })
    }
  })
}
$(function() {
  produceCategory(), $.ajax({
    url: hostUrl + "getProcedure",
    data: {
      qlick: 1,
      qshopid: shUserId
    },
    type: "GET",
    dataType: "json",
    async: !1,
    success: function(data) {
      var str = "",
        hang = eval(data);
      $.each(hang.data, function(index, item) {
        var vlike, vdelete, qtypename, shopname, id = item.id,
          qname = item.qname,
          qtype = item.qtype,
          qprice = item.qprice,
          qlike = item.qlike,
          qimage1 = hostUrl + "img/" + item.qimage1,
          qimage2 = hostUrl + "img/" + item.qimage2,
          qimage3 = hostUrl + "img/" + item.qimage3,
          qnote1 = item.qnote1,
          qnote2 = item.qnote2,
          qnote3 = item.qnote3,
          qdelete = item.qdelete,
          qdetail = hostUrl + "img/" + item.qdetail,
          qshopid = item.qshopid;
        vlike = 0 == qlike ? "不上架" : "上架", vdelete = 0 == qdelete ? "不删除" : "删除", qtypename = "空", shopname = localStorage.userName, null == qtype ? qtypename = "空" : $.ajax({
          url: hostUrl + "getProcedureType",
          data: {
            qshopid: shUserId,
            id: qtype
          },
          type: "GET",
          dataType: "json",
          async: !1,
          success: function(res) {
            var hang = eval(res);
            $.each(hang.data, function(a, b) {
              qtypename = b.qcaption
            })
          }
        }), str += "<tr><td>" + id + "</td><td>" + qname + "</td><td>" + qtypename + "</td><td>" + qprice + "</td><td>" + vlike + "</td><td><img src=" + qimage1 + " onclick='showLarge(event,this)' /></td><td><img src=" + qimage2 + " onclick='showLarge(event,this)' /></td><td><img src=" + qimage3 + " onclick='showLarge(event,this)' /></td><td><img src=" + qdetail + " onclick='showLarge(event,this)' /></td><td>" + shopname + "</td><td><div class ='one'><button class='btn btn-danger btn-xs sc1' onclick='deleteProduce(this)' bs='" + id + "'><i class='fa fa-trash-o'></i></button>&nbsp;&nbsp;&nbsp;<a data-toggle='modal' href='spbj.html#xgnrModal' onclick='produceDetail(this)' class='btn btn-primary btn-xs xiugai' bs='" + id + "'><i class='fa fa-pencil'></i></a></div></td></tr>", $("#bjsp").html(str)
      })
    }
  })
}), $("#tjsp").click(function() {
  var a = $("#tjQname").val(),
    b = $("#tjQtype").val(),
    c = $("#tjQprice").val(),
    d = $("#tjQlike").val(),
    e = $("#tjDelete").val(),
    f = shUserId,
    g = $("#tjimg1Box").children()[0].src,
    h = $("#tjimg2Box").children()[0].src,
    i = $("#tjimg3Box").children()[0].src,
    j = $("#tjimgdeBox").children()[0].src,
    k = g.substring(g.lastIndexOf("/") + 1, g.length),
    l = h.substring(h.lastIndexOf("/") + 1, h.length),
    m = i.substring(i.lastIndexOf("/") + 1, i.length),
    n = j.substring(j.lastIndexOf("/") + 1, j.length);
  $.ajax({
    url: hostUrl + "saveProcedure",
    data: {
      qname: a,
      qtype: b,
      qprice: c,
      qlike: d,
      qdelete: e,
      qshopid: f,
      qimage1: k,
      qimage2: l,
      qimage3: m,
      qdetail: n
    },
    type: "POST",
    dataType: "json",
    async: !1,
    success: function(a) {
      "200" == a.code ? (alert("添加成功"), location.reload()) : alert("添加失败")
    }
  })
});
