<%@page import="java.util.ArrayList"%>
<%@page import="bean.Toy"%>

<!DOCTYPE html>
<html lang="en">
  <head>
      
      <!--if no product is stored in the attr, send request to controller to update items (and then reload the page)"-->
     <% 
      if (request.getAttribute("productItems")==null){
        String targetURL="viewProduct?type=latest&page=index";
        RequestDispatcher rd;
        rd = getServletContext().getRequestDispatcher("/" + targetURL);
        rd.forward(request, response);
      }
     %>
      
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
    <jsp:include page="mainmenu.jsp"/>
    
    <!-- Promo area -->
    <jsp:include page="promo.jsp"/>
    
    
    <% ArrayList<Toy> productItems=(ArrayList<Toy>) request.getAttribute("productItems");%>
    
    <!-- Main Content area -->
    <div class="maincontent-area">
        <div class="zigzag-bottom"></div>
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <div class="latest-product">
                        <h2 class="section-title">Latest Products</h2>
                        <%
                             if (request.getAttribute("msg")!=null)
                                   out.println("<div class=row>"+request.getAttribute("msg")+"</div>");
                        %>
                        <div class="product-carousel">
                            
                            <% 
                              
                               for (int i=0;i<productItems.size();i++){
                                   Toy product=productItems.get(i);
                                   out.println("<div class=\"single-product\">"
                                       + "<div class=\"product-f-image\">");
                                   out.println("<img class=\"product-img\" src=\"img/"+product.getImage()+"\">");
                                   out.println("<div class=\"product-hover\">");
                                   out.println("<a href=\"shopping?quantity=1&action=add&cb=true&page=index&id="+product.getTid()+"\" class=\"add-to-cart-link\"><i class=\"fa fa-shopping-cart\"></i> Add to cart</a>");
                                   out.println("<a href=\"viewProduct?type="+product.getTid()+"&page=detail\" class=\"view-details-link\"><i class=\"fa fa-link\"></i> See details</a>");                             
                                   out.println("</div>"
                                           + "</div>");
                                   out.println("<h2><a href=\"viewProduct?type="+product.getTid()+"&page=detail\" >"+product.getName()+"</a></h2>");
                                   out.println("<div class=\"product-carousel-price\">");
                                   out.println("<ins> $"+product.getPrice()+"</ins>");
                                   out.println("</div>"
                                           + "</div>");
                               }
                               
                                
                            %>
                            
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div> <!-- End main content area -->
    
    <!-- Footer -->
    <jsp:include page="footer.jsp"/>
  </body>
</html>