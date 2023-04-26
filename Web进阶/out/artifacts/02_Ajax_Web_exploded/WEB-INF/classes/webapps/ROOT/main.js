function showLarge(a, b) {
  a.stopPropagation();
  var c = $(".ue-masked");
  c.length < 1 && (c = $('<div class="ue-masked">    <div class="ue-masked-img ue-center">        <img class="zoomimgbg" src="background-image:url()">    </div>    <div class="ue-masked-close">        <a onclick="$(\'.ue-masked\').fadeOut(200);">X</a>    </div></div>').appendTo($("body"))), c.find(".zoomimgbg").attr({
    src: b.src
  }), $(document).one("click", function() {
    $(".ue-masked").fadeOut(200)
  }), c.fadeIn(200)
}

function loginindex() {
  var username = $("#username").val(),
    password = $("#password").val();
  return "" == username || "" == password ? (alert("手机号密码均不能为空！"), !1) : ($.ajax({
    type: "POST",
    url: hostUrl + "logon",
    data: {
      qphone: username,
      qpassword: password
    },
    dataType: "json",
    async: !1,
    success: function(data) {
      var dd = data,
        hang = eval(data);
      setUser(data), $.each(hang.data, function(a, b) {
        id = b.id
      }), 0 != dd.data.length && "200" == data.code ? window.location.href = "splb.html" : alert("用户名或密码错误，登录失败")
    }
  }), void 0)
}

function setUser(user) {
  var ha = eval(user);
  window.localStorage.clear(), $.each(ha.data, function(a, b) {
    shId = b.id, shImg = b.qshopIcon, shName = b.qshopName
  }), localStorage.userid = shId, localStorage.userImg = shImg, localStorage.userName = shName
}

function doUpload(a) {
  var b = a.getAttribute("id"),
    c = b + "Box",
    d = b + "Form",
    e = b + "Url",
    f = document.getElementById(d),
    g = new FormData(f),
    h = document.getElementById(e),
    i = document.getElementById(c);
  $.ajax({
    url: hostUrl + "file/onefile2",
    type: "POST",
    data: g,
    async: !1,
    cache: !1,
    contentType: !1,
    processData: !1,
    success: function(a) {
      var b, c;
      a = a, b = hostUrl + "img/", c = b + a, h.innerText = a, $(i).html('<img src="' + c + '">')
    },
    error: function(a) {
      alert(a)
    }
  })
}
var shid, hostUrl = "http://123.206.229.14/llsc/",
  shUserId = localStorage.userid,
  shUserImg = hostUrl + "img/" + localStorage.userImg,
  shUserName = localStorage.userName;
$(function() {
  var a = $("#gly");
  1 == shUserId ? a.removeClass("hide") : a.addClass("hide"), $("#v-usericon").attr("src", shUserImg), $("#v-username").text(shUserName)
});
