<html>
<head>
    <%@ include file="/CustomView/JSPF/head.jspf"%>
</head>
<body>
<header>
    <%@ include file="/CustomView/header.jsp"%>
</header>
<main>
    <div class="container">
        <div class="row">
            <div class="col s6 offset-s3">
                <div class="row">
                    <div class="section">
                        <div class="row">
                            <h5><fmt:message key="adminPage.getAllRequests"/></h5>
                        </div>
                        <div class="row">
                            <p><a class="waves-effect waves-light btn col s6 right" href="/AdminPage?command=request"><fmt:message key="adminPage.Go"/></a></p>
                        </div>
                    </div>
                    <div class="divider"></div>
                    <div class="section">
                        <div class="row">
                            <h5><fmt:message key="adminPage.getAllFlights"/></h5>
                        </div>
                        <div class="row">
                            <p><a class="waves-effect waves-light btn col s6 right" href="/AdminPage?command=flights"><fmt:message key="adminPage.Go"/></a></p>
                        </div>
                    </div>
                    <div class="divider"></div>
                    <div class="section">
                        <div class="row">
                            <h5><fmt:message key="adminPage.getAllEmployees"/></h5>
                        </div>
                        <div class="row">
                            <p><a class="waves-effect waves-light btn col s6 right" href="/AdminPage?command=employee"><fmt:message key="adminPage.Go"/></a></p>
                        </div>
                    </div>

                </div>


            </div>
        </div>

    </div>
</main>

</body>
</html>
