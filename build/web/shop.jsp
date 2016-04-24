<%@page import="java.util.ArrayList"%>
<%@page import="bean.Toy"%>
<%@page import="database.ToyInventoryDB"%>
<!DOCTYPE html>
<html lang="en">
  <head>
      
      <!--if no product is stored in the attr, send request to controller to update items (and then reload the page)"-->
     <% 
         
      if (request.getAttribute("productItems")==null){
        String targetURL="viewProduct?type=all&page=all&pageNo=1";
        RequestDispatcher rd;
        rd = getServletContext().getRequestDispatcher("/" + targetURL);
        rd.forward(request, response);
      }
     %>
      
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Shop Page</title>
    
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
    
    <div class="product-big-title-area">
        <div class="container">
            
            <div class="row">
                <div class="col-md-12">
                    <div class="product-bit-title text-center">
                        <h2>Shop</h2>
                    </div>
                </div>
            </div>
        </div>
    </div>
    
    <% ArrayList<Toy> productItems=(ArrayList<Toy>) request.getAttribute("productItems");
      
    %>
    
    <div class="single-product-area">
       
        <div class="zigzag-bottom"></div>
        <div class="container">
             <% if (request.getAttribute("msg")!=null)
                out.println("<div class=\"row\"><center>"+request.getAttribute("msg")+"</center></div><br>");
            %>
            <div class="col-md-3">
                <h2>Category</h2>
                
                <ul>
                    <li><a href="viewProduct?type=all&page=all&pageNo=1">All Category</a></li>
                    <%
                       ToyInventoryDB toydb=new ToyInventoryDB();
                       for (String s: toydb.getAllCategory()) 
                           out.println("<li><a href=\"viewProduct?type="+s+"&page=all&pageNo=1\">"+s.replaceAll("_"," ")+"</a></li>");
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
                               Toy product=productItems.get(i);

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
                               out.println("<h2><a href=\"viewProduct?type="+product.getTid()+"&page=detail\" >"+product.getName()+"</a></h2>");
                               out.println("<div class=\"product-carousel-price\">");
                               out.println("<ins> $"+product.getPrice()+"</ins>");
                               out.println("</div>");
                               out.println("<div class=\"product-option-shop\">");
                               out.println("<a class=\"add_to_cart_button\" data-quantity=\"1\" data-product_sku=\"\" data-product_id=\"70\" rel=\"nofollow\" a href=\"shopping?quantity=1&action=add&type="+product.getCategory()+"&page=all&pageNo=1&id="+product.getTid()+"\">Add to cart</a>");
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
                                 out.println("<li><a href=\"viewProduct?type=all&page=all&pageNo="+i+"\">"+i+"</a></li>");  
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
    
    <!-- Footer -->
    <jsp:include page="footer.jsp"/> 
  </body>
</html>