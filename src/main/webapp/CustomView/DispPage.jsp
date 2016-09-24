
<html>
<head>
    <%@ include file="/CustomView/JSPF/head.jspf"%>
    <script src="${pageContext.request.contextPath}/view/js/disMultiSelect.js"></script>

</head>
<body>

<header>
    <%@ include file="/CustomView/header.jsp"%>
</header>
<h3>${param.result}</h3>
<div class="container">
    <div class="row">
        <div class="col s12">
            <div class="section">
                <div class="row">
                    <h5><fmt:message key="dispPage.createFlight"/></h5>
                </div>
                <div class="row">
                    <form name="createFlight" method="post">
                        <table border="1">
                            <input type="hidden" name="flightID" value="${requestScope.flightID}">
                            <input type="hidden" name="crewID" value="${requestScope.crewID}">
                            <tr>
                                <td valign="top"><fmt:message key="dispPage.flightName"/></td>
                                <td valign="top"><input type="text" name="flightName" size="20" placeholder="flightName">
                                </td>

                            </tr>

                            <tr>
                                <td valign="top"><fmt:message key="dispPage.departure"/></td>
                                <td>
                                    <select name="departureCity" class="browser-default">
                                        <option selected="selected" disabled="disabled">Empty</option>
                                        <c:forEach items="${applicationScope.cities}" var="citiesView">
                                            <option value="${citiesView}">
                                                    ${citiesView}
                                            </option>
                                        </c:forEach>
                                    </select>
                                </td>

                            </tr>

                            <tr>
                                <td valign="top"><fmt:message key="dispPage.destination"/></td>
                                <td>
                                    <select name="destinationCity" class="browser-default">
                                        <option selected="selected" disabled="disabled">Empty</option>
                                        <c:forEach items="${applicationScope.cities}" var="citiesView">
                                            <option value="${citiesView}">
                                                    ${citiesView}
                                            </option>
                                        </c:forEach>
                                    </select>
                                </td>
                            </tr>

                            <tr>
                                <td valign="top"><fmt:message key="dispPage.departureDate"/></td>
                                <td valign="top"><input type="text" name="departureDate" size="20" placeholder="departure date"></td>
                            </tr>

                            <c:forEach items="${requestScope.allAvailableEmployee}" var="speciality">
                                <c:if test="${speciality.key eq 'allPilots'}">
                                    <c:set var="pilots" value="${speciality.value}"/>
                                </c:if>
                                <c:if test="${speciality.key eq 'allStewardess'}">
                                    <c:set var="stewardess" value="${speciality.value}"/>
                                </c:if>
                                <c:if test="${speciality.key eq 'allNavigators'}">
                                    <c:set var="navigators" value="${speciality.value}"/>
                                </c:if>
                                <c:if test="${speciality.key eq 'allRadioman'}">
                                    <c:set var="radiomen" value="${speciality.value}"/>
                                </c:if>
                            </c:forEach>

                            <tr>
                                <td valign="top"><fmt:message key="dispPage.firstPilot"/></td>
                                <td>
                                    <select name="firstPilot" class="browser-default">
                                        <option selected="selected" disabled="disabled">Empty</option>
                                        <c:forEach items="${pilots}" var="pilot1">
                                            <option value="${pilot1}">
                                                    ${pilot1}
                                            </option>
                                        </c:forEach>
                                    </select>
                                </td>
                            </tr>

                            <tr>
                                <td valign="top"><fmt:message key="dispPage.secondPilot"/></td>
                                <td><select name="secondPilot" class="browser-default">
                                    <option selected="selected" disabled="disabled">Empty</option>
                                    <c:forEach items="${pilots}" var="pilot2">
                                        <option value="${pilot2}">
                                                ${pilot2}
                                        </option>
                                    </c:forEach>
                                </select>
                                </td>
                            </tr>

                            <tr>
                                <td valign="top"><fmt:message key="dispPage.firstStewardess"/></td>
                                <td><select name="firstStewardess" class="browser-default">
                                    <option selected="selected" disabled="disabled">Empty</option>
                                    <c:forEach items="${stewardess}" var="stewardess1">
                                        <option value="${stewardess1}">
                                                ${stewardess1}
                                        </option>
                                    </c:forEach>
                                </select>
                                </td>
                            </tr>

                            <tr>
                                <td valign="top"><fmt:message key="dispPage.secondStewardess"/></td>
                                <td><select name="secondStewardess" class="browser-default">
                                    <option selected="selected" disabled="disabled">Empty</option>
                                    <c:forEach items="${stewardess}" var="stewardess2">
                                        <option value="${stewardess2}">
                                                ${stewardess2}
                                        </option>
                                    </c:forEach>
                                </select>
                                </td>
                            </tr>

                            <tr>
                                <td valign="top"><fmt:message key="dispPage.navigator"/></td>
                                <td><select name="navigator" class="browser-default">
                                    <option selected="selected" disabled="disabled">Empty</option>
                                    <c:forEach items="${navigators}" var="navigator">
                                        <option value="${navigator}">
                                                ${navigator}
                                        </option>
                                    </c:forEach>
                                </select>
                                </td>
                            </tr>

                            <tr>
                                <td valign="top"><fmt:message key="dispPage.radiomen"/></td>
                                <td><select name="radiomen" class="browser-default">
                                    <option selected="selected" disabled="disabled">Empty</option>
                                    <c:forEach items="${radiomen}" var="radioman">
                                        <option value="${radioman}">
                                                ${radioman}
                                        </option>
                                    </c:forEach>
                                </select>
                                </td>
                            </tr>

                            <tr>
                                <td valign="top"><fmt:message key="dispPage.status"/></td>
                                <td>
                                    <select class="browser-default" name="status">
                                        <option selected="selected" disabled="disabled">Empty</option>
                                        <option selected="selected">ready</option>
                                        <option selected="selected">not ready</option>
                                    </select>
                                </td>
                            </tr>
                            <c:if test="${sessionScope.role=='disp'}">
                            <tr>
                                <td valign="top"><input class="btn waves-effect col s5" type="submit" value="<fmt:message key="dispPage.buttonCreate"/>" size="20"  formaction="/DispPage?command=createFlight"></td>
                            </tr>

                            <tr>
                                <td valign="top"><input class="btn waves-effect col s5" type="submit" value="<fmt:message key="dispPage.createRequest"/>" size="20"  formaction="/DispPage?command=createRequest" ></td>
                            </tr>
                            </c:if>
                            <c:if test="${sessionScope.role=='admin'}">
                                <tr>
                                    <td valign="top"><input class="btn waves-effect col s5" type="submit" value="<fmt:message key="dispPage.updateFlight"/>"  formaction="/AdminPage/Flights?command=updateFlight" ></td>
                                </tr>
                            </c:if>
                        </table>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>


    </body>
    </html>

