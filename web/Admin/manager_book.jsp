<%-- 
    Document   : mângerbook
    Created on : Nov 9, 2017, 5:03:15 PM
    Author     : shadyside
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>

        <!-- Title -->
        <title>Quản lý sách</title>

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
                        <h3>Quản lý sách</h3>
                        <div class="page-breadcrumb">
                            <ol class="breadcrumb">
                                <li><a href="index.html">Home</a></li>
                                <li class="active">Quanlysach</li>
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
                                        <form class="form-horizontal">
                                            <div class="form-group">
                                                <label for="input-Default" class="col-lg-1 control-label">Tìm kiếm</label>
                                                <div class="col-lg-3">
                                                    <input type="text" class="form-control" id="input-Default">
                                                </div>
                                                <div class="col-lg-1">
                                                    <button type="button" class="btn btn-success">Tìm kiếm</button>
                                                </div>
                                                <div class="col-lg-1 col-lg-offset-3">
                                                <s:url action="RedirectAddBook" id="RedirectAddbook"></s:url>
                                                <s:a href="%{RedirectAddbook}"><button type="button" class="btn btn-success">Thêm sách</button></s:a>
                                                </div>
                                            </div>
                                        </form>
                                    </div>

                                    <div class="panel-body">
                                        <div class="table-responsive project-stats">  
                                            <table class="table">
                                                <thead>
                                                    <tr>
                                                        <th>#</th>
                                                        <th style="width: 300px">Tên sách</th>
                                                        <th style="width: 150px">Tác giả</th>
                                                        <th>Mô tả</th>
                                                        <th>Hình ảnh</th>
                                                        <th>Giá</th>
                                                        <th colspan="3">Action</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                <%
                                                    int i = 1;
                                                %>
                                                <s:iterator value="lstBook">
                                                    <tr>
                                                        <th scope="row"><%= i++%></th>
                                                        <td><s:property value="Title"/></td>
                                                        <td><s:property value="Author"/></td>
                                                        <td style="width: 300px"><s:property value="Description"/></td>
                                                        <td><img src="http://localhost:8080/BookManagement_NEW_VER1.0/bookimages/<s:property value="Image"/>" style="width: 70px; height: 70px"></td>
                                                        <td><s:property value="Price"/></td>
                                                        <td>
                                                            <a href=""><button type="button" class="btn btn-success">Chi tiết</button></a>
                                                            <s:url action="RedirectEditBook" id="RedirectEditBook">
                                                                <s:param name="ID"><s:property value="ID"/></s:param>
                                                            </s:url>
                                                            <s:a href="%{RedirectEditBook}"><button type="button" class="btn btn-success">Sửa</button></s:a>

                                                            <%--<s:url action="DeleteBook" id="DeleteBook">--%>
                                                            <%--<s:param name="ID"><s:property value="ID"/></s:param>--%>
                                                            <%--</s:url>--%>
                                                            <%--<s:a href="%{DeleteBook}"><button type="button" class="btn btn-danger">Xóa</button></s:a>--%>

                                                            <s:form action="DeleteBook" method="POST">
                                                                <s:token/>
                                                                <input type="hidden" name="ID" value="<s:property value="ID"/>">
                                                                <button type="submit" class="btn btn-danger">Xóa</button>
                                                            </s:form>
                                                        </td>
                                                        <td></td>
                                                    </tr>
                                                </s:iterator>

                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div><!-- Row -->
                </div>
                <div class="page-footer">
                    <p class="no-s">2015 &copy; Modern by Steelcoders.</p>
                </div>
            </div> Page Inner 
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