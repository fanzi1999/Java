package org.example;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.util.Scanner;

public class StudentView {
    /*数据在后期会反复录入，所以定义成成员变量*/
        Scanner sc = new Scanner(System.in);
    /*定义一个数组来存储学生的信息*/
        Student[] students = new Student[50];
    /*由于有删除功能，而删除功能又是将原数组内的数据赋值到新数组，
    所以还需要创建一个新数组,而他的长度要比原来的数组小*/
        Student[] newstudents = new Student[students.length-1];
    /*由于对刚开始对数组的赋值都为null，如果调用get方法会出现空指针，
    所以在这里，加一个计数器，count表示添加个数和删除个数，
    在最后遍历的时候，遍历count即可。*/
        int count = 0 ;
    public void start(){
        while (true){
            System.out.println("------------管理系统------------");
            System.out.println("1 添加功能");
            System.out.println("2 删除功能");
            System.out.println("3 修改功能");
            System.out.println("4 查看功能");
            System.out.println("5 退出功能");
            System.out.println("请选择（1-5）:");

        int num =sc.nextInt();
            System.out.println("------------------------------");

        switch (num){
            case 1:addStudent();
            break;
            case 2:deleteStudent();
            break;
            case 3:updateStudent();
            break;
            case 4:findAllStudent();
            break;
            case 5:
                //进行一次判断，如果为0结束，为9取消
                System.out.println("是否结束？（按0结束/按9取消）");
                int num1 = sc.nextInt();
                if(num1==0){
                    System.out.println("再见，欢迎回来！");
                    return;
                }else if (num1==9){
                    break;
                }
        }
        }
    }

    private void findAllStudent() {
        //如果删除再添加可能会出现序号1 3 2的情况，所以需要进行排序
        for (int i = 0; i < count-1; i++) {
            for (int j = 0; j < count-1-i; j++) {
                if(students[j].getId()>students[j+1].getId()) {
                    Student temp = students[j];
                    students[j] = students[j + 1];
                    students[j + 1] = temp;
                }
            }
        }
        //输出遍历的内容
        System.out.println("学号"+"\t"+"姓名"+"\t"+"年龄"+"\t"+"性别");
        if(count == 0){
            System.out.println("您还未添加成员，请添加成员。");
        }else{
            for (int i = 0; i <count ; i++) {
                System.out.println(students[i].getId()+"\t"+students[i].getName()+"\t"+students[i].getAge()+"\t"+students[i].getSex());
            }
        }
    }

    private void updateStudent() {
        System.out.println("请您输入想要修改的学号：");
        int id = sc.nextInt();
        //需要根据学号找到对应的索引值，通过索引值对student对象数组再进行保存
        int Index = ArrayUtils.FindIndex(students, id, count);
        //对需要修改的数值进行修改
        System.out.println("请输入姓名：");
        String name = sc.next();
        System.out.println("请输入年龄：");
        int age = sc.nextInt();
        System.out.println("请输入性别：");
        String sex = sc.next();
        /*重新对数据进行封装+赋值 这里本应该是Student student = new student(id,name,age,sex),
        students[Index] = student,但是在这里两步合成一步*/
        students[Index] = new Student(id,name,age,sex);
        //输出
        System.out.println("修改成功！！");
    }

    private void deleteStudent() {
        //这里因为需要将数组进行复制，所以用到了一个新工具System.arraycopy(旧数组，复制位置，新数组，粘贴位置，复制长度)
        System.out.println("请输入需要删除的学生号");
        int id = sc.nextInt();
        //因为arraycopy用到了索引，所以这里需要和修改信息一样，用到学号查找的索引值
        int removeIndex = ArrayUtils.FindIndex(students,id,count);
        //对数组分两段复制
        System.arraycopy(students,0,newstudents,0,removeIndex);
        System.arraycopy(students,removeIndex+1,newstudents,removeIndex,students.length-removeIndex-1);
        //将新数组的地址值给到旧数组
        students = newstudents;
        //删除之后要记录count--
        count--;
        System.out.println("删除成功！！");
    }

    private void addStudent() {
        //记录添加信息
        System.out.println("请输入学号：");
        int id = sc.nextInt();
        System.out.println("请输入姓名：");
        String name = sc.next();
        System.out.println("请输入年龄：");
        int age = sc.nextInt();
        System.out.println("请输入性别：");
        String sex = sc.next();
        //封装Student
        Student student = new Student(id,name,age,sex);
        //将封装好的对象记录到数组中
        students[count]=student;
        //添加成功就需要count++
        count++;
        System.out.println("添加成功！！！");
    }
}
