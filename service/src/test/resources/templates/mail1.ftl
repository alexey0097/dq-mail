<!DOCTYPE html>
<html lang="ru">
    <head>
        <meta charset="UTF-8">
        <style type="text/css">
            * {
                font-family: CenturyGothic;
            }
            h1, h2, h3, h4, h5 {
                margin: 0;
                font-weight: normal;
            }
            .param-name {
                margin-right: 5px;
            }
            #image-container {
                padding: 0 15px 15px 0;
                width: 200px;
            }
            .separator-middle {
                margin: 10px 0 0 0;
            }
            .separator-big {
                margin: 20px 0 0 0;
            }
            #photo {
                width: 180px;
            }
        </style>
    </head>
    <body>
        <table width="100%">
            <tr>
                <td>
                    <span><b>ТЕСТОВОЕ ПИСЬМО: </b></span>
                    <#if test??>
                        <span>${test}</span>
                    <#else>
                        <span>по сегодняшний день</span>
                    </#if>
                </td>
            </tr>
        </table>
    </body>
</html>
