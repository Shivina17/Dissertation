package com.underdog.jersey.grizzly;

import com.google.gson.Gson;
import com.harmreduction.model.Forum;
import com.harmreduction.model.Subqueries;
import com.harmreduction.service.ForumService;
import com.harmreduction.service.ForumServiceImpl;
import com.harmreduction.service.SubqueryService;
import com.harmreduction.service.SubqueryServiceImpl;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.List;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("myresource")
public class MyResource {


    private static SubqueryService subqueryService = null;
    private static ForumService forumService = null;


    @GET
    //@path
    @Produces(MediaType.APPLICATION_JSON)
    public String getIt() throws Exception {
        forumService = new ForumServiceImpl();
        String posts = printBySearch("response");
        return posts;
    }

    @GET
    @Path("/query/{query}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getItByQuery(@PathParam("query") String query) throws Exception {
        forumService = new ForumServiceImpl();
        String posts = printByQuery(query);
        return posts;
    }



    @GET
    @Path("/topicID/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getByTopicID(@PathParam("id") String id) throws Exception {
        forumService = new ForumServiceImpl();
        String posts = printByTopicID(id);
        return posts;
    }


    @GET
    @Path("/username/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getByUsername(@PathParam("username") String username) throws Exception {
        forumService = new ForumServiceImpl();
        String posts = printByUsername(username);
        return posts;
    }


    @GET
    @Path("/allFilters")
    @Produces(MediaType.APPLICATION_JSON)
    public String getFilters() throws Exception {
        subqueryService = new SubqueryServiceImpl();
        String subqueries = getAllFilters();
        return subqueries;
    }


    @GET
    @Path("/filters/{filterName}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getQueryByName(@PathParam("filterName") String filterName) throws Exception {
        System.out.println("hi");
        subqueryService = new SubqueryServiceImpl();
        String subqueries = printSubqueryByName(filterName);
        return subqueries;
    }



    @GET
    @Path("/subquery/getsubquery/{nameOfSubquery}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getSubqueryByName(@PathParam("nameOfSubquery") String nameOfSubquery) throws Exception {
        subqueryService = new SubqueryServiceImpl();
        List<Subqueries> subqueries = subqueryService.getByName(nameOfSubquery);
        if (subqueries != null) {
            String json = new Gson().toJson(subqueries);
            return json;
        }
        else
            return null;
    }


    @POST
    @Path("/addQuery/{newName}/{newQuery}/{currentuser}/{datetime}")
   // @Produces(MediaType.APPLICATION_JSON)

    public void addQuery(@PathParam("newName") String newName,
                           @PathParam("newQuery") String newQuery
                            ,@PathParam("currentuser") String currentuser
                           ,@PathParam("datetime") String datetime
    ) throws Exception {

        System.out.println("adding query!");
        subqueryService = new SubqueryServiceImpl();

        Subqueries newSub = new Subqueries();


            newSub.setQueryName(newName);
            newSub.setAuthor(currentuser);
            newSub.setDate(datetime);
            newSub.setQuery(newQuery);
            subqueryService.createQuery(newSub);


    }


    @DELETE
    @Path("/deleteFilter/{subqname}")
    // @Produces(MediaType.APPLICATION_JSON)

    public void deleteFilter(@PathParam("subqname") String subqname) throws Exception {

        System.out.println("deleting query!");
        subqueryService = new SubqueryServiceImpl();
        subqueryService.deleteQuery(subqname);
    }

    @POST
    @Path("/editFilter/{subqname}/{newQuery}/{currentuser}/{datetime}")
    // @Produces(MediaType.APPLICATION_JSON)

    public void editFilter(@PathParam("subqname") String subqname,
                            @PathParam("newQuery") String newQuery
            ,@PathParam("currentuser") String currentuser
            ,@PathParam("datetime") String datetime
                           ) throws Exception {

        System.out.println("editing query!");

        subqueryService = new SubqueryServiceImpl();
       subqueryService.editQuery(subqname, newQuery, currentuser,datetime);
    }




    private String getAllFilters() throws IOException {
        List<Subqueries> subqueries = subqueryService.getAllSubqueries();
        if (subqueries != null) {
            //json string
            String json = new Gson().toJson(subqueries);
            return json;
        }
        else
            return null;
    }


    private String printSubqueryByName(String filterName) throws IOException {
        List<Subqueries> subqueries = subqueryService.getByName(filterName);
        if (subqueries != null) {
            //json string
            String json = new Gson().toJson(subqueries);
            return json;
        }
        else
            return null;
    }


    private String printBySearch(String quote) throws Exception{
       List<Forum> posts = forumService.findBySearch(quote);

        if (posts != null) {
            //json string
            String json = new Gson().toJson(posts);
            return json;

//            String str = "[\n" +
//                    "  {\n" +
//                    "    \"username\": \"user1\",\n" +
//                    "    \"forumId\": \"123\",\n" +
//                    "    \"postId\": \"1\",\n" +
//                    "    \"topicId\": \"1\",\n" +
//                    "    \"post\": \"Thanks blue. Now, about your helpful response:&nbsp; it's *you're = YOU ARE welcome.&nbsp; It's not *your.&nbsp; *Your implies possession e.g. YOUR misspelling will be YOUR doom! ah ha ha ha ha ha ha ha ha ha ha haThat's the least I can do for you, buddy.&nbsp; I forgot how to find my ratings also.&nbsp; I'm perfect so far! Yay\"\n" +
//                    "  },\n" +
//                    "  {\n" +
//                    "    \"username\": \"user2\",\n" +
//                    "    \"forumId\": \"123\",\n" +
//                    "    \"postId\": \"2\",\n" +
//                    "    \"topicId\": \"2\",\n" +
//                    "    \"post\": \"Thanks blue. Appreciate the timely response!\"\n" +
//                    "  }\n" +
//                    "]";
//            return str;
        } else {
            return null;
        }
    }


    private String printByTopicID(String id) throws IOException {
        List<Forum> posts = forumService.findByTopicId(id);
        try {
            if (posts != null) {
                String json = new Gson().toJson(posts);
                return json;
            } else {
                return null;
            }
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }


    private String printByUsername(String username) throws IOException {
        List<Forum> posts = forumService.findByUsername(username);

        try {
            if (posts != null) {
                String json = new Gson().toJson(posts);
                return json;
            } else {
                return null;
            }
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }

    private String printByQuery(String query) throws IOException {
        List<Forum> posts = forumService.findByJsonQuery(query);

        try {
            if (posts != null) {
                String json = new Gson().toJson(posts);
                return json;
            } else {
                return null;
            }
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }

}


//slogin -L9201:localhost:9200 -L5601:localhost:5601 e-research


//{ "match": { "title":   "Search"        }},