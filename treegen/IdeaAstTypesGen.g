grammar IdeaAstTypesGen;

@parser::header { 
    package com.redhat.ceylon.compiler.typechecker.treegen; 
    import static com.redhat.ceylon.compiler.typechecker.treegen.Util.*; 
}
@lexer::header { 
    package com.redhat.ceylon.compiler.typechecker.treegen; 
}

nodeList : { 
            println("package org.intellij.plugins.ceylon.psi;\n");
            println("import com.intellij.psi.tree.IElementType;");
            println("import com.intellij.psi.PsiElement;");
            println("import com.intellij.lang.ASTNode;");
            println("import org.intellij.plugins.ceylon.psi.stub.impl.ClassElementType;");
            println("import org.intellij.plugins.ceylon.psi.stub.impl.ModuleElementType;");
            println("import org.intellij.plugins.ceylon.psi.impl.*;\n");
            println("/* Generated using Antlr by IdeaAstTypesGen.g */");
            println("public interface CeylonTypes {");
            println("");
           }
           (DESCRIPTION? node)+ 
           EOF
           { println("\n}"); }
           ;

node : '^' '('
       'abstract'? n=NODE_NAME
         { println("    IElementType " + $n.text + " = new CeylonElementType(\"" + $n.text + "\");"); }
           (':' en=NODE_NAME)?
           (DESCRIPTION? subnode)*
       (DESCRIPTION? field)*
       ')'
     ;

subnode : n=NODE_NAME '?'? f=FIELD_NAME? 
        | mn=NODE_NAME '*' f=FIELD_NAME?
        ;

field : 'abstract'? (TYPE_NAME|'boolean') FIELD_NAME ';';

NODE_NAME : ('A'..'Z'|'_')+;

FIELD_NAME : ('a'..'z') ('a'..'z'|'A'..'Z')*;
TYPE_NAME : ('A'..'Z') ('a'..'z'|'A'..'Z'|'<'|'>')*;

WS : (' ' | '\n' | '\t' | '\r' | '\u000C') { skip(); };

CARAT : '^';

LPAREN : '(';
RPAREN : ')';

MANY : '*'|'+';
OPTIONAL : '?';

EXTENDS : ':';

SEMI : ';';

DESCRIPTION : '\"' (~'\"')* '\"';
