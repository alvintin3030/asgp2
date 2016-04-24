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
    <title>Checkout Page</title>
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
    <%  User user=(User)session.getAttribute("userInfo");   
        String username=user.getUsername();
    %>
    
    <script type="text/javascript" language="JavaScript">
     function deleteItem(tid)   {
        $.ajax({
            url: "shopping",
            data: {username:"<%=username%>",id:tid,action:"delete"}
          }).done(function() {
            location.reload();
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
                        <h2>Recycle</h2>
                    </div>
                </div>
            </div>
        </div>
    </div>
    
    
    <div class="single-product-area">
        <div class="zigzag-bottom"></div>
        <div class="container">
            <div class="row">
             
                
                <div class="col-md-12">
                    <div class="product-content-right">
                        <div class="woocommerce">
                            
                            

                           


                            <form  action="RecycleCheckoutController" class="checkout" method="get" >
                                <input type="text" style="display:none" name="action" value="pay"/>  

                                <h3 id="order_review_heading">Your order</h3>

                                <div id="order_review" style="position: relative;">
                                    <table class="shop_table">
                                        <thead>
                                            <tr>
                                                <th class="product-name">Recycle Product</th>
                                                <th class="product-total">Total</th>
                                            </tr>
                                        </thead>
                                        
                                        
                                        <tbody>
                                            
                                            <% 
                                                username=((User)session.getAttribute("userInfo")).getUsername();
                                                float total=0;
                                                RecycleToyDB rtdb =new RecycleToyDB();
                                                ArrayList<RecycleCart> products=(ArrayList<RecycleCart>)session.getAttribute("products");
                                                for (RecycleCart p: products){
                                                   RecycleToy rt=rtdb.getRToyById(p.getToyId());
                                                   total=total+rt.getPrice();
                                            %>
                                            
                                                <tr class="cart_item">
                                                    <td class="product-name">
                                                        <%=rt.getName()%> <strong class="product-quantity"></strong> </td>
                                                    <td class="product-total">
                                                        <span class="amount subTotal">$<%=rt.getPrice()%></span> </td>
                                                </tr>
                                            
                                            <% } %>
                                            
                                        </tbody>
                                        
                                        
                                        <tfoot>

                                            <tr class="cart-subtotal">
                                                <th>Cart Subtotal</th>
                                                <td><span class="amount totalAmount">$<%=total%></span>
                                                </td>
                                            </tr>

                                            <tr class="shipping">
                                                <th>Shipping and Handling</th>
                                                <td>

                                                    Free Shipping
                                                    <input type="hidden" class="shipping_method" value="free_shipping" id="shipping_method_0" data-index="0" name="shipping_method[0]">
                                                </td>
                                            </tr>


                                            <tr class="order-total">
                                                <th>Order Total</th>
                                                <td><strong><span class="amount totalAmount">$<%=total%></span></strong> </td>
                                            </tr>
                                            
                                        
                                        </tfoot>
                                    </table>

                                                    
                                    <div id="payment">
                                        
                                                

                                               <ul class="payment_methods methods">
                                                   <li class="payment_method_bacs">
                                                       <input type="radio" data-order_button_text="" checked="checked" value="Direct_Bank_Transfer" name="payment_method" class="input-radio" id="payment_method_bacs">
                                                       <label for="payment_method_bacs">Direct Bank Transfer </label>
                                                       <div class="payment_box payment_method_bacs">
                                                           <p>Make your payment directly into our bank account. Please use your Order ID as the payment reference. Your order won’t be shipped until the funds have cleared in our account.</p>
                                                       </div>
                                                   </li>
                                                   <li class="payment_method_cheque">
                                                       <input type="radio" data-order_button_text="" value="Cheque" name="payment_method" class="input-radio" id="payment_method_cheque">
                                                       <label for="payment_method_cheque">Cheque Payment </label>
                                                       <div style="display:none;" class="payment_box payment_method_cheque">
                                                           <p>Please send your cheque to Store Name, Store Street, Store Town, Store State / County, Store Postcode.</p>
                                                       </div>
                                                   </li>
                                                   <li class="payment_method_paypal">
                                                       <input type="radio" data-order_button_text="Proceed to PayPal" value="Paypal" name="payment_method" class="input-radio" id="payment_method_paypal">
                                                       <label for="payment_method_paypal">PayPal <img alt="PayPal Acceptance Mark" src="https://www.paypalobjects.com/webstatic/mktg/Logo/AM_mc_vs_ms_ae_UK.png"><a title="What is PayPal?" onclick="javascript:window.open('https://www.paypal.com/gb/webapps/mpp/paypal-popup','WIPaypal','toolbar=no, location=no, directories=no, status=no, menubar=no, scrollbars=yes, resizable=yes, width=1060, height=700'); return false;" class="about_paypal" href="https://www.paypal.com/gb/webapps/mpp/paypal-popup">What is PayPal?</a>
                                                       </label>
                                                       <div style="display:none;" class="payment_box payment_method_paypal">
                                                           <p>Pay via PayPal; you can pay with your credit card if you don’t have a PayPal account.</p>
                                                       </div>
                                                   </li>
                                               </ul>

                                               <div class="form-row place-order">

                                                   <input type="submit" data-value="Place order" value="Place order" id="place_order" name="woocommerce_checkout_place_order" class="button alt">


                                               </div>

                                               <div class="clear"></div>
                                        
                                    </div>
                                </div>
                            </form>

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