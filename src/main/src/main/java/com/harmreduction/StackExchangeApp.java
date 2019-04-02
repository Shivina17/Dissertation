package com.harmreduction;


import com.harmreduction.model.StackExchange;
import com.harmreduction.service.StackExchangeService;
import com.harmreduction.service.StackExchangeServiceImpl;

import java.io.IOException;
import java.util.Date;
import java.util.List;

public class StackExchangeApp {

    private static StackExchangeService service = null;

    public static void main(String[] args) throws Exception {
        service = new StackExchangeServiceImpl();

       // insert();
       printBySearch("printing technologies");

    }

    private static void insert() {
        // Save music 1
        StackExchange post1 = new StackExchange();
        post1.setYear(new Date());
        post1.setDocumentId("1");
        post1.setPost("<p>I have been wanting to learn about 3D printing a long time so I really want this site to succeed but I have no previous experience with the subject. </p><p>I was wondering how can I help the site at this early stage. I thought about asking about how to get started with 3D printing but SE explicitly discourages \"easy\" questions in the private beta.</p><p>What can newbies like me do for the site at this stage besides voting questions and answers?</p>");
        post1.setName("Post 1");
        create(post1);

        // Save music 2
        StackExchange post2 = new StackExchange();
        post2.setYear(new Date());
        post2.setDocumentId("2");
        post2.setPost("<p>There are many different printing technologies. While it might be clear to the asker that he's talking about his home 3D printing machine, that's not necessarily the case for those that try to answer the question.</p><p>I think it would help to specify the scope of questions with tags, to allow future visitors to identify what type of 3D printer it is about. Answers to a lot of questions related to general properties of the printing process like printing speed, resolution, additional manual work, support structures, etc. depend heavily on the technology that's being used.</p><p>This also prevents \\\"Well, it depends...\\\" type of answers that cover a lot of possibly unrelated things.</p>");
        post2.setName(" Post 2");
        create(post2);

        // Save music 3
        StackExchange post3 = new StackExchange();
        post3.setYear(new Date());
        post3.setDocumentId("3");
        post3.setPost("<p>No, there will be many questions which are more general in nature, and have little to do with the actual process used to produce the part.</p><p>We should encourage tag use for process when appropriate, but I don't think making it mandatory will work in the long term.</p>");
        post3.setName("Post 3");
        create(post3);

        // Save music 3
        StackExchange post4 = new StackExchange();
        post4.setYear(new Date());
        post4.setDocumentId("4");
        post4.setName("Post 4");
        post4.setPost("<p>For questions where the technology matters, absolutely.</p><p>If you look at Stack Overflow, for example, all questions must be tagged with the language the OP is asking about. I doubt there will be many questions that are asking about all technologies at once - and if they are, that's likely to be marked as too broad anyway.</p>");
        create(post4);

        StackExchange post5 = new StackExchange();
        post5.setYear(new Date());
        post5.setDocumentId("5");
        post5.setPost("<p>I have been wanting to learn about 3D printing a long time so I really want this site to succeed but I have no previous experience with the subject. </p><p>I was wondering how can I help the site at this early stage. I thought about asking about how to get started with 3D printing but SE explicitly discourages \"easy\" questions in the private beta.</p><p>What can newbies like me do for the site at this stage besides voting questions and answers?</p>");
        post5.setName("Post 5");
        create(post5);  //repetiton



    }

    private static void create(StackExchange post) {
        try {
            service.create(post);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void createFromSourceString(String post) {
        try {
            service.createFromSourceString(post);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static void printById(String id) throws IOException {
        StackExchange post = service.findById(id);

        if (post != null) {
            System.out.println(post.toString());
        } else {
            System.out.println("No  music with the ID[" + id + "] found");
        }
    }

    private static void printBySearch(String quote) throws Exception{
//        List<StackExchange> posts = service.findBySearch(quote);
        List<StackExchange> posts = service.findByJsonQuery(quote);

        if (posts != null) {
            //print music
        } else {
            System.out.println("No music found");
        }
    }
}
