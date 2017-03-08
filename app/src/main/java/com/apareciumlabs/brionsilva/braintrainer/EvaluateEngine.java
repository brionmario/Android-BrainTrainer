package com.apareciumlabs.brionsilva.braintrainer;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Function;
import org.mozilla.javascript.RhinoException;
import org.mozilla.javascript.Scriptable;


/**
 * Created by brionsilva on 08/03/2017.
 */

/*Used the RHINO open source library to build the class
    Github link - https://github.com/brionsilva
 */

public class EvaluateEngine {

    private Context rhino;
    private Scriptable scope;

    Double answer;

    public Double evaluate (String question) {

            Object[] functionParams = new Object[]{question};

            Context rhino = Context.enter();

            //disabling the optimizer to better support Android.
            rhino.setOptimizationLevel(-1);

            try {

                Scriptable scope = rhino.initStandardObjects();

                rhino.evaluateString(scope, "function Calculer (formule){  return eval(formule)    ;} ", "JavaScript", 1, null);


                Function function = (Function) scope.get("Calculer", scope);


                answer = (Double) function.call(rhino, scope, scope, functionParams);


            } catch (RhinoException e) {

                e.printStackTrace();

            } finally {
                Context.exit();
            }
return answer;
    }
}
