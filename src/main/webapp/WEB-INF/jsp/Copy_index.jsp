<%--
  Created by IntelliJ IDEA.
  User: teo
  Date: 25/05/15
  Time: 21:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="description" content="Bootstrap Admin App + jQuery">
    <meta name="keywords" content="app, responsive, jquery, bootstrap, dashboard, admin">
    <title>Angle - Bootstrap Admin Template</title>
    <!-- =============== VENDOR STYLES ===============-->
    <!-- FONT AWESOME-->
    <link rel="stylesheet" href="/resource/css/font-awesome.min.css">
    <!-- SIMPLE LINE ICONS-->
    <link rel="stylesheet" href="/resource/css/simple-line-icons.css">
    <!-- ANIMATE.CSS-->
    <link rel="stylesheet" href="/resource/css/animate.min.css">
    <!-- WHIRL (spinners)-->
    <link rel="stylesheet" href="/resource/css/whirl.css">
    <!-- =============== PAGE VENDOR STYLES ===============-->
    <!-- WEATHER ICONS-->
    <link rel="stylesheet" href="/resource/css/weather-icons.min.css">
    <!-- =============== BOOTSTRAP STYLES ===============-->
    <link rel="stylesheet" href="/resource/css/bootstrap.css" id="bscss">
    <!-- =============== APP STYLES ===============-->
    <link rel="stylesheet" href="/resource/css/app.css" id="maincss">
    <script src="/resource/js/ga.js"></script>
 </head>
<body class="aside-collapsed">
<div class="wrapper">
<!-- top navbar-->
<header class="topnavbar-wrapper">
    <!-- START Top Navbar-->
    <nav role="navigation" class="navbar topnavbar">
        <!-- START navbar header-->
        <div class="navbar-header">
            <a href="#/" class="navbar-brand">
                <div class="brand-logo">
                    <img src="/resource/img/logo.png" alt="App Logo" class="img-responsive">
                </div>
                <div class="brand-logo-collapsed">
                    <img src="/resource/img/logo-single.png" alt="App Logo" class="img-responsive">
                </div>
            </a>
        </div>
        <!-- END navbar header-->
        <!-- START Nav wrapper-->
        <div class="nav-wrapper">
            <!-- START Left navbar-->
            <ul class="nav navbar-nav">
                <li>
                    <!-- Button used to collapse the left sidebar. Only visible on tablet and desktops-->
                    <a href="#" data-toggle-state="aside-collapsed" class="hidden-xs">
                        <em class="fa fa-navicon"></em>
                    </a>
                    <!-- Button to show/hide the sidebar on mobile. Visible on mobile only.-->
                    <a href="#" data-toggle-state="aside-toggled" data-no-persist="true" class="visible-xs sidebar-toggle">
                        <em class="fa fa-navicon"></em>
                    </a>
                </li>

            </ul>
            <!-- END Left navbar-->

        </div>
        <!-- END Nav wrapper-->

    </nav>
    <!-- END Top Navbar-->
</header>
<!-- sidebar-->
<aside class="aside">
<!-- START Sidebar (left)-->
<div class="aside-inner">
<nav data-sidebar-anyclick-close="" class="sidebar">
<!-- START sidebar nav-->
<ul class="nav">

<!-- Iterates over all sidebar items-->
<li class="nav-heading ">
    <span data-localize="sidebar.heading.HEADER">Navegacion principal</span>
</li>
<li class="active">
    <a href="#dashboard" title="Dashboard" data-toggle="collapse" class="" aria-expanded="true">
        <em class="icon-speedometer"></em>
        <span data-localize="sidebar.nav.DASHBOARD">Inicio</span>
    </a>
    <ul id="dashboard" class="nav sidebar-subnav collapse in" aria-expanded="true">
        <li class="sidebar-subnav-header">Dashboard</li>
        <li class=" active">
            <a href="dashboard.html" title="Dashboard v1">
                <span>Dashboard v1</span>
            </a>
        </li>

    </ul>
</li>
<li class=" ">
    <a href="widgets.html" title="Widgets">
        <em class="icon-grid"></em>
        <div class="label label-success pull-right">30</div>
        <span data-localize="sidebar.nav.WIDGETS">Widgets</span>
    </a>
</li>
<li class=" ">
    <a href="#layout" title="Layouts" data-toggle="collapse">
        <em class="icon-layers"></em>
        <span>Layouts</span>
    </a>
    <ul id="layout" class="nav sidebar-subnav collapse">
        <li class="sidebar-subnav-header">Layouts</li>
        <li class=" ">
            <a href="dashboard_h.html" title="Horizontal">
                <span>Horizontal</span>
            </a>
        </li>
    </ul>
</li>
<li class="nav-heading ">
    <span data-localize="sidebar.heading.COMPONENTS">Componentes</span>
</li>
<li class=" ">
    <a href="#elements" title="Elements" data-toggle="collapse">
        <em class="icon-chemistry"></em>
        <span data-localize="sidebar.nav.element.ELEMENTS">Elementos</span>
    </a>
    <ul id="elements" class="nav sidebar-subnav collapse">
        <li class="sidebar-subnav-header">Elements</li>
        <li class=" ">
            <a href="buttons.html" title="Buttons">
                <span data-localize="sidebar.nav.element.BUTTON">Boton</span>
            </a>
        </li>
        <li class=" ">
            <a href="notifications.html" title="Notifications">
                <span data-localize="sidebar.nav.element.NOTIFICATION">Notificaciones</span>
            </a>
        </li>
        <li class=" ">
            <a href="carousel.html" title="Carousel">
                <span data-localize="sidebar.nav.element.INTERACTION">Carousel</span>
            </a>
        </li>
        <li class=" ">
            <a href="spinners.html" title="Spinners">
                <span data-localize="sidebar.nav.element.SPINNER">Indicadores</span>
            </a>
        </li>
        <li class=" ">
            <a href="animations.html" title="Animations">
                <span data-localize="sidebar.nav.element.ANIMATION">Animacion</span>
            </a>
        </li>
        <li class=" ">
            <a href="dropdown-animations.html" title="Dropdown">
                <span data-localize="sidebar.nav.element.DROPDOWN">Desplegables</span>
            </a>
        </li>
        <li class=" ">
            <a href="nestable.html" title="Nestable">
                <span>Nestable</span>
            </a>
        </li>
        <li class=" ">
            <a href="sortable.html" title="Sortable">
                <span>Sortable</span>
            </a>
        </li>
        <li class=" ">
            <a href="panels.html" title="Panels">
                <span data-localize="sidebar.nav.element.PANEL">Paneles</span>
            </a>
        </li>
        <li class=" ">
            <a href="portlets.html" title="Portlets">
                <span data-localize="sidebar.nav.element.PORTLET">Portlet</span>
            </a>
        </li>
        <li class=" ">
            <a href="grid.html" title="Grid">
                <span data-localize="sidebar.nav.element.GRID">Grilla</span>
            </a>
        </li>
        <li class=" ">
            <a href="grid-masonry.html" title="Grid Masonry">
                <span data-localize="sidebar.nav.element.GRID_MASONRY">Grilla Masonry</span>
            </a>
        </li>
        <li class=" ">
            <a href="typo.html" title="Typography">
                <span data-localize="sidebar.nav.element.TYPO">Tipografia</span>
            </a>
        </li>
        <li class=" ">
            <a href="icons-font.html" title="Font Icons">
                <div class="label label-success pull-right">+400</div>
                <span data-localize="sidebar.nav.element.FONT_ICON">Iconos</span>
            </a>
        </li>
        <li class=" ">
            <a href="icons-weather.html" title="Weather Icons">
                <div class="label label-success pull-right">+100</div>
                <span data-localize="sidebar.nav.element.WEATHER_ICON">Iconos de clima</span>
            </a>
        </li>
        <li class=" ">
            <a href="colors.html" title="Colors">
                <span data-localize="sidebar.nav.element.COLOR">Colores</span>
            </a>
        </li>
    </ul>
</li>
<li class=" ">
    <a href="#forms" title="Forms" data-toggle="collapse">
        <em class="icon-note"></em>
        <span data-localize="sidebar.nav.form.FORM">Formularios</span>
    </a>
    <ul id="forms" class="nav sidebar-subnav collapse">
        <li class="sidebar-subnav-header">Forms</li>
        <li class=" ">
            <a href="form-standard.html" title="Standard">
                <span data-localize="sidebar.nav.form.STANDARD">Standard</span>
            </a>
        </li>
        <li class=" ">
            <a href="form-extended.html" title="Extended">
                <span data-localize="sidebar.nav.form.EXTENDED">Extendido</span>
            </a>
        </li>
        <li class=" ">
            <a href="form-validation.html" title="Validation">
                <span data-localize="sidebar.nav.form.VALIDATION">Validacion</span>
            </a>
        </li>
        <li class=" ">
            <a href="form-wizard.html" title="Wizard">
                <span>Wizard</span>
            </a>
        </li>
        <li class=" ">
            <a href="form-upload.html" title="Upload">
                <span>Upload</span>
            </a>
        </li>
        <li class=" ">
            <a href="form-xeditable.html" title="xEditable">
                <span>xEditable</span>
            </a>
        </li>
    </ul>
</li>
<li class=" ">
    <a href="#charts" title="Charts" data-toggle="collapse">
        <em class="icon-graph"></em>
        <div class="label label-success pull-right">new</div>
        <span data-localize="sidebar.nav.chart.CHART">Graficos</span>
    </a>
    <ul id="charts" class="nav sidebar-subnav collapse">
        <li class="sidebar-subnav-header">Charts</li>
        <li class=" ">
            <a href="chart-flot.html" title="Flot">
                <span data-localize="sidebar.nav.chart.FLOT">Flot</span>
            </a>
        </li>
        <li class=" ">
            <a href="chart-radial.html" title="Radial">
                <span data-localize="sidebar.nav.chart.RADIAL">Radiales</span>
            </a>
        </li>
        <li class=" ">
            <a href="chart-js.html" title="Chart JS">
                <span>Chart JS</span>
            </a>
        </li>
        <li class=" ">
            <a href="chart-rickshaw.html" title="Rickshaw">
                <span>Rickshaw</span>
            </a>
        </li>
        <li class=" ">
            <a href="chart-morris.html" title="MorrisJS">
                <span>MorrisJS</span>
            </a>
        </li>
        <li class=" ">
            <a href="chart-chartist.html" title="Chartist">
                <span>Chartist</span>
            </a>
        </li>
    </ul>
</li>
<li class=" ">
    <a href="#tables" title="Tables" data-toggle="collapse">
        <em class="icon-grid"></em>
        <span data-localize="sidebar.nav.table.TABLE">Tablas</span>
    </a>
    <ul id="tables" class="nav sidebar-subnav collapse">
        <li class="sidebar-subnav-header">Tables</li>
        <li class=" ">
            <a href="table-standard.html" title="Standard">
                <span data-localize="sidebar.nav.table.STANDARD">Standard</span>
            </a>
        </li>
        <li class=" ">
            <a href="table-extended.html" title="Extended">
                <span data-localize="sidebar.nav.table.EXTENDED">Extendida</span>
            </a>
        </li>
        <li class=" ">
            <a href="table-datatable.html" title="DataTables">
                <span data-localize="sidebar.nav.table.DATATABLE">Datatable</span>
            </a>
        </li>
        <li class=" ">
            <a href="table-jqgrid.html" title="jqGrid">
                <span>jqGrid</span>
            </a>
        </li>
    </ul>
</li>
<li class=" ">
    <a href="#maps" title="Maps" data-toggle="collapse">
        <em class="icon-map"></em>
        <span data-localize="sidebar.nav.map.MAP">Maps</span>
    </a>
    <ul id="maps" class="nav sidebar-subnav collapse">
        <li class="sidebar-subnav-header">Maps</li>
        <li class=" ">
            <a href="maps-google.html" title="Google Maps">
                <span data-localize="sidebar.nav.map.GOOGLE">Mapa Google</span>
            </a>
        </li>
        <li class=" ">
            <a href="maps-vector.html" title="Vector Maps">
                <span data-localize="sidebar.nav.map.VECTOR">Mapa Vectorial</span>
            </a>
        </li>
    </ul>
</li>
<li class="nav-heading ">
    <span data-localize="sidebar.heading.MORE">Aun Mas</span>
</li>
<li class=" ">
    <a href="#pages" title="Pages" data-toggle="collapse">
        <em class="icon-doc"></em>
        <span data-localize="sidebar.nav.pages.PAGES">Paginas</span>
    </a>
    <ul id="pages" class="nav sidebar-subnav collapse">
        <li class="sidebar-subnav-header">Pages</li>
        <li class=" ">
            <a href="login.html" title="Login">
                <span data-localize="sidebar.nav.pages.LOGIN">Ingreso</span>
            </a>
        </li>
        <li class=" ">
            <a href="register.html" title="Sign up">
                <span data-localize="sidebar.nav.pages.REGISTER">Registro</span>
            </a>
        </li>
        <li class=" ">
            <a href="recover.html" title="Recover Password">
                <span data-localize="sidebar.nav.pages.RECOVER">Recuperar contraseña</span>
            </a>
        </li>
        <li class=" ">
            <a href="lock.html" title="Lock">
                <span data-localize="sidebar.nav.pages.LOCK">Bloqueo</span>
            </a>
        </li>
        <li class=" ">
            <a href="template.html" title="Starter Template">
                <span data-localize="sidebar.nav.pages.STARTER">Plantilla en blanco</span>
            </a>
        </li>
        <li class=" ">
            <a href="404.html" title="404">
                <span>404</span>
            </a>
        </li>
    </ul>
</li>
<li class=" ">
    <a href="#extras" title="Extras" data-toggle="collapse">
        <em class="icon-cup"></em>
        <span data-localize="sidebar.nav.extra.EXTRA">Extras</span>
    </a>
    <ul id="extras" class="nav sidebar-subnav collapse">
        <li class="sidebar-subnav-header">Extras</li>
        <li class=" ">
            <a href="mailbox.html" title="Mailbox">
                <span data-localize="sidebar.nav.extra.MAILBOX">Email</span>
            </a>
        </li>
        <li class=" ">
            <a href="timeline.html" title="Timeline">
                <span data-localize="sidebar.nav.extra.TIMELINE">Linea de Tiempo</span>
            </a>
        </li>
        <li class=" ">
            <a href="calendar.html" title="Calendar">
                <span data-localize="sidebar.nav.extra.CALENDAR">Calendario</span>
            </a>
        </li>
        <li class=" ">
            <a href="invoice.html" title="Invoice">
                <span data-localize="sidebar.nav.extra.INVOICE">Factura</span>
            </a>
        </li>
        <li class=" ">
            <a href="search.html" title="Search">
                <span data-localize="sidebar.nav.extra.SEARCH">Busqueda</span>
            </a>
        </li>
        <li class=" ">
            <a href="todo.html" title="Todo List">
                <span data-localize="sidebar.nav.extra.TODO">Lista Todo</span>
            </a>
        </li>
        <li class=" ">
            <a href="profile.html" title="Profile">
                <span data-localize="sidebar.nav.extra.PROFILE">Perfil</span>
            </a>
        </li>
    </ul>
</li>
<li class=" ">
    <a href="#multilevel" title="Multilevel" data-toggle="collapse">
        <em class="fa fa-folder-open-o"></em>
        <span>Multilevel</span>
    </a>
    <ul id="multilevel" class="nav sidebar-subnav collapse">
        <li class="sidebar-subnav-header">Multilevel</li>
        <li class=" ">
            <a href="#level1" title="Level 1" data-toggle="collapse">
                <span>Level 1</span>
            </a>
            <ul id="level1" class="nav sidebar-subnav collapse">
                <li class="sidebar-subnav-header">Level 1</li>
                <li class=" ">
                    <a href="multilevel-1.html" title="Level1 Item">
                        <span>Level1 Item</span>
                    </a>
                </li>
                <li class=" ">
                    <a href="#level2" title="Level 2" data-toggle="collapse">
                        <span>Level 2</span>
                    </a>
                    <ul id="level2" class="nav sidebar-subnav collapse">
                        <li class="sidebar-subnav-header">Level 2</li>
                        <li class=" ">
                            <a href="#level3" title="Level 3" data-toggle="collapse">
                                <span>Level 3</span>
                            </a>
                            <ul id="level3" class="nav sidebar-subnav collapse">
                                <li class="sidebar-subnav-header">Level 3</li>
                                <li class=" ">
                                    <a href="multilevel-3.html" title="Level3 Item">
                                        <span>Level3 Item</span>
                                    </a>
                                </li>
                            </ul>
                        </li>
                    </ul>
                </li>
            </ul>
        </li>
    </ul>
</li>
<li class=" ">
    <a href="documentation.html" title="Documentation">
        <em class="icon-graduation"></em>
        <span data-localize="sidebar.nav.DOCUMENTATION">Documentación</span>
    </a>
</li>
</ul>
<!-- END sidebar nav-->
</nav>
</div>
<!-- END Sidebar (left)-->
</aside>
<!-- offsidebar-->
<aside class="offsidebar">
<!-- START Off Sidebar (right)-->
<nav>
<div role="tabpanel">
<!-- Nav tabs-->
<ul role="tablist" class="nav nav-tabs nav-justified">
    <li role="presentation" class="active">
        <a href="#app-settings" aria-controls="app-settings" role="tab" data-toggle="tab">
            <em class="icon-equalizer fa-lg"></em>
        </a>
    </li>
    <li role="presentation">
        <a href="#app-chat" aria-controls="app-chat" role="tab" data-toggle="tab">
            <em class="icon-users fa-lg"></em>
        </a>
    </li>
</ul>
<!-- Tab panes-->
<div class="tab-content">
<div id="app-settings" role="tabpanel" class="tab-pane fade in active">
    <h3 class="text-center text-thin">Settings</h3>
    <div class="p">
        <h4 class="text-muted text-thin">Themes</h4>
        <div class="table-grid mb">
            <div class="col mb">
                <div class="setting-color">
                    <label data-load-css="css/theme-a.css">
                        <input type="radio" name="setting-theme" checked="checked">
                        <span class="icon-check"></span>
                                    <span class="split">
                                       <span class="color bg-info"></span>
                                       <span class="color bg-info-light"></span>
                                    </span>
                        <span class="color bg-white"></span>
                    </label>
                </div>
            </div>
            <div class="col mb">
                <div class="setting-color">
                    <label data-load-css="css/theme-b.css">
                        <input type="radio" name="setting-theme">
                        <span class="icon-check"></span>
                                    <span class="split">
                                       <span class="color bg-green"></span>
                                       <span class="color bg-green-light"></span>
                                    </span>
                        <span class="color bg-white"></span>
                    </label>
                </div>
            </div>
            <div class="col mb">
                <div class="setting-color">
                    <label data-load-css="css/theme-c.css">
                        <input type="radio" name="setting-theme">
                        <span class="icon-check"></span>
                                    <span class="split">
                                       <span class="color bg-purple"></span>
                                       <span class="color bg-purple-light"></span>
                                    </span>
                        <span class="color bg-white"></span>
                    </label>
                </div>
            </div>
            <div class="col mb">
                <div class="setting-color">
                    <label data-load-css="css/theme-d.css">
                        <input type="radio" name="setting-theme">
                        <span class="icon-check"></span>
                                    <span class="split">
                                       <span class="color bg-danger"></span>
                                       <span class="color bg-danger-light"></span>
                                    </span>
                        <span class="color bg-white"></span>
                    </label>
                </div>
            </div>
        </div>
        <div class="table-grid mb">
            <div class="col mb">
                <div class="setting-color">
                    <label data-load-css="css/theme-e.css">
                        <input type="radio" name="setting-theme">
                        <span class="icon-check"></span>
                                    <span class="split">
                                       <span class="color bg-info-dark"></span>
                                       <span class="color bg-info"></span>
                                    </span>
                        <span class="color bg-gray-dark"></span>
                    </label>
                </div>
            </div>
            <div class="col mb">
                <div class="setting-color">
                    <label data-load-css="css/theme-f.css">
                        <input type="radio" name="setting-theme">
                        <span class="icon-check"></span>
                                    <span class="split">
                                       <span class="color bg-green-dark"></span>
                                       <span class="color bg-green"></span>
                                    </span>
                        <span class="color bg-gray-dark"></span>
                    </label>
                </div>
            </div>
            <div class="col mb">
                <div class="setting-color">
                    <label data-load-css="css/theme-g.css">
                        <input type="radio" name="setting-theme">
                        <span class="icon-check"></span>
                                    <span class="split">
                                       <span class="color bg-purple-dark"></span>
                                       <span class="color bg-purple"></span>
                                    </span>
                        <span class="color bg-gray-dark"></span>
                    </label>
                </div>
            </div>
            <div class="col mb">
                <div class="setting-color">
                    <label data-load-css="css/theme-h.css">
                        <input type="radio" name="setting-theme">
                        <span class="icon-check"></span>
                                    <span class="split">
                                       <span class="color bg-danger-dark"></span>
                                       <span class="color bg-danger"></span>
                                    </span>
                        <span class="color bg-gray-dark"></span>
                    </label>
                </div>
            </div>
        </div>
    </div>
    <div class="p">
        <h4 class="text-muted text-thin">Layout</h4>
        <div class="clearfix">
            <p class="pull-left">Fixed</p>
            <div class="pull-right">
                <label class="switch">
                    <input id="chk-fixed" type="checkbox" data-toggle-state="layout-fixed">
                    <span></span>
                </label>
            </div>
        </div>
        <div class="clearfix">
            <p class="pull-left">Boxed</p>
            <div class="pull-right">
                <label class="switch">
                    <input id="chk-boxed" type="checkbox" data-toggle-state="layout-boxed">
                    <span></span>
                </label>
            </div>
        </div>
        <div class="clearfix">
            <p class="pull-left">RTL</p>
            <div class="pull-right">
                <label class="switch">
                    <input id="chk-rtl" type="checkbox">
                    <span></span>
                </label>
            </div>
        </div>
    </div>
    <div class="p">
        <h4 class="text-muted text-thin">Aside</h4>
        <div class="clearfix">
            <p class="pull-left">Collapsed</p>
            <div class="pull-right">
                <label class="switch">
                    <input id="chk-collapsed" type="checkbox" data-toggle-state="aside-collapsed">
                    <span></span>
                </label>
            </div>
        </div>
        <div class="clearfix">
            <p class="pull-left">Float</p>
            <div class="pull-right">
                <label class="switch">
                    <input id="chk-float" type="checkbox" data-toggle-state="aside-float">
                    <span></span>
                </label>
            </div>
        </div>
        <div class="clearfix">
            <p class="pull-left">Hover</p>
            <div class="pull-right">
                <label class="switch">
                    <input id="chk-hover" type="checkbox" data-toggle-state="aside-hover">
                    <span></span>
                </label>
            </div>
        </div>
    </div>
</div>
<div id="app-chat" role="tabpanel" class="tab-pane fade">
    <h3 class="text-center text-thin">Connections</h3>
    <ul class="nav">
        <!-- START list title-->
        <li class="p">
            <small class="text-muted">ONLINE</small>
        </li>
        <!-- END list title-->
        <li>
            <!-- START User status-->
            <a href="#" class="media-box p mt0">
                              <span class="pull-right">
                                 <span class="circle circle-success circle-lg"></span>
                              </span>
                              <span class="pull-left">
                                 <!-- Contact avatar-->
                                 <img src="/resource/img/user/05.jpg" alt="Image" class="media-box-object img-circle thumb48">
                              </span>
                <!-- Contact info-->
                              <span class="media-box-body">
                                 <span class="media-box-heading">
                                    <strong>Juan Sims</strong>
                                    <br>
                                    <small class="text-muted">Designeer</small>
                                 </span>
                              </span>
            </a>
            <!-- END User status-->
            <!-- START User status-->
            <a href="#" class="media-box p mt0">
                              <span class="pull-right">
                                 <span class="circle circle-success circle-lg"></span>
                              </span>
                              <span class="pull-left">
                                 <!-- Contact avatar-->
                                 <img src="/resource/img/user/06.jpg" alt="Image" class="media-box-object img-circle thumb48">
                              </span>
                <!-- Contact info-->
                              <span class="media-box-body">
                                 <span class="media-box-heading">
                                    <strong>Maureen Jenkins</strong>
                                    <br>
                                    <small class="text-muted">Designeer</small>
                                 </span>
                              </span>
            </a>
            <!-- END User status-->
            <!-- START User status-->
            <a href="#" class="media-box p mt0">
                              <span class="pull-right">
                                 <span class="circle circle-danger circle-lg"></span>
                              </span>
                              <span class="pull-left">
                                 <!-- Contact avatar-->
                                 <img src="/resource/img/user/07.jpg" alt="Image" class="media-box-object img-circle thumb48">
                              </span>
                <!-- Contact info-->
                              <span class="media-box-body">
                                 <span class="media-box-heading">
                                    <strong>Billie Dunn</strong>
                                    <br>
                                    <small class="text-muted">Designeer</small>
                                 </span>
                              </span>
            </a>
            <!-- END User status-->
            <!-- START User status-->
            <a href="#" class="media-box p mt0">
                              <span class="pull-right">
                                 <span class="circle circle-warning circle-lg"></span>
                              </span>
                              <span class="pull-left">
                                 <!-- Contact avatar-->
                                 <img src="/resource/img/user/08.jpg" alt="Image" class="media-box-object img-circle thumb48">
                              </span>
                <!-- Contact info-->
                              <span class="media-box-body">
                                 <span class="media-box-heading">
                                    <strong>Tomothy Roberts</strong>
                                    <br>
                                    <small class="text-muted">Designer</small>
                                 </span>
                              </span>
            </a>
            <!-- END User status-->
        </li>
        <!-- START list title-->
        <li class="p">
            <small class="text-muted">OFFLINE</small>
        </li>
        <!-- END list title-->
        <li>
            <!-- START User status-->
            <a href="#" class="media-box p mt0">
                              <span class="pull-right">
                                 <span class="circle circle-lg"></span>
                              </span>
                              <span class="pull-left">
                                 <!-- Contact avatar-->
                                 <img src="/resource/img/user/09.jpg" alt="Image" class="media-box-object img-circle thumb48">
                              </span>
                <!-- Contact info-->
                              <span class="media-box-body">
                                 <span class="media-box-heading">
                                    <strong>Lawrence Robinson</strong>
                                    <br>
                                    <small class="text-muted">Developer</small>
                                 </span>
                              </span>
            </a>
            <!-- END User status-->
            <!-- START User status-->
            <a href="#" class="media-box p mt0">
                              <span class="pull-right">
                                 <span class="circle circle-lg"></span>
                              </span>
                              <span class="pull-left">
                                 <!-- Contact avatar-->
                                 <img src="/resource/img/user/10.jpg" alt="Image" class="media-box-object img-circle thumb48">
                              </span>
                <!-- Contact info-->
                              <span class="media-box-body">
                                 <span class="media-box-heading">
                                    <strong>Tyrone Owens</strong>
                                    <br>
                                    <small class="text-muted">Designer</small>
                                 </span>
                              </span>
            </a>
            <!-- END User status-->
        </li>
        <li>
            <div class="p-lg text-center">
                <!-- Optional link to list more users-->
                <a href="#" title="See more contacts" class="btn btn-purple btn-sm">
                    <strong>Load more..</strong>
                </a>
            </div>
        </li>
    </ul>
    <!-- Extra items-->
    <div class="p">
        <p>
            <small class="text-muted">Tasks completion</small>
        </p>
        <div class="progress progress-xs m0">
            <div role="progressbar" aria-valuenow="80" aria-valuemin="0" aria-valuemax="100" class="progress-bar progress-bar-success progress-80">
                <span class="sr-only">80% Complete</span>
            </div>
        </div>
    </div>
    <div class="p">
        <p>
            <small class="text-muted">Upload quota</small>
        </p>
        <div class="progress progress-xs m0">
            <div role="progressbar" aria-valuenow="40" aria-valuemin="0" aria-valuemax="100" class="progress-bar progress-bar-warning progress-40">
                <span class="sr-only">40% Complete</span>
            </div>
        </div>
    </div>
</div>
</div>
</div>
</nav>
<!-- END Off Sidebar (right)-->
</aside>
<!-- Main section-->
<section>
<!-- Page content-->
<div class="content-wrapper">
<div class="content-heading">

    Dashboard
    <small data-localize="dashboard.WELCOME">Bienvenido a Angle</small>
</div>

<div class="row">
<!-- START dashboard main content-->
<div class="col-lg-9">

<div class="row">

</div>

<div class="row">


    <div class="col-lg-8">
        <div class="panel panel-default">
            <div class="panel-heading">
                <div class="pull-right label label-danger">5</div>
                <div class="pull-right label label-success">12</div>
                <div class="panel-title">Team messages</div>
            </div>
            <!-- START list group-->
            <div class="slimScrollDiv" style="position: relative; overflow: hidden; width: auto; height: 180px;"><div data-height="180" data-scrollable="" class="list-group" style="overflow: hidden; width: auto; height: 180px;">
                <!-- START list group item-->
                <a href="#" class="list-group-item">
                    <div class="media-box">
                        <div class="pull-left">
                            <img src="/resource/img/user/02.jpg" alt="Image" class="media-box-object img-circle thumb32">
                        </div>
                        <div class="media-box-body clearfix">
                            <small class="pull-right">2h</small>
                            <strong class="media-box-heading text-primary">
                                <span class="circle circle-success circle-lg text-left"></span>Catherine Ellis</strong>
                            <p class="mb-sm">
                                <small>Cras sit amet nibh libero, in gravida nulla. Nulla...</small>
                            </p>
                        </div>
                    </div>
                </a>
                <!-- END list group item-->
                <!-- START list group item-->
                <a href="#" class="list-group-item">
                    <div class="media-box">
                        <div class="pull-left">
                            <img src="/resource/img/user/03.jpg" alt="Image" class="media-box-object img-circle thumb32">
                        </div>
                        <div class="media-box-body clearfix">
                            <small class="pull-right">3h</small>
                            <strong class="media-box-heading text-primary">
                                <span class="circle circle-success circle-lg text-left"></span>Jessica Silva</strong>
                            <p class="mb-sm">
                                <small>Cras sit amet nibh libero, in gravida nulla. Nulla facilisi.</small>
                            </p>
                        </div>
                    </div>
                </a>
                <!-- END list group item-->
                <!-- START list group item-->
                <a href="#" class="list-group-item">
                    <div class="media-box">
                        <div class="pull-left">
                            <img src="/resource/img/user/09.jpg" alt="Image" class="media-box-object img-circle thumb32">
                        </div>
                        <div class="media-box-body clearfix">
                            <small class="pull-right">4h</small>
                            <strong class="media-box-heading text-primary">
                                <span class="circle circle-danger circle-lg text-left"></span>Jessie Wells</strong>
                            <p class="mb-sm">
                                <small>Cras sit amet nibh libero, in gravida nulla. Nulla...</small>
                            </p>
                        </div>
                    </div>
                </a>
                <!-- END list group item-->
                <!-- START list group item-->
                <a href="#" class="list-group-item">
                    <div class="media-box">
                        <div class="pull-left">
                            <img src="/resource/img/user/11.jpg" alt="Image" class="media-box-object img-circle thumb32">
                        </div>
                        <div class="media-box-body clearfix">
                            <small class="pull-right">1d</small>
                            <strong class="media-box-heading text-primary">
                                <span class="circle circle-danger circle-lg text-left"></span>Rosa Burke</strong>
                            <p class="mb-sm">
                                <small>Cras sit amet nibh libero, in gravida nulla. Nulla...</small>
                            </p>
                        </div>
                    </div>
                </a>
                <!-- END list group item-->
                <!-- START list group item-->
                <a href="#" class="list-group-item">
                    <div class="media-box">
                        <div class="pull-left">
                            <img src="/resource/img/user/10.jpg" alt="Image" class="media-box-object img-circle thumb32">
                        </div>
                        <div class="media-box-body clearfix">
                            <small class="pull-right">2d</small>
                            <strong class="media-box-heading text-primary">
                                <span class="circle circle-danger circle-lg text-left"></span>Michelle Lane</strong>
                            <p class="mb-sm">
                                <small>Mauris eleifend, libero nec cursus lacinia...</small>
                            </p>
                        </div>
                    </div>
                </a>
                <!-- END list group item-->
            </div><div class="slimScrollBar" style="width: 7px; position: absolute; top: 0px; opacity: 0.4; display: block; border-radius: 7px; z-index: 99; right: 1px; height: 104.180064308682px; background: rgb(0, 0, 0);"></div><div class="slimScrollRail" style="width: 7px; height: 100%; position: absolute; top: 0px; display: none; border-radius: 7px; opacity: 0.2; z-index: 90; right: 1px; background: rgb(51, 51, 51);"></div></div>
            <!-- END list group-->
            <!-- START panel footer-->
            <div class="panel-footer clearfix">
                <div class="input-group">
                    <input type="text" placeholder="Search message .." class="form-control input-sm">
                                 <span class="input-group-btn">
                                    <button type="submit" class="btn btn-default btn-sm"><i class="fa fa-search"></i>
                                    </button>
                                 </span>
                </div>
            </div>
            <!-- END panel-footer-->
        </div>
    </div>
</div>
</div>
<!-- END dashboard main content-->
<!-- START dashboard sidebar-->
<aside class="col-lg-3">

    <!-- START messages and activity-->
    <div class="panel panel-default">
        <div class="panel-heading">
            <div class="panel-title">Latest activities</div>
        </div>
        <!-- START list group-->
        <div class="list-group">
            <!-- START list group item-->
            <div class="list-group-item">
                <div class="media-box">
                    <div class="pull-left">
                                 <span class="fa-stack">
                                    <em class="fa fa-circle fa-stack-2x text-purple"></em>
                                    <em class="fa fa-cloud-upload fa-stack-1x fa-inverse text-white"></em>
                                 </span>
                    </div>
                    <div class="media-box-body clearfix">
                        <small class="text-muted pull-right ml">15m</small>
                        <div class="media-box-heading"><a href="#" class="text-purple m0">NEW FILE</a>
                        </div>
                        <p class="m0">
                            <small><a href="#">Bootstrap.xls</a>
                            </small>
                        </p>
                    </div>
                </div>
            </div>
            <!-- END list group item-->
            <!-- START list group item-->
            <div class="list-group-item">
                <div class="media-box">
                    <div class="pull-left">
                                 <span class="fa-stack">
                                    <em class="fa fa-circle fa-stack-2x text-info"></em>
                                    <em class="fa fa-file-text-o fa-stack-1x fa-inverse text-white"></em>
                                 </span>
                    </div>
                    <div class="media-box-body clearfix">
                        <small class="text-muted pull-right ml">2h</small>
                        <div class="media-box-heading"><a href="#" class="text-info m0">NEW DOCUMENT</a>
                        </div>
                        <p class="m0">
                            <small><a href="#">Bootstrap.doc</a>
                            </small>
                        </p>
                    </div>
                </div>
            </div>
            <!-- END list group item-->
            <!-- START list group item-->
            <div class="list-group-item">
                <div class="media-box">
                    <div class="pull-left">
                                 <span class="fa-stack">
                                    <em class="fa fa-circle fa-stack-2x text-danger"></em>
                                    <em class="fa fa-exclamation fa-stack-1x fa-inverse text-white"></em>
                                 </span>
                    </div>
                    <div class="media-box-body clearfix">
                        <small class="text-muted pull-right ml">5h</small>
                        <div class="media-box-heading"><a href="#" class="text-danger m0">BROADCAST</a>
                        </div>
                        <p class="m0"><a href="#">Read</a>
                        </p>
                    </div>
                </div>
            </div>
            <!-- END list group item-->
            <!-- START list group item-->
            <div class="list-group-item">
                <div class="media-box">
                    <div class="pull-left">
                                 <span class="fa-stack">
                                    <em class="fa fa-circle fa-stack-2x text-success"></em>
                                    <em class="fa fa-clock-o fa-stack-1x fa-inverse text-white"></em>
                                 </span>
                    </div>
                    <div class="media-box-body clearfix">
                        <small class="text-muted pull-right ml">15h</small>
                        <div class="media-box-heading"><a href="#" class="text-success m0">NEW MEETING</a>
                        </div>
                        <p class="m0">
                            <small>On
                                <em>10/12/2015 09:00 am</em>
                            </small>
                        </p>
                    </div>
                </div>
            </div>
            <!-- END list group item-->
            <!-- START list group item-->
            <div class="list-group-item">
                <div class="media-box">
                    <div class="pull-left">
                                 <span class="fa-stack">
                                    <em class="fa fa-circle fa-stack-2x text-warning"></em>
                                    <em class="fa fa-tasks fa-stack-1x fa-inverse text-white"></em>
                                 </span>
                    </div>
                    <div class="media-box-body clearfix">
                        <small class="text-muted pull-right ml">1w</small>
                        <div class="media-box-heading"><a href="#" class="text-warning m0">TASKS COMPLETION</a>
                        </div>
                        <div class="progress progress-xs m0">
                            <div role="progressbar" aria-valuenow="22" aria-valuemin="0" aria-valuemax="100" style="width: 22%" class="progress-bar progress-bar-warning progress-bar-striped">
                                <span class="sr-only">22% Complete</span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- END list group item-->
        </div>
        <!-- END list group-->
        <!-- START panel footer-->
        <div class="panel-footer clearfix">
            <a href="#" class="pull-left">
                <small>Load more</small>
            </a>
        </div>
        <!-- END panel-footer-->
    </div>
    <!-- END messages and activity-->
</aside>
<!-- END dashboard sidebar-->
</div>
</div>
</section>
<!-- Page footer-->
<footer>
    <span>© 2015 - Angle</span>
</footer>
</div>
<!-- =============== VENDOR SCRIPTS ===============-->
<!-- MODERNIZR-->
<script src="/resource/js/modernizr.js"></script>
<!-- JQUERY-->
<script src="/resource/js/jquery.js"></script>
<!-- BOOTSTRAP-->
<script src="/resource/js/bootstrap.js"></script>
<!-- STORAGE API-->
<script src="/resource/js/jquery.storageapi.js"></script>
<!-- JQUERY EASING-->
<script src="/resource/js/jquery.easing.js"></script>
<!-- ANIMO-->
<script src="/resource/js/animo.js"></script>
<!-- SLIMSCROLL-->
<script src="/resource/js/jquery.slimscroll.min.js"></script>
<!-- SCREENFULL-->
<script src="/resource/js/screenfull.js"></script>
<!-- LOCALIZE-->
<script src="/resource/js/jquery.localize.js"></script>
<!-- RTL demo-->
<script src="/resource/js/demo-rtl.js"></script>
<!-- =============== PAGE VENDOR SCRIPTS ===============-->
<!-- SPARKLINE-->
<script src="/resource/js/jquery.sparkline.min.js"></script>
<!-- FLOT CHART-->
<script src="/resource/js/jquery.flot.js"></script>
<script src="/resource/js/jquery.flot.tooltip.min.js"></script>
<script src="/resource/js/jquery.flot.resize.js"></script>
<script src="/resource/js/jquery.flot.pie.js"></script>
<script src="/resource/js/jquery.flot.time.js"></script>
<script src="/resource/js/jquery.flot.categories.js"></script>
<script src="/resource/js/jquery.flot.spline.min.js"></script>
<!-- CLASSY LOADER-->
<script src="/resource/js/jquery.classyloader.min.js"></script>
<!-- MOMENT JS-->
<script src="/resource/js/moment-with-locales.min.js"></script>
<!-- SKYCONS-->
<script src="/resource/js/skycons.js"></script>
<!-- DEMO-->
<script src="/resource/js/demo-flot.js"></script>
<!-- =============== APP SCRIPTS ===============-->
<script src="/resource/js/app.js"></script>


<div id="flotTip" style="display: none; position: absolute; z-index: 1040; padding: 0.4em 0.6em; border-radius: 0.5em; font-size: 0.8em; border: 1px solid rgb(17, 17, 17); white-space: nowrap; background: rgb(255, 255, 255);"></div></body>
</html>
