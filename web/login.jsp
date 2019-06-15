<%@ page contentType="text/html;charset=utf-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
<script>

    window.onload=function () {
        var img = document.getElementById("checkcode");
        img.onclick=function () {
            var date = new Date().getTime();
            img.src= "/day15/checkCodetestServlet?"+date
        }

        var bt = document.getElementById("change");
        bt.onclick=function () {
            var img = document.getElementById("checkcode");
            var date = new Date().getTime()
            img.src = "/day15/checkCodetestServlet?"+date
        }

        var us = document.getElementById("user");
        us.focus()

    }


</script>
<style>
    div{
        color: red;
    }
</style>

</head>
<body>
<form action="/day15/loginServlet" method="post">
    <table>
        <tr>
            <td>用户名</td>
            <td><input type="text" id="user" name="username"></td>
        </tr>
        <tr>
            <td>密码</td>
            <td><input type="password" id="pass" name="password"></td>
        </tr>
        <tr>
            <td>验证码</td>
            <td><input type="text" name="checkcode" style="width: 50px" maxlength="4"></td>
        </tr>
        <tr>
            <td colspan="2"><img id="checkcode" src="/day15/checkCodetestServlet"><a id="change" href="#">看不清换一张?</a></td>
        </tr>
        <tr>
            <td colspan="2"><input type="submit" value="submit"></td>
        </tr>
    </table>
</form>
<div><%= request.getAttribute("msg_cc")==null?"":request.getAttribute("msg_cc")%></div>
<div><%= request.getAttribute("msg_pp")==null?"":request.getAttribute("msg_pp")%></div>
</body>
</html>

