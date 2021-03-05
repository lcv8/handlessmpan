package springcore.entity;

/**
 * 用作 map 的value存储
 * 保存 xml 文件中 class属性对象
 *  以后你存储的 属性不仅仅时class ，还可能是是其他都可以在这个类中扩展
 * */
public class ContextApplication {
    private Object obj;

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }
}
