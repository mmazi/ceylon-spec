void splitFunction() {
    Float f(Float x);
    Float g(Float x);
    Integer h(Float x);
    Float i(Float x);
    Float j(Object x);
    Float k(Float x);
    Float l(Float x);
    
    f(Float x) = x**0.5;
    j(Float x) = x**0.5;
    
    @error g(Float y, Float x) = x**0.5;
    h(@error Integer x) = x**2;
    @error i() = 0.5;
    k(@error Object x) = 1.0;
    @error l(Float x) = 3;
    
    Float add(Float x)(Float y);
    add(Float x)(Float y) = x+y;

    Float add1(Float x)(Integer y);
    add1(Float x) = (Integer y) x+y.float;

    Float add2(Float x)(Integer y);
    add2(Float x)(Integer y) = x+y.float;

    Float add3(Integer x)(Float y);
    add3(@error Float x)(@error Integer y) = x+y.float;

    Float add4(Float x)(Integer y);
    @error add4(Float x) = x;

    Float add5(Float x)(Integer y);
    @error add5(Float x)(Integer y)(Float z) = x+z;

}