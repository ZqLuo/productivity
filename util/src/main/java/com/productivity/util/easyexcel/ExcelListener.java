package com.productivity.util.easyexcel;

import com.alibaba.excel.read.context.AnalysisContext;
import com.alibaba.excel.read.event.AnalysisEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA
 *
 * @Author luozeqiang
 * @Description 监听类，可以自定义
 */
public class ExcelListener<T> extends AnalysisEventListener {

    //自定义用于暂时存储data。
    //可以通过实例获取该值
    private List<T> datas = new ArrayList<>();

    /**
     * 通过 AnalysisContext 对象还可以获取当前 sheet，当前行等数据
     */
    @Override
    public void invoke(Object object, AnalysisContext context) {
        //数据存储到list，供批量处理，或后续自己业务逻辑处理。
        T t = (T)object;
        datas.add(t);
        //根据自己业务做处理
        //doSomething(t);
    }

    private void doSomething(T object) {
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        /*
            datas.clear();
            解析结束销毁不用的资源
         */
    }

    public List<T> getDatas() {
        return datas;
    }

    public void setDatas(List<T> datas) {
        this.datas = datas;
    }
}