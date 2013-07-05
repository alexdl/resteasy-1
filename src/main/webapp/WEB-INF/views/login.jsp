<%--
  Created by IntelliJ IDEA.
  User: sean
  Date: 7/3/13
  Time: 10:23 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="static/header.jsp"/>
<body>
<div class="container">

    <form class="form-signin" action="j_spring_security_check" method="post">
        <h2 class="form-signin-heading">Please sign in</h2>
        <input type="text" id="j_username" name="j_username" class="input-block-level" placeholder="Username">
        <input type="password" id="j_password" name="j_password" class="input-block-level" placeholder="Password">
        <label class="checkbox">
            <input type="checkbox" value="remember-me"> Remember me
        </label>
        <button class="btn btn-large btn-primary" type="submit">Sign in</button>
        <div class="control-group">
            Not a user? <a href="/user/create">Create an account.</a>
        </div>
    </form>

</div> <!-- /container -->

<c:import url="static/jsscripts.jsp"/>
</body>

<c:import url="static/footer.jsp"/>