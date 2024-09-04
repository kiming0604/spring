package org.jae.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//@Data 불필요한 object 요소까지 같이 적용됨

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Person {
   private String name;
   private int age; 
   private double height;
   
   
 
}
