<%@page import="bean.User"%>
<%@page import="util.Getter"%>

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
    <% 
        boolean isLogin = false;
        User user = null;
        if (session.getAttribute("userInfo") != null) {
            isLogin = true;
            user = (User) session.getAttribute("userInfo");
        } 
        Boolean editFail = Getter.getBoolean(request.getAttribute("editFail"));
        Boolean editSuccess = Getter.getBoolean(request.getAttribute("editSuccess"));
    %>
      
    <!-- Header -->
    <jsp:include page="header.jsp"/>
    
    <!-- Main Menu -->
    <jsp:include page="mainmenu.jsp"/>
    
    <!-- Main Content area -->
    <div class="maincontent-area">
        <div class="zigzag-bottom"></div>
        <div class="container">
            <div clas="row">
                <div class="col-md-8 col-md-offset-2">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <h2 class="panel-title">My Account Information</h2>
                        </div>
                        
                        <div class="panel-body">
                            <form class="form-horizontal" method="POST" action="editUserInfo">
                                <input name="tid" type="hidden" value="<%=user.getUid() %>" />
                                <input name="groupId" type="hidden" value="<%=user.getGroupId() %>" />
                                <div class="form-group">
                                    <label class="control-label col-sm-4" for="username">Username:</label>
                                    <div class="col-sm-8">
                                        <input name="username" type="hidden" value="<%=user.getUsername()%>" />
                                        <input name="showUsername" type="text" class="form-control" value="<%=user.getUsername()%>" disabled />
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4" for="username">Password:</label>
                                    <div class="col-sm-6">
                                        <input name="password" type="password" class="form-control" value="" required />
                                    </div>
                                    <div class="col-sm-1">
                                        <button type="button" class="btn btn-primary" onclick="changePassword()">Edit</button>
                                    </div>
                                </div>
                                <div class="form-group" id="newPwDiv1" style="display:none">
                                    <label class="control-label col-sm-4" for="username">New Password:</label>
                                    <div class="col-sm-8">
                                        <input name="newPassword" type="password" class="form-control" id="newPassword" value="" disabled />
                                    </div>
                                </div>
                                <div class="form-group" id="newPwDiv2" style="display:none">
                                    <label class="control-label col-sm-4" for="username">Confirm New Password:</label>
                                    <div class="col-sm-8">
                                        <input name="newPassword_confirm" type="password" class="form-control" id="newPassword_confirm" disabled />
                                    </div>
                                </div>    
                                <div class="form-group">
                                    <label class="control-label col-sm-4" for="email">Email:</label>
                                    <div class="col-sm-8">
                                        <input name="email" type="text" class="form-control" value="<%=user.getEmail() %>" required />
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4" for="phone">Phone:</label>
                                    <div class="col-sm-8">
                                        <input name="phone" class="form-control" value="<%=user.getPhone() %>" required />
                                    </div>
                                </div>       
                                <div class="form-group">
                                    <label class="control-label col-sm-4" for="address">Address:</label>
                                    <div class="col-sm-8">
                                        <textarea name="address" type="text" class="form-control noresize" rows="3" required ><%=user.getAddress() %></textarea>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4" for="credit">Credit:</label>
                                    <div class="col-sm-8">
                                        <input name="credit" type="text" class="form-control" value="<%=user.getCredit() %>" disabled />
                                    </div>
                                </div>
                                    
                                <input type="submit" class="btn btn-info btn-block" value="Update!" />
                                <input name="action" type="hidden" value="update" />
                            </form>                                         
                        </div>
                        <% if (editFail) { %>           
                            <div class="error">Failed to update, please try again!</div>
                        <% } else if (editSuccess) { %>   
                            <div class="error">Updated successfully!</div>
                        <% } %>

                    </div>
                </div>
            </div>
        </div>
    </div>
    
    <!-- Footer -->
    <jsp:include page="footer.jsp"/>
    
    <!-- Validate Password -->
    <script type="text/javascript">
        var password = document.getElementById("newPassword");
        var password_confirm = document.getElementById("newPassword_confirm");

        function validatePassword(){
          if (password.value !== password_confirm.value) {
            password_confirm.setCustomValidity("Passwords Don't Match");
          } else {
            password_confirm.setCustomValidity('');
          }
        }

        password.onchange = validatePassword;
        password_confirm.onkeyup = validatePassword;
        
        function changePassword(){
            document.getElementById("newPassword").disabled = false;
            document.getElementById("newPassword_confirm").disabled = false;
            document.getElementById("newPwDiv1").style.display = "block";
            document.getElementById("newPwDiv2").style.display = "block";
        }
    </script>
  </body>
</html>