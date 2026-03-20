package com.app.myCommerce.utilities.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class APIStructure <T> {

   private Boolean success;
   private String message;
   private T data;


   public static <T> APIStructure<T> success(T data,String message){
      return APIStructure.<T>builder().data(data).success(true).message(message).build();
   }

   public static <T> APIStructure<T> failure(String message){
      return APIStructure.<T>builder().data(null).success(false).message(message).build();
   }

}
