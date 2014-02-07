package com.jkahigiso.ngomastations.domain;

/**
 * Created by Jean Kahigiso on 06-02-2014.
 */
public class Station {

       Integer priority;
       String name;
       String url;
       String icon;
       String frequency;
       boolean active;


       public void setPriority(Integer priority){
           this.priority = priority;
       }
       public Integer getPriority(){
            return priority;
       }

       public void setName(String name){
           this.name= name;
       }
       public String getName(){
           return name;
       }

       public  void setUrl(String url){
           this.url = url;
       }

       public String getUrl(){
           return url;
       }

       public String getIcon(){
           return icon;
       }

       public void setIcon(String icon){
           this.icon =icon;
       }

       public String setFrequency(){
           return frequency;
       }

       public void setFrequency(String frequency){
           this.frequency = frequency;
       }

       public boolean isActive(){
           return this.active;
       }

       public void setActive(boolean active){
           this.active = active;
       }
       @Override
       public String toString(){
           return ("priority: \n"+priority+" name: \n"+name+" active: \n"+active+" url: \n"+url);
       }
}
