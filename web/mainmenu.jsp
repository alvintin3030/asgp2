<%@page import="bean.User"%>

<% 
    String uri = request.getRequestURI();
    String[] target = uri.substring(uri.lastIndexOf("/")+1).split("\\W");
    String pageName = target[0];
    
    boolean isAdmin = false;
    User user = null;
    if (session.getAttribute("userInfo") != null) {
        user = (User) session.getAttribute("userInfo");
        if (user.getGroupId() == 0)
            isAdmin = true;
    }
%>

<div class="mainmenu-area">
    <div class="container">
        <div class="row">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
            </div> 
            <div class="navbar-collapse collapse">
                <% if (isAdmin) { %>
                    <ul class="nav navbar-nav">
                        <li class="<%=pageName.equals("index") ? "active" : "" %>"><a href="index.jsp">Home</a></li>
                        <li class="<%=pageName.equals("manageToy") ? "active" : "" %>"><a href="manageToy">Manage</a></li>
                        <li class="<%=pageName.equals("approveRecycle") ? "active" : "" %>"><a href="#">Approve</a></li>
                        <li class="<%=pageName.equals("replyComment") ? "active" : "" %>"><a href="#">Comment</a></li>
                    </ul>         
                <% } else { %>
                    <ul class="nav navbar-nav">
                        <li class="<%=pageName.equals("index") ? "active" : "" %>"><a href="index.jsp">Home</a></li>
                        <li class="<%=pageName.equals("shop") ? "active" : "" %>"><a href="shop.jsp">Shop</a></li>
                        <li class="<%=pageName.equals("cart") ? "active" : "" %>"><a href="cart.jsp">Cart</a></li>
                        <li class="<%=pageName.equals("recycle") ? "active" : "" %>"><a href="recycle.jsp">Recycle</a></li>
                    </ul>
                <% } %>
            </div>                
        </div>
    </div>
</div>