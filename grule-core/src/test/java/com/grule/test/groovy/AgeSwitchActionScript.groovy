package com.grule.test.groovy

import com.grule.core.context.Context
import com.grule.core.exception.UnitRunException
import com.grule.core.script.groovy.ActionScript

/**
 * Created on 2018/03/05
 */
class AgeSwitchActionScript implements ActionScript {
    @Override
    void run(Context context) {
        int age = context.get("age");
        switch (age) {
            case 18:
                println("18")
                break
            case 16:
                println("16,too young")
                break
            case 13:
                println("13 is not ok")
                break
            case 0:
                throw new UnitRunException("age can not be 0     ~~~~~")
            default:
                println("default action，balabala～")
        }
    }
}
