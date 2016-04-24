<%@page import="java.util.ArrayList"%>
<%@page import="bean.RecycleToy"%>

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
        ArrayList<RecycleToy> approvedToys = request.getAttribute("approvedToys") != null ?  (ArrayList<RecycleToy>) request.getAttribute("approvedToys") : new ArrayList<RecycleToy>();        
        ArrayList<RecycleToy> notApprovedToys = request.getAttribute("notApprovedToys") != null ?  (ArrayList<RecycleToy>) request.getAttribute("notApprovedToys") : new ArrayList<RecycleToy>();
        ArrayList<RecycleToy> rejectedToys = request.getAttribute("rejectedToys") != null ?  (ArrayList<RecycleToy>) request.getAttribute("rejectedToys") : new ArrayList<RecycleToy>();
    %>  
      
    <!-- Header -->
    <jsp:include page="header.jsp"/>
    
    <!-- Main Menu -->
    <jsp:include page="mainmenu.jsp"/>
    
    <!-- Main Content area -->
    <div class="maincontent-area">
        <div class="zigzag-bottom"></div>
        <div class="container">
            <h4>Approved Recycle Toys</h4>
            <table class="table">
                <tr>
                    <th>Toy ID</th>
                    <th>Name</th>
                    <th>Category</th>
                    <th>Image</th>
                    <th>Price</th>
                    <th>Donated by</th>
                    <th>Edit</th>
                </tr>
                <% for (RecycleToy t : approvedToys) { %>
                <tr>
                    <td><%=t.getTid() %></td>
                    <td><%=t.getName() %></td>
                    <td><%=t.getCategory() %></td>
                    <td><img src="img/<%=t.getImage() %>" width="50px" height="50px" /></td>
                    <td>$<%=t.getPrice() %></td>
                    <td><%=t.getDonatedBy() %></td>
                    <td><a href="manageSingleRToy?tid=<%=t.getTid() %>"><span class="glyphicon glyphicon-pencil"></span></a></td>
                </tr>
                <% } %>
            </table>
            
            <h4>Pending Recycling Requirements</h4>
            <table class="table">
                <tr>
                    <th>Toy ID</th>
                    <th>Name</th>
                    <th>Category</th>
                    <th>Image</th>
                    <th>Price</th>
                    <th>Required by</th>
                    <th>Approve</th>
                </tr>
                <% for (RecycleToy t : notApprovedToys) { %>
                <tr>
                    <td><%=t.getTid() %></td>
                    <td><%=t.getName() %></td>
                    <td><%=t.getCategory() %></td>
                    <td><img src="img/<%=t.getImage() %>" width="50px" height="50px" /></td>
                    <td>$<%=t.getPrice() %></td>
                    <td><%=t.getDonatedBy() %></td>
                    <td>
                        <a href="approveRecycle?tid=<%=t.getTid() %>"><span class="glyphicon glyphicon-ok"></span></a>
                        <a href="rejectRecycle?tid=<%=t.getTid() %>"><span class="glyphicon glyphicon-remove"></span></a>
                    </td>
                </tr>
                <% } %>
            </table>
            
            <h4>Rejected Recycling Requirements</h4>
            <table class="table">
                <tr>
                    <th>Toy ID</th>
                    <th>Name</th>
                    <th>Category</th>
                    <th>Image</th>
                    <th>Price</th>
                    <th>Required by</th>
                    <th>Status</th>
                </tr>
                <% for (RecycleToy t : rejectedToys) { %>
                <tr>
                    <td><%=t.getTid() %></td>
                    <td><%=t.getName() %></td>
                    <td><%=t.getCategory() %></td>
                    <td><img src="img/<%=t.getImage() %>" width="50px" height="50px" /></td>
                    <td>$<%=t.getPrice() %></td>
                    <td><%=t.getDonatedBy() %></td>
                    <td>Rejected</td>
                </tr>
                <% } %>
            </table>
        </div>
    </div>
    
    <!-- Footer -->
    <jsp:include page="footer.jsp"/>
  </body>
</html>