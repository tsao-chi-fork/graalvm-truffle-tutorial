package com.endoflineblog.truffle.part_05.nodes.exprs;

import com.endoflineblog.truffle.part_05.EasyScriptException;
import com.endoflineblog.truffle.part_05.EasyScriptLanguageContext;
import com.endoflineblog.truffle.part_05.EasyScriptTruffleLanguage;
import com.oracle.truffle.api.dsl.CachedContext;
import com.oracle.truffle.api.dsl.NodeField;
import com.oracle.truffle.api.dsl.Specialization;

@NodeField(name = "name", type = String.class)
public abstract class GlobalVarReferenceExprNode extends EasyScriptExprNode {
    protected abstract String getName();

    @Specialization
    protected Object readVariable(
            @CachedContext(EasyScriptTruffleLanguage.class) EasyScriptLanguageContext context) {
        String variableId = this.getName();
        var value = context.globalScopeObject.getValue(variableId);
        if (value == null) {
            throw new EasyScriptException("'" + variableId + "' is not defined");
        }
        return value;
    }
}
