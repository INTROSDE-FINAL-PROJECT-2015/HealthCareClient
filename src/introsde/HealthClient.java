package introsde;


import introsde.models.*;
import introsde.helper.*;

//import introsde.client.adapterClient.*;
//import introsde.client.dataBaseClient.*;


import java.text.SimpleDateFormat;
import java.util.*;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.PathParam;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.io.IOException;
import java.io.Console;

import java.util.ArrayList;

import javax.ws.rs.core.GenericType;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import java.lang.Exception;

import java.util.Scanner;



public class HealthClient{
  static String UERRELLE = UriHelper.getProcessCentricServiceURL();
  static Scanner scanner = new Scanner(System.in);
  static Console console = System.console();



  public static void main(String[] args) throws IOException {
    asd("Hi! How are you?");
    pressAnyKeyToContinue();
    getPeopleList();
    String userID = userinput("Please enter your ID:");
    Person p = getPerson(userID);
    checkDailyGoals(p);
    createNewDailyGoal(p.getIdPerson());
    List<CustomGoal> customGoals = theGoals(p.getIdPerson());
    theMeasures(customGoals);
  }

  public static void asd(String s){
      System.out.println(s);
  }

  private static void pressAnyKeyToContinue() throws IOException {
    System.out.println("\n \n  Press any key to continue... \n \n");
    System.in.read();
  }

  private static String userinput(String s){
    asd(s);
    return scanner.next();
  }

  private static String userinput1(String s){
    asd(s);
    return scanner.nextLine();
    }


  private static Response doGet(String address){
    Client client = ClientBuilder.newClient();
    WebTarget webTarget = client.target(UERRELLE+address);
    Builder builder = webTarget.request(MediaType.APPLICATION_JSON);
    Response res = builder.get();
    return res;
  }

  private static <T> Response doPost(String address, List<T> dgl){
    Client client = ClientBuilder.newClient();
    WebTarget webTarget = client.target(UERRELLE + address);
    Builder builder = webTarget.request(MediaType.APPLICATION_JSON);
    Response res = builder.post(Entity.json(dgl));
    return res;
  }

  private static <T> Response doPost1(String address, T dgl){
    Client client = ClientBuilder.newClient();
    WebTarget webTarget = client.target(UERRELLE + address);
    Builder builder = webTarget.request(MediaType.APPLICATION_JSON);
    Response res = builder.post(Entity.json(dgl));
    return res;
  }

  private static Response doDelete(String address){
    Client client = ClientBuilder.newClient();
    WebTarget webTarget = client.target(UERRELLE + address);
    Builder builder = webTarget.request();
    Response res = builder.delete();
    return res;
  }

  private static String formatDate(Date theDate){
      return new SimpleDateFormat("yyyy-MM-dd").format(theDate);
  }

  private static void getPeopleList(){
    Response res = doGet("get/peopleList");
    List<PeopleList> pp = res.readEntity(new GenericType<List<PeopleList>>(){});
    for (PeopleList pl : pp){
      asd( pl.getFirstname() + " " + pl.getLastname() + " ID: \t " + pl.getIdPerson() + " " + "Birth Date: " + formatDate(pl.getBirthday()));
    }
  }

  private static Person getPerson(String pid){
      Response res = doGet("get/person/" + pid);
      Person p = res.readEntity(Person.class);
      asd("Hi " + p.getFirstname()+"!");
      String ans = userinput("\n\nWould you like to have a look to your last measurements?");
      if(ans.equals("YES") || ans.equals("yes") || ans.equals("Yes")){
        for(CurrentHealth ch : p.getCurrentHealth()){
          asd(ch.getMeasureType() + ": " + ch.getMeasureValue() + " " + formatDate(ch.getDateRegistered()));
        }
      }
      return p;
  }

  private static void checkDailyGoals(Person p){
    asd("\n\n.. getting your daily goals ..\n\n");
    asd(UERRELLE+"get/person/"+p.getIdPerson()+"/dailygoals");
    Response res = doGet("get/person/"+p.getIdPerson()+"/dailygoals");
    List<DailyGoal> dgl = res.readEntity(new GenericType<List<DailyGoal>>(){});
    if(dgl==null)
      asd("Nullo");

    asd("What about your daily goals? :) ");
    for (DailyGoal dg : dgl){
      String ans = userinput(dg.getQuestion());
      if(ans.equals("YES") || ans.equals("yes") || ans.equals("Yes")){
        dg.setValue("T");
      } else{dg.setValue("F");}
    }
    Response res1 = doPost("get/person/" + p.getIdPerson() + "/dailygoals",dgl);
    Score gr = res1.readEntity(Score.class);

    if(Integer.parseInt(gr.getReached())>(Integer.parseInt(gr.getTotal())/2)){
      asd("Very Good! You reached a VERY GOOD score!!!");
      asd(gr.getReached()+"/"+gr.getTotal());
    } else {
      asd("Your score should be higer.. You got:");
      asd(gr.getReached()+"/"+gr.getTotal()+"\n\n");
      asd("Are your DailyGoals too many or too difficult?");
      deleteDailyGoals(dgl,p.getIdPerson());
    }

  }

  private static void deleteDailyGoals(List<DailyGoal> dgl,int pid){
    //asd("\nWould you like to delete some DailyGoal?\n");
    String ans = userinput("\nWould you like to delete some DailyGoal?\n");
    Response res;
    String ansia;
    String newQuestion;
    if(ans.equals("YES") || ans.equals("yes") || ans.equals("Yes")){
      for (DailyGoal dg : dgl){
        ansia = userinput("Would you like to Delete: " + "'''" + dg.getQuestion() + "''' ???");
        if(ansia.equals("YES") || ansia.equals("yes") || ansia.equals("Yes")){
          doDelete("get/person/" + pid + "/dailygoals/" + dg.getIdGoal());
          asd("...DailyGoal deleted...");
        }
      }
      asd("now your DailyGoals are:");
      res = doGet("get/person/"+ pid +"/dailygoals");
      dgl = res.readEntity(new GenericType<List<DailyGoal>>(){});
      for (DailyGoal dg : dgl){
        asd(dg.getQuestion());
      }
    }
    ans = userinput("\nWould you like to edit some DailyGoals?\n");
    if(ans.equals("YES") || ans.equals("yes") || ans.equals("Yes")){
      for (DailyGoal dg : dgl){

        ansia = userinput("Would you like to edit " + "'''" + dg.getQuestion() + "''' ???");
        if(ansia.equals("YES") || ansia.equals("yes") || ansia.equals("Yes")){
          // http://127.0.1.1:5800/sdelab/get/person/1/dailygoals/saveorupdate
          // TODO doDelete("get/person/" + pid + "/dailygoals/" + dg.getIdGoal());
          newQuestion = userinput("please provide the new question");
          dg.setQuestion(newQuestion);
          doPost1("get/person/" + pid + "/dailygoals/saveorupdate",dg);
          asd("...DailyGoal edited...");
          }
        }
      }
      asd("now your DailyGoals are:");
      res = doGet("get/person/" + pid + "/dailygoals");
      dgl = res.readEntity(new GenericType<List<DailyGoal>>(){});
      for (DailyGoal dg : dgl){
        asd(dg.getQuestion());
      }
    }

    private static void createNewDailyGoal(int pid){
      //asd("\nWould you like to delete some DailyGoal?\n");
      String ans = userinput("\n Would you like to create a new DailyGoal? \n");
      Response res;
      //String ansia;
      if(ans.equals("YES") || ans.equals("yes") || ans.equals("Yes")){
        ans = userinput("\n please provide a new question for the new DailyGoal :) \n");
        DailyGoal newDailyGoal = new DailyGoal();
        newDailyGoal.setIdPerson(pid);
        newDailyGoal.setQuestion(ans);

        doPost1("get/person/" + pid + "/dailygoals/saveorupdate",newDailyGoal);
        asd("...DailyGoal added...");

        asd("now your DailyGoals are:");
        res = doGet("get/person/" + pid + "/dailygoals");
        List<DailyGoal> dgl = res.readEntity(new GenericType<List<DailyGoal>>(){});

        for (DailyGoal dg : dgl){
          asd(dg.getQuestion());
      }
    }
  }

  private static List<CustomGoal> theGoals(int pid){
    String ans = userinput("\n Would you like to have a look at your goals? \n");
    Response res = doGet("get/person/" + pid + "/goals");
    List<CustomGoal> goals = res.readEntity(new GenericType<List<CustomGoal>>(){});
    if(ans.equals("YES") || ans.equals("yes") || ans.equals("Yes")){
      asd(" \n Your Goals: \n ");
      for (CustomGoal g : goals){
        asd(g.getMeasureDefinition().getMeasureType() + " " + g.getOperator() + " " +g.getValue());
      }
    }
    return goals;
  }

  private static void theMeasures(List<CustomGoal> goals){
    String ans = userinput("\n Would you like to register some heatlh measurements? \n");
    Response res = null;
    if(ans.equals("YES") || ans.equals("yes") || ans.equals("Yes")){
      for (CustomGoal g : goals){
        String measureType = String.valueOf(g.getMeasureDefinition().getMeasureType());
        String pid = String.valueOf(g.getIdPerson());
        ans = userinput("\n What about your " + measureType + "?");
        Measure m = new Measure();
        m.setMeasureValue(ans);
        String address ="get/person/" + pid + "/measure/"+ g.getMeasureDefinition().getMeasureType();
        res = doPost1(address,m);
        GoalResponse gres = res.readEntity(GoalResponse.class);
        if(gres.getReached().equals("T")){
          asd("\n\n Wooooow great, you reached the goal...");
          asd(" The doctor have been notified about your progress");
          asd("\n\n Now you need some relax :) let's listen this Masterpiece:");
          asd("\n\n TITLE : " + gres.getAuthor());
          asd(" URI : " + gres.getContent());
        }else{
          asd("\n\n You still have not reached the goal....");
          asd(" I hope this quote will encourage you:");
          asd("\n\n QUOTE : " + gres.getContent());
          asd(" AUTHOR : " + gres.getAuthor());
        }
      }
    }
  }
}
