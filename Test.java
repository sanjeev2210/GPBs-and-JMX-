import com.example.tutorial.AddressBookProtos.Person;

import java.io.*;

public class Test{
        public static void main(String[] args) throws Exception
        {
                 FileOutputStream fos = new FileOutputStream("sample2.bin");

                 /*
                 Person.Builder person = Person.newBuilder();
                  person.setId(1);
                  person.setName("sanjeev");
                  person.setEmail("ddd@gmail.com");
                  person.build().writeTo(fos);*/
		             for(int i=0;i<10;i++)
		          {
                Person.Builder person = Person.newBuilder();
                String s1="sonu"+(i+1);
                String s2="sanjeev" + (i+1) + ".oct2210" + "@gmail.com";
                int id=i+1;
               
                  person.setId(id);
                  person.setName(s1);
                  person.setEmail(s2);
                  person.build().writeTo(fos);
                }
                 fos.close();
                System.out.println("ok");
        }
}


