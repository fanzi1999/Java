package org.example;

public class ArrayUtils {
    private ArrayUtils(){
    }
    public static int FindIndex(Student student[],int id, int count){
        for (int i = 0; i < count; i++) {
            if(id == student[i].getId())
            {
                return i;
            }
        }
        return -1;
    }
}
