package API;

/***
 * Initial version created by: Silas
 * Edited by: 
 * Created: 11-06-2020
 * This class is responsible for:
 *  -
 */
import javax.ws.rs.*;

@Path("test")
public class TestService {
    @GET
    public String helloWorld (){
        return "Hello world";
    }

    // rest/test/name/{name}
    @GET
    @Path("name/{name}")
    public String helloName (@PathParam("name") String name){
        return "hello " + name;
    }
    // rest/test/form (html form) -> hello Magnus
    @POST
    @Path("form")
    public String helloForm (@FormParam("name") String name){
        return "hello "+name;
    }



    // rest/test/query?name=Magnus?
    @POST
    @Path("query")
    public String helloQuery (@QueryParam("name") String name){
        return "hello " + name;
    }
}