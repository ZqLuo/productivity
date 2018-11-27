package com.productivity.util;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.*;

/**
 * Created by zqLuo
 */
public class XmlUtil {

    /**
     * 将对象直接转换成String类型的 XML输出
     *
     * @param obj
     * @return
     */
    public static String convertToXml(Object obj) {
        // 创建输出流
        StringWriter sw = new StringWriter();
        try {
            // 利用jdk中自带的转换类实现
            JAXBContext context = JAXBContext.newInstance(obj.getClass());

            Marshaller marshaller = context.createMarshaller();
            // 格式化xml输出的格式
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,
                    Boolean.TRUE);
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
            // 将对象转换成输出流形式的xml
            marshaller.marshal(obj, sw);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return sw.toString();
    }

    /**
     * 将对象根据路径转换成xml文件
     *
     * @param obj
     * @param path
     * @return
     */
    public static void convertToXml(Object obj, String path) {
        OutputStreamWriter out = null;
        try {
            out = new OutputStreamWriter(new FileOutputStream(path),"UTF-8");
            String xmlString = convertToXml(obj);
            out.write(xmlString);
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @SuppressWarnings("unchecked")
    /**
     * 将String类型的xml转换成对象
     */
    public static <T> T convertXmlStrToObject(Class<? extends T> clazz, String xmlStr) {
        T xmlObject = null;
        try {
            JAXBContext context = JAXBContext.newInstance(clazz);
            // 进行将Xml转成对象的核心接口
            Unmarshaller unmarshaller = context.createUnmarshaller();
            StringReader sr = new StringReader(xmlStr);
            xmlObject = (T)unmarshaller.unmarshal(sr);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return xmlObject;
    }

    @SuppressWarnings("unchecked")
    /**
     * 将file类型的xml转换成对象
     */
    public static <T> T convertXmlFileToObject(Class<? extends T> clazz, String xmlPath) {
        File file = new File(xmlPath);
        return convertXmlFileToObject(clazz,file);
    }

    /**
     *  将file类型的xml转换成对象
     * @param clazz
     * @param file
     * @return
     */
    public static <T> T convertXmlFileToObject(Class<? extends T> clazz, File file){
        T xmlObject = null;
        try {
            JAXBContext context = JAXBContext.newInstance(clazz);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            InputStreamReader isr = new InputStreamReader(new FileInputStream(file), "UTF-8");
            xmlObject = (T)unmarshaller.unmarshal(isr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return xmlObject;
    }

    public static boolean validXsd(String xsdPath, String xml){
        try {
            //建立schema工厂
            SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            //建立验证文档文件对象，利用此文件对象所封装的文件进行schema验证
            File schemaFile = new File(xsdPath);
            //利用schema工厂，接收验证文档文件对象生成Schema对象
            Schema schema= schemaFactory.newSchema(schemaFile);
            //通过Schema产生针对于此Schema的验证器，利用schenaFile进行验证
            Validator validator=schema.newValidator();
            //从xml转换为字节流构造 StreamSource
            Source xmlSource = new StreamSource(new ByteArrayInputStream(xml.getBytes()));
//            validator.validate(xsdSource);


            StringWriter stringWriter = new StringWriter();
            PrintWriter printWriter = new PrintWriter(stringWriter);
            Result destResult = new StreamResult(printWriter);
            validator.validate(xmlSource, destResult);
            String desc = stringWriter.toString();

            printWriter.close();
            System.out.println(desc);
        } catch (Exception e){
           // e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return false;
    }
}
