package Chapter08Script.script;

import javax.script.*;
import javax.swing.*;
import java.util.List;

public class Test {
    public static void main(String[] args) throws ScriptException {
        //Java SE 自带Nashorn引擎
        ScriptEngineManager manager = new ScriptEngineManager();
        List<ScriptEngineFactory> engineFactories =
                manager.getEngineFactories();
        engineFactories.forEach(System.out::println);
        ScriptEngine nashorn = manager.getEngineByName("nashorn");

        nashorn.eval("n=189");
        Object result = nashorn.eval("n+1");
        System.out.println(result);

        List<String> extensions = nashorn.getFactory().getExtensions();
        extensions.forEach(System.out::println);
        List<String> mimeTypes = nashorn.getFactory().getMimeTypes();
        mimeTypes.forEach(System.out::println);
        System.out.println(nashorn.getFactory().getNames());

        ScriptEngine groovy = manager.getEngineByName("groovy");
        System.out.println(groovy);


        //用于判断脚本执行安不安全，factory.getParameter()     null-不安全
        System.out.println(nashorn.getFactory().getParameter("THREADING"));

        //engine.put(key,value)是引擎作用域，在ScriptEngineManager中绑定的对所有引擎都是可视的，即全局作用域。
        //还有一种方式，通过Bindings对象
        manager.put("k", 234);

        Bindings scope = nashorn.createBindings();
        scope.put("b", new JButton());
        //nashorn.eval(scriptString, scope);
    }
}
