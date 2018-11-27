package com.productivity.lucene.base;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlAccessorType(XmlAccessType.FIELD)
public class Sync {

    @XmlElement(name = "cron")
    private String cron;
    @XmlElement(name = "interval")
    private String interval;
    @XmlElement(name = "jdbc")
    private Jdbc jdbc;

    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Jdbc{
        @XmlElement(name = "driver")
        private String driver;
        @XmlElement(name = "url")
        private String url;
        @XmlElement(name = "user")
        private String user;
        @XmlElement(name = "password")
        private String password;
        @XmlElement(name = "batch_size")
        private int batch_size;
        @XmlElement(name = "sql")
        private String sql;
        @XmlTransient
        private String[] sqls;

        public String getDriver() {
            return driver;
        }

        public void setDriver(String driver) {
            this.driver = driver;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getUser() {
            return user;
        }

        public void setUser(String user) {
            this.user = user;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public int getBatch_size() {
            return batch_size;
        }

        public void setBatch_size(int batch_size) {
            this.batch_size = batch_size;
        }

        public String getSql() {
            return sql;
        }

        public void setSql(String sql) {
            this.sql = sql;
        }

        public String[] getSqls() {
            if(sql != null && !"".equals(sql.trim())){
                sqls = sql.split(";");
            }
            return sqls;
        }

    }

    public String getCron() {
        return cron;
    }

    public void setCron(String cron) {
        this.cron = cron;
    }

    public String getInterval() {
        return interval;
    }

    public void setInterval(String interval) {
        this.interval = interval;
    }

    public Jdbc getJdbc() {
        return jdbc;
    }

    public void setJdbc(Jdbc jdbc) {
        this.jdbc = jdbc;
    }
}
