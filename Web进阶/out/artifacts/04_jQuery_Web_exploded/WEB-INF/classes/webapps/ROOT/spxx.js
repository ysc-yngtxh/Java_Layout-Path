$(function() {
  var loc = location.href,
    n1 = loc.length,
    n2 = loc.indexOf("="),
    id = decodeURI(loc.substr(n2 + 1, n1 - n2)),
    vId = id;
  $.ajax({
    url: hostUrl + "getProcedure",
    data: {
      qlick: 1,
      qshopid: shUserId,
      id: vId
    },
    type: "GET",
    dataType: "json",
    success: function(data) {
      var str = "",
        hang = eval(data);
      $.each(hang.data, function(a, b) {
        var f, h, i, j, o, c = b.id,
          d = b.qname;
        b.qtype, f = b.qprice, b.qlike, h = hostUrl + "img/" + b.qimage1, i = hostUrl + "img/" + b.qimage2, j = hostUrl + "img/" + b.qimage3, b.qnote1, b.qnote2, b.qnote3, b.qdelete, o = hostUrl + "img/" + b.qdetail, b.qshopid, b.qgroupId, str += "<div class='col-lg-4 col-xs-12'><div class='white-header'><h5>" + d + "</h5></div><div><img src='" + h + "' height='200' width='200' onclick='showLarge(event,this)''></div><div class='col-xs-12 img-3-box'><div class='col-xs-4 showimgbox v-center' id='img1Box'><img src=" + h + " onclick='showLarge(event,this)' /></div><div class='col-xs-4 showimgbox v-center' id='img2Box'><img src=" + i + " onclick='showLarge(event,this)' /></div><div class='col-xs-4 showimgbox v-center' id='img3Box'><img src=" + j + " onclick='showLarge(event,this)' /></div></div></div><div class='col-lg-8 col-xs-12'><div class='col-xs-12'><table class='table table-striped table-advance'><thead><tr><th>商品名称</th><th>商品价格</th></tr></thead><tbody><tr><td><p id='bjQname'>" + d + "</p></td><td><p id='bjQprice'>" + f + "</p></td></tr></tbody></table></div><div class='col-xs-12'><table class='table table-striped table-advance qxzsp-table ggbox'><thead><tr><th>规格ID</th><th>规格名</th><th>价格</th><th>说明</th><th>团购ID</th><th>操作</th></tr></thead><tbody id='spggbox'></tbody></table><div class='v-no-border'><button class='btn btn-info xgxz' data-toggle='modal' data-target='#spggModal' bs='" + c + "'>新增</button></div></div></div><div class='col-lg-12 xxtuimg'><div class='v-center showimgbox' id='imgdeBox'><img src=" + o + " onclick='showLarge(event,this)' /></div></div>"
      }), $("#spxxbox").html(str)
    }
  })
});
