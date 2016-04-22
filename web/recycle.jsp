<%@page import="database.RecycleToyDB"%>
<%@page import="bean.RecycleToy"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
  <head>
      
      <% 
      if (request.getAttribute("productItems")==null){
        String targetURL="viewRecycleToy?type=all&page=all&pageNo=1";
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
    </div> <!-- End mainmenu area -->
    
    <!-- Promo area -->
    <jsp:include page="promo.jsp"/>
    
    <!-- Browse recycle toy area -->
    
    <% ArrayList<RecycleToy> productItems=(ArrayList<RecycleToy>) request.getAttribute("productItems");%>
    <div class="single-product-area">
        <div class="zigzag-bottom"></div>
        <div class="container">
            
            <div class="col-md-3">
                <h2>Category</h2>
                
                <ul>
                    <li><a href="viewRecycleToy?type=all&page=all&pageNo=1">All Category</a></li>
                    <%
                       RecycleToyDB rtDB=new RecycleToyDB();
                       for (String s: rtDB.getAllCategory()) 
                           out.println("<li><a href=\"viewRecycleToy?type="+s+"&page=all&pageNo=1\">"+s.replaceAll("_"," ")+"</a></li>");
                    %>
                </ul>
                
                
            </div>
            
            <div class="col-md-8">
                <div class="">
                    <div class="row">
                
                  <% 
                       
                        int pageNo=Integer.parseInt(String.valueOf(request.getAttribute("pageNo"))); //current Page no, start from 1

                        int itemPerPage=16;
                        int firstItem=(pageNo-1)*itemPerPage;
                        int lastItem=firstItem+itemPerPage;
                        for (int i=firstItem;i<(Math.min(productItems.size(),lastItem));i++){
                               RecycleToy product=productItems.get(i);

                               out.println("<div class=\"col-md-4 col-sm-6\">");
                               out.println("<div class=\"single-shop-product\">"
                                   + "<div class=\"product-f-image\">");
                               out.println("<img class=\"product-img\" src=\"img/"+product.getImage()+"\">");
                               out.println("</div>");
//                               out.println(" <div class=\"product-hover\">");
//                               out.println("<a href=\"shopping?action=add&cb=true&id="+product.getTid()+"\" class=\"add-to-cart-link\"><i class=\"fa fa-shopping-cart\"></i> Add to cart</a>");
//                               out.println("<a href=\"viewProduct?type=\""+product.getTid()+"&page=detail class=\"view-details-link\"><i class=\"fa fa-link\"></i> See details</a>");                             
//                               out.println("</div>"
//                                       + "</div>");
                               out.println("<h2><a href=\"viewRecycleToy?type="+product.getTid()+"&page=detail\" >"+product.getName()+"</a></h2>");
                               out.println("<div class=\"product-carousel-price\">");
                               out.println("<ins> $"+product.getPrice()+"</ins>");
                               out.println("</div>");
                               out.println("<div class=\"product-option-shop\">");
                               out.println("<a class=\"add_to_cart_button\" data-quantity=\"1\" data-product_sku=\"\" data-product_id=\"70\" rel=\"nofollow\" a href=\"shopping?action=add&cb=true&id="+product.getTid()+"\">Add to cart</a>");
                               out.println("</div>");
                               out.println("</div>");
                               out.println("</div>");

                                      
                           }
                            
                 
                               
                 %>
        
                
            </div>
                    
                </div>
                
            </div>
            
            
            
            <div class="row">
                <div class="col-md-12">
                    <div class="product-pagination text-center">
                        <nav>
                          <ul class="pagination">
                            <li>
                              <a href="#" aria-label="Previous">
                                <span aria-hidden="true">&laquo;</span>
                              </a>
                            </li>
                            <%
                             int totalPage=productItems.size()%itemPerPage==0?productItems.size()/itemPerPage:productItems.size()/itemPerPage+1;
                             for (int i=1;i<totalPage+1;i++)
                                 out.println("<li><a href=\"viewRecycleToy?type=all&page=all&pageNo="+i+"\">"+i+"</a></li>");  
                            %>

                            <li>
                              <a href="#" aria-label="Next">
                                <span aria-hidden="true">&raquo;</span>
                              </a>
                            </li>
                          </ul>
                        </nav>                        
                    </div>
                </div>
            </div>
        </div>
    </div>
    
    
    <!-- Add Recycle Area-->
    <div class="container">
        <div class="row centered-form">
            <div class="col-xs-12 col-sm-8 col-md-6 col-sm-offset-2 col-md-offset-3">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h2 class="panel-title">Recycle</h2>
                        <small>Please fill in all the field</small>
                    </div>
            
                    <div class="panel-body">
                        <form role="form" action="recycle" method="post">
                            <div class="form-group">
                                <input type="text" name="name" placeholder="Toy Name" class="form-control" id="name" required />  
                            </div>

                            <div class="form-group">
                                <input type="text" name="description" placeholder="Toy Description" class="form-control" id="description" required />  
                            </div>
                            
                            <div class="form-group">
                                <input type="text" name="category" placeholder="Toy Category" class="form-control" id="category" required />
                            </div>
                            
                            <div class="form-group">
                                <input type="number" step="0.1" name="price" placeholder="Price" class="form-control" id="price" required />
                            </div>
                            
                            
                            <input type="submit" value="Confirm Donation" class="btn btn-info btn-block">
                            <input type="hidden" name="action" value="donate" />
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- End main content area -->
    
    <!-- Footer -->
    <jsp:include page="footer.jsp"/>
  </body>
</html>