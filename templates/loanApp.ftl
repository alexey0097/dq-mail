<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width"/>

    <style type="text/css">

    * {
      margin: 0;
      padding: 0;
      font-size: 100%;
      font-family: 'Avenir Next', "Helvetica Neue", "Helvetica", Helvetica, Arial, sans-serif;
      line-height: 1.65; }

    img {
      max-width: 100%;
      margin: 0 auto;
      display: block; }

    body,
    .body-wrap {
      width: 100% !important;
      height: 100%;
      background: #efefef;
      -webkit-font-smoothing: antialiased;
      -webkit-text-size-adjust: none; }

    a {
      color: #B30032;
      text-decoration: none; }

    .text-center {
      text-align: center; }

    .text-right {
      text-align: right; }

    .text-left {
      text-align: left; }

    .button {
      display: inline-block;
      color: white;
      background: #B30032;
      border: solid #B30032;
      border-width: 10px 20px 8px;
      font-weight: bold;
      border-radius: 4px; }

    h1, h2, h3, h4, h5, h6 {
      margin-bottom: 20px;
      line-height: 1.25; }

    h1 {
      font-size: 32px; }

    h2 {
      font-size: 28px; }

    h3 {
      font-size: 24px; }

    h4 {
      font-size: 20px; }

    h5 {
      font-size: 16px; }

    p, ul, ol {
      font-size: 16px;
      font-weight: normal;
      margin-bottom: 20px; }

    .container {
      display: block !important;
      clear: both !important;
      margin: 0 auto !important;
      max-width: 580px !important; }
      .container table {
        width: 100% !important;
        border-collapse: collapse; }
      .container .masthead {
        padding: 80px 0;
        background: #B30032;
        color: white; }
        .container .masthead h1 {
          margin: 0 auto !important;
          max-width: 90%;
          text-transform: uppercase; }
      .container .content {
        background: white;
        padding: 30px 35px; }
        .container .content.footer {
          background: none; }
          .container .content.footer p {
            margin-bottom: 0;
            color: #888;
            text-align: center;
            font-size: 14px; }
          .container .content.footer a {
            color: #888;
            text-decoration: none;
            font-weight: bold; }

    </style>
</head>
<body>
<table class="body-wrap">
    <tr>
        <td class="container">

            <!-- Message start -->
            <table>
                <tr>
                    <td align="center" class="masthead">

                        <h1>Ваш кредит по программе ${loanType} одобрен!</h1>

                    </td>
                </tr>
                <tr>
                    <td class="content">

                        <h2>Добрый день, ${clientName}</h2>

                        <p>
                            Рады Вам сообщить, что Ваш кредит по программе ${loanType} был одобрен.
                            В ходе долгих обсуждений мы пришли к выводу что Ваша платежеспособность
                            позволяет оформить кредит у банка ${bankName}. Вы  можете подойти к ближайшему
                            отделению, в любое удобное для Вас время. График работы наших филиалов - ${timeWork}.
                        </p>

                        <table>
                            <tr>
                                <td align="center">
                                    <p>
                                        <a href="#" class="button">Открыть заявку</a>
                                    </p>
                                </td>
                            </tr>
                        </table>

                        <p>Для более подробной информации Вы можете обратиться на нашу горячую линию ${hotNumberPhone}.</p>

                        <p><em>– ${senderName}</em></p>

                    </td>
                </tr>
            </table>

        </td>
    </tr>
    <tr>
        <td class="container">

            <!-- Message start -->
            <table>
                <tr>
                    <td class="content footer" align="center">
                        <p>Отправлено компанией <a href="#">${companyName}</a>, расположенной по адресу ${companyAddress}</p>
                        <p><a href="mailto:">${companyEmail}</a> | <a href="#">Отписаться от рассылки</a></p>
                    </td>
                </tr>
            </table>

        </td>
    </tr>
</table>
</body>
</html>
