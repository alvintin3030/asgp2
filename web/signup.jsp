<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="util.Getter"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Toy Market</title>
    
    <!-- Google Fonts -->
    <link href='http://fonts.googleapis.com/css?family=Titillium+Web:400,200,300,700,600' rel='stylesheet' type='text/css'>
    <link href='http://fonts.googleapis.com/css?family=Roboto+Condensed:400,700,300' rel='stylesheet' type='text/css'>
    <link href='http://fonts.googleapis.com/css?family=Raleway:400,100' rel='stylesheet' type='text/css'>
    
    <!-- Bootstrap -->
    <link rel="stylesheet" href="css/bootstrap.min.css">
    
    <!-- Font Awesome -->
    <link rel="stylesheet" href="css/font-awesome.min.css">
    
    <!-- Custom CSS -->
    <link rel="stylesheet" href="css/owl.carousel.css">
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="css/responsive.css">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>
  <body>
    <%
        Boolean registerError = Getter.getBoolean(request.getAttribute("registerError"));
    %>
      
    <!-- Header -->
    <jsp:include page="header.jsp"/>
    
    <!-- Main Menu -->
    <jsp:include page="mainmenu.jsp"/>
    
    <!-- Sign up -->
    <div class="container">
        <div class="row centered-form">
            <div class="col-xs-12 col-sm-8 col-md-6 col-sm-offset-2 col-md-offset-3">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h2 class="panel-title">Sign up now</h2>
                        <small>Please fill in all the field</small>
                    </div>
            
                    <div class="panel-body">
                        <form role="form" action="signup" method="post">
                            <div class="form-group">
                                <input type="text" name="username" placeholder="Username" class="form-control" id="username" required />  
                            </div>

                            <div class="row">
                                <div class="col-xs-6 col-sm-6 col-md-6">
                                    <div class="form-group">
                                        <input type="password" name="password" placeholder="Password" class="form-control" id="password" required />
                                    </div>
                                </div>
                                <div class="col-xs-6 col-sm-6 col-md-6">
                                    <div class="form-group">
                                        <input type="password" name="password_confirm" placeholder="Confirm password" class="form-control" id="password_confirm" required />
                                    </div>
                                </div>
                            </div>
                            
                            <div class="form-group">
                                <input type="text" name="email" placeholder="Email" class="form-control" id="email" required />
                            </div>
                            
                            <div class="form-group">
                                <input type="text" name="phone" placeholder="Phone" class="form-control" id="phone" required />
                            </div>
                            
                            <div class="form-group">
                                <textarea name="address" placeholder="Address" class="form-control noresize" id="address" required></textarea>
                            </div>
                            
                            <input type="submit" value="Register" class="btn btn-info btn-block">
                            <input type="hidden" name="action" value="register" />
                        </form>
                    </div>
                    
                    <% if (registerError) { %>           
                        <div class="error">Register failed - username already in use</div>
                    <% } %>
                </div>
            </div>
        </div>
    </div>

    <!-- Promo area -->
    <jsp:include page="promo.jsp"/>
    
    <!-- Footer -->
    <jsp:include page="footer.jsp"/>
   
    <!-- Validate Password -->
    <script type="text/javascript">
        var password = document.getElementById("password");
        var password_confirm = document.getElementById("password_confirm");

        function validatePassword(){
          if (password.value !== password_confirm.value) {
            password_confirm.setCustomValidity("Passwords Don't Match");
          } else {
            password_confirm.setCustomValidity('');
          }
        }

        password.onchange = validatePassword;
        password_confirm.onkeyup = validatePassword;
    </script>
  </body>
</html>