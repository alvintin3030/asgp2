<%@page import="java.util.ArrayList"%>
<%@page import="bean.*"%>
<%@page import="database.CommentDB"%>


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
   <script type="text/javascript" language="JavaScript">
    function reply(commentID){
        console.log("onclick");
        document.getElementById(commentID).style.display = 'block';
    }
    </script>
    
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
    
    <% Toy productItems=(Toy)request.getAttribute("productItems");%>
    <div class="single-product-area">
        <div class="zigzag-bottom"></div>
        <div class="container">
            <div class="row">
             
                
                <div class="col-md-12">
                    <div class="product-content-right">
                        <div class="product-breadcroumb">
                            <a href="">Home</a>
                            <a href="viewProduct?&page=all&type=<%=productItems.getCategory()%>&pageNo=1"><%=productItems.getCategory().replaceAll("_"," ")%></a>
                            <a href=""><%=productItems.getName()%></a>
                        </div>
                        
                        <div class="row">
                            <div class="col-sm-4">
                                <div class="product-images">
                                    <div class="product-main-img">
                                        <img src=img/<%=productItems.getImage()%> alt="">
                                    </div>
                                    
                                  
                                </div>
                            </div>
                            
                            <div class="col-sm-8">
                                <div class="product-inner">
                                    <h2 class="product-name"><%=productItems.getName()%></h2>
                                    <div class="product-inner-price">
                                        <ins>$<%=productItems.getPrice()%></ins>
                                    </div>    
                                    
                                    <form action="shopping" class="cart">
                                        <div class="quantity">
                                            <input type="number" size="4" class="input-text qty text" title="Qty" value="1" name="quantity" min="1" step="1">
                                            <input type="text" style="display:none" name="id" value="<%=productItems.getTid()%>">
                                            <input type="text" style="display:none" name="action" value="add">
                                        </div>
                                        <button class="add_to_cart_button" type="submit">Add to cart</button>
                                    </form>   
                                    
                                    <div class="product-inner-category">
                                        <p>Quantity in stock: <%=productItems.getQuantity()%></p>
                                        <p>Category: <a href="viewProduct?&page=all&type=<%=productItems.getCategory()%>&pageNo=1"><%=productItems.getCategory().replaceAll("_"," ")%></a>  </p>
                                    </div> 
                                    
                                    <div role="tabpanel">
                                        <ul class="product-tab" role="tablist">
                                            <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab" data-toggle="tab">Description</a></li>
                                         
                                        </ul>
                                        <div class="tab-content">
                                            <div role="tabpanel" class="tab-pane fade in active" id="home">
                                                <h2>Product Description</h2>  
                                                <p><%=productItems.getDescription()%></p>
                                            </div>
                                            
                                        </div>
                                    </div>
                                            
                                    <div role="tabpanel">
                                        <ul class="product-tab" role="tablist">
                                            <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab" data-toggle="tab">Comment</a></li>
                                         
                                        </ul>
                                        
                                        <%
                                            String username;
                                                        User user=(User) session.getAttribute("UserInfo");
                                                        if (user!=null)
                                                           username=user.getUsername();
                                                        else
                                                            username="Anonymous";
                                        %>
                                        
                                        <div class="tab-content">
                                            <div role="tabpanel" class="tab-pane fade in active" id="home">
                                                <form action="CommentController" method="get">
                                                    <div class="form-group">
                                                        <label for="commetArea">Having Comment?</label>
                                                        <textarea class="form-control" rows="5" id="commentArea" name="commentArea"></textarea> 
                                                        <input type="text" style="display:none" name="tid" value="<%=productItems.getTid()%>" />
                                                        <input type="text" style="display:none" name="action" value="add" />
                                                       <input type="text" style="display:none" name="username" value="<%=username%>" />
                                                    </div>
                                                    <div class="form-group">
                                                         <button type="submit" class="btn btn-default">Submit</button>
                                                    </div>
                                                </form>
                                                    
                                                
                                                <div>
                                                    <%
                                                        CommentDB cdb=new CommentDB();
                                                        for (Comment c:cdb.getMainComment()){
                                                            
                                                            if (c.getToyID()==productItems.getTid()){
                                                                out.println("<table class=\"table table-bordered\"><td>");
                                                                out.println("<p>Username: "+c.getUsername()+"<br/>Reply date: "+c.getDatetime()+"</p>");
                                                                out.println("<p>"+c.getContent()+"</p>");
                                                                
                                                                out.println("<p><a href=\"javascript:reply("+c.getCommentID()+")\"> Reply this comment</a></p>");
                                                                out.println("<form style=\"display:none\" id="+c.getCommentID()+" action=\"CommentController\" method=\"get\">"
                                                                        + "<div class=\"form-group\"><input type=\"text\" style=\"display:none\" name=\"action\" value=\"reply\"></input>"
                                                                        +"<input type=\"text\" style=\"display:none\" id=\"tid\" name=\"tid\" value=\""+productItems.getTid()+"\"></input>"
                                                                        +"<input type=\"text\" style=\"display:none\" id=\"id\" name=\"id\" value=\""+c.getCommentID()+"\"></input>"
                                                                        +"<input type=\"text\" style=\"display:none\" id=\"username\"  name=\"username\" value=\""+username+"\"></input>"
                                                                        + "<textarea class=\"form-control\" rows=\"5\" id=\"commentArea\" name=\"commentArea\"></textarea>"
                                                                        + "</div><div class=\"form-group\">"
                                                                        + "<button type=\"submit\" class=\"btn btn-default\">Submit</button>"
                                                                        + "</div></form>");
                                                                for (Comment sc:cdb.getSubComment(c.getCommentID())){
                                                                    
                                                                    out.println("<table class=\"table table-bordered\"><td>");
                                                                    out.println("<p>Username: "+sc.getUsername()+"<br/>Reply date: "+sc.getDatetime()+"</p>");
                                                                    out.println("<p>"+sc.getContent()+"</p>");
                                                                    out.println("</td></table>");
                                                                }
                                                                out.println("</td></table>");
                                                            }
                                                        }
                                                        
                                                    %>
                                            </div>
                                            
                                        </div>
                                    </div>
                                    
                                </div>
                            </div>
                        </div>
                        
                        
                     
                    </div>                    
                </div>
            </div>
        </div>
    </div>

    <!-- Footer -->
    <jsp:include page="footer.jsp"/>
  </body>
</html>