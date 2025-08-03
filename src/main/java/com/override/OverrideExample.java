package com.override;

import com.exception.ResourseNotFoundException;

public class OverrideExample extends OverrideMain{

    @Override
    public void showData(int i,String name){
        System.out.printf("%s: %d\n", name, i);
    }

    public static void main(String[] args) {
        OverrideExample overrideExample = new OverrideExample();
        throw new ResourseNotFoundException("Resource is not Found");
        //overrideExample.showData(1,"Hari");
    }
}
