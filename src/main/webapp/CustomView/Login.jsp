<html>
<head>
    <%@ include file="/CustomView/JSPF/head.jspf"%>

</head>
<body>
    <header>
        <%@ include file="/CustomView/header.jsp"%>
    </header>
    <h5>${param.error}</h5>
    <main class="container">
        <div class="valign-wrapper">
            <div class="container">
                <div class="valign">
                    <form name="VisitForm" method="post" class="col s6" action="/login">
                        <input type="hidden" name="command" value="logIn">
                        <div class="row">
                            <span class="error-text">${errors['global']}</span>
                        </div>
                        <div class="row">
                            <div class="input-field сol s6 col offset-s3">
                                <input id="login" type="text" name="login" size="20">
                                <label for="login"><fmt:message key="login.login"/></label>
                                <span class="error-text">${errors['login']}</span></td>
                            </div>
                        </div>
                        <div class="row">
                            <div class="input-field сol s6 col offset-s3">
                                <input id="password" type="password" name="password" size="20">
                                <label for="password"><fmt:message key="login.password"/></label>
                                <span class="error-text">${errors['password']}</span></td>
                            </div>
                        </div>
                        <div class="row">
                            <div class="сol s6 col offset-s3">
                                <button class="btn waves-effect col s12" type="submit" name="action"><fmt:message key="login.submit"/>
                                    <i class="material-icons right"></i>
                                </button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </main>
</body>
</html>