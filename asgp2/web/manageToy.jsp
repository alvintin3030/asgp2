<%@page import="java.util.ArrayList"%>
<%@page import="bean.Toy"%>

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
        ArrayList<Toy> toys = request.getAttribute("toys") != null ?  (ArrayList<Toy>) request.getAttribute("toys") : new ArrayList<Toy>();        
    %>  
      
    <!-- Header -->
    <jsp:include page="header.jsp"/>
    
    <!-- Main Menu -->
    <jsp:include page="mainmenu.jsp"/>
    
    <!-- Main Content area -->
    <div class="maincontent-area">
        <div class="zigzag-bottom"></div>
        <div class="container">
            <table class="table">
                <tr>
                    <th>Toy ID</th>
                    <th>Name</th>
                    <th>Category</th>
                    <th>Image</th>
                    <th>Price</th>
                    <th>Quantity</th>
                    <th>Edit</th>
                    <th>Delete</th>
                </tr>
                <% for (Toy t : toys) { %>
                <tr>
                    <td><%=t.getTid() %></td>
                    <td><%=t.getName() %></td>
                    <td><%=t.getCategory() %></td>
                    <td><img src="img/<%=t.getImage() %>" width="50px" height="50px" /></td>
                    <td>$<%=t.getPrice() %></td>
                    <td><%=t.getQuantity() %></td>
                    <td><a href="manageSingle?tid=<%=t.getTid() %>"><span class="glyphicon glyphicon-pencil"></span></a></td>
                    <td><a href="deleteToy?tid=<%=t.getTid() %>"><span class="glyphicon glyphicon-remove"></span></a></td>
                </tr>
                <% } %>
            </table>
        </div>
    </div>
    
    <!-- Footer -->
    <jsp:include page="footer.jsp"/>
  </body>
</html>