<%@page import="bean.User"%>

<% 
    boolean isLogin = false;
    User user = null;
    if (session.getAttribute("userInfo") != null) {
        isLogin = true;
        user = (User) session.getAttribute("userInfo");
    }
%>

<script>
    var currentUrl = document.baseURI;
    var lastSegment;
    do {
        lastSegment = currentUrl.split('/').pop();
    } while (lastSegment == null);

    function onClickDirectPage(url) {
        document.location.href = url;
    }
</script>

<div class="header-area">
    <div class="row">
        <div class="note">
            This web site exists to fulfill the coursework requirement of CS4280. Do not use your real personal data as input.     
        </div>
    </div>
    
    <div class="container">
        <div class="row">
            <div class="col-md-8">
                <div class="user-menu">
                    <ul>
                        <li><a href="#"><i class="fa fa-user"></i> My Account</a></li>
                        <li><a href="cart.jsp"><i class="glyphicon glyphicon-shopping-cart"></i> My Cart</a></li>
                        <li><a href="checkout.jsp"><i class="glyphicon glyphicon-check"></i> Checkout</a></li>
                    </ul>
                </div>
            </div> 
        
            <div class="col-md-4">
                <div class="header-right">
                    <ul class="list-unstyled list-inline">
                        <%
                            if (isLogin == true) {
                                out.println("<li><i class='glyphicon glyphicon-user'></i> Hi " + user.getUsername() + "</li>");
                                out.println("<li><a href='#logout' onclick=\"onClickDirectPage('login?action=logout');\"><i class='glyphicon glyphicon-log-in'></i> Logout</a></li>");
                            } else {
                                out.println("<li><a href='signup.jsp'><i class='glyphicon glyphicon-pencil'></i> Sign up</a></li>");
                                out.println("<li><a href='login.jsp'><i class='glyphicon glyphicon-log-in'></i> Login</a></li>");
                            }
                        %>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div> 

<!-- Site Branding Area -->
<div class="site-branding-area">
    <div class="container">
        <div class="row">
            <div class="col-sm-6">
                <div class="logo">
                    <h1><a href="./">Toy Market</a></h1>
                </div>
            </div>

            <div class="col-sm-6">
                <div class="shopping-item">
                    <a href="cart.jsp">Cart - <span class="cart-amunt">$100</span> <i class="fa fa-shopping-cart"></i> <span class="product-count">5</span></a>
                </div>
            </div>
        </div>
    </div>
</div> <!-- End site branding area -->