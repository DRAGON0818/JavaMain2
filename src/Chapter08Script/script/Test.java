package Chapter08Script.script;

import jdk.nashorn.api.scripting.NashornScriptEngineFactory;

import javax.script.*;
import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

public class Test {
    public static void main(String[] args) throws ScriptException, FileNotFoundException {
        //Java SE 自带Nashorn引擎
        ScriptEngineManager manager = new ScriptEngineManager();
        List<ScriptEngineFactory> engineFactories =
                manager.getEngineFactories();
        engineFactories.forEach(System.out::println);
        ScriptEngine nashorn = manager.getEngineByName("nashorn");

        engineFactories.forEach(e->{
            System.out.println(e.getNames());
            System.out.println(e.getMimeTypes());
            System.out.println(e.getEngineName()+" "+e.getLanguageName()+" "+e.getLanguageVersion());
        } );


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
        ScriptContext context = nashorn.getContext();
        System.out.println("context: " + context);

        Bindings scope = nashorn.createBindings();
        scope.put("b", new JButton());
        //nashorn.eval(scriptString, scope);

        //调用脚本的函数和方法，需要脚本引擎实现Invocable接口。调用函数名 用Invocable.invokeFunction()
        nashorn.eval("function greet(how,whom){return how + ','+whom + '!'}");
        try {
            Object o = ((Invocable) nashorn).invokeFunction("greet", "Hello", "World");
            System.out.println(o);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        //脚本语言是面向对象的，则调用Invocable.invokeMethod()
        nashorn.eval("function Greeter(how){this.how=how}");
        nashorn.eval("Greeter.prototype.welcome=" +
                " function(whom){return this.how +','+whom+'!'}");

        Object yo = nashorn.eval("new Greeter('Yo')");
        try {
            System.out.println(((Invocable)nashorn).invokeMethod(yo,"welcome","World"));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        //定义了一个JS的方法，function welcome()，和接口Greeter的方法名一致。
        nashorn.eval("function welcome(whom) { return 'Hello,'+ whom +'!'}");
        //获得一个java对象并且调用发的方法
        Greeter g = ((Invocable) nashorn).getInterface(Greeter.class);
        System.out.println(g.welcome("World"));


        FileReader reader = new FileReader("myscript.js");
        //引擎实现了Compilable接口，就可以将脚本代码编译为某种中间格式.编译的作用，就是我们希望能够重复的执行b
        CompiledScript script = null;
        if (nashorn instanceof Compilable) {
            script = ((Compilable) nashorn).compile(reader);
        }
    }
}

