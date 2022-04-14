/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package fi.tuni.prog3.sisu;

import java.util.HashMap;


public class PrintDegree {
    
        public PrintDegree(String degreeName, HashMap modules){
            System.out.println(modules);
            System.out.println(degreeName);
            int depth = 1;
            printAll(modules, depth);
        }
        private void printAll(HashMap<String, Module> modules, int depth){
            System.out.println(modules);
            for(Module m:modules.values()){
                String space = "  ";
                System.out.println(space.repeat(depth)+m.getName());
                HashMap<String, Course> cors = m.getCourses();
                System.out.println(cors);
                for(Course c:cors.values()){
                    System.out.println(space.repeat(depth+1)+c.getName()+ " " + c.getTargetCredits());
                }
                HashMap<String, Module> mods = m.getModules();
                if(!mods.isEmpty()){
                    printAll(mods, depth);
                }
            }
        }
}
