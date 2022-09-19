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
