package yte.internship.app.service;

import com.sun.istack.ByteArrayDataSource;
import com.sun.mail.iap.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import yte.internship.app.entity.dto.TicketDetailsDTO;

import javax.activation.DataSource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Base64;

/**
 * Receives event-related requests.
 *
 * Created 11.08.2020
 * @author Umut Emre Bayramoglu
 */


@Service
@RequiredArgsConstructor
public class MailService {

    private final JavaMailSender mailSender;

    /**
     * Sends email about event participation is done. And attach qr code which
     * contains some ticket and user details.
     * @param ticketDetails A {@link TicketDetailsDTO} object.
     * @return
     * @throws MessagingException
     */
    public int sendQrCodeMail(TicketDetailsDTO ticketDetails) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
        String imageUrl = ticketDetails.getBase64ImageUrl();
        imageUrl = imageUrl.split(",")[1];
        byte[] imageURLByte = Base64.getDecoder().decode(imageUrl);
        DataSource res = new ByteArrayDataSource(imageURLByte, "image/*");

        messageHelper.setSubject(ticketDetails.getEventTitle());
        messageHelper.addAttachment("Ticket_Qr_Code.png", res);
        messageHelper.setTo(ticketDetails.getParticipantEmail());


        // TODO: CREATE AN EXTERNAL HTML FILE
        /** MAIL BODY */
        messageHelper.setText(
                "<!doctype html>\n" +
                        "<html>\n" +
                        "  <head>\n" +
                        "    <meta name=\"viewport\" content=\"width=device-width\" />\n" +
                        "    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\n" +
                        "    <title>Simple Transactional Email</title>\n" +
                        "    <style>\n" +
                        "      /* -------------------------------------\n" +
                        "          GLOBAL RESETS\n" +
                        "      ------------------------------------- */\n" +
                        "      \n" +
                        "      /*All the styling goes here*/\n" +
                        "      \n" +
                        "      img {\n" +
                        "        border: none;\n" +
                        "        -ms-interpolation-mode: bicubic;\n" +
                        "        max-width: 100%; \n" +
                        "      }\n" +
                        "\n" +
                        "      body {\n" +
                        "        background-color: #f6f6f6;\n" +
                        "        font-family: sans-serif;\n" +
                        "        -webkit-font-smoothing: antialiased;\n" +
                        "        font-size: 14px;\n" +
                        "        line-height: 1.4;\n" +
                        "        margin: 0;\n" +
                        "        padding: 0;\n" +
                        "        -ms-text-size-adjust: 100%;\n" +
                        "        -webkit-text-size-adjust: 100%; \n" +
                        "      }\n" +
                        "\n" +
                        "      table {\n" +
                        "        border-collapse: separate;\n" +
                        "        mso-table-lspace: 0pt;\n" +
                        "        mso-table-rspace: 0pt;\n" +
                        "        width: 100%; }\n" +
                        "        table td {\n" +
                        "          font-family: sans-serif;\n" +
                        "          font-size: 14px;\n" +
                        "          vertical-align: top; \n" +
                        "      }\n" +
                        "\n" +
                        "      /* -------------------------------------\n" +
                        "          BODY & CONTAINER\n" +
                        "      ------------------------------------- */\n" +
                        "\n" +
                        "      .body {\n" +
                        "        background-color: #f6f6f6;\n" +
                        "        width: 100%; \n" +
                        "      }\n" +
                        "\n" +
                        "      /* Set a max-width, and make it display as block so it will automatically stretch to that width, but will also shrink down on a phone or something */\n" +
                        "      .container {\n" +
                        "        display: block;\n" +
                        "        margin: 0 auto !important;\n" +
                        "        /* makes it centered */\n" +
                        "        max-width: 580px;\n" +
                        "        padding: 10px;\n" +
                        "        width: 580px; \n" +
                        "      }\n" +
                        "\n" +
                        "      /* This should also be a block element, so that it will fill 100% of the .container */\n" +
                        "      .content {\n" +
                        "        box-sizing: border-box;\n" +
                        "        display: block;\n" +
                        "        margin: 0 auto;\n" +
                        "        max-width: 580px;\n" +
                        "        padding: 10px; \n" +
                        "      }\n" +
                        "\n" +
                        "      /* -------------------------------------\n" +
                        "          HEADER, FOOTER, MAIN\n" +
                        "      ------------------------------------- */\n" +
                        "      .main {\n" +
                        "        background: #ffffff;\n" +
                        "        border-radius: 3px;\n" +
                        "        width: 100%; \n" +
                        "      }\n" +
                        "\n" +
                        "      .wrapper {\n" +
                        "        box-sizing: border-box;\n" +
                        "        padding: 20px; \n" +
                        "      }\n" +
                        "\n" +
                        "      .content-block {\n" +
                        "        padding-bottom: 10px;\n" +
                        "        padding-top: 10px;\n" +
                        "      }\n" +
                        "\n" +
                        "      .footer {\n" +
                        "        clear: both;\n" +
                        "        margin-top: 10px;\n" +
                        "        text-align: center;\n" +
                        "        width: 100%; \n" +
                        "      }\n" +
                        "        .footer td,\n" +
                        "        .footer p,\n" +
                        "        .footer span,\n" +
                        "        .footer a {\n" +
                        "          color: #999999;\n" +
                        "          font-size: 12px;\n" +
                        "          text-align: center; \n" +
                        "      }\n" +
                        "\n" +
                        "      /* -------------------------------------\n" +
                        "          TYPOGRAPHY\n" +
                        "      ------------------------------------- */\n" +
                        "      h1,\n" +
                        "      h2,\n" +
                        "      h3,\n" +
                        "      h4 {\n" +
                        "        color: #000000;\n" +
                        "        font-family: sans-serif;\n" +
                        "        font-weight: 400;\n" +
                        "        line-height: 1.4;\n" +
                        "        margin: 0;\n" +
                        "        margin-bottom: 30px; \n" +
                        "      }\n" +
                        "\n" +
                        "      h1 {\n" +
                        "        font-size: 35px;\n" +
                        "        font-weight: 300;\n" +
                        "        text-align: center;\n" +
                        "        text-transform: capitalize; \n" +
                        "      }\n" +
                        "\n" +
                        "      p,\n" +
                        "      ul,\n" +
                        "      ol {\n" +
                        "        font-family: sans-serif;\n" +
                        "        font-size: 14px;\n" +
                        "        font-weight: normal;\n" +
                        "        margin: 0;\n" +
                        "        margin-bottom: 15px; \n" +
                        "      }\n" +
                        "        p li,\n" +
                        "        ul li,\n" +
                        "        ol li {\n" +
                        "          list-style-position: inside;\n" +
                        "          margin-left: 5px; \n" +
                        "      }\n" +
                        "\n" +
                        "      a {\n" +
                        "        color: #3498db;\n" +
                        "        text-decoration: underline; \n" +
                        "      }\n" +
                        "\n" +
                        "      /* -------------------------------------\n" +
                        "          BUTTONS\n" +
                        "      ------------------------------------- */\n" +
                        "      .btn {\n" +
                        "        box-sizing: border-box;\n" +
                        "        width: 100%; }\n" +
                        "        .btn > tbody > tr > td {\n" +
                        "          padding-bottom: 15px; }\n" +
                        "        .btn table {\n" +
                        "          width: auto; \n" +
                        "      }\n" +
                        "        .btn table td {\n" +
                        "          background-color: #ffffff;\n" +
                        "          border-radius: 5px;\n" +
                        "          text-align: center; \n" +
                        "      }\n" +
                        "        .btn a {\n" +
                        "          background-color: #ffffff;\n" +
                        "          border: solid 1px #3498db;\n" +
                        "          border-radius: 5px;\n" +
                        "          box-sizing: border-box;\n" +
                        "          color: #3498db;\n" +
                        "          cursor: pointer;\n" +
                        "          display: inline-block;\n" +
                        "          font-size: 14px;\n" +
                        "          font-weight: bold;\n" +
                        "          margin: 0;\n" +
                        "          padding: 12px 25px;\n" +
                        "          text-decoration: none;\n" +
                        "          text-transform: capitalize; \n" +
                        "      }\n" +
                        "\n" +
                        "      .btn-primary table td {\n" +
                        "        background-color: #3498db; \n" +
                        "      }\n" +
                        "\n" +
                        "      .btn-primary a {\n" +
                        "        background-color: #3498db;\n" +
                        "        border-color: #3498db;\n" +
                        "        color: #ffffff; \n" +
                        "      }\n" +
                        "\n" +
                        "      /* -------------------------------------\n" +
                        "          OTHER STYLES THAT MIGHT BE USEFUL\n" +
                        "      ------------------------------------- */\n" +
                        "      .last {\n" +
                        "        margin-bottom: 0; \n" +
                        "      }\n" +
                        "\n" +
                        "      .first {\n" +
                        "        margin-top: 0; \n" +
                        "      }\n" +
                        "\n" +
                        "      .align-center {\n" +
                        "        text-align: center; \n" +
                        "      }\n" +
                        "\n" +
                        "      .align-right {\n" +
                        "        text-align: right; \n" +
                        "      }\n" +
                        "\n" +
                        "      .align-left {\n" +
                        "        text-align: left; \n" +
                        "      }\n" +
                        "\n" +
                        "      .clear {\n" +
                        "        clear: both; \n" +
                        "      }\n" +
                        "\n" +
                        "      .mt0 {\n" +
                        "        margin-top: 0; \n" +
                        "      }\n" +
                        "\n" +
                        "      .mb0 {\n" +
                        "        margin-bottom: 0; \n" +
                        "      }\n" +
                        "\n" +
                        "      .preheader {\n" +
                        "        color: transparent;\n" +
                        "        display: none;\n" +
                        "        height: 0;\n" +
                        "        max-height: 0;\n" +
                        "        max-width: 0;\n" +
                        "        opacity: 0;\n" +
                        "        overflow: hidden;\n" +
                        "        mso-hide: all;\n" +
                        "        visibility: hidden;\n" +
                        "        width: 0; \n" +
                        "      }\n" +
                        "\n" +
                        "      .powered-by a {\n" +
                        "        text-decoration: none; \n" +
                        "      }\n" +
                        "\n" +
                        "      hr {\n" +
                        "        border: 0;\n" +
                        "        border-bottom: 1px solid #f6f6f6;\n" +
                        "        margin: 20px 0; \n" +
                        "      }\n" +
                        "\n" +
                        "      /* -------------------------------------\n" +
                        "          RESPONSIVE AND MOBILE FRIENDLY STYLES\n" +
                        "      ------------------------------------- */\n" +
                        "      @media only screen and (max-width: 620px) {\n" +
                        "        table[class=body] h1 {\n" +
                        "          font-size: 28px !important;\n" +
                        "          margin-bottom: 10px !important; \n" +
                        "        }\n" +
                        "        table[class=body] p,\n" +
                        "        table[class=body] ul,\n" +
                        "        table[class=body] ol,\n" +
                        "        table[class=body] td,\n" +
                        "        table[class=body] span,\n" +
                        "        table[class=body] a {\n" +
                        "          font-size: 16px !important; \n" +
                        "        }\n" +
                        "        table[class=body] .wrapper,\n" +
                        "        table[class=body] .article {\n" +
                        "          padding: 10px !important; \n" +
                        "        }\n" +
                        "        table[class=body] .content {\n" +
                        "          padding: 0 !important; \n" +
                        "        }\n" +
                        "        table[class=body] .container {\n" +
                        "          padding: 0 !important;\n" +
                        "          width: 100% !important; \n" +
                        "        }\n" +
                        "        table[class=body] .main {\n" +
                        "          border-left-width: 0 !important;\n" +
                        "          border-radius: 0 !important;\n" +
                        "          border-right-width: 0 !important; \n" +
                        "        }\n" +
                        "        table[class=body] .btn table {\n" +
                        "          width: 100% !important; \n" +
                        "        }\n" +
                        "        table[class=body] .btn a {\n" +
                        "          width: 100% !important; \n" +
                        "        }\n" +
                        "        table[class=body] .img-responsive {\n" +
                        "          height: auto !important;\n" +
                        "          max-width: 100% !important;\n" +
                        "          width: auto !important; \n" +
                        "        }\n" +
                        "      }\n" +
                        "\n" +
                        "      /* -------------------------------------\n" +
                        "          PRESERVE THESE STYLES IN THE HEAD\n" +
                        "      ------------------------------------- */\n" +
                        "      @media all {\n" +
                        "        .ExternalClass {\n" +
                        "          width: 100%; \n" +
                        "        }\n" +
                        "        .ExternalClass,\n" +
                        "        .ExternalClass p,\n" +
                        "        .ExternalClass span,\n" +
                        "        .ExternalClass font,\n" +
                        "        .ExternalClass td,\n" +
                        "        .ExternalClass div {\n" +
                        "          line-height: 100%; \n" +
                        "        }\n" +
                        "        .apple-link a {\n" +
                        "          color: inherit !important;\n" +
                        "          font-family: inherit !important;\n" +
                        "          font-size: inherit !important;\n" +
                        "          font-weight: inherit !important;\n" +
                        "          line-height: inherit !important;\n" +
                        "          text-decoration: none !important; \n" +
                        "        }\n" +
                        "        #MessageViewBody a {\n" +
                        "          color: inherit;\n" +
                        "          text-decoration: none;\n" +
                        "          font-size: inherit;\n" +
                        "          font-family: inherit;\n" +
                        "          font-weight: inherit;\n" +
                        "          line-height: inherit;\n" +
                        "        }\n" +
                        "        .btn-primary table td:hover {\n" +
                        "          background-color: #34495e !important; \n" +
                        "        }\n" +
                        "        .btn-primary a:hover {\n" +
                        "          background-color: #34495e !important;\n" +
                        "          border-color: #34495e !important; \n" +
                        "        } \n" +
                        "      }\n" +
                        "\n" +
                        "    </style>\n" +
                        "  </head>\n" +
                        "  <body class=\"\">\n" +
                        "    <span class=\"preheader\">Nofitications from eventBell -> </span>\n" +
                        "    <table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"body\">\n" +
                        "      <tr>\n" +
                        "        <td>&nbsp;</td>\n" +
                        "        <td class=\"container\">\n" +
                        "          <div class=\"content\">\n" +
                        "\n" +
                        "            <!-- START CENTERED WHITE CONTAINER -->\n" +
                        "            <table role=\"presentation\" class=\"main\">\n" +
                        "\n" +
                        "              <!-- START MAIN CONTENT AREA -->\n" +
                        "              <tr>\n" +
                        "                <td class=\"wrapper\">\n" +
                        "                  <table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">\n" +
                        "                    <tr>\n" +
                        "                      <td>\n" +
                        "                        <p><b>Hi "+ ticketDetails.getParticipantName() +"</b></p>\n" +
                        "                        <p>You participated "+ ticketDetails.getEventTitle() +" ! </p>\n" +
                        "                        <table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"btn btn-primary\">\n" +
                        "                          <tbody>\n" +
                        "                            <tr>\n" +
                        "                              <td align=\"left\">\n" +
                        "                                <table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">\n" +
                        "                                  <tbody>\n" +
                        "                                    <tr>\n" +
                        "                                      <td> <a href=\""+ ticketDetails.getEventUrl()+ "\" target=\"_blank\">Go to the event</a> </td>\n" +
                        "                                    </tr>\n" +
                        "                                  </tbody>\n" +
                        "                                </table>\n" +
                        "                              </td>\n" +
                        "                            </tr>\n" +
                        "                          </tbody>\n" +
                        "                        </table>\n" +
                        "                        <p><b>You can see your ticket details by qr code which in the attachments.</b></p>\n" +
                        "                        <p>See you later !</p>\n" +
                        "                      </td>\n" +
                        "                    </tr>\n" +
                        "                  </table>\n" +
                        "                </td>\n" +
                        "              </tr>\n" +
                        "\n" +
                        "            <!-- END MAIN CONTENT AREA -->\n" +
                        "            </table>\n" +
                        "            <!-- END CENTERED WHITE CONTAINER -->\n" +
                        "\n" +
                        "            <!-- START FOOTER -->\n" +
                        "            <div class=\"footer\">\n" +
                        "              <table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">\n" +
                        "                <tr>\n" +
                        "                  <td class=\"content-block\">\n" +
                        "                    <span class=\"apple-link\">Company Inc, 3 Abbey Road, San Francisco CA 94102</span>\n" +
                        "                    <br> Don't like these emails? <a href=\"http://i.imgur.com/CScmqnj.gif\">Unsubscribe</a>.\n" +
                        "                  </td>\n" +
                        "                </tr>\n" +
                        "                <tr>\n" +
                        "                </tr>\n" +
                        "              </table>\n" +
                        "            </div>\n" +
                        "            <!-- END FOOTER -->\n" +
                        "\n" +
                        "          </div>\n" +
                        "        </td>\n" +
                        "        <td>&nbsp;</td>\n" +
                        "      </tr>\n" +
                        "    </table>\n" +
                        "  </body>\n" +
                        "</html>",

                true);

        mailSender.send(mimeMessage);
        return Response.OK;
    }
}