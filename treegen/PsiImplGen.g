grammar PsiImplGen;

@parser::header { 
    package com.redhat.ceylon.compiler.typechecker.treegen;
    import static com.redhat.ceylon.compiler.typechecker.treegen.Util.*;
}
@lexer::header { 
    package com.redhat.ceylon.compiler.typechecker.treegen; 
}

nodeList : { 
           println("package org.intellij.plugins.ceylon.psi;\n");
           println("import com.intellij.lang.ASTNode;");
           println("import org.intellij.plugins.ceylon.psi.impl.CeylonCompositeElementImpl;");
           println("/* Generated using Antlr by PsiImplGen.g */");
           println("\npublic class CeylonPsiImpl {\n");
           }
           (nodeDescription? node)+ 
           EOF
           { println("}"); }
           ;

node : '^' '(' 
       { print("    public static "); }
       ('abstract'
        { print("abstract "); }
       )?
       { print("class "); }
       n=NODE_NAME 
       { print(className($n.text) + "PsiImpl"); }
       extendsNode
       { println("\n            implements CeylonPsi." + className($n.text) + "Psi {"); }
       { println("        public " + className($n.text) + "PsiImpl(ASTNode astNode) { super(astNode); }" ); }
       (memberDescription? subnode)*
       (memberDescription? field)*
       ')' 
       { println("    }\n"); }
     ;

extendsNode : ':' n=NODE_NAME 
              { print(" extends " + className($n.text) + "PsiImpl"); }
            | { print(" extends CeylonCompositeElementImpl"); }
            ;

nodeDescription : d=DESCRIPTION 
                  ;

memberDescription : d=DESCRIPTION 
                  ;

subnode : 
          n=NODE_NAME '?'? f=FIELD_NAME
        | n=NODE_NAME '?'?
        | mn=NODE_NAME '*'
        | mn=NODE_NAME '*' f=FIELD_NAME
        ;

field : t=TYPE_NAME f=FIELD_NAME
        ';'
      | 'boolean' f=FIELD_NAME
        ';'
      | l=TYPE_NAME '<' t=TYPE_NAME '>' f=FIELD_NAME
        ';'
      | 'abstract' t=TYPE_NAME f=FIELD_NAME
        ';'
      ;

NODE_NAME : ('A'..'Z'|'_')+;

FIELD_NAME : ('a'..'z') ('a'..'z'|'A'..'Z')*;
TYPE_NAME : ('A'..'Z') ('a'..'z'|'A'..'Z')*;

WS : (' ' | '\n' | '\t' | '\r' | '\u000C') { skip(); };

CARAT : '^';

LPAREN : '(';
RPAREN : ')';

MANY : '*'|'+';
OPTIONAL : '?';

EXTENDS : ':';

SEMI : ';';

DESCRIPTION : '\"' (~'\"')* '\"';
