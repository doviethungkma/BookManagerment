<%-- 
    Document   : index.jsp
    Created on : Nov 5, 2017, 9:52:36 PM
    Author     : shadyside
--%>

<%@page import="struts.dao.BookDAO"%>
<%@page import="struts.model.Book"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>

        <!-- Title -->
        <title>Modern | Extra - Gallery</title>

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
        <link href="assets/plugins/gridgallery/css/component.css" rel="stylesheet" type="text/css"/>

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
        </s:if><s:elseif test="#session.role != 'Customer'">
            <jsp:include page="404.jsp"></jsp:include>
        </s:elseif>
        <s:else>
            <div class="overlay"></div>
            <s:form action="SearchBook" cssClass="search-form">
                <div class="input-group">
                    <input type="text" name="nameSearch" class="form-control search-input" placeholder="Search..."/>
                    <span class="input-group-btn">
                        <button class="btn btn-default close-search waves-effect waves-button waves-classic" type="submit"><i class="fa fa-times"></i></button>
                    </span>
                </div> <!--Input Group -->
            </s:form><!-- Search Form -->
            <main class="page-content content-wrap">
                <jsp:include page="navbar.jsp"></jsp:include>
                <jsp:include page="sidebar.jsp"></jsp:include>

                    <div class="page-inner">
                        <!--                <div class="page-title">
                                            <h3>Gallery</h3>
                                            <div class="page-breadcrumb">
                                                <ol class="breadcrumb">
                                                    <li><a href="index.html">Home</a></li>
                                                    <li><a href="#">Extra</a></li>
                                                    <li class="active">Gallery</li>
                                                </ol>
                                            </div>
                                        </div>-->
                        <div id="main-wrapper">
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="panel panel-white">
                                        <div class="panel-body">
                                            <div id="grid-gallery" class="grid-gallery">
                                                <section class="grid-wrap">
                                                    <ul class="grid">
                                                        <li class="grid-sizer" ></li>
                                                        <%
                                                            BookDAO bookDAO = new BookDAO();
                                                            List<Book> lstAllBook = bookDAO.getAllBook();
                                                            for (Book book : lstAllBook) {
                                                        %>
                                                        <s:url value="redirectBookDetail" var="redirectBookDetail">
                                                            <s:param name="ID"><%= book.getID()%></s:param>
                                                        </s:url>
                                                        <s:a href="%{redirectBookDetail}" cssStyle="color:black">
                                                        <li>
                                                            <figure>
                                                                <img src="http://localhost:8080/BookManagement_NEW_VER1.0/bookimages/<%=book.getImage()%>" alt="img01"/>
                                                                <figcaption>
                                                                    <h3><%= book.getTitle()%></h3><p><%= book.getDescription()%></p> 
                                                                    <s:a href="%{redirectBookDetail}" cssStyle="color:blue">Xem thêm</s:a>
                                                                </figcaption>
                                                                </figure>
                                                            </li>
                                                    </s:a>
                                                    <%
                                                        }
                                                    %>
                                                </ul>
                                            </section>
                                            <section class="slideshow">
                                                <ul>

                                                </ul>
                                            </section>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div><!-- Row -->
                    </div><!-- Main Wrapper -->
                </div><!-- Page Inner -->
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
        <script src="assets/plugins/gridgallery/js/imagesloaded.pkgd.min.js"></script>
        <script src="assets/plugins/gridgallery/js/masonry.pkgd.min.js"></script>
        <script src="assets/plugins/gridgallery/js/classie.js"></script>
        <script src="assets/plugins/gridgallery/js/cbpgridgallery.js"></script>
        <script src="assets/js/modern.min.js"></script>
        <script src="assets/js/pages/gallery.js"></script>

    </body>
</html>