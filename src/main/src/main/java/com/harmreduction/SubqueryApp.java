package com.harmreduction;

import com.harmreduction.model.Subqueries;
import com.harmreduction.service.SubqueryService;
import com.harmreduction.service.SubqueryServiceImpl;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class SubqueryApp {
    private static SubqueryService service = null;

    public static void main(String[] args) throws Exception {
        service = new SubqueryServiceImpl();

        //insert();

       //printAll();
       // printByName("Me");
       //update();

        delete();

    }

    private static void insert() {

        Subqueries subqueries = new Subqueries();
        subqueries.setQueryName("subqLang");
        subqueries.setQuery(
                "\"term\": {\n" +
                "          \"languages\": \"en\"\n" +
                "}");

        String date = getCurrentDateTime();
        subqueries.setDate(date);

        subqueries.setAuthor("sks8");
        create(subqueries);

    }



    private static void create(Subqueries subqueries) {
        try {
            //todo if not exists
            service.createQuery(subqueries);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getCurrentDateTime(){
        Date date = new Date();
        String strDateFormat = "hh:mm:ss a";
        DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
        String formattedDate= dateFormat.format(date);
        System.out.println("Current time of the day using Date - 12 hour format: " + formattedDate);

        return formattedDate;
    }

    private static void printAll() throws IOException {
        List<Subqueries> subqueries = service.getAllSubqueries();
        if (subqueries != null) {
            for (Subqueries queries: subqueries){
                System.out.println(queries.getQueryName()+"\n");
            }
        } else {
            System.out.println("No query found");
        }
    }

    private static void printByName(String queryName) throws IOException {
        List<Subqueries> subqueries = service.getByName(queryName);
        if (subqueries != null) {
            for (Subqueries queries: subqueries){
                System.out.println(queries.getQueryName()+"\n");
                System.out.println(queries.getQuery()+"\n");
            }
        } else {
            System.out.println("No query found");
        }
    }


//    public static void update() throws IOException {
//        String newQ = "\"filter\": { \"term\": { \"userName\": \"Meeee\" } }\n";
//        String newQN = "me";
//        service.editQuery(newQN, newQ);
//    }

    public static void delete() throws IOException {
        String newQN = "lang";

        service.deleteQuery(newQN);
    }



}


/*
curl -X DELETE "localhost:9201/subqueries/subquerydocs/uIQsu2kB8yqs1ktxX4Wh"


"query": {
    "bool": {
      "must": {
        "match_phrase": {
            "text":"help"
        }
      },
      "filter": {
        "term": {
          "userName": "cookerblocks"
        }
      }
    }
}


curl -X POST "localhost:9201/subqueries/subquerydocs/xITsv2kB8yqs1ktxUYVX/_update_by_query" -H 'Content-Type: application/json' -d'
{
  "query": {
    "term": {
      "author": "kimchy"
    }
  }
}
'

curl -XPOST 'localhost:9201/subqueries/_update_by_query' -H 'Content-Type: application/json' -d '
{
  "query": {

              "term": {
                "queryName": "me"
              }
            },
  "script" : "ctx._source.query = \"\\\"filter\\\": {        \\\"term\\\": {          \\\"userName\\\": \\\"MEe\\\"        }      }        \";"
}'

{
  "query": {

              "term": {
                "queryName": "me"
              }
            },
  "script" : "ctx._source.query = "\"\\\"filter\\\": { \\\"term\\\": { \\\"userName\\\": \\\"Meeee\\\" } }\"";"
}

curl -XPOST 'localhost:9201/subqueries/_update_by_query' -H 'Content-Type: application/json' -d '
{
  "query": {

              "term": {
                "queryName": "me"
              }
            },
  "script" : "ctx._source.query = \"\\\"filter\\\": { \\\"term\\\": { \\\"userName\\\": \\\"Meeeee\\\" } }\";"
}'

curl -XPOST 'localhost:9201/subqueries/_update_by_query' -H 'Content-Type: application/json' -d '
{
  "query": {

              "term": {
                "queryName": "me"
              }
            },
  "script" : "ctx._source.query = \"\\\"filter\\\": { \\\"term\\\": { \\\"userName\\\": \\\"Me\\\" } }\";"
}'


curl -XPOST 'localhost:9201/subqueries/_update_by_query' -H 'Content-Type: application/json' -d '
{
  "query": {

              "term": {
                "queryName": "me"
              }
            },
  "script" : "ctx._source.query = \"\\\"filter\\\": { \\\"term\\\": { \\\"userName\\\": \\\"Me33\\\" } }\\n\";"
}'


 */