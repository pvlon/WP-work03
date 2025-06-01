<%@ page import="com.example.work03personalitytest.service.UserDO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String username = (String) request.getAttribute("username");
    Integer score = (Integer) request.getAttribute("score");
    String evaluationResult = (String) request.getAttribute("evaluationResult");
    if (username == null) username = "";
    if (score == null) score = 0;
    if (evaluationResult == null) evaluationResult = "";
%>
<html>
<head>
    <meta charset="UTF-8">
    <title>재미있는 2분 성격 테스트</title>
    <style type="text/css">
        body {
            width: 600px;
            margin-top: 50px;
            margin-left: 50px;
        }

        header {
            height: 100px;
        }

        .question {
            padding-top: 25px;
        }

        .buttons {
            padding-top: 50px;
            text-align: right;
        }
    </style>
</head>
<body>
<header>
    <h1>재미있는 2분 성격 테스트</h1>
    <div style="text-align: right">
        <p>반갑습니다. <%= username %> 님</p>
    </div>
    <hr>
</header>
<article>
    <div>
        <h3>성격 테스트 분석 결과</h3>
    </div>
    <div>
        <p><b>1. 평가 점수 : <%= score %></b></p>
        <p><b>2. 분석 결과 :</b></p>
        <div style="padding-left: 25px">
            <%= evaluationResult %>
        </div>

        <div class="buttons">
            <!-- 🟢 그냥 처음 화면으로 돌아가기 (세션 유지, 삭제 안 함) -->
            <input type="button" value="첫 화면으로 가기"
                   onclick="window.location.href='<%= request.getContextPath() %>/index.jsp';">

            <!-- 🔴 유저 삭제 및 로그아웃 -->
            <form action="<%= request.getContextPath() %>/logout" method="post" style="display: inline;">
                <input type="submit" value="유저 삭제 및 로그아웃">
            </form>
        </div>
    </div>
</article>
</body>
</html>
