package com.productivity.plugin;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import java.io.File;

@Mojo(name="countFile",defaultPhase= LifecyclePhase.PACKAGE)
public class CountFileMojo extends AbstractMojo {

    @Parameter(property = "path")
    private String path;
    int num1 = 0;   //总文件数量
    int num2 = 0;   //目录数量
    int num3 = 0;   //java文件数量

    public void execute() throws MojoExecutionException, MojoFailureException {
        System.out.println(path);
        System.out.println(countFile(path));
    }

    public String  countFile(String dir){
        File f = new File(dir);
        File fs[] = f.listFiles();

        if(fs!=null){
            for(int i=0;i<fs.length;i++){
                File  currenFile = fs[i];
                if(currenFile.isFile()){
                    num1+=1;   //如果是文件就加1
                    String fileName = currenFile.getName();
                    String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
                    if(suffix.equals("java")){
                        num3+=1;
                    }
                }else{
                    //否则就是目录
                    num2+=1;
                    countFile(currenFile.getAbsolutePath());
                }
            }
        }
        return "Total number of File:"+num1+"||||The number of dir is:"+num2+"||||Total number of Java File:"+num3;
    }
}
