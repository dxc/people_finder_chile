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
                    <div class="homeEventLong">{Tutorial para usar la API,(Utilizando algun cliente REST)}</div>

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
                            <p> Puedes ver el codigo en Github <a href="https://github.com/tchambil/people_finder_chile" TARGET="_blank">aqui</a>
                            </p>
                            <h2><u>1. Registro de Users</u></h2>
                            <h2>  a. Crear usuarios</h2>
                            <p> curl -X POST 'http://localhost:8080/users' -H Content-type:application/json -d</p>
                            <div id="code1" class="syntaxhighlighter  jscript">
                                <table border="0" cellpadding="0" cellspacing="0">
                                    <tbody>
                                    <tr>
                                        <td class="gutter">
                                            <div class="line number1 index0 alt0">1</div>
                                            <div class="line number2 index1 alt1">2</div>
                                            <div class="line number3 index2 alt2">3</div>
                                            <div class="line number4 index3 alt3">4</div>
                                            <div class="line number5 index4 alt4">5</div>
                                            <div class="line number6 index5 alt5">6</div>

                                        </td>
                                        <td class="code">
                                            <div class="container">
                                                <div class="line number1 index0 alt0"><code class="jscript plain">{</code></div>
                                                <div class="line number2 index1 alt1"><code class="jscript plain"> "id": "test-user-1",</code></div>
                                                <div class="line number3 index2 alt2"><code class="jscript plain"> "password": "test-user-1"</code></div>
                                                <div class="line number4 index3 alt3"><code class="jscript plain">}</code></div>
                                                <div> <u>Datos soportados: </u></div>
                                                <div class="line number5 index4 alt4"><code class="jscript plain"> (id,password,fullName,displayName,,
                                                </code></div>
                                                <div class="line number6 index5 alt5"><code class="jscript plain"> nickName,organization,organization)
                                                </code></div>


                                            </div>

                                        </td>
                                    </tr>
                                    </tbody>
                                </table>

                            </div>
                            <h2>  b. Obtener usuarios Registrados</h2>
                            <p> curl 'http://localhost:8080/users'</p>
                            <h2>  c. Actualizar Registro</h2>
                            <p> curl -X PUT 'http://localhost:8080/users' -H Content-type:application/json -d</p>
                            <h2>   <u>2. Registro de Incidentes</u></h2>
                            <h2>   a.Crear Incidentes</h2>
                            <p> curl -X POST 'http://localhost:8080/incident/{idUser}' -H Content-type:application/json -d</p>
                            <div id="code2" class="syntaxhighlighter  jscript">
                                <table border="0" cellpadding="0" cellspacing="0">
                                    <tbody>
                                    <tr>
                                        <td class="gutter">
                                            <div class="line number1 index0 alt0">1</div>
                                            <div class="line number2 index1 alt1">2</div>
                                            <div class="line number3 index2 alt2">3</div>
                                            <div class="line number4 index3 alt3">4</div>
                                            <div class="line number5 index4 alt4">5</div>
                                            <div class="line number6 index5 alt5">6</div>
                                            <div class="line number7 index6 alt6">7</div>

                                        </td>
                                        <td class="code">
                                            <div class="container">
                                                <div class="line number1 index0 alt0"><code class="jscript plain">{</code></div>
                                                <div class="line number2 index1 alt1"><code class="jscript plain"> "incidentId": "test-incident-1",</code></div>
                                                <div class="line number3 index2 alt2"><code class="jscript plain"> "incidentName": "test-incident-Name"</code></div>
                                                <div class="line number4 index3 alt3"><code class="jscript plain">}</code></div>
                                                <div> <u>Datos soportados: </u></div>
                                                <div class="line number5 index4 alt4"><code class="jscript plain"> (incidentId; parentId;searchId; incidentName;shortName;date;,
                                                </code></div>
                                                <div class="line number6 index5 alt5"><code class="jscript plain"> incidenttype;latitude;longitude; privategroup;
                                                </code></div>
                                                <div class="line number7 index6 alt6"><code class="jscript plain">  closed;description;street; externalreport;
                                                </code></div>


                                            </div>

                                        </td>
                                    </tr>
                                    </tbody>
                                </table>

                            </div>
                            <h2>  b. Obtener Incidentes Registrados</h2>
                            <p> curl 'http://localhost:8080/incidents' </p>
                            <h2>  c. Actualizar Incidentes</h2>
                            <p> curl -X PUT 'http://localhost:8080/users/incident/{idUser}' -H Content-type:application/json -d</p>
                            <h2>   <u>3. Registro de Personas</u></h2>
                            <h2>   a.Crear Personas</h2>
                            <p> curl -X POST 'http://localhost:8080/incident/{idincident}/people/{idUser}' -H Content-type:application/json -d</p>
                            <div id="code3" class="syntaxhighlighter  jscript">
                                <table border="0" cellpadding="0" cellspacing="0">
                                    <tbody>
                                    <tr>
                                        <td class="gutter">
                                            <div class="line number1 index0 alt0">1</div>
                                            <div class="line number2 index1 alt1">2</div>
                                            <div class="line number3 index2 alt2">3</div>
                                            <div class="line number4 index3 alt3">4</div>
                                            <div class="line number5 index4 alt4">5</div>
                                            <div class="line number6 index5 alt5">6</div>
                                            <div class="line number7 index6 alt6">7</div>
                                            <div class="line number8 index7 alt7">8</div>
                                        </td>
                                        <td class="code">
                                            <div class="container">
                                                <div class="line number1 index0 alt0"><code class="jscript plain">{</code></div>
                                                <div class="line number2 index1 alt1"><code class="jscript plain"> "Id": "test-people-1",</code></div>
                                                <div class="line number3 index2 alt2"><code class="jscript plain"> "fullname": "test-people-name"</code></div>
                                                <div class="line number4 index3 alt3"><code class="jscript plain">}</code></div>
                                                <div> <u>Datos soportados: </u></div>
                                                <div class="line number5 index4 alt4"><code class="jscript string"> (Id, fullname, info_location, uri, source_date, last_status,
                                                </code></div>
                                                <div class="line number6 index5 alt5"><code class="jscript string"> status_author, status_date, status_found, cont,
                                                </code></div>
                                                <div class="line number7 index6 alt6"><code class="jscript string"> last_known_location, cont_note,  home_street
                                                </code></div>
                                                <div class="line number8 index7 alt7"><code class="jscript string"> home_neighborhood,  home_city,  home_state)
                                                </code></div>
                                            </div>

                                        </td>
                                    </tr>
                                    </tbody>
                                </table>



                            </div>
                            <h2>  b. Obtener Personas Registrados</h2>
                            <p> curl 'http://localhost:8080/incident/{idIncident}/peoples'</p>
                            <h2>  c. Actualizar Personas</h2>
                            <p> curl -X PUT 'http://localhost:8080/incident/{idincident}/people/{idUser}' -H Content-type:application/json -d</p>

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
