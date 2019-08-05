package com.example.order.util;


import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

@Component
@Configuration
@PropertySource(value= "classpath:ftp.properties",encoding = "UTF-8")
public class FtpFileUtil {
//   / /ftp服务器ip地址
    @Value( "${FTP_ADDRESS}" )
    private String FTP_ADDRESS ;
    //端口号
    @Value( "${FTP_PORT}" )
    private int FTP_PORT ;
    //用户名
    @Value( "${FTP_USERNAME}" )
    private String FTP_USERNAME ;
    //密码
    @Value( "${FTP_PASSWORD}" )
    private  String FTP_PASSWORD ;
    //图片路径
    @Value( "${FTP_BASEPATH}" )
    private String FTP_BASEPATH;

    public   boolean uploadFile(String originFileName, InputStream input){
        boolean success = false;
        FTPClient ftp = new FTPClient();
        ftp.setControlEncoding("UTF-8");
        try {
            int reply;
            ftp.connect(FTP_ADDRESS, FTP_PORT);// 连接FTP服务器
            ftp.login(FTP_USERNAME, FTP_PASSWORD);// 登录
            reply = ftp.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftp.disconnect();
                return success;
            }
            //设置上传文件的类型为二进制类型
            ftp.setFileType( FTP.BINARY_FILE_TYPE);
            ftp.makeDirectory(FTP_BASEPATH );
            ftp.changeWorkingDirectory(FTP_BASEPATH );
            ftp.storeFile(originFileName,input);
            input.close();
            ftp.logout();
            success = true;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ftp.isConnected()) {
                try {
                    ftp.disconnect();
                } catch (IOException ioe) {
                }
            }
        }
        return success;
    }
}
