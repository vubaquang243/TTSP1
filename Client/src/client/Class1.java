package client;

import javax.jws.WebService;

@WebService(targetNamespace = "http://tempuri.org/")
public class Class1 {
    public Class1() {
        super();
    }
    public String hello(){
      return "Hello";
    }
}
