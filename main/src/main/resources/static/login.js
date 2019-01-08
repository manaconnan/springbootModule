
function login() {

    var username = document.getElementById("username").value;
    var pass = document.getElementById("password").value;

    if (username.value == "") {

        alert("请输入用户名");

    } else if (pass.value  == "") {

        alert("请输入密码");

    } else {

//        window.location.href="/loginPost";
           var params = {
                "username":username,
                "password":pass
           }
           httpPost("/loginPost",params);
    }
}

function httpPost(URL, PARAMS) {
    var temp = document.createElement("form");
    temp.action = URL;
    temp.method = "post";
    temp.style.display = "none";

    for (var x in PARAMS) {
        var opt = document.createElement("textarea");
        opt.name = x;
        opt.value = PARAMS[x];
        temp.appendChild(opt);
    }

    document.body.appendChild(temp);
    temp.submit();

    return temp;
}
