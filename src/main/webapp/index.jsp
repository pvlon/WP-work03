<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>재미있는 2분 성격 테스트</title>
    <style type="text/css">
        body, header {
            font-family: 'Nanum Gothic', sans-serif;
        }
        body {
            width: 600px;
            margin-top: 50px;
            margin-left: 50px;
        }
        header {
            height: 100px;
        }
        .description {
            height: 150px;
        }
        .form-container {
            text-align: center;
        }
        form {
            display: inline-block;
            text-align: left;
        }
        .form-group {
            margin-bottom: 10px;
        }
        .form-group label {
            display: inline-block;
            width: 80px;
        }
        .form-submit {
            text-align: center;
            margin-top: 10px;
        }
    </style>
</head>
<body>
<header>
    <h1>재미있는 2분 성격 테스트</h1>
</header>
<article>
    <div class="description">
        <p>"2분 성격 테스트"는 10개의 문항을 살펴본 뒤 점수를 매기고 결과에 따른 자신의 성격을 점검하는 테스트입니다.</p>
        <p>하루 중 기분이 좋을 때, 걸을 때 보폭, 앉을 때 자세, 좋아하는 색 등 일상 속 자신의 모습을 생각해보고 자신이 현명한 사람인지, 깐깐한지, 또는 소심한지 알아볼 수 있습니다.</p>
    </div>
    <div class="form-container">
        <form action="userController" method="post">
            <div class="form-group">
                <label for="email">E-mail :</label>
                <input type="text" name="email" id="email" required />
            </div>
            <div class="form-group">
                <label for="username">이름 :</label>
                <input type="text" name="username" id="username" required />
            </div>
            <div class="form-group" style="margin-bottom: 20px;">
                <label for="password">비밀번호 :</label>
                <input type="text" name="password" id="password" required />
            </div>
            <div class="form-submit">
                <input type="submit" value="시작하기 >> " />
            </div>
        </form>
    </div>
</article>
</body>
</html>
<% String error = (String) request.getAttribute("error"); %>
<% if (error != null) { %>
<script>
    alert("<%= error %>");
</script>
<% } %>