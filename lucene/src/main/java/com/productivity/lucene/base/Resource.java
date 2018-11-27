package com.productivity.lucene.base;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="resource")
public class Resource {

    @XmlAttribute(name = "name")
    private String name;
    @XmlAttribute(name = "cn_name")
    private String cn_name;
    @XmlAttribute(name = "dynamic")
    private String dynamic;
    @XmlAttribute(name = "share")
    private String share;

    @XmlElement(name = "sync")
    private Sync sync;
    @XmlElement(name = "properties")
    private Properties properties;

    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Properties{
        @XmlElement(name = "property")
        List<Property> properties;
    }

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

    public String getDynamic() {
        return dynamic;
    }

    public void setDynamic(String dynamic) {
        this.dynamic = dynamic;
    }

    public String getShare() {
        return share;
    }

    public void setShare(String share) {
        this.share = share;
    }

    public Sync getSync() {
        return sync;
    }

    public void setSync(Sync sync) {
        this.sync = sync;
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }
}
