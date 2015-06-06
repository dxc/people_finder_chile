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

    <meta name="viewport" content="width=480, initial-scale=1.0">
    <meta name="mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <link rel="chrome-webstore-item" href="https://chrome.google.com/webstore/detail/fbnpmpdcnjkhfcgeeklebjmopaheplce">
    <link href="//fonts.googleapis.com/css?family=Open+Sans:300italic,300,400italic,400,600italic,600,700italic,700,800italic,800"
          rel="stylesheet" type="text/css">

    <title>PEOPLE LOCATOR</title>

    <link rel="stylesheet" href="/resource/css/people2.css">
    <link rel="stylesheet" href="/resource/css/peoplefinder.css">

</head>
<body>


<div id="container">
<div id="page">
<div id="content">

<div id="site">
    <div class="clickable linkHome" id="site_name">PEOPLE LOCATOR</div>
    <div id="site_logo" class="clickable linkHome"></div>
</div>
<div class="titleSeparator"></div>
<div id="uHolder" style="opacity: 1;">
    <paper-button id="user" class="nonUpper" role="button" tabindex="0">Login or Register</paper-button>
</div>
<div class="articleHolder primaryArticle" id="hurblanca2015Holder">
    <script>window.useHolder = "#hurblanca2015Holder";</script>
    <div class="article" id="hurblanca2015FULL">
        <div class="homeEventLong">Hurricane Blanca</div>
        <div class="homeEventDate">June 5, 2015</div>
        <div class="clearEm"></div>
        <div class="articleActions">
            <div class="articleActionsFlex">
                <div id="hurblanca2015SearchButton"
                     class="flex3 nonselect raised clickable noUnderline articleActionItem aaMarginRight"
                     onclick="goSearch('hurblanca2015', true);">Search for a Person
                </div>
                <div id="hurblanca2015ReportButton"
                     class="flex3 nonselect raised clickable noUnderline articleActionItem"
                     onclick="goReport('hurblanca2015', true, 'personHTML');">Report a Person
                </div>
                <div id="hurblanca2015HelpButton"
                     class="flex3 nonselect raised clickable noUnderline articleActionItem aaMarginLeft"
                     onclick="goHelp('hurblanca2015', true);">Help and Resources
                </div>
            </div>
        </div>
        <div class="articleBody" id="hurblanca2015Article">
            <div class="articleContent">
                <figure><img src="https://pl.nlm.nih.gov/tmp/plus_cache/eventArticleImage_152062_thumb.jpg">
                    <figcaption>Hurricane Blanca to hit Baja California by this weekend. Landfall is possible between
                        Cabo San Lucas and Puerto San Carlos.
                    </figcaption>
                </figure>
                <b><span class="wysiwyg-font-size-large">
                <h2>Blanca to Threaten Baja California Sur Late This Weekend</h2>
              </span></b><span class="wysiwyg-font-size-small">By Eric Leister, </span><a target="_blank" rel="nofollow"
                                                                                          href="http://www.accuweather.com">
                <span class="wysiwyg-font-size-small">www.accuweather.com</span></a><span
                    class="wysiwyg-font-size-small">&nbsp; June 5, 2015</span>
                <br><br><a
                    title="Link: http://www.accuweather.com/en/weather-news/hurricane-blanca-eastern-pacific-baja-california-mexico/48129225"
                    href="http://www.accuweather.com/en/weather-news/hurricane-blanca-eastern-pacific-baja-california-mexico/48129225">http://www.accuweather.com/en/weather-news/hurricane-blanca-eastern-pacific-baja-california-mexico/4...</a><br>

                <br>Despite Hurricane Blanca expected to weaken this weekend,
                dangers loom for residents and visitors on Mexico's Baja California Sur.
                <p>Blanca reached major hurricane status on Wednesday; however, it has gone through a period of
                    weakening and <a title="Link: null">is now the equivalent of a Category 2 hurricane</a>.</p>

                <p></p>

                <p></p>

                <p></p>

                <p></p>Blanca's strength may fluctuate slightly over the next 24 hours. However, the storm is likely
                past its peak intensity.
                <p>This is the earliest on record, since 1971, that two major
                    hurricanes have formed in the Eastern Pacific. There have been four
                    other seasons that have had two major hurricanes develop before the end
                    of June.</p>

                <p>Blanca will continue to track northwestward well off the
                    Mexico mainland through Saturday before approaching Mexico's Baja
                    California Sur.</p>

                <p>Even through Blanca will remain over the open ocean through
                    Saturday, large surf and dangerous rip currents will threaten beachgoers
                    and operators of small craft along the coast of Mexico from Acapulco to
                    near Puerto Vallarta.</p>

                <div class="eventInformation">This event has a total of <b>0</b> person records.</div>
                <div style="clear:both;"></div>
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
</body>
</html>
