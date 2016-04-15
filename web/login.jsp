<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
   
    <!-- Header -->
    <jsp:include page="header.jsp"/>
    
    <!-- Main Menu -->
    <div class="mainmenu-area">
        <div class="container">
            <div class="row">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                </div> 
                <div class="navbar-collapse collapse">
                    <ul class="nav navbar-nav">
                        <li><a href="index.jsp">Home</a></li>
                        <li><a href="shop.jsp">Shop page</a></li>
                        <li><a href="cart.jsp">Cart</a></li>
                        <li><a href="checkout.jsp">Checkout</a></li>
                        <li><a href="recycle.jsp">Recycle</a></li>
                    </ul>
                </div>  
            </div>
        </div>
    </div> <!-- End mainmenu area -->
    
    <!-- Login -->
    <div class="container">
        <div class="row centered-form">
            <div class="col-xs-12 col-sm-8 col-md-4 col-sm-offset-2 col-md-offset-4">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h2 class="panel-title">
                            Login now
                        </h2>
                    </div>

                    <div class="panel-body" >
                        <form role="form" action="login" method="post">
                            <div class="form-group">
                                <input type="text" name="username" placeholder="Username" class="form-control" id="username" required />
                            </div>

                            <div class="form-group">
                                <input type="password" name="password" placeholder="Password" class="form-control" id="password" required />
                            </div>
                            
                            <input type="submit" value="Login" class="btn btn-info btn-block" />
                            <input type="hidden" name="action" value="authenticate" />
                        </form>
                    </div>
                    
                    <%
                        Boolean flag = (Boolean) request.getAttribute("loginError");
                        if (flag != null) {
                            if (flag == true) {
                                out.println("<div class=\"error\">");
                                out.println("Invalid username or password");
                                out.println("</div>");
                            } else {
                                out.println("<br />");
                            }
                        } else {
                            out.println("<br />");
                        }
                    %>
                </div>
            </div>
        </div>
    </div>

    <!-- Promo area -->
    <jsp:include page="promo.jsp"/>
    
    <!-- Footer -->
    <jsp:include page="footer.jsp"/>
   
    <!-- Latest jQuery form server -->
    <script src="https://code.jquery.com/jquery.min.js"></script>
    
    <!-- Bootstrap JS form CDN -->
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
    
    <!-- jQuery sticky menu -->
    <script src="js/owl.carousel.min.js"></script>
    <script src="js/jquery.sticky.js"></script>
    
    <!-- jQuery easing -->
    <script src="js/jquery.easing.1.3.min.js"></script>
    
    <!-- Main Script -->
    <script src="js/main.js"></script>
    
    <!-- Slider -->
    <script type="text/javascript" src="js/bxslider.min.js"></script>
    <script type="text/javascript" src="js/script.slider.js"></script>
  </body>
</html>