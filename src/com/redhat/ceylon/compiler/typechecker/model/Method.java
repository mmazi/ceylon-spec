package com.redhat.ceylon.compiler.typechecker.model;

import static com.redhat.ceylon.compiler.typechecker.model.Util.isNamed;
import static java.util.Collections.emptyList;

import java.util.ArrayList;
import java.util.List;

/**
 * A method. Note that a method must have
 * at least one parameter list.
 *
 * @author Gavin King
 */
public class Method extends MethodOrValue implements Generic, Scope, Functional {

    public Method() {}
    
    //private boolean formal;

    private List<TypeParameter> typeParameters = emptyList();
    private List<ParameterList> parameterLists = new ArrayList<ParameterList>(1);
    private boolean overloaded;
    private boolean abstraction;
    private List<Declaration> overloads;
    private boolean declaredAnything;
    private Object annotationConstructor;
    private boolean deferred;
    

    /*public boolean isFormal() {
         return formal;
     }

     public void setFormal(boolean formal) {
         this.formal = formal;
     }*/
    

    public Object getAnnotationConstructor() {
        return annotationConstructor;
    }

    public void setAnnotationConstructor(Object annotationInstantiation) {
        this.annotationConstructor = annotationInstantiation;
    }

    @Override
    public boolean isParameterized() {
        return !typeParameters.isEmpty();
    }

    public List<TypeParameter> getTypeParameters() {
        return typeParameters;
    }

    public void setTypeParameters(List<TypeParameter> typeParameters) {
        this.typeParameters = typeParameters;
    }

    @Override
    public List<ParameterList> getParameterLists() {
        return parameterLists;
    }

    @Override
    public void addParameterList(ParameterList pl) {
        parameterLists.add(pl);
    }

    @Override
    public boolean isOverloaded() {
    	return overloaded;
    }
    
    public void setOverloaded(boolean overloaded) {
		this.overloaded = overloaded;
	}
    
    public void setAbstraction(boolean abstraction) {
        this.abstraction = abstraction;
    }
    
    @Override
    public boolean isAbstraction() {
        return abstraction;
    }
    
    @Override
    public boolean isDeclaredVoid() {
        return declaredAnything;
    }
    
    public void setDeclaredAnything(boolean declaredAnything) {
        this.declaredAnything = declaredAnything;
    }
    
    public boolean isDeferred() {
        return deferred;
    }
    
    public void setDeferred(boolean deferred) {
        this.deferred = deferred;
    }
    
    @Override
    public List<Declaration> getOverloads() {
        return overloads;
    }
    
    public void setOverloads(List<Declaration> overloads) {
        this.overloads = overloads;
    }
    
    public Parameter getParameter(String name) {
        for (Declaration d : getMembers()) {
            if (d.isParameter() && isNamed(name, d)) {
                return ((MethodOrValue) d).getInitializerParameter();
            }
        }
        return null;
    }
    
    @Override
    public void setSetter(Setter setter) {
        throw new UnsupportedOperationException();
    }

}
