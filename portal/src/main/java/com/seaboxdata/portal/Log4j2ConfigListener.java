package com.seaboxdata.portal;

import com.quick.portal.security.authority.metric.PropertiesUtil;
import org.apache.logging.log4j.core.config.Configurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Enumeration;


public class Log4j2ConfigListener implements ServletContextListener {
    private static final String KEY = "log4jConfigLocation";

    @Override
    public void contextDestroyed(ServletContextEvent arg0) {
//        Path symLinkPath = Paths.get(arg0.getServletContext().getRealPath("/") + "/upload/files");
//        try {
//            Files.deleteIfExists(symLinkPath);
//        }catch (IOException e){
//            Logger logger = LoggerFactory.getLogger(getClass());
//            logger.error("Failed to delete symbolic link for file.dir", e);
//        }
    }


    @Override
    public void contextInitialized(ServletContextEvent arg0) {
        String fileName = getContextParam(arg0);
        Configurator.initialize(null, fileName);

//        Path realPath = Paths.get(PropertiesUtil.getPropery("file.dir"));
//        Path symLinkPath = Paths.get(arg0.getServletContext().getRealPath("/") + "/upload/files");
//        if (System.getProperty("os.name").equalsIgnoreCase("linux")) {
//            try {
//                System.out.println("Trying to create symbolic link from " + realPath.toString() + " to " + symLinkPath.toString());
//                Files.createSymbolicLink(symLinkPath, realPath);
//            } catch (FileAlreadyExistsException e) {
//                // Do nothing ...
//            } catch (Exception e) {
//                Logger logger = LoggerFactory.getLogger(getClass());
//                logger.error("Failed to create symbolic link for file.dir", e);
//            }
//        }
    }


    private String getContextParam(ServletContextEvent event) {
        Enumeration<String> names = event.getServletContext().getInitParameterNames();
        while (names.hasMoreElements()) {
            String name = names.nextElement();
            String value = event.getServletContext().getInitParameter(name);
            if (name.trim().equals(KEY)) {
                return value;
            }
        }

        return null;
    }
}