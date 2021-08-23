package com.endoflineblog.truffle.part_05.nodes.exprs;

import com.endoflineblog.truffle.part_05.EasyScriptException;
import com.endoflineblog.truffle.part_05.EasyScriptLanguageContext;
import com.endoflineblog.truffle.part_05.EasyScriptTruffleLanguage;
import com.oracle.truffle.api.dsl.CachedContext;
import com.oracle.truffle.api.dsl.NodeChild;
import com.oracle.truffle.api.dsl.NodeField;
import com.oracle.truffle.api.dsl.Specialization;

/**
 * A Node that represents the expression of assigning a value to a global variable in EasyScript.
 */
@NodeChild(value = "assignmentExpr")
@NodeField(name = "name", type = String.class)
public abstract class GlobalVarAssignmentExprNode extends EasyScriptExprNode {
    protected abstract String getName();

    @Specialization
    protected Object assignVariable(
            Object value,
            @CachedContext(EasyScriptTruffleLanguage.class) EasyScriptLanguageContext context) {
        String variableId = this.getName();
        if (!context.globalScopeObject.updateValue(variableId, value)) {
            throw new EasyScriptException("'" + variableId + "' is not defined");
        }
        return value;
    }
}
