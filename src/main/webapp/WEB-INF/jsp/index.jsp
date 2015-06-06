<%--
  Created by IntelliJ IDEA.
  User: teo
  Date: 06/06/15
  Time: 03:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html style="background-color: rgb(233, 233, 233);">
<head>

    <meta charset="utf-8">
    <title>People Locator v1.0</title>
    <meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <link href="//fonts.googleapis.com/css?family=Open+Sans:300italic,300,400italic,400,600italic,600,700italic,700,800italic,800"
          rel="stylesheet" type="text/css">
     <link rel="stylesheet" href="/resource/css/peoplefinder.css">
    <link rel="stylesheet" type="text/css" href="/resource/css/bootstrap.css">
    <link rel="stylesheet" href="/resource/css/font-awesome.css">
    <script src="/resource/js/site/jquery-1.11.1.min.js" type="text/javascript"></script>
    <script src="/resource/js/site/jquery.knob.js" type="text/javascript"></script>
    <link rel="stylesheet" type="text/css" href="/resource/css/theme.css">
    <link rel="stylesheet" type="text/css" href="/resource/css/premium.css">

</head>
<body>
<div id="container">
    <div id="page">
        <div id="content">
            <div id="site">
                <div id="site_name">PEOPLE LOCATOR</div>
                <div id="site_logo"></div>
            </div>
            <div class="titleSeparator"></div>
            <div id="uHolder" style="opacity: 1;">
                <paper-button id="user" class="nonUpper" role="button" tabindex="0">Login or Register</paper-button>
            </div>
            <div class="articleHolder primaryArticle" id="hurblanca2015Holder">
              <div class="article" id="idarticle">
                    <div class="homeEventLong">{Title Incident}</div>
                    <div class="homeEventDate">{Incident Date}</div>
                    <div class="clearEm"></div>
                    <div class="articleActions">
                        <div class="articleActionsFlex">
                            <div id="SearchButton"
                                 class="flex3 nonselect raised clickable noUnderline articleActionItem aaMarginRight"
                                 onclick="goSearch('hurblanca2015', true);">Search for a Person
                            </div>
                            <div id="ReportButton"
                                 class="flex3 nonselect raised clickable noUnderline articleActionItem"
                                 onclick="goReport('hurblanca2015', true, 'personHTML');">Report a Person
                            </div>
                            <div id="HelpButton"
                                 class="flex3 nonselect raised clickable noUnderline articleActionItem aaMarginLeft"
                                 onclick="goHelp('hurblanca2015', true);">Help and Resources
                            </div>
                        </div>
                    </div>
                    <div class="articleBody">
                        <div class="articleContent">
                        </div>
                    </div>
                </div>
            </div>

        </div>
    </div>
    <!-- content -->
    <div id="footer-holder">
        <div style="height: 11px;"></div>
        <div class="footlink linkHome" id="linkHome"><span class="clickable">Home</span></div>
        <div class="footlink linkAbout" id="linkAbout"><span class="clickable">About</span></div>
        <div class="footlink linkPrivacy" id="linkPrivacy"><span class="clickable">Privacy</span></div>
        <div class="footlink linkContact" id="linkContact"><span class="clickable">Contact</span></div>
        <div class="footlink linkLinks" id="linkLinks"><span class="clickable">Links</span></div>
    </div>
    <!-- page -->
</div>
<script src="/resource/js/site/bootstrap.js"></script>
</body>
</html>
