package com.endoflineblog.truffle.part_04;

import com.endoflineblog.truffle.part_03.EasyScriptNode;
import com.endoflineblog.truffle.part_03.EasyScriptRootNode;
import com.oracle.truffle.api.CallTarget;
import com.oracle.truffle.api.Truffle;
import com.oracle.truffle.api.TruffleLanguage;

import java.io.IOException;

@TruffleLanguage.Registration(id = "ezs", name = "EasyScript")
public final class EasyScriptTruffleLanguage extends TruffleLanguage<Void> {
    @Override
    protected CallTarget parse(ParsingRequest request) throws IOException {
        EasyScriptNode exprNode = EasyScriptTruffleParser.parse(request.getSource().getReader());
        var rootNode = new EasyScriptRootNode(exprNode);
        return Truffle.getRuntime().createCallTarget(rootNode);
    }

    @Override
    protected Void createContext(Env env) {
        return null;
    }
}
