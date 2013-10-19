package com.redhat.ceylon.compiler.typechecker.treegen;


import java.io.*;

import org.antlr.runtime.ANTLRInputStream;
import org.antlr.runtime.CommonTokenStream;

public class Generate {

    private static final String GENERATED_PACKAGE_DIR = "gensrc/com/redhat/ceylon/compiler/typechecker/tree/";

    public static void main(String[] args) throws Exception {
        File file = new File(args[0]);
        ideaPsiIntf(file);
        ideaPsiImpl(file);
        ideaPsiFactory(file);
    }
    
    private static void tree(File file) throws Exception {
        ANTLRInputStream input = getAntlrInputStream(file);
        TreegenLexer lexer = new TreegenLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        TreegenParser parser = new TreegenParser(tokens);
        setOut("Tree");
        parser.nodeList();
    }

    /*private static void builder(File file) throws Exception {
        InputStream is = new FileInputStream( file );
        ANTLRInputStream input = new ANTLRInputStream(is);
        BuildergenLexer lexer = new BuildergenLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        BuildergenParser parser = new BuildergenParser(tokens);
        File out = new File( GENERATED_PACKAGE_DIR + "Builder.java" );
        out.createNewFile();
        Util.out=new PrintStream(out);
        parser.nodeList();
    }*/
    
    private static void walker(File file) throws Exception {
        ANTLRInputStream input = getAntlrInputStream(file);
        WalkergenLexer lexer = new WalkergenLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        WalkergenParser parser = new WalkergenParser(tokens);
        setOut("Walker");
        parser.nodeList();
    }
    
    private static void visitor(File file) throws Exception {
        ANTLRInputStream input = getAntlrInputStream(file);
        VisitorgenLexer lexer = new VisitorgenLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        VisitorgenParser parser = new VisitorgenParser(tokens);
        setOut("Visitor");
        parser.nodeList();
    }
    
    private static void validator(File file) throws Exception {
        ANTLRInputStream input = getAntlrInputStream(file);
        ValidatorgenLexer lexer = new ValidatorgenLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        ValidatorgenParser parser = new ValidatorgenParser(tokens);
        setOut("Validator");
        parser.nodeList();
    }

    private static void ideaAstTypes(File file) throws Exception {
        ANTLRInputStream input = getAntlrInputStream(file);
        IdeaAstTypesGenLexer lexer = new IdeaAstTypesGenLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        IdeaAstTypesGenParser parser = new IdeaAstTypesGenParser(tokens);
        setOut("CeylonTypes");
        parser.nodeList();
    }

    private static void ideaPsiIntf(File file) throws Exception {
        ANTLRInputStream input = getAntlrInputStream(file);
        PsiIntfGenLexer lexer = new PsiIntfGenLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        PsiIntfGenParser parser = new PsiIntfGenParser(tokens);
        setOut("CeylonPsi");
        parser.nodeList();
    }

    private static void ideaPsiImpl(File file) throws Exception {
        ANTLRInputStream input = getAntlrInputStream(file);
        PsiImplGenLexer lexer = new PsiImplGenLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        PsiImplGenParser parser = new PsiImplGenParser(tokens);
        setOut("CeylonPsiImpl");
        parser.nodeList();
    }

    private static void ideaPsiFactory(File file) throws Exception {
        ANTLRInputStream input = getAntlrInputStream(file);
        PsiFactoryGenLexer lexer = new PsiFactoryGenLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        PsiFactoryGenParser parser = new PsiFactoryGenParser(tokens);
        setOut("CeylonPsiFactory");
        parser.nodeList();
    }

    private static void setOut(String fileNameBase) throws IOException {
        File out = new File( GENERATED_PACKAGE_DIR + fileNameBase + ".java");
        out.createNewFile();
        Util.out=new PrintStream(out);
    }

    private static ANTLRInputStream getAntlrInputStream(File file) throws IOException {InputStream is = new FileInputStream(file);
        return new ANTLRInputStream(is);
    }

}
