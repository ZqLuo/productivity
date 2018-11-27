package com.productivity.lucene.base;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

@XmlAccessorType(XmlAccessType.FIELD)
public class Property {

    @XmlAttribute(name = "name")
    private String name;
    @XmlAttribute(name = "cn_name")
    private String cn_name;
    @XmlAttribute(name = "column_type")
    private String column_type;
    @XmlAttribute(name = "index_type")
    private String index_type;
    @XmlAttribute(name = "api_format")
    private String api_format;
    @XmlAttribute(name = "primary_key")
    private String primary_key;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCn_name() {
        return cn_name;
    }

    public void setCn_name(String cn_name) {
        this.cn_name = cn_name;
    }

    public String getColumn_type() {
        return column_type;
    }

    public void setColumn_type(String column_type) {
        this.column_type = column_type;
    }

    public String getIndex_type() {
        return index_type;
    }

    public void setIndex_type(String index_type) {
        this.index_type = index_type;
    }

    public String getApi_format() {
        return api_format;
    }

    public void setApi_format(String api_format) {
        this.api_format = api_format;
    }

    public String getPrimary_key() {
        return primary_key;
    }

    public void setPrimary_key(String primary_key) {
        this.primary_key = primary_key;
    }
}
