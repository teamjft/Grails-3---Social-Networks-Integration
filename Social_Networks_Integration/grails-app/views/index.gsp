<%@ page import="com.jft.RegisterController" %>
<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Welcome</title>
    <asset:link rel="icon" href="favicon.ico" type="image/x-ico" />
</head>
<body>

<div id="content" role="main">
    %{--<h1>Welcome to Integration demo</h1>--}%
    <div class="page-container">
        <div class="register-box">
            <div style="margin-left: 3%">
                <h3>Register</h3>
            </div>
            <table>
                <tr>
                    <td><g:link controller="register" action="usingGoogle">Google</g:link> </td>
                    <td><g:link controller="register" action="usingFacebook">Facebook</g:link> </td>

                </tr>
            </table>
            <div>
                <h6 class="center">OR</h6>
            </div>
            <g:form name="register" controller='register' action="register">
                <table>
                     <tr>
                        <td> Email</td>
                        <td><input type="text" name="email"></td>
                    </tr>
                    <tr>
                        <td> First Name</td>
                        <td><input type="text" name="firstName"></td>
                    </tr>
                    <tr>
                        <td> Last Name</td>
                        <td><input type="text" name="lastName"></td>
                    </tr>
                    <tr>
                        <td> Password</td>
                        <td><input type="text" name="password"></td>
                    </tr>
                    <tr>
                        <td> Gender</td>
                        <td>Male<input type="radio" name="gender" value="Male" checked>
                        Female  <input type="radio" name="gender" value="Female"></td>
                    </tr>
                    <tr>
                        <td><input type="submit" value="Register"></td>
                    </tr>
            </g:form>
        </table>
        </div>


        <div class="login-box">
            <div style="margin-left: 3%">
                <h3>Login</h3>
            </div>
            <table>
                <tr>
                    <td><g:link controller="register" action="usingGoogle">Google</g:link> </td>
                    <td><g:link controller="register" action="usingFacebook">Facebook</g:link> </td>

                </tr>
            </table>
            <div>
                <h6 class="center">OR</h6>
            </div>
            <g:form name="login" controller='Login' action="auth">
                <table>
                <tr>
                        <td> Email:</td>
                        <td><input type="text" name="email"></td>
                    </tr>
                    <tr>
                        <td> Password</td>
                        <td><input type="text" name="password"></td>
                    </tr>

                        <td><input type="submit" value="Login"></td>
                    </tr>
            </g:form>
        </table>
        </div>
        <div style="clear: both"></div>
    </div>
</div>
</body>
</html>
