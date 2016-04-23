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
    
    <% RecycleToy productItems=(RecycleToy)request.getAttribute("productItems");%>
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
                                    
                                    <form action="" class="cart">
                                        <div class="quantity">
                                            <input type="number" size="4" class="input-text qty text" title="Qty" value="1" name="quantity" min="1" step="1">
                                        </div>
                                        <button class="add_to_cart_button" type="submit">Add to cart</button>
                                    </form>   
                                    
                                    <div class="product-inner-category">
                                        <p>Category: <a href="viewProduct?&page=all&type=<%=productItems.getCategory()%>&pageNo=1"><%=productItems.getCategory().replaceAll("_"," ")%></a>  </p>
                                    </div> 
                                    
                                    <div class="product-inner-category">
                                        <p>Donated By: <a href="viewProduct?&page=all&type=<%=productItems.getDonatedBy()%>&pageNo=1"></a>  </p>
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