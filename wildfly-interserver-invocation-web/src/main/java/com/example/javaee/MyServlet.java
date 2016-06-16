package com.example.javaee;

import com.example.javaee.Greeter;
import java.io.IOException;
import java.util.Hashtable;
import javax.naming.Context;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MyServlet
 */
@WebServlet(name = "myServlet", urlPatterns = { "/myServlet" })
public class MyServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    @Override
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response)
        throws ServletException, IOException {

        String outputText = null;
        try {
            final Hashtable props = new Hashtable();
            // setup the ejb: namespace URL factory
            props.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
            // create the InitialContext
            final Context context = new javax.naming.InitialContext(props);

            // Lookup the Greeter bean using the ejb: namespace syntax which is
            // explained here
            // https://docs.jboss.org/author/display/AS71/EJB+invocations+from+a+remote+client+using+JNDI
            final Greeter bean = (Greeter) context
                .lookup("ejb:" + "wildfly-interserver-invocation-ear" + "/" + "wildfly-interserver-invocation-ejb" +
                    "/" + "myserver" + "/" +
                "GreeterBean" + "!" + Greeter.class.getName());

            // invoke on the bean
            outputText = bean.greet("Tom");

            System.out.println("Received greeting: " + outputText);
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }
        response.getWriter().append("Response: ").append(outputText);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    @Override
    protected void doPost(final HttpServletRequest request, final HttpServletResponse response)
        throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

}
