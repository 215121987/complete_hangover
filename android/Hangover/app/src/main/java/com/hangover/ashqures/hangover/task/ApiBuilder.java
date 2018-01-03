package com.hangover.ashqures.hangover.task;

import java.util.List;

/**
 * Created by ashqures on 8/14/16.
 */
public class ApiBuilder {

    private String url;

   public ApiBuilder(String url){
       this.url = url;
   }

   public ApiBuilder addPath(String path){
       this.url = this.url.replaceFirst("PATH_PARAM", path);
       return this;
   }

   public ApiBuilder addPaths(List<String> paths){
       for(String path :  paths){
           this.url = this.url.replaceFirst("PATH_PARAM", path);
       }
       return this;
   }

   public String build(){
       return this.url;
   }

    @Override
    public String toString() {
        return "ApiBuilder{" +
                "url='" + url + '\'' +
                '}';
    }
}
