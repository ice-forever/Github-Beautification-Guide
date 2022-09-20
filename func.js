/**
 * 
 */
function showMoeCounter() {
    x = document.getElementById("page"); // 找到元素
    x.innerHTML = "<iframe align=\"center\" width=\"100%\" height=\"100%\" src=\"./Moe-counter.html\"  frameborder=\"no\" border=\"0\" marginwidth=\"0\"marginheight=\"0\" scrolling=\"yes\"> </iframe>"; // 改变内容
}
function showMain() {
    x = document.getElementById("page"); // 找到元素
    x.innerHTML = "<iframe src=\"./Main.html\" align=\"center\" width=\"100%\" height=\"100%\"  frameborder=\"no\" border=\"0\" marginwidth=\"0\"marginheight=\"0\" scrolling=\"yes\"> </iframe>"; // 改变内容
}
function showTime(clock) {
    var now = new Date();
    var year = now.getFullYear();
    var month = now.getMonth();
    var day = now.getDate();
    var hour = now.getHours();
    var minu = now.getMinutes();
    var second = now.getSeconds();
    month = month + 1;
    var arr_work = new Array("星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六");
    var week = arr_work[now.getDay()];
    var time = year + "年" + month + "月" + day + "日 " + week + " " + hour + ":" + minu + ":" + second;
    clock.innerHTML = "当前时间: " + time;
}

