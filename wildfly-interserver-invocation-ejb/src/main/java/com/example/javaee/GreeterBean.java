
package com.example.javaee;

import com.example.javaee.Greeter;
import javax.ejb.Remote;
import javax.ejb.Stateless;

@Stateless
@Remote(Greeter.class)
public class GreeterBean implements Greeter {

    @Override
    public String greet(final String user) {
        final String text = "Hello " + user + ", have a pleasant day!";
        System.out.println("Bean called: " + text);
        return text;
    }
}
