$(".logOutBtn").click(function(){
    var r = confirm("确定要退出吗？")
    if (r == true) {
        location.href="/logOut";
        return false;
    } else {
        return false;
    }
});