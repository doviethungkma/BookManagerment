<%-- 
    Document   : edit_book
    Created on : Nov 23, 2017, 2:32:57 PM
    Author     : shadyside
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>

        <!-- Title -->
        <title>Sửa thông tin sách</title>

        <meta content="width=device-width, initial-scale=1" name="viewport"/>
        <meta charset="UTF-8">
        <meta name="description" content="Admin Dashboard Template" />
        <meta name="keywords" content="admin,dashboard" />
        <meta name="author" content="Steelcoders" />

        <!-- Styles -->
<!--        <link href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,600' rel='stylesheet' type='text/css'>-->
        <link href="assets/plugins/pace-master/themes/blue/pace-theme-flash.css" rel="stylesheet"/>
        <link href="assets/plugins/uniform/css/uniform.default.min.css" rel="stylesheet"/>
        <link href="assets/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link href="assets/plugins/fontawesome/css/font-awesome.css" rel="stylesheet" type="text/css"/>
        <link href="assets/plugins/line-icons/simple-line-icons.css" rel="stylesheet" type="text/css"/>	
        <link href="assets/plugins/offcanvasmenueffects/css/menu_cornerbox.css" rel="stylesheet" type="text/css"/>	
        <link href="assets/plugins/waves/waves.min.css" rel="stylesheet" type="text/css"/>	
        <link href="assets/plugins/switchery/switchery.min.css" rel="stylesheet" type="text/css"/>
        <link href="assets/plugins/3d-bold-navigation/css/style.css" rel="stylesheet" type="text/css"/>
        <link href="assets/plugins/slidepushmenus/css/component.css" rel="stylesheet" type="text/css"/>	
        <link href="assets/plugins/weather-icons-master/css/weather-icons.min.css" rel="stylesheet" type="text/css"/>	
        <link href="assets/plugins/metrojs/MetroJs.min.css" rel="stylesheet" type="text/css"/>	
        <link href="assets/plugins/toastr/toastr.min.css" rel="stylesheet" type="text/css"/>	

        <!-- Theme Styles -->
        <link href="assets/css/modern.min.css" rel="stylesheet" type="text/css"/>
        <link href="assets/css/themes/green.css" class="theme-color" rel="stylesheet" type="text/css"/>
        <link href="assets/css/custom.css" rel="stylesheet" type="text/css"/>

        <script src="assets/plugins/3d-bold-navigation/js/modernizr.js"></script>
        <script src="assets/plugins/offcanvasmenueffects/js/snap.svg-min.js"></script>

        <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
        <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
        <![endif]-->

    </head>
    <body class="page-header-fixed">
        <s:if test="#session.loginID == 0">
            <jsp:include page="404.jsp"></jsp:include>
        </s:if><s:elseif test="#session.role != 'Admin'">
            <jsp:include page="404.jsp"></jsp:include>
        </s:elseif>
        <s:else>
            <jsp:include page="navbar.jsp"></jsp:include>
            <jsp:include page="sidebar.jsp"></jsp:include>   
                <div class="page-inner">
                    <div class="page-title">
                        <h3>Sửa thông tin sách</h3>
                        <div class="page-breadcrumb">
                            <ol class="breadcrumb">
                                <li><a href="index.html">Home</a></li>
                                <li class="active">Suathongtinsach</li>
                            </ol>
                        </div>
                    </div>
                    <div id="main-wrapper">
                        <div class="row">
                            <div class="col-lg-12 col-md-12">
                                <div class="panel panel-white">
                                    <div class="panel-heading">
                                        <!--<h4 class="panel-title">Quản lý người dùng</h4>-->
                                    </div>
                                    <div class="panel-body">
                                    <s:form action="EditBook" method="POST" cssClass="form-horizontal" enctype="multipart/form-data">
                                        <s:iterator value="book">
                                            <s:token/>
                                            <input type="hidden" name="ID" class="form-control" id="input-Default txtFullName" value="<s:property value="ID"/>">
                                            <div class="form-group">
                                                <label for="input-Default" class="col-sm-2 control-label">Tên sách</label>
                                                <div class="col-sm-10">
                                                    <input type="text" name="title" class="form-control" id="input-Default txtFullName" value="<s:property value="Title"/>" autocomplete="off">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-2 control-label">Tác giả</label>
                                                <div class="col-sm-10">
                                                    <input type="text" name="author" class="form-control date-picker" value="<s:property value="Author"/>" autocomplete="off">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label for="input-Default" class="col-sm-2 control-label">Mô tả</label>
                                                <div class="col-sm-10">
                                                    <!--<input type="text" name="description" class="form-control" id="input-Default" value="" autocomplete="off">-->
                                                    <textarea name="description" class="form-control" id="input-Default"><s:property value="Description"/></textarea>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label for="input-Default" class="col-sm-2 control-label">Giá</label>
                                                <div class="col-sm-10">
                                                    <input type="text" name="price" class="form-control" id="input-Default" value="<s:property value="Price"/>" autocomplete="off">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-2 control-label">Hình ảnh</label>
                                                <div class="col-sm-10">
                                                    <label class="custom-file">
                                                        <input type="file" name="bookImage" class="custom-file-input">
                                                        <span class="custom-file-input"></span>
                                                    </label>
                                                </div>
                                            </div>
                                            <div class="col-lg-2 col-lg-offset-8">
                                                <button type="submit" class="btn btn-success">Sửa sách</button>
                                            </div>
                                            <div class="col-lg-2">
                                                <s:url action="ManagerBook" id="ManagerBook"></s:url>
                                                <s:a href="%{ManagerBook}"><button type="button" class="btn btn-default">&nbsp;&nbsp;&nbsp;Hủy&nbsp;&nbsp;&nbsp;</button></s:a>
                                                </div>
                                        </s:iterator>
                                    </s:form>
                                </div>
                            </div>
                        </div><!-- Row -->
                    </div>
                </div>
            </main><!-- Page Content -->
            <div class="cd-overlay"></div>
        </s:else>

        <!-- Javascripts -->
        <script src="assets/plugins/jquery/jquery-2.1.4.min.js"></script>
        <script src="assets/plugins/jquery-ui/jquery-ui.min.js"></script>
        <script src="assets/plugins/pace-master/pace.min.js"></script>
        <script src="assets/plugins/jquery-blockui/jquery.blockui.js"></script>
        <script src="assets/plugins/bootstrap/js/bootstrap.min.js"></script>
        <script src="assets/plugins/jquery-slimscroll/jquery.slimscroll.min.js"></script>
        <script src="assets/plugins/switchery/switchery.min.js"></script>
        <script src="assets/plugins/uniform/jquery.uniform.min.js"></script>
        <script src="assets/plugins/offcanvasmenueffects/js/classie.js"></script>
        <script src="assets/plugins/offcanvasmenueffects/js/main.js"></script>
        <script src="assets/plugins/waves/waves.min.js"></script>
        <script src="assets/plugins/3d-bold-navigation/js/main.js"></script>
        <script src="assets/plugins/waypoints/jquery.waypoints.min.js"></script>
        <script src="assets/plugins/jquery-counterup/jquery.counterup.min.js"></script>
        <script src="assets/plugins/toastr/toastr.min.js"></script>
        <script src="assets/plugins/flot/jquery.flot.min.js"></script>
        <script src="assets/plugins/flot/jquery.flot.time.min.js"></script>
        <script src="assets/plugins/flot/jquery.flot.symbol.min.js"></script>
        <script src="assets/plugins/flot/jquery.flot.resize.min.js"></script>
        <script src="assets/plugins/flot/jquery.flot.tooltip.min.js"></script>
        <script src="assets/plugins/curvedlines/curvedLines.js"></script>
        <script src="assets/plugins/metrojs/MetroJs.min.js"></script>
        <script src="assets/js/modern.js"></script>
        <script src="assets/js/pages/dashboard.js"></script>

</body>
</html>