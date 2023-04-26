$(function() {
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
      var hang, str = "";
      "111" == data ? $("#ts").css("display", "block") : (hang = eval(data), $.each(hang.data, function(a, b) {
        var f, h, c = b.id,
          d = b.qname;
        b.qtype, f = b.qprice, b.qlike, h = b.qimage1, b.qimage2, b.qimage3, b.qnote1, b.qnote2, b.qnote3, b.qdelete, b.qprocedureId, b.qdetail, b.qshopid, b.qgroupId, str += "<div class='col-lg-3 col-md-3 col-sm-3 mb'><div class='product-panel-2 pn'><div class='badge badge-hot' id='id'>ID:" + c + "</div><img class='qimagebox' src='" + hostUrl + "img/" + h + "' id='qimage'><h5 class='mt' id='qname'>商品名称:" + d + "</h5><h6 id='qprice'>商品价格:" + f + "</h6><button class='btn btn-small btn-theme04 ckxx' bs='" + c + "'>查看详情</button></div></div>"
      }), $("#spzs").html(str))
    }
  }), $(".ckxx").click(function() {
    var a = $(this).attr("bs");
    location.href = "spxx.html?id=" + encodeURI(a)
  })
});
