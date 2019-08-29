# Servlet
(ref: https://stackoverflow.com/questions/3106452/how-do-servlets-work-instantiation-sessions-shared-variables-and-multithreadi)

Servlet container starts, then it:
- deploys and loads all web applications (folder webapp)
- creates ServletContext once and keep in server's memory.
- web descriptors is parsed (including web-fragment.xml), then tags: servlet, filter, listener (or annotated classes e.g. @WebServlet,...) found and are instantiated once and kept in server's memory.
- each servlet will have it's own URL mapping in the web descriptor.

---
> Question: Describe how Servlets are mapped to urls

Answer: There are 2 way: 
- use web.xml to define servlet
web.xml:
```xml
<web-app>
...
    <servlet>
        <servlet-name>CurioServlet</servlet-name>
        <servlet-class>game.curio.web.servlet.CurioServlet</servlet-class>
    </servlet>
    
    <servlet-mapping>
        <servlet-name>CurioServlet</servlet-name>
        <url-pattern>/curio</url-pattern>
    </servlet-mapping>
...
</web-app>
```
- add mappings or @WebServlet annotation:
```
@WebServlet(urlPatterns = "/curioAnnotation")
```

Only apply 1 approach for each servlet, otherwise there will be side effects.

---
# Use HTTP methods
Servlet extend from HttpServlet which supports doGet(), doPut(), doPost(),... We handle our own request and issue response by overriding these method corresponding to the request.

ex:
```java
@WebServlet(urlPatterns = "/curioAnnotation")
public class CurioServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
		res.getWriter().write("Hello game curio");
	}
}
```

---
# How Servlet handle parameters
## Init Params
- static parameters
- declared inside the <servlet> tag
- only available for that servlet scope.

## Context Params
- static parameters
- declared inside <web-app> tag
- (outside of servlet scope) available globally within application
- e.g. javax.faces.PROJECT_STAGE

to access init or context params: `getInitParameter("paramName")`

## Request Params
- GET & POST: Parameters are passed in the query string as name/value pairs.
- HttpServletRequest catches these parameters.
ex:
```
request url = http://localhost:8080/game-curio/curio?ask-for=Batman%20Arkham%20City
```

```
String reqParam = req.getParameter("ask-for"); // Batman Arkham City
```

# How Servlet handle HTTP headers
/todo

# How Servlet handle cookies
/todo

# Manage servlet life cycle with container callback methods and WebFilters
/todo
