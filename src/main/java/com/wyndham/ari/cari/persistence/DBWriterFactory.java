package com.wyndham.ari.cari.persistence;



import net.sf.ehcache.Ehcache;
import net.sf.ehcache.writer.CacheWriter;
import net.sf.ehcache.writer.CacheWriterFactory;

import java.io.File;
import java.util.Properties;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;


import org.apache.commons.pool2.ObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.dbcp2.ConnectionFactory;
import org.apache.commons.dbcp2.PoolableConnection;
import org.apache.commons.dbcp2.PoolingDataSource;
import org.apache.commons.dbcp2.PoolableConnectionFactory;
import org.apache.commons.dbcp2.DriverManagerConnectionFactory;








public class DBWriterFactory extends CacheWriterFactory {

  private static final String PATH = System.getProperty("user.home");

  @Override
  public CacheWriter createCacheWriter(final Ehcache ehcache, final Properties properties) {
    return new DBWriter((String)properties.get("url"),(String)properties.get("id"),(String)properties.get("pw"));

  }
}