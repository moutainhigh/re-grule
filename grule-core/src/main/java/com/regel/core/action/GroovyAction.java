package com.regel.core.action;

import com.regel.core.Constants;
import com.regel.core.context.Context;
import com.regel.core.exception.UnitRunException;
import com.regel.core.manager.GroovyClassUtil;
import com.regel.core.manager.GroovyManager;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by  on 2017/8/11.
 */
public class GroovyAction extends AbstractAction {

    private Class groovyClass = null;

    private String path;
    private String script;

    /**
     * Groovy 动作类
     */
    public GroovyAction() {
        super();
    }

    /**
     * Groovy 动作类
     *
     * @param file Groovy 脚本文件
     * @throws IOException
     */
    public GroovyAction(File file) throws IOException {
        super();
        if (file != null && file.exists()) {
            this.groovyClass = GroovyManager.getInstance().getGroovyClassLoader().parseClass(file);
        }
    }

    /**
     * Groovy 动作类
     *
     * @param script Groovy 脚本
     */
    public GroovyAction(String script) {
        super();
        try {
            this.script = script;
            this.groovyClass = GroovyClassUtil.loadClass(this.script, this.path);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void run(Context context) throws UnitRunException {
        try {
            if (this.groovyClass == null) {
                this.groovyClass = GroovyClassUtil.loadClass(this.script, this.path);
            }
            GroovyManager.getInstance().invokeMethod(this.groovyClass, Constants.RUN, context);
        } catch (IOException | InstantiationException | InvocationTargetException | NoSuchMethodException | IllegalAccessException e) {
            throw new UnitRunException(e);
        }
    }
}
