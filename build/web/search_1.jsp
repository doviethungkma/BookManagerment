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
            <form class="search-form" action="#" method="GET">
                <div class="input-group">
                    <input type="text" name="search" class="form-control search-input" placeholder="Search...">
                    <span class="input-group-btn">
                        <button class="btn btn-default close-search waves-effect waves-button waves-classic" type="button"><i class="fa fa-times"></i></button>
                    </span>
                </div> Input Group 
            </form><!-- Search Form -->
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
                                                        <li class="grid-sizer"></li>
                                                        <%
                                                            BookDAO bookDAO = new BookDAO();
                                                            List<Book> lstAllBook = bookDAO.getAllBook();
                                                            for (Book book : lstAllBook) {
                                                        %>
                                                    <li>
                                                        <figure>
                                                            <img src="<%=  book.getImage()%>" alt="img01"/>
                                                            <figcaption><h3><%= book.getTitle()%></h3><p><%= book.getDescription()%></p></figcaption>
                                                        </figure>
                                                    </li>
                                                    <%
                                                        }
                                                    %>
                                                    <li>
                                                        <figure>
                                                            <img src="BookImage/156381_p57200mbiatruoc.jpg" alt="img02"/>
                                                            <figcaption><h3>Vice velit chia</h3><p>Laborum tattooed iPhone, Schlitz irure nulla Tonx retro 90's chia cardigan quis asymmetrical paleo. </p></figcaption>
                                                        </figure>
                                                    </li>
                                                    <li>
                                                        <figure>
                                                            <img src="BookImage/161585_p60043mbiatruoc.jpg" alt="img03"/>
                                                            <figcaption><h3>Brunch semiotics</h3><p>Ex disrupt cray yr, butcher pour-over magna umami kitsch before they sold out commodo.</p></figcaption>
                                                        </figure>
                                                    </li>
                                                    <li>
                                                        <figure>
                                                            <img src="BookImage/168679_p61035mdau001.jpg" alt="img04"/>
                                                            <figcaption><h3>Chillwave nihil occupy</h3><p>In post-ironic gluten-free deserunt, PBR&amp;B non pork belly cupidatat polaroid. </p></figcaption>
                                                        </figure>
                                                    </li>
                                                    <li>
                                                        <figure>
                                                            <img src="BookImage/190806_p65315mbiatruoc.jpg" alt="img06"/>
                                                            <figcaption><h3>Exercitation occaecat</h3><p>Street chillwave hoodie ea gentrify.</p></figcaption>
                                                        </figure>
                                                    </li>
                                                    <li>
                                                        <figure>
                                                            <img src="BookImage/194155_p65714mbiatruoc.jpg" alt="img01"/>
                                                            <figcaption><h3>Selfies viral four</h3><p>Raw denim duis Tonx Shoreditch labore swag artisan High Life cred, stumptown Schlitz quinoa flexitarian mollit fanny pack.</p></figcaption>
                                                        </figure>
                                                    </li>
                                                    <li>
                                                        <figure>
                                                            <img src="BookImage/203534_p67314mconchonho.jpg" alt="img02"/>
                                                            <figcaption><h3>Photo booth skateboard</h3><p>Vinyl squid ex High Life. Paleo Neutra nulla master cleanse, Helvetica et enim nesciunt esse.</p></figcaption>
                                                        </figure>
                                                    </li>
                                                    <li>
                                                        <figure>
                                                            <img src="BookImage/208597_p68050m001.jpg" alt="img03"/>
                                                            <figcaption><h3>Ex fashion axe</h3><p>Qui nesciunt et, in chia cliche irure. Eu YOLO nihil mollit twee locavore, tempor keytar asymmetrical irure aute sriracha consequat.</p></figcaption>
                                                        </figure>
                                                    </li>
                                                    <li>
                                                        <figure>
                                                            <img src="BookImage/209419_p68256mgietconchimnhai.jpg" alt="img04"/>
                                                            <figcaption><h3>Thundercats next level</h3><p>Portland nulla butcher ea XOXO, consequat Bushwick Pinterest elit twee pickled direct trade vero. </p></figcaption>
                                                        </figure>
                                                    </li>
                                                    <li>
                                                        <figure>
                                                            <img src="BookImage/230782_p71351mbiatruoc.jpg" alt="img05"/>
                                                            <figcaption><h3>Bushwick selvage synth</h3><p>Bicycle rights flannel Shoreditch, art party laboris Bushwick sriracha authentic chambray hella umami sed distillery master cleanse.</p></figcaption>
                                                        </figure>
                                                    </li>
                                                    <li>
                                                        <figure>
                                                            <img src="BookImage/234109_p71761mimg336.jpg" alt="img01"/>
                                                            <figcaption><h3>Bottle wayfarers locavore</h3><p>Once there was a little asparagus, he was green and lonely.</p></figcaption>
                                                        </figure>
                                                    </li>
                                                    <li>
                                                        <figure>
                                                            <img src="BookImage/246166_p73565mbiatruoc.jpg" alt="img01"/>
                                                            <figcaption><h3>Letterpress asymmetrical</h3><p>Chillwave hoodie ea gentrify aute sriracha consequat.</p></figcaption>
                                                        </figure>
                                                    </li>
                                                    <li>
                                                        <figure>
                                                            <img src="BookImage/246346_p73567mbiatruoc.jpg" alt="img02"/>
                                                            <figcaption><h3>Vice velit chia</h3><p>Laborum tattooed iPhone, Schlitz irure nulla Tonx retro 90's chia cardigan quis asymmetrical paleo. </p></figcaption>
                                                        </figure>
                                                    </li>
                                                    <li>
                                                        <figure>
                                                            <img src="BookImage/262019_p76040mbiatruoc.jpg" alt="img03"/>
                                                            <figcaption><h3>Brunch semiotics</h3><p>Ex disrupt cray yr, butcher pour-over magna umami kitsch before they sold out commodo.</p></figcaption>
                                                        </figure>
                                                    </li>
                                                    <li>
                                                        <figure>
                                                            <img src="BookImage/264137_p76197m002.jpg" alt="img04"/>
                                                            <figcaption><h3>Chillwave nihil occupy</h3><p>In post-ironic gluten-free deserunt, PBR&amp;B non pork belly cupidatat polaroid. </p></figcaption>
                                                        </figure>
                                                    </li>
                                                    <li>
                                                        <figure>
                                                            <img src="BookImage/266556_p76556mbia1khaive.jpg" alt="img05"/>
                                                            <figcaption><h3>Kale chips lomo biodiesel</h3><p>Pariatur food truck street art consequat sustainable, et kogi beard qui paleo. </p></figcaption>
                                                        </figure>
                                                    </li>
                                                    <li>
                                                        <figure>
                                                            <img src="BookImage/27761_p34263m13nguyentac.jpg" alt="img06"/>
                                                            <figcaption><h3>Exercitation occaecat</h3><p>Street chillwave hoodie ea gentrify.</p></figcaption>
                                                        </figure>
                                                    </li>
                                                    <li>
                                                        <figure>
                                                            <img src="BookImage/49294_p55837mnhagiakimpaulocoelho.jpg" alt="img01"/>
                                                            <figcaption><h3>Selfies viral four</h3><p>Raw denim duis Tonx Shoreditch labore swag artisan High Life cred, stumptown Schlitz quinoa flexitarian mollit fanny pack.</p></figcaption>
                                                        </figure>
                                                    </li>
                                                    <li>
                                                        <figure>
                                                            <img src="BookImage/Be-happy.jpg" alt="img02"/>
                                                            <figcaption><h3>Photo booth skateboard</h3><p>Vinyl squid ex High Life. Paleo Neutra nulla master cleanse, Helvetica et enim nesciunt esse.</p></figcaption>
                                                        </figure>
                                                    </li>
                                                    <li>
                                                        <figure>
                                                            <img src="BookImage/anh-dep-cho-dien-thoai-2.jpg" alt="img03"/>
                                                            <figcaption><h3>Ex fashion axe</h3><p>Qui nesciunt et, in chia cliche irure. Eu YOLO nihil mollit twee locavore, tempor keytar asymmetrical irure aute sriracha consequat.</p></figcaption>
                                                        </figure>
                                                    </li>
                                                    <li>
                                                        <figure>
                                                            <img src="BookImage/demo.jpg" alt="img04"/>
                                                            <figcaption><h3>Thundercats next level</h3><p>Portland nulla butcher ea XOXO, consequat Bushwick Pinterest elit twee pickled direct trade vero. </p></figcaption>
                                                        </figure>
                                                    </li>
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