package com.chinapex.nexus.util

/**
  * created by pengmingguo on 2/14/18
  */
object MailUtil {

  def sendInviteMail(to: String,
                     subject: String,
                     token: String,
                     env: String) = {
    var content = "<html> <head> <meta charset=\"utf-8\"> <title>send email</title> </head> <body> <div style=\"width:60%;margin: 15px 15px 0;border: 1px solid #CCCCCC;\"> <div style=\"padding: 10px 20px;border-bottom: 1px solid #DDDDDD;\"> <div class=\"title\"  style=\"width: 300px;height: 60px;background: url('" + env + "/assets/img/logo-nexus.png') no-repeat center center;background-size:100%;\"></div> </div> <div style=\"padding: 20px;font-size: 14px;\"> <p>您好！</p> <p>欢迎使用APEX NEXUS，点击按钮激活账号</p> <a href=\"" + env + "/#/register/detail/" + token + "\" style=\"background:#4C8EFA; color: white;border:1px solid #4C8EFA;padding:5px 20px;text-decoration: none;\">激活账号</a> <p style=\"font-size: 13px;color:#333;\">或点击链接:<a style=\"text-decoration: underline;padding-left: 8px;font-size:14px;\">" + env + "/#/register/detail/" + token + "</a></p> <p style=\"font-size: 13px;color:#333;\">链接有效期为24小时</p> <p style=\"font-size: 13px;color:#333;\">如有疑问，请联系我们:<a href=\"Mailto:service@chinapex.com.cn\" style=\"font-size:14px;color:#333;padding-right:5px;text-decoration: none;\">service@chinapex.com.cn</a></p> </div> </div> </body> </html>"
    com.chinapex.util.MailUtil.send(to, subject, content)
  }

  def sendResetMail(to: String, subject: String, userId: String, env: String) = {
    var content = "<html> <head> <meta charset=\"utf-8\"> <title>send email</title> </head> <body> <div style=\"width:60%;margin: 15px 15px 0;border: 1px solid #CCCCCC;\"> <div style=\"padding: 10px 20px;border-bottom: 1px solid #DDDDDD;\"> <div class=\"title\"  style=\"width: 300px;height: 60px;background: url('" + env + "/assets/img/logo-nexus.png') no-repeat center center;background-size:100%;\"></div> </div> <div style=\"padding: 20px;font-size: 14px;\"> <p>您好！</p> <p>欢迎使用APEX NEXUS，点击按钮重置密码</p> <a href=\"" + env + "/#/reset_password/detail/" + userId + "\" style=\"background:#4C8EFA; color: white;border:1px solid #4C8EFA;padding:5px 20px;text-decoration: none;\">重置密码</a> <p style=\"font-size: 13px;color:#333;\">或点击链接:<a style=\"text-decoration: underline;padding-left: 8px;font-size:14px;\">" + env + "/#/reset_password/detail/" + userId + "</a></p> <p style=\"font-size: 13px;color:#333;\">链接有效期为24小时</p> <p style=\"font-size: 13px;color:#333;\">如有疑问，请联系我们:<a href=\"Mailto:support@chinapex.com\" style=\"font-size:14px;color:#333;padding-right:5px;text-decoration: none;\">support@chinapex.com</a></p> </div> </div> </body> </html>"
    com.chinapex.util.MailUtil.send(to, subject, content)
  }
}
