<nav>
    <div class="nav-wrapper">
        <a href="#!" class="brand-logo center">Logo</a>
        <ul class="left hide-on-med-and-down">
            <li><a href="?lang=ru"><fmt:message key="lang.russian"/></a></li>
            <li><a href="?lang=en"><fmt:message key="lang.english"/></a></li>
            <c:if test="${userData != null}">
                <li><a href="/login?command=logOut"><fmt:message key="header.logout"/></a></li>
            </c:if>
            <c:if test="${userData == null}">
                <li><a href="/login">LogIn</a></li>
            </c:if>
        </ul>
    </div>
</nav>