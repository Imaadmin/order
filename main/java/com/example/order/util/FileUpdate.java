package com.example.order.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
@Component
public class FileUpdate {
    @Autowired
    private SpringBeansUtil beans;
    public  String fileUpdate(MultipartFile file) throws IOException {
        if (null != file) {
            String myFileName = file.getOriginalFilename();// 文件原名称
            myFileName = new SimpleDateFormat( "yyyyMMddHHmmss" ).format( new Date() ) + "_" + myFileName;
            InputStream inputStream = file.getInputStream();
            String filePath = null;
//            Boolean flag = FtpFileUtil.uploadFile( myFileName, inputStream );
            FtpFileUtil bean = beans.getBean( FtpFileUtil.class );
            boolean flag = bean.uploadFile(  myFileName, inputStream );
            if (flag == true) {
                filePath = myFileName;
                String fileName = "http://images.tx-q.cn/" + myFileName;
                return fileName;
            }
        } else {
            return ("文件不能为空");
        }
        return "出现错误";
}



}

