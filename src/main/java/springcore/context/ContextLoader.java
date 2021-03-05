package springcore.context;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import springcore.entity.ContextApplication;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * 主要负责xml文件验证，解析
 * */
public abstract class ContextLoader {

    /**
     * 加载xml配置文件
     * */
    public void loadXmlFile(String path){
        try {
            this.checkPath(path);
            this.buildPath();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //文档文件对象
    private Document document;

    /**
     * 检查xml文件是否存在
     * */
    public void checkPath(String path) throws FileNotFoundException, DocumentException {
        //通过获取来的xml文件所在文件路径包拼接出文件真实路径
        String webPath = path + "\\WEB-INF\\classes\\handlessm\\application.xml";
        //System.out.println(webPath);
        File file = new File(webPath);
        if(!file.exists()){
            throw new FileNotFoundException(webPath + "不存在");
        }
        SAXReader saxReader = new SAXReader();
        this.document = saxReader.read(file);
    }

    //存储对象的集合（容器）
    private static Map<String, ContextApplication> objCacheMap = new HashMap<>();

    /**
     * 解析xml文件
     * */
    public void buildPath() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        //获取根目录
        Element rootElement = document.getRootElement();
        //获得bean的集合
        List<Element> beans = rootElement.elements("bean");
        //循环遍历bean
        for (Element bean : beans) {
            System.out.println(bean);
            //获取<bean name=""> name属性
            String beanName = bean.attributeValue("name");
            //获取<bean class=""> class属性
            String clazz = bean.attributeValue("class");
            //创建 class对象
            Object clazzObject = Class.forName(clazz).newInstance();
            //将class对象保存至 ContextApplication
            ContextApplication contextApplication = new ContextApplication();
            contextApplication.setObj(clazzObject);
            //将 name -> class 保存到 map容器
            objCacheMap.put(beanName,contextApplication);
        }
        Set<Map.Entry<String, ContextApplication>> entries = objCacheMap.entrySet();
        for(Map.Entry<String, ContextApplication> entrie : entries ){
            System.out.println(entrie.getKey() + "->" + entrie.getValue());
        }
    }
}
