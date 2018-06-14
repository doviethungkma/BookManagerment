<%-- 
    Document   : sidebar
    Created on : Nov 5, 2017, 10:20:28 PM
    Author     : shadyside
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
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

    </head>
    <body>
        <div class="page-sidebar sidebar">
            <div class="page-sidebar-inner slimscroll">
                <div class="sidebar-header">
                    <div class="sidebar-profile">
                        <s:iterator value="#session.USER">
                            <div class="sidebar-profile-image">
                                <img src="userimages/<s:property value="Avatar"/>" class="img-circle img-responsive" alt="">
                            </div>
                            <div class="sidebar-profile-details">
                                <span><s:property value="Username"/><br></span>
                            </div>
                        </s:iterator>
                    </div>
                </div>
                <ul class="menu accordion-menu">
                    <s:url action="RedirectHome" id="RedirectHome"></s:url>
                    <li><s:a href="%{RedirectHome}"><span class="menu-icon glyphicon glyphicon-home"></span><p>Trang chủ</p></s:a></li>
                            <s:url action="ManagerUser" id="ManagerUser"></s:url>
                    <li><s:a href="%{ManagerUser}"><span class="menu-icon glyphicon glyphicon-user"></span><p>Quản lý tài khoản</p></s:a></li>
                            <s:url action="ManagerBook" id="ManagerBook"></s:url>
                    <li><s:a href="%{ManagerBook}"><span class="menu-icon glyphicon glyphicon-home"></span><p>Quản lý sách</p></s:a></li>
                            <s:url action="RedirectBackup" id="RedirectBackup"></s:url>
                    <li><s:a href="%{RedirectBackup}"><span class="menu-icon glyphicon glyphicon-home"></span><p>Backup source code</p></s:a></li>
                            <s:url action="Logout" id="Logout"></s:url>
                    <li><s:a href="%{Logout}"><span class="menu-icon glyphicon glyphicon-home"></span><p>Đăng xuất</p></s:a></li>
                </ul>
            </div><!-- Page Sidebar Inner -->
        </div><!-- Page Sidebar -->
    </body>
</html>
