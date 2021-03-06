<%@page import="bean.RecycleToy"%>
<%@page import="database.RecycleToyDB"%>
<%@page import="bean.RecycleCart"%>
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
                        <h2>Buy Recycle Toy</h2>
                    </div>
                </div>
            </div>
        </div>
    </div> <!-- End Page title area -->
    
     <%
        User user=(User)session.getAttribute("userInfo");
        String username="";
        ArrayList<RecycleCart> products=null;
        if (request.getAttribute("update")!="update"){
            RequestDispatcher rd;
            rd = getServletContext().getRequestDispatcher("/RecycleCartController?action=view");
            rd.forward(request, response);
        }
        
        products=(ArrayList<RecycleCart>)request.getAttribute("products");
        
        if (user==null ){
            RequestDispatcher rd;
            rd = getServletContext().getRequestDispatcher("/RecycleCartController?action=view");
            rd.forward(request, response);
        }else{
            username=user.getUsername();
        }
	     
      %>
   
    <script type="text/javascript" language="JavaScript">
    function deleteItem(tid)   {
        $.ajax({
            url: "RecycleCartController",
            data: {username:"<%=username%>",id:tid,action:"delete"}
          }).done(function() {
            location.reload();
          });
      }
     
     function editQuantity(tid,change,unitPrice){
        var value=$("#"+tid).val();
        var newValue=parseInt(value)+parseInt(change);
        if (newValue<0)
            newValue=0;
        $("#"+tid).val(newValue);
        $("#"+tid+"Total").html(newValue*unitPrice);
        updateTotal();
            $.ajax({
               url: "RecycleCartController",
               data: {username:"<%=username%>",id:tid,quantity:newValue,action:"edit"}
             });
    }
    
    function updateTotal(){
        var sum=0;
        $(".subTotal").each(function() {
            var value = $(this).text();
            // add only if the value is number
            if(!isNaN(value) && value.length != 0) {
                sum += parseFloat(value);
            }
        });
        $(".totalAmount").html(sum)
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
                                            <th class="product-thumbnail">&nbsp;</th>
                                            <th class="product-name">Product</th>
                                            <th class="product-price">Price</th>
                                            <th class="product-subtotal">Total</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <%  RecycleToyDB rtdb=new RecycleToyDB();
                                        float total=0;
                                        if (products!=null && products.size()>0){
                                           
                                            for (RecycleCart cart:products){
                                                RecycleToy rt=rtdb.getRToyById(cart.getToyId());
                                                total=total+rt.getPrice();
                                        %>
					<tr class="cart_item">
                                           <td class="product-remove">
                                                <a title="Remove this item" class="remove" onclick="deleteItem(<%=rt.getTid()%>)">Delete</a> 
                                            </td>
											
                                            <td class="product-thumbnail">
                                                <a href="viewRecycleToy?type=<&=rt.getTid()%>&page=detail"><img width="145" height="145" alt="poster_1_up" class="shop_thumbnail" src="img/<%=rt.getImage()%>"></a>
                                            </td>

                                            <td class="product-name">
                                                <a href="viewRecycleToy?type=<%=rt.getTid()%>&page=detail"><%=rt.getName()%></a> 
                                            </td>

                                            <td class="product-price">
                                                <span class="amount">$<%=rt.getPrice()%></span> 
                                            </td>

                                            <td class="product-subtotal">
                                                <span class="amount subTotal" id="<%=rt.getTid()%>Total" >$<%=rt.getPrice()%></span> 
                                            </td>
                                        </tr>
										
					<% };} %>
                                        <tr>
                                            <td class="actions" colspan="6">
                                                <!--<form action="/checkoutController" method="get">-->
                                                    <!--<input type="textbox" style="display:none" value="newPayment"/>-->
                                                   <!--<input type="submit" value="submit" name="proceed" class="checkout-button button alt wc-forward"  />-->
                                                <!--</form>-->
                                                <a href="RecycleCheckoutController?action=newPayment">Checkout</a>
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
                                            <td><span class="amount totalAmount">$<%=total%></span></td>
                                        </tr>

                                        <tr class="shipping">
                                            <th>Shipping and Handling</th>
                                            <td>Free Shipping</td>
                                        </tr>

                                        <tr class="order-total">
                                            <th>Order Total</th>
                                            <td><strong><span class="amount totalAmount">$<%=total%></span></strong> </td>
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