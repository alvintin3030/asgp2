/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/**
 *
 * @author alvintin
 */
public class Getter {
    public static Boolean getBoolean(Object o){
        if(o != null && (o.toString().equals("True") || o.toString().equals("true"))){
            return true;
        }else{
            return false;
        }
    }
}
