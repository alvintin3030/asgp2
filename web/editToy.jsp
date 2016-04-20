<%@page import="bean.Toy"%>
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
        Toy t = request.getAttribute("toy") != null ? (Toy) request.getAttribute("toy") : new Toy();
        Boolean updateFail = Getter.getBoolean(request.getAttribute("updateFail"));
    %>  
      
    <!-- Header -->
    <jsp:include page="header.jsp"/>
    
    <!-- Main Menu -->
    <jsp:include page="mainmenu.jsp"/>
    
    <!-- Main Content area -->
    <div class="maincontent-area">
        <div class="zigzag-bottom"></div>
        <div class="container">
            <div clas="row">
                <div class="col-md-4">
                    <img src="img/<%=t.getImage() %>" max-width="200px" max-height="200px" />
                </div>
                <div class="col-md-8">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <h2 class="panel-title">Update Toy</h2>
                        </div>
                        
                        <div class="panel-body">
                            <form class="form-horizontal" method="POST" action="editToy?tid=<%=t.getTid() %>">
                                <input name="tid" type="hidden" value="<%=t.getTid() %>" />
                                <input name="image" type="hidden" value="<%=t.getImage() %>" />
                                <div class="form-group">
                                    <label class="control-label col-sm-2" for="name">Name:</label>
                                    <div class="col-sm-10">
                                        <input name="name" type="text" class="form-control" value="<%=t.getName()%>" />
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-2" for="description">Description:</label>
                                    <div class="col-sm-10">
                                        <textarea name="description" type="text" class="form-control noresize" rows="8"><%=t.getDescription() %></textarea>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-2" for="category">Category:</label>
                                    <div class="col-sm-10">
                                        <input name="category" type="text" class="form-control" value="<%=t.getCategory() %>" />
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-2" for="price">Price:</label>
                                    <div class="col-sm-10">
                                        <input name="price" type="text" class="form-control" value="<%=t.getPrice() %>" />
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-2" for="quantity">Quantity:</label>
                                    <div class="col-sm-10">
                                        <input name="quantity" class="form-control" value="<%=t.getQuantity() %>"/>
                                    </div>
                                </div>       
                                <input type="submit" class="btn btn-info btn-block" value="Update!" />
                                <input name="action" type="hidden" value="update" />
                            </form>                                         
                        </div>
                        <% if (updateFail) { %>           
                            <div class="error">Failed to update, please try again!</div>
                        <% } %>            
                    </div>
                </div>
            </div>
        </div>
    </div>
    
    <!-- Footer -->
    <jsp:include page="footer.jsp"/>
  </body>
</html>