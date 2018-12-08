package com.mytest;

//import java.io.BufferedReader;
//import java.io.FileReader;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;


import java.io.*;

public class ReadTxt {

    public static void main(String[] args){
        readFile();
        //writeFile();
        StrMotion();
    }

    public static void readFile(){
        String Filepath = "E:/study/temp/readtest.txt";
        try{
            InputStreamReader isr =new InputStreamReader(new FileInputStream(Filepath),"UTF-8");
            BufferedReader br = new BufferedReader(isr);
            String line;
            while ((line = br.readLine())!=null){
                System.out.println(line);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
/*
    public static void writeFile(){
        try{
            File writeFile = new File("E:/study/temp/output.txt");
            writeFile.createNewFile();
            FileWriter writer = new FileWriter(writeFile);
            BufferedWriter out = new BufferedWriter(writer);
            out.write("写入第一行\r\n");
            out.write("写入第二行\r\n");
            out.flush();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
*/
    public static void StrMotion(){

        String Filepath = "E:/study/temp/StrMotion.json";
        String old_name = "\\$\\{name\\}";
        String new_name = "newname";
        try{
            File file = new File(Filepath);
            InputStreamReader isr =new InputStreamReader(new FileInputStream(file),"UTF-8");
            BufferedReader br = new BufferedReader(isr);
            CharArrayWriter caw = new CharArrayWriter();

            String line;
            while ((line = br.readLine())!=null){
                line = line.replaceAll(old_name,new_name);
                caw.write(line);
                caw.append(System.getProperty("line.separator"));
            }
            br.close();

            File writefile = new File("E:/study/temp/output.json");
            FileWriter fw = new FileWriter(writefile);
            caw.writeTo(fw);
            fw.close();

        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
