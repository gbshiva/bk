TC_HOME=/u01/software/bigmemory-max-4.1.3
TC_LICENSE_KEY=$TC_HOME/terracotta-license.key
PERF_CLASSPATH=$(echo ${TC_HOME}/apis/ehcache/lib/*.jar | tr ' ' ':')
PERF_CLASSPATH=${PERF_CLASSPATH}:$(echo ${TC_HOME}/apis/toolkit/lib/*.jar | tr ' ' ':')
PERF_CLASSPATH=$PERF_CLASSPATH:search-perf.jar:.

java -Dcom.sn.management.jmxremote="true" -Dcom.sun.management.jmxremote.authenticate="false" -Dcom.sun.management.jmxremote.ssl="false" -Dcom.sun.management.jmxremote.port=9900 -XX:+UseLargePages -d64 -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -verbose:gc -Xms4g -Xmx4g -XX:MaxDirectMemorySize=200G -Dcom.tc.productkey.path=${TC_LICENSE_KEY} -cp ${PERF_CLASSPATH} com.wyndham.ari.controller.BookingASL booking.properties
