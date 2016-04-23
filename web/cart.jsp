<%@page import="bean.Toy"%>
<%@page import="database.ToyInventoryDB"%>
<%@page import="bean.ShoppingCart"%>
<%@page import="java.util.ArrayList"%>
<%@page import="bean.User"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Cart Page</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
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
                        <h2>Shopping Cart</h2>
                    </div>
                </div>
            </div>
        </div>
    </div> <!-- End Page title area -->
    
     <%
        User user=(User)session.getAttribute("userInfo");
        String username="";
        ArrayList<ShoppingCart> products=null;
        if (request.getAttribute("update")!="update"){
            RequestDispatcher rd;
            rd = getServletContext().getRequestDispatcher("/shopping?action=view");
            rd.forward(request, response);
        }else{
            String CartItem="products"+username;
            products=(ArrayList<ShoppingCart>)request.getAttribute(CartItem);
            request.setAttribute("update","read");
        }
        
        if (user==null ){
            RequestDispatcher rd;
            rd = getServletContext().getRequestDispatcher("/shopping?action=view");
            rd.forward(request, response);
        }else{
            username=user.getUsername();
        }
	     
      %>
   
    <script type="text/javascript" language="JavaScript">
    function deleteItem(tid)   {
        $.ajax({
            url: "shopping",
            data: {username:<%=username%>,tid:tid}
          }).done(function() {
            location.reload();
          });
      }
     
     function editQuantity(tid,action){
        
    }
    </script>	
    
    
    <div class="single-product-area">
        <div class="zigzag-bottom"></div>
        <div class="container">
            <div class="row">
                  <div class="col-md-12">
                    <div class="product-content-right">
                        <div class="woocommerce">
                            <form method="post" action="#">
                                <table cellspacing="0" class="shop_table cart">
                                    <thead>
                                        <tr>
                                            <th class="product-thumbnail">&nbsp;</th>
                                            <th class="product-name">Product</th>
                                            <th class="product-price">Price</th>
                                            <th class="product-quantity">Quantity</th>
                                            <th class="product-subtotal">Total</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <%  ToyInventoryDB tdb=new ToyInventoryDB();
                                        
                                        if (products!=null && products.size()>0){
                                            
                                            for (ShoppingCart cart:products){
                                                Toy toy=tdb.getToyById(cart.getToyId());
                                        %>
					<tr class="cart_item">
                                           <td class="product-remove">
                                                <a title="Remove this item" class="remove" onclick="deleteItem(<%=toy.getTid()%>">×</a> 
                                            </td>
											
                                            <td class="product-thumbnail">
                                                <a href="/viewProduct?type=<&=toy.getTid()%>&page=detail"><img width="145" height="145" alt="poster_1_up" class="shop_thumbnail" src="img/<%=toy.getImage()%>"></a>
                                            </td>

                                            <td class="product-name">
                                                <a href="/viewProduct?type=<&=toy.getTid()%>&page=detail"><%=toy.getName()%></a> 
                                            </td>

                                            <td class="product-price">
                                                <span class="amount">$<%=toy.getPrice()%></span> 
                                            </td>

                                            <td class="product-quantity">
                                                <div class="quantity buttons_added">
                                                    <input type="button" class="editQuantity minus" onclick="editQuantity(<%=toy.getTid()%>,plus)" value="-">
                                                    <input type="number" id="<%=toy.getTid()%>" size="4" class="input-text qty text" title="Qty" value="1" min="1" step="1">
                                                    <input type="button" class="editQuantity plus" onclick="editQuantity(<%=toy.getTid()%>,minus)" value="+">
                                                </div>
                                            </td>

                                            <td class="product-subtotal">
                                                <span class="amount">£15.00</span> 
                                            </td>
                                        </tr>
										
					<% };} %>
                                        <tr>
                                            <td class="actions" colspan="6">
                                               
                                                <input type="submit" value="Update Cart" name="update_cart" class="button">
                                                <input type="submit" value="Checkout" name="proceed" class="checkout-button button alt wc-forward">
                                            </td>
                                        </tr>
                                    </tbody>
                                </table>
                            </form>

                            <div class="cart-collaterals">





                            <div class="cart_totals ">
                                <h2>Cart Totals</h2>

                                <table cellspacing="0">
                                    <tbody>
                                        <tr class="cart-subtotal">
                                            <th>Cart Subtotal</th>
                                            <td><span class="amount">£15.00</span></td>
                                        </tr>

                                        <tr class="shipping">
                                            <th>Shipping and Handling</th>
                                            <td>Free Shipping</td>
                                        </tr>

                                        <tr class="order-total">
                                            <th>Order Total</th>
                                            <td><strong><span class="amount">£15.00</span></strong> </td>
                                        </tr>
                                    </tbody>
                                </table>
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