<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>异常详情</title>
    <style>
        pre{
            width:100%;
            white-space:pre-wrap; /* css3.0 */
            white-space:-moz-pre-wrap; /* Firefox */
            white-space:-pre-wrap; /* Opera 4-6 */
            white-space:-o-pre-wrap; /* Opera 7 */
            word-wrap:break-word; /* Internet Explorer 5.5+ */
        }
    </style>
</head>
<body>
    <pre>${data}</pre>
</body>
</html>
