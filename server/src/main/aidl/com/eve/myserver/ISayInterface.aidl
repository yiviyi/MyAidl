// ISayInterface.aidl
package com.eve.myserver;

import com.eve.myserver.Guy;
// Declare any non-default types here with import statements

interface ISayInterface {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);
            void sayHell0(String str);
            int sum(int a,int b);
            Guy addGuy(in Guy guy);
            List<Guy> getGuys();
}
