package springcore.context;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @author dyh
 * @date 2021.03.05
 * 该类作为tomact容器启动时，同时加载xml配置文件，
 * */
public class ContextLoadListener extends ContextLoader implements ServletContextListener {

    /**
     * 整个容器生涯最先执行，加载xml配置文件
     * */
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        //获得文件在项目中的真实路径，并传给ContextLoader类解析，读取
        String realPath = servletContextEvent.getServletContext().getRealPath("");
        super.loadXmlFile(realPath);
        System.out.println(realPath);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
