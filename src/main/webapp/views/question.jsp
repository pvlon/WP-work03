<%@ page import="java.util.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String username = (String) request.getAttribute("username");
    Integer pageNum = (Integer) request.getAttribute("page");

    LinkedHashMap<String, Integer> answerMap1 = (LinkedHashMap<String, Integer>) request.getAttribute("answerMap1");
    LinkedHashMap<String, Integer> answerMap2 = (LinkedHashMap<String, Integer>) request.getAttribute("answerMap2");
    String questionTitle1 = (String) request.getAttribute("questionTitle1");
    String questionTitle2 = (String) request.getAttribute("questionTitle2");
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
        <p>페이지: <%= pageNum %></p>
    </div>
    <hr>
</header>
<article>
    <form action="questionController" method="post">
        <div class="question">
            <p><%= questionTitle1 %></p>
            <div>
                <%
                    if (answerMap1 != null) {
                        for (String key1 : answerMap1.keySet()) {
                            int value1 = answerMap1.get(key1);
                %>
                <input type="radio" name="q1" value="<%= value1 %>" required><%= key1 %><br>
                <%
                        }
                    }
                %>

            </div>
        </div>
        <div class="question">
            <p><%= questionTitle2 %></p>
            <div>
                <%
                    if (answerMap2 != null) {
                        for (String key2 : answerMap2.keySet()) {
                            int value2 = answerMap2.get(key2);
                %>
                <input type="radio" name="q2" value="<%= value2 %>" required><%= key2 %><br>
                <%
                        }
                    }
                %>

            </div>
        </div>
        <div class="buttons">
            <input type="submit" value="다음 >>"/>
        </div>
    </form>
</article>
</body>
</html>
